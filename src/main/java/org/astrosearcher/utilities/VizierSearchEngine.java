package org.astrosearcher.utilities;

import cds.savot.model.SavotVOTable;
import cds.savot.pull.SavotPullEngine;
import cds.savot.pull.SavotPullParser;
import org.astrosearcher.classes.vizier.VizierRequestObject;
import org.astrosearcher.classes.vizier.VizierResponse;
import org.astrosearcher.classes.xmatch.CDSCrossmatchRequestObject;
import org.astrosearcher.enums.cds.vizier.VizierServices;
import org.astrosearcher.models.SearchFormInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.util.concurrent.CompletableFuture;

/**
 * Class serves as inter-level between general SearchEngine class and
 * ConnectionUtils class.
 * <p>
 * Class provides usage of RequestObject needed for constructing and sending
 * of request as well as parsing of response acquired from Vizier (cds) server.
 *
 * @author Ľuboslav Halama
 */
@Service
public class VizierSearchEngine {

    private static final Logger log = LoggerFactory.getLogger(VizierSearchEngine.class);

    private static boolean timeQuantumUsed = false;

    public synchronized static boolean isTimeQuantumFree() {
        return !timeQuantumUsed;
    }

    public synchronized static void setTimeQuantumUsed(boolean flag) {
        timeQuantumUsed = flag;
        if (flag) {
            log.debug("Time Quantum used");
        } else {
            log.debug("Time Quantum freed");
        }
    }

    @Async("threadPoolTaskExecutor")
    public CompletableFuture<VizierResponse> findAllById(SearchFormInput input) {
        String response = new VizierRequestObject(VizierServices.VIZIER_ID, input).send();
        return processResponse(VizierServices.VIZIER_ID, response);
    }

    @Async("threadPoolTaskExecutor")
    public CompletableFuture<VizierResponse> findAllByPosition(SearchFormInput input) {
        String response = new VizierRequestObject(VizierServices.VIZIER_COORDINATES, input).send();
        return processResponse(VizierServices.VIZIER_COORDINATES, response);
    }

    @Async("threadPoolTaskExecutor")
    public CompletableFuture<VizierResponse> findAllByCrossmatch(SearchFormInput input) {
        String response = new CDSCrossmatchRequestObject(input, "vizier:" + input.getVizierCat()).send();
        return processResponse(VizierServices.VIZIER_CROSSMATCH, response);
    }

    @Async("threadPoolTaskExecutor")
    public CompletableFuture<VizierResponse> getVizierCatalogMetadataForObjectId(String id) {
        String response = new VizierRequestObject(id, 5, "ap", 1).send();
        return processResponse(VizierServices.VIZIER_META, response);
    }

    private static boolean isEmptyResponse(SavotVOTable vot) {
        log.debug("        Checking resources count... ");
        log.debug("{} resources found", vot.getResources().getItemCount());
        return vot.getResources().getItemCount() == 0;
    }

    private static CompletableFuture<VizierResponse> processResponse(VizierServices service, String response) {

        if (response == null) {
            return CompletableFuture.completedFuture(new VizierResponse());
        }

        log.debug("        Initializing SavotPullParser...");
        SavotPullParser parser = new SavotPullParser(new ByteArrayInputStream(response.getBytes()),
                SavotPullEngine.FULL,
                "UTF-8");

        SavotVOTable vot = parser.getVOTable();

        if (isEmptyResponse(vot)) {
            return CompletableFuture.completedFuture(new VizierResponse());
        }

        return CompletableFuture.completedFuture(new VizierResponse(service, vot.getResources()));
    }
}
