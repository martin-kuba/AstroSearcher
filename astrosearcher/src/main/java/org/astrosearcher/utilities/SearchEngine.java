package org.astrosearcher.utilities;

import org.astrosearcher.classes.PositionInput;
import org.astrosearcher.classes.ResponseData;
import org.astrosearcher.classes.mast.MastResponse;
import org.astrosearcher.classes.mast.services.caom.cone.ResponseForReqByPos;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * Method takes String representation of id/name of astronomical object and resolves its id/name
     * into position (coordinates) in the sky.
     *
     * In case response was empty/unsuccessful - empty List of coordinates is returned.
     *
     * @param  input      String representation of ID/name to be searched by
     * @return resolved   All the resolved positions (coordinates) for given ID/name
     */
    public static List<PositionInput> resolvePositionByNameOrID(String input) {

        List<PositionInput> resolved = new ArrayList<>();

        resolved.addAll(MASTSearchEngine.resolvePositionByNameOrID(input));

        return resolved;
    }

    /**
     * Method takes String representation of id/name and searches for all the information connected to it
     * in internet astronomical catalogues.
     *
     * @param input
     */
    public static void findAllByID(String input) {
        SimbadSearchEngine.findAllById(input);
    }

    /*
       TODO: Search for Vizier
       TODO: Search for Simbad
    */
    /**
     * Method takes class representing necessary information about position in the sky (ra, dec and radius as well)
     * and searches all the information associated with this position in internet astronomical catalogues.
     *
     * @param  input         class representing position in the sky
     * @return responseData  class containing parsed responses from astronomical catalogues
     */
    public static ResponseData findAllByPosition(PositionInput input) {
        ResponseData responseData = new ResponseData();

//        System.out.println("Sending request for MAST server...");
        ResponseForReqByPos resp = MASTSearchEngine.findAllByPosition(input.getRa(), input.getDec(), input.getRadius());
//        System.out.println("Response from MAST acquired.");
        responseData.setMastResponse(resp == null ? new MastResponse() : new MastResponse(resp));

//        System.out.println("Sending request for Simbad server...");
        SimbadSearchEngine.findAllByPosition(input);
//        System.out.println("Response from Simbad acquired.");

        return responseData;
    }
}
