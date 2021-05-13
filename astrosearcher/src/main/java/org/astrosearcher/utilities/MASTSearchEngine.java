package org.astrosearcher.utilities;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.astrosearcher.TomcatConfig;
import org.astrosearcher.classes.PositionInput;
import org.astrosearcher.AppConfig;
import org.astrosearcher.classes.mast.MastResponse;
import org.astrosearcher.enums.mast.MastServices;
import org.astrosearcher.classes.mast.MastRequestObject;
import org.astrosearcher.classes.mast.services.caom.cone.ResponseForReqByPos;
import org.astrosearcher.classes.mast.services.name.lookup.ResponseForReqByName;
import org.astrosearcher.models.SearchFormInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Class serves as inter-level between general SearchEngine class and
 * ConnectionUtils class.
 *
 * Class provides usage of RequestObject needed for constructing and sending
 * of request as well as parsing of response acquired from MAST server.
 *
 * @author Ľuboslav Halama
 */
@Service
public class MASTSearchEngine {

    private static final Logger log = LoggerFactory.getLogger(TomcatConfig.class);

    private static boolean timeQuantumUsed = false;

    public synchronized static boolean isTimeQuantumFree() {
        return !timeQuantumUsed;
    }

    public synchronized static void setTimeQuantumUsed(boolean flag) {
        timeQuantumUsed = flag;
        if (AppConfig.DEBUG_SCHEDULE) {
            if (flag) {
                log.debug("    {} ::: [ MAST ]             : Time Quantum used", LocalTime.now());
            } else {
                log.debug("    {} ::: [ MAST ]             : Time Quantum freed", LocalTime.now());
            }
        }
    }


    private static List<PositionInput> resolvePositionByNameOrID(SearchFormInput input) {

        List<PositionInput> resolved = new ArrayList<>();
        String response = new MastRequestObject(MastServices.MAST_NAME_LOOKUP, input).send();

        if (response == null) {
            return resolved;
        }

        // in case parsing would not be valid
        try {
            ResponseForReqByName resp = new Gson().fromJson(response, ResponseForReqByName.class);

            for (JsonObject obj : resp.getResolvedCoordinate()) {

                resolved.add(new PositionInput(
                        obj.get("ra").getAsDouble(),
                        obj.get("decl").getAsDouble()
                ));
            }
        } catch (Exception e) {
            return resolved;
        }

        return resolved;
    }

    @Async("threadPoolTaskExecutor")
    public CompletableFuture<MastResponse> findAllByID(SearchFormInput input) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<PositionInput> resolved = resolvePositionByNameOrID(input);

        if (resolved.isEmpty()) {
            return CompletableFuture.completedFuture(new MastResponse());
        }

        return findAllByPosition(resolved.get(0), input);
    }

    @Async("threadPoolTaskExecutor")
    public CompletableFuture<MastResponse> findAllByPosition(SearchFormInput input) {
        String response = new MastRequestObject(MastServices.MAST_CAOM_CONE, input).send();

        if (response == null) {
            return CompletableFuture.completedFuture(new MastResponse());
        }

        return CompletableFuture.completedFuture(
                new MastResponse(new Gson().fromJson(response, ResponseForReqByPos.class)));
    }

    @Async("threadPoolTaskExecutor")
    public CompletableFuture<MastResponse> findAllByPosition(PositionInput position, SearchFormInput input) {
        String response = new MastRequestObject(MastServices.MAST_CAOM_CONE, position, input).send();

        if (response == null) {
            return CompletableFuture.completedFuture(new MastResponse());
        }

        return CompletableFuture.completedFuture(
                new MastResponse(new Gson().fromJson(response, ResponseForReqByPos.class)));
    }

    @Async("threadPoolTaskExecutor")
    public CompletableFuture<MastResponse> findAllByPositionCrossmatch(SearchFormInput input) {
        String response = new MastRequestObject(MastServices.MAST_CAOM_CROSSMATCH, input).send();

        if (response == null) {
            return CompletableFuture.completedFuture(new MastResponse());
        }

        return CompletableFuture.completedFuture(
                new MastResponse(new Gson().fromJson(response, ResponseForReqByPos.class)));
    }

}