package org.astrosearcher.utilities;

import org.astrosearcher.classes.PositionInput;
import org.astrosearcher.classes.ResponseData;
import org.astrosearcher.classes.constants.Limits;
import org.astrosearcher.classes.constants.messages.ExceptionMSG;
import org.astrosearcher.classes.mast.MastResponse;
import org.astrosearcher.classes.mast.services.caom.cone.ResponseForReqByPos;
import org.astrosearcher.classes.simbad.SimbadResponse;
import org.astrosearcher.classes.vizier.VizierResponse;
import org.astrosearcher.enums.SearchType;
import org.astrosearcher.models.SearchFormInput;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;


/**
 * Provides general interface for searching in three defined catalogues (Mast, Vizier, Simbad)
 *
 * Class focuses mainly on searching by two diferrent types:
 *   1.) ID/name
 *   2.) Coordinates (position) -> RA, Dec, (opt. Radius)
 *
 * However, querying on MAST server is possible only by using coordinates, so ID/name must be firstly resolved
 * into coordinates (position) and query by coordinates (position) is used then.
 *
 * @author Ľuboslav Halama
 */
public class SearchEngine {


    public static ResponseData findAllByPosition(SearchFormInput input) {
        ResponseData responseData = new ResponseData();

        // MAST
        if (input.isQueryMast()) {
            ResponseForReqByPos resp = MASTSearchEngine.findAllByPosition(input);
            responseData.setMastResponse(resp == null ? new MastResponse() : new MastResponse(resp));
        } else {
            responseData.setMastResponse(new MastResponse());
        }

        // SIMBAD
        if (input.isQuerySimbad()) {
            responseData.setSimbadResponse(SimbadSearchEngine.findAllByPosition(input));
        } else {
            responseData.setSimbadResponse(new SimbadResponse());
        }


        // Vizier
        if (input.isQueryVizier()) {
            responseData.setVizierResponse(VizierSearchEngine.findAllByPosition(input));
        } else {
            responseData.setVizierResponse(new VizierResponse());
        }


        return responseData;
    }

    public static ResponseData findAllByPositionCrossmatch(SearchFormInput input) {
        ResponseData responseData = new ResponseData();

//        System.out.println("File: " + input.getFile().getName());

        // MAST
        if (input.isQueryMast()) {
            ResponseForReqByPos resp = MASTSearchEngine.findAllByPositionCrossmatch(input);
            if (Limits.DEBUG) {
                System.out.println("    MAST Crossmatch response data:");

                if (resp != null && resp.getData() != null) {
                    for (var line : resp.getData()) {
                        System.out.println("        " + line);
                    }
                }
            }

            responseData.setMastResponse(resp == null ? new MastResponse() : new MastResponse(resp));
        } else {
            responseData.setMastResponse(new MastResponse());
        }

//        responseData.setSimbadResponse(SimbadSearchEngine.findAllByPosition(input));

        return responseData;
    }

    public static ResponseData findAllByID(SearchFormInput input) {
        ResponseData responseData = new ResponseData();

        // MAST
        if (input.isQueryMast()) {
            ResponseForReqByPos response = MASTSearchEngine.findAllByID(input);
            responseData.setMastResponse(response == null ? new MastResponse() : new MastResponse(response));
        } else {
            responseData.setMastResponse(new MastResponse());
        }

        // Simbad
        if (input.isQuerySimbad()) {
            responseData.setSimbadResponse(SimbadSearchEngine.findAllById(input));
        } else {
            responseData.setSimbadResponse(new SimbadResponse());
        }

//        System.out.println("Simbad response acquired and parsed.\n");

        // Vizier
        if (input.isQueryVizier()) {
            responseData.setVizierResponse(VizierSearchEngine.findAllById(input));
        } else {
            responseData.setVizierResponse(new VizierResponse());
        }


        return responseData;
    }

    public static ResponseData process (SearchFormInput input) {

        if ( Limits.DEBUG ) {
            System.out.print("Resolving which type of query has been selected by user... ");
        }

        if (SearchType.ID_NAME.equals(input.getSearchBy())) {
            if ( Limits.DEBUG ) {
                System.out.println("query by ID");
            }

            // If user put coordinates into search bar but selected search by id/name...
            if (PositionInput.isPositionInput(input.getSearchInput())) {
                throw new IllegalArgumentException(ExceptionMSG.SELECT_POSITION_FOR_QUERY_BY_COORDS);
            }

            if (input.getSearchInput() == null || input.getSearchInput().isEmpty()) {
                throw new IllegalArgumentException(ExceptionMSG.EMPTY_SEARCH_INPUT);
            }
            return findAllByID(input);
        }

        if (SearchType.POSITION.equals(input.getSearchBy())) {
            if ( Limits.DEBUG ) {
                System.out.println("query by POSITION");
            }

            return findAllByPosition(input);
        }

        if (SearchType.POSITION_CROSSMATCH.equals(input.getSearchBy())) {
            if ( Limits.DEBUG ) {
                System.out.println("CROSSMATCH query");
            }
//            try {
//                System.out.println(URLEncoder.encode(
//                        new BufferedReader(new InputStreamReader(input.getFile().getInputStream())).lines()., StandardCharsets.UTF_8););
//            } catch (Exception e) {
//                System.err.println("Something went wrong...\n" + e);
//            }

            return findAllByPositionCrossmatch(input);
        }
        throw new IllegalArgumentException(ExceptionMSG.NOT_DEFINED_SEARCH_OPTION + input.getSearchInput());
    }
}
