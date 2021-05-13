package org.astrosearcher.utilities;

import org.astrosearcher.TomcatConfig;
import org.astrosearcher.classes.ResponseData;
import org.astrosearcher.AppConfig;
import org.astrosearcher.classes.constants.RegularExpressions;
import org.astrosearcher.classes.constants.messages.ExceptionMSG;
import org.astrosearcher.classes.mast.MastResponse;
import org.astrosearcher.classes.simbad.SimbadMeasurementsTable;
import org.astrosearcher.classes.simbad.SimbadResponse;
import org.astrosearcher.classes.vizier.VizierResponse;
import org.astrosearcher.enums.SearchType;
import org.astrosearcher.models.SearchFormInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


/**
 * Provides general interface for searching in three defined catalogues
 * (Mast, Vizier, Simbad)
 * <p>
 * Class focuses mainly on searching by two diferrent types:
 * 1.) ID/name
 * 2.) Coordinates (position) -> RA, Dec, (opt. Radius)
 * <p>
 * However, querying on MAST server is possible only by using coordinates,
 * so ID/name must be firstly resolved into coordinates (position) and query
 * by coordinates (position) is used then.
 *
 * @author Ľuboslav Halama
 */
@EnableScheduling
@EnableAsync
@Service
public class SearchEngine {

    private static final Logger log = LoggerFactory.getLogger(SearchEngine.class);

    @Autowired
    MASTSearchEngine mastSearchEngine;

    @Autowired
    SimbadSearchEngine simbadSearchEngine;

    @Autowired
    VizierSearchEngine vizierSearchEngine;

    private static boolean finished(SearchFormInput input) {
        return !input.isQueryMast() && !input.isQueryVizier() && !input.isQuerySimbad();
    }

    private void storeResults(ResponseData responseData,
                                     CompletableFuture<MastResponse>   mastResponse,
                                     CompletableFuture<SimbadResponse> simbadResponse,
                                     CompletableFuture<VizierResponse> vizierResponse) {


        List<CompletableFuture> tasks = new ArrayList<>();

        if (mastResponse != null) {
            tasks.add(mastResponse);
        }
        if (simbadResponse != null) {
            tasks.add(simbadResponse);
        }
        if (vizierResponse != null) {
            tasks.add(vizierResponse);
        }

        if (tasks.isEmpty()) {
            return;
        }

        if (tasks.size() == 1) {
            CompletableFuture.allOf(tasks.get(0)).join();
        }

        if (tasks.size() == 2) {
            CompletableFuture.allOf(tasks.get(0), tasks.get(1)).join();
        }

        if (tasks.size() == 3) {
            CompletableFuture.allOf(tasks.get(0), tasks.get(1), tasks.get(2)).join();
        }

        try {
            if (simbadResponse != null) {
                responseData.setSimbadResponse(simbadResponse.get());
            }

            if (mastResponse != null) {
                responseData.setMastResponse(mastResponse.get());
            }

            if (vizierResponse != null) {
                responseData.setVizierResponse(vizierResponse.get());
            }
        } catch (ExecutionException | InterruptedException ignored) {
        }
    }


    public ResponseData findAllByPosition(SearchFormInput input) {

        ResponseData responseData = new ResponseData();

        CompletableFuture<MastResponse>   mastResponse   = null;
        CompletableFuture<SimbadResponse> simbadResponse = null;
        CompletableFuture<VizierResponse> vizierResponse = null;

        while (true) {

            try {

                // MAST
                if (input.isQueryMast() && MASTSearchEngine.isTimeQuantumFree()) {
                    MASTSearchEngine.setTimeQuantumUsed(true);
                    mastResponse = mastSearchEngine.findAllByPosition(input);
                    input.setQueryMast(false);
                }


                // SIMBAD
                if (input.isQuerySimbad() && SimbadSearchEngine.isTimeQuantumFree()) {
                    SimbadSearchEngine.setTimeQuantumUsed(true);
                    simbadResponse = simbadSearchEngine.findAllByPosition(input);
                    input.setQuerySimbad(false);
                }

                // Vizier
                if (input.isQueryVizier() && VizierSearchEngine.isTimeQuantumFree()) {
                    VizierSearchEngine.setTimeQuantumUsed(true);
                    vizierResponse = vizierSearchEngine.findAllByPosition(input);
                    input.setQueryVizier(false);
                }

                if (finished(input)) {
                    storeResults(responseData, mastResponse, simbadResponse, vizierResponse);
                    break;
                } else {
                    synchronized (SearchEngine.class) {
                        SearchEngine.class.wait();
                    }
                }
            } catch (InterruptedException ie) {
                break;
            }
        }

        return responseData;
    }

