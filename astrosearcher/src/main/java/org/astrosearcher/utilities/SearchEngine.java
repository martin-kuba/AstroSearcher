package org.astrosearcher.utilities;

import org.astrosearcher.classes.AstroObject;
import org.astrosearcher.classes.PositionInput;
import org.astrosearcher.classes.ResponseData;
import org.astrosearcher.classes.mast.MastResponse;
import org.astrosearcher.classes.mast.services.caom.cone.ResponseForReqByPos;

import java.util.ArrayList;
import java.util.List;

public class SearchEngine {

    public AstroObject find(String searchInput) {
        // TODO: just a mock data - has to be removed and new object created from server response
        // TODO: return type of findAll() has been completely changed, this method must be fixed/removed.
        AstroObject result = new AstroObject(0);

        return result;
    }

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
    public static ResponseData findAllByPosition(double ra, double dec, double radius) {
        ResponseData responseData = new ResponseData();

        ResponseForReqByPos resp = MASTSearchEngine.findAllByPosition(ra, dec, radius);
        responseData.setMastResponse(resp == null ? new MastResponse() : new MastResponse(resp));

        return responseData;
    }
}
