package org.astrosearcher.utilities;

import org.astrosearcher.classes.PositionInput;
import org.astrosearcher.classes.ResponseData;
import org.astrosearcher.classes.constants.messages.ExceptionMSG;
import org.astrosearcher.classes.mast.MastResponse;
import org.astrosearcher.classes.mast.services.caom.cone.ResponseForReqByPos;
import org.astrosearcher.enums.SearchType;
import org.astrosearcher.models.SearchFormInput;


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
        ResponseForReqByPos resp = MASTSearchEngine.findAllByPosition(input);
        responseData.setMastResponse(resp == null ? new MastResponse() : new MastResponse(resp));

        // SIMBAD
        responseData.setSimbadResponse(SimbadSearchEngine.findAllByPosition(input));

        // Vizier
        responseData.setVizierResponse(VizierSearchEngine.findAllByPosition(input));

        return responseData;
    }

    public static ResponseData findAllByPositionCrossmatch(SearchFormInput input) {
        ResponseData responseData = new ResponseData();

//        System.out.println("File: " + input.getFile().getName());
        ResponseForReqByPos resp = MASTSearchEngine.findAllByPositionCrossmatch(input);
        responseData.setMastResponse(resp == null ? new MastResponse() : new MastResponse(resp));

//        responseData.setSimbadResponse(SimbadSearchEngine.findAllByPosition(input));

        return responseData;
    }

    public static ResponseData findAllByID(SearchFormInput input) {
        ResponseData responseData = new ResponseData();

//        System.out.println("Starting to query MAST... ");
        ResponseForReqByPos response = MASTSearchEngine.findAllByID(input);
        responseData.setMastResponse(response == null ? new MastResponse() : new MastResponse(response));
//        System.out.println("Mast response acquired and parsed.\n");

//        System.out.println("Starting to query Simbad...");
        responseData.setSimbadResponse(SimbadSearchEngine.findAllById(input));
//        System.out.println("Simbad response acquired and parsed.\n");

        return responseData;
    }

    public static ResponseData process (SearchFormInput input) {

        if (SearchType.ID_NAME.equals(input.getSearchBy())) {

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
            return findAllByPosition(input);
        }

        if (SearchType.POSITION_CROSSMATCH.equals(input.getSearchBy())) {
            return findAllByPositionCrossmatch(input);
        }
        throw new IllegalArgumentException(ExceptionMSG.NOT_DEFINED_SEARCH_OPTION + input.getSearchInput());
    }
}
