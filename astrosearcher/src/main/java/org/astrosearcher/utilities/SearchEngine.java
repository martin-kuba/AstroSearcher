package org.astrosearcher.utilities;

import org.astrosearcher.classes.PositionInput;
import org.astrosearcher.classes.ResponseData;
import org.astrosearcher.classes.mast.MastResponse;
import org.astrosearcher.classes.mast.services.caom.cone.ResponseForReqByPos;

import java.util.ArrayList;
import java.util.List;


public class SearchEngine {

    public static List<PositionInput> resolvePositionByNameOrID(String input) {

        List<PositionInput> resolved = new ArrayList<>();

        resolved.addAll(MASTSearchEngine.resolvePositionByNameOrID(input));

        return resolved;
    }

    public static void findAllByID(String input) {
        SimbadSearchEngine.findAllById(input);
    }

    /*
       TODO: Search for Vizier
       TODO: Search for Simbad
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
