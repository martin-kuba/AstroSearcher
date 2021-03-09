package org.astrosearcher.utilities;

import org.astrosearcher.classes.PositionInput;
import org.astrosearcher.classes.ResponseData;
import org.astrosearcher.classes.mast.MastResponse;
import org.astrosearcher.classes.mast.services.caom.cone.ResponseForReqByPos;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

public class SearchEngine {

    public static List<PositionInput> resolvePositionByNameOrID(String input) {
        List<PositionInput> resolved = new ArrayList<>();

        resolved.addAll(MASTSearchEngine.resolvePositionByNameOrID(input));

        return resolved;
    }

    /* TODO: Choose between MAST Search services
             -> coords (ra, dec, [radius]) -> cone search
             -> id (name) -> lookup, then cone search

       TODO: Search for Vizier
       TODO: Search for Simbad

       TODO: change return type, now its only for MAST tables...
    */
    public static ResponseData findAllByPosition(@Valid PositionInput input) {
        ResponseData responseData = new ResponseData();

        ResponseForReqByPos resp = MASTSearchEngine.findAllByPosition(input.getRa(), input.getDec(), input.getRadius());
        responseData.setMastResponse(resp == null ? new MastResponse() : new MastResponse(resp));

        return responseData;
    }
}
