package org.astrosearcher.utilities;

import cds.savot.model.SavotResource;
import cds.savot.model.SavotVOTable;
import cds.savot.pull.SavotPullEngine;
import cds.savot.pull.SavotPullParser;
import org.astrosearcher.classes.constants.cds.SimbadConstants;
import org.astrosearcher.classes.simbad.SimbadMeasurementsTable;
import org.astrosearcher.classes.simbad.SimbadRequestObject;
import org.astrosearcher.classes.simbad.SimbadResponse;
import org.astrosearcher.classes.xmatch.CDSCrossmatchRequestObject;
import org.astrosearcher.enums.cds.simbad.SimbadServices;
import org.astrosearcher.models.SearchFormInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Class serves as inter-level between general SearchEngine class and
 * ConnectionUtils class.
 * <p>
 * Class provides usage of RequestObject needed for constructing and sending
 * of request as well as parsing of response acquired from Simbad (cds) server.
 *
 * @author Ľuboslav Halama
 */
@Service
public class SimbadSearchEngine {

    private static final Logger log = LoggerFactory.getLogger(SimbadSearchEngine.class);

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
    public CompletableFuture<SimbadResponse> findAllById(SearchFormInput input) {
        String response = new SimbadRequestObject(SimbadServices.SIMBAD_ID, input).send();
        return processResponse(SimbadServices.SIMBAD_ID, response);
    }

    @Async("threadPoolTaskExecutor")
    public CompletableFuture<SimbadResponse> findAllByPosition(SearchFormInput input) {
        String response = new SimbadRequestObject(SimbadServices.SIMBAD_COORDINATES, input).send();
        return processResponse(SimbadServices.SIMBAD_COORDINATES, response);
    }

    @Async("threadPoolTaskExecutor")
    public CompletableFuture<SimbadResponse> findAllByCrossmatch(SearchFormInput input) {
        String response = new CDSCrossmatchRequestObject(input, "simbad").send();
        return processResponse(SimbadServices.SIMBAD_CROSSMATCH, response);
    }

    public static List<SimbadMeasurementsTable> findAllMeasurementsById(SearchFormInput input) {
        List<SimbadMeasurementsTable> measurements = new ArrayList<>();
        input.setSimbadFormat(SimbadConstants.ASCII_FORMAT);

        String response = new SimbadRequestObject(SimbadServices.SIMBAD_ID, input, input.getSimbadFormat()).send();

        if (response == null) {
            return measurements;
        }

        return new SimbadASCIIParser().parseForMeasurements(response);
    }

    private static boolean isEmptyResponse(SavotVOTable vot) {
        if (vot.getResources().getItemCount() == 0) {
            return true;
        }

        if (((SavotResource) vot.getResources().getItemAt(0)).getTableCount() == 0) {
            return true;
        }

        return ((SavotResource) vot.getResources().getItemAt(0)).getData(0) == null;
    }

    private static CompletableFuture<SimbadResponse> processResponse(SimbadServices service, String response) {
        if (response == null) {
            return CompletableFuture.completedFuture(new SimbadResponse());
        }

        try {
            SavotPullParser parser = new SavotPullParser(new ByteArrayInputStream(response.getBytes()),
                    SavotPullEngine.FULL,
                    "UTF-8");
            SavotVOTable vot = parser.getVOTable();

            if (isEmptyResponse(vot)) {
                return CompletableFuture.completedFuture(new SimbadResponse());
            }

            return CompletableFuture.completedFuture(new SimbadResponse(
                    service,
                    ((SavotResource) vot.getResources().getItemAt(0)).getFieldSet(0).getItems(),
                    ((SavotResource) vot.getResources().getItemAt(0)).getTRSet(0).getItems()
            ));
        } catch (Exception e) {
            log.error("..::!!!   Exception caught (Simbad - {})  !!!::..", service);
            log.error("Message: {}", e.getMessage(), e);
            return CompletableFuture.completedFuture(new SimbadResponse());
        }
    }

}
