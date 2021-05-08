package org.astrosearcher.utilities;

import org.astrosearcher.classes.ResponseData;
import org.astrosearcher.classes.constants.AppConfig;
import org.astrosearcher.classes.constants.RegularExpressions;
import org.astrosearcher.classes.constants.messages.ExceptionMSG;
import org.astrosearcher.classes.mast.MastResponse;
import org.astrosearcher.classes.mast.services.caom.cone.ResponseForReqByPos;
import org.astrosearcher.classes.simbad.SimbadMeasurementsTable;
import org.astrosearcher.classes.simbad.SimbadResponse;
import org.astrosearcher.classes.vizier.VizierResponse;
import org.astrosearcher.enums.SearchType;
import org.astrosearcher.models.SearchFormInput;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.ArrayList;
import java.util.List;


/**
 * Provides general interface for searching in three defined catalogues (Mast, Vizier, Simbad)
 * <p>
 * Class focuses mainly on searching by two diferrent types:
 * 1.) ID/name
 * 2.) Coordinates (position) -> RA, Dec, (opt. Radius)
 * <p>
 * However, querying on MAST server is possible only by using coordinates, so ID/name must be firstly resolved
 * into coordinates (position) and query by coordinates (position) is used then.
 *
 * @author Ľuboslav Halama
 */
@EnableScheduling
@EnableAsync
public class SearchEngine {

//    public static boolean timeQuantumUsed = false;

    private static boolean finished(SearchFormInput input) {
        return !input.isQueryMast() && !input.isQueryVizier() && !input.isQuerySimbad();
    }


    public static ResponseData findAllByPosition(SearchFormInput input) {

        ResponseData responseData = new ResponseData();

        while (true) {

            try {

                // MAST
                if (input.isQueryMast()) {
                    if (!MASTSearchEngine.isTimeQuantumUsed()) {
                        MASTSearchEngine.setTimeQuantum(true);
                        responseData.setMastResponse(MASTSearchEngine.findAllByPosition(input));
                        input.setQueryMast(false);
                    }
                }

                // SIMBAD
                if (input.isQuerySimbad()) {
                    if (!SimbadSearchEngine.isTimeQuantumUsed()) {
                        SimbadSearchEngine.setTimeQuantum(true);
                        responseData.setSimbadResponse(SimbadSearchEngine.findAllByPosition(input));
                        input.setQuerySimbad(false);
                    }
                }

                // Vizier
                if (input.isQueryVizier()) {
                    if (!VizierSearchEngine.isTimeQuantumUsed()) {
                        VizierSearchEngine.setTimeQuantum(true);
                        responseData.setVizierResponse(VizierSearchEngine.findAllByPosition(input));
                        input.setQueryVizier(false);
                    }

                }

                if (finished(input)) {
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

    public static ResponseData findAllByPositionCrossmatch(SearchFormInput input) {

        ResponseData responseData = new ResponseData();

        while (true) {

            try {

                // MAST
                if (input.isQueryMast()) {
                    if (!MASTSearchEngine.isTimeQuantumUsed()) {
                        MASTSearchEngine.setTimeQuantum(true);
                        responseData.setMastResponse(MASTSearchEngine.findAllByPositionCrossmatch(input));
                        input.setQueryMast(false);
                    }
                }

                // SIMBAD
                if (input.isQuerySimbad()) {
                    if (!SimbadSearchEngine.isTimeQuantumUsed()) {
                        SimbadSearchEngine.setTimeQuantum(true);
                        responseData.setSimbadResponse(SimbadSearchEngine.findAllByCrossmatch(input));
                        input.setQuerySimbad(false);
                    }
                }

                // Vizier
                if (input.isQueryVizier()) {
                    if (!VizierSearchEngine.isTimeQuantumUsed()) {
                        VizierSearchEngine.setTimeQuantum(true);
                        responseData.setVizierResponse(VizierSearchEngine.findAllByCrossmatch(input));
                        input.setQueryVizier(false);
                    }

                }

                if (finished(input)) {
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

    public static ResponseData findAllByID(SearchFormInput input) {
        ResponseData responseData = new ResponseData();

        while (true) {

            try {

                // MAST
                if (input.isQueryMast()) {
                    if (!MASTSearchEngine.isTimeQuantumUsed()) {
                        MASTSearchEngine.setTimeQuantum(true);
                        responseData.setMastResponse(MASTSearchEngine.findAllByID(input));
                        input.setQueryMast(false);
                    }
                }

                // SIMBAD
                if (input.isQuerySimbad()) {
                    if (!SimbadSearchEngine.isTimeQuantumUsed()) {
                        SimbadSearchEngine.setTimeQuantum(true);
                        if (RegularExpressions.isIAUFormat(input.getSearchInput())) {
                            responseData.setSimbadResponse(SimbadSearchEngine.findAllByPosition(input));
                        } else {
                            responseData.setSimbadResponse(SimbadSearchEngine.findAllById(input));
                        }
                        input.setQuerySimbad(false);
                    }
                }

                // Vizier
                if (input.isQueryVizier()) {
                    if (!VizierSearchEngine.isTimeQuantumUsed()) {
                        VizierSearchEngine.setTimeQuantum(true);
                        responseData.setVizierResponse(VizierSearchEngine.findAllById(input));
                        input.setQueryVizier(false);
                    }

                }

                if (finished(input)) {
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

    public static List<SimbadMeasurementsTable> findAllMeasurementsByID(SearchFormInput input) {

        try {
            while (SimbadSearchEngine.isTimeQuantumUsed()) {
                SearchEngine.class.wait();
            }
        } catch (InterruptedException e) {
            return new ArrayList<>();
        }

        SimbadSearchEngine.setTimeQuantum(true);
        return SimbadSearchEngine.findAllMeasurementsById(input);
    }

    public static ResponseData process(SearchFormInput input) {

//        try {
//            synchronized (SearchEngine.class) {
//                while (timeQuantumUsed) {
//                    SearchEngine.class.wait();
//                }
//            }
//        } catch (InterruptedException e) {
//            return new ResponseData();
//        }
//        timeQuantumUsed = true;

        if (AppConfig.DEBUG) {
            System.out.print("Resolving which type of query has been selected by user... ");
        }

        if (SearchType.ID_NAME.equals(input.getSearchBy())) {
            if (AppConfig.DEBUG) {
                System.out.println("query by ID");
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
                System.out.println("query by POSITION");
            }

            return findAllByPosition(input);
        }

        if (SearchType.POSITION_CROSSMATCH.equals(input.getSearchBy())) {
            if (AppConfig.DEBUG) {
                System.out.println("CROSSMATCH query");
            }

            return findAllByPositionCrossmatch(input);
        }
        throw new IllegalArgumentException(ExceptionMSG.NOT_DEFINED_SEARCH_OPTION + input.getSearchInput());
    }
}