    public ResponseData findAllByPositionCrossmatch(SearchFormInput input) {

        ResponseData responseData = new ResponseData();

        CompletableFuture<MastResponse>   mastResponse   = null;
        CompletableFuture<SimbadResponse> simbadResponse = null;
        CompletableFuture<VizierResponse> vizierResponse = null;

        while (true) {

            try {

                // MAST
                if (input.isQueryMast() && MASTSearchEngine.isTimeQuantumFree()) {
                    MASTSearchEngine.setTimeQuantumUsed(true);
                    mastResponse = mastSearchEngine.findAllByPositionCrossmatch(input);
                    input.setQueryMast(false);
                }

                // SIMBAD
                if (input.isQuerySimbad() && SimbadSearchEngine.isTimeQuantumFree()) {
                    SimbadSearchEngine.setTimeQuantumUsed(true);
                    simbadResponse = simbadSearchEngine.findAllByCrossmatch(input);
                    input.setQuerySimbad(false);
                }

                // Vizier
                if (input.isQueryVizier() && VizierSearchEngine.isTimeQuantumFree()) {
                    VizierSearchEngine.setTimeQuantumUsed(true);
                    vizierResponse = vizierSearchEngine.findAllByCrossmatch(input);
                    input.setQueryVizier(false);
                }

                if (finished(input)) {
                    storeResults(responseData, mastResponse, simbadResponse, vizierResponse);
                    break;
                } else {
                    synchronized (SearchEngine.class) {
                        SearchEngine.class.wait();
                    }
                }
            } catch (InterruptedException ie) {
                break;
            }
        }

        return responseData;
    }

    public ResponseData findAllByID(SearchFormInput input) {

        ResponseData responseData = new ResponseData();

        CompletableFuture<MastResponse>   mastResponse   = null;
        CompletableFuture<SimbadResponse> simbadResponse = null;
        CompletableFuture<VizierResponse> vizierResponse = null;

        while (true) {

            try {

                // MAST
                if (input.isQueryMast() && MASTSearchEngine.isTimeQuantumFree()) {
                    MASTSearchEngine.setTimeQuantumUsed(true);
                    if (mastSearchEngine == null) {
                        log.error("    mastSearchEngine is NULL!");
                    }
                    mastResponse = mastSearchEngine.findAllByID(input);
                    input.setQueryMast(false);
                }

                // SIMBAD
                if (input.isQuerySimbad() && SimbadSearchEngine.isTimeQuantumFree()) {
                    SimbadSearchEngine.setTimeQuantumUsed(true);
                    if (RegularExpressions.isIAUFormat(input.getSearchInput())) {
                        simbadResponse = simbadSearchEngine.findAllByPosition(input);
                    } else {
                        simbadResponse = simbadSearchEngine.findAllById(input);
                    }
                    input.setQuerySimbad(false);
                }

                // Vizier
                if (input.isQueryVizier() && VizierSearchEngine.isTimeQuantumFree()) {
                    VizierSearchEngine.setTimeQuantumUsed(true);
                    vizierResponse = vizierSearchEngine.findAllById(input);
                    input.setQueryVizier(false);
                }

                if (finished(input)) {
                    storeResults(responseData, mastResponse, simbadResponse, vizierResponse);
                    break;
                } else {
                    synchronized (SearchEngine.class) {
                        SearchEngine.class.wait();
                    }
                }
            } catch (InterruptedException ie) {
                break;
            }
        }

        return responseData;
    }

    public List<SimbadMeasurementsTable> findAllMeasurementsByID(SearchFormInput input) {

        try {
            while ( !SimbadSearchEngine.isTimeQuantumFree() ) {
                synchronized (SearchEngine.class) {
                    SearchEngine.class.wait();
                }
            }
        } catch (InterruptedException e) {
            return new ArrayList<>();
        }

        SimbadSearchEngine.setTimeQuantumUsed(true);
        return SimbadSearchEngine.findAllMeasurementsById(input);
    }

    public ResponseData process(SearchFormInput input) {

        if (AppConfig.DEBUG) {
            log.debug("Resolving which type of query has been selected by user...");
        }

        if (SearchType.ID_NAME.equals(input.getSearchBy())) {
            if (AppConfig.DEBUG) {
                log.debug("query by ID");
            }

            // If user put coordinates into search bar but selected search by id/name...
            if (RegularExpressions.isPositionInput(input.getSearchInput())) {
                throw new IllegalArgumentException(ExceptionMSG.SELECT_POSITION_FOR_QUERY_BY_COORDS);
            }

            if (input.getSearchInput() == null || input.getSearchInput().isEmpty()) {
                throw new IllegalArgumentException(ExceptionMSG.EMPTY_SEARCH_INPUT);
            }
            return findAllByID(input);
        }

        if (SearchType.POSITION.equals(input.getSearchBy())) {
            if (AppConfig.DEBUG) {
                log.debug("query by POSITION");
            }

            return findAllByPosition(input);
        }

        if (SearchType.POSITION_CROSSMATCH.equals(input.getSearchBy())) {
            if (AppConfig.DEBUG) {
                log.debug("CROSSMATCH query");
            }

            return findAllByPositionCrossmatch(input);
        }
        throw new IllegalArgumentException(ExceptionMSG.NOT_DEFINED_SEARCH_OPTION + input.getSearchInput());
    }
}
