package org.astrosearcher.utilities;

import org.astrosearcher.classes.AstroObject;
import org.astrosearcher.classes.mast.TableFromReqByPos;

import java.util.ArrayList;
import java.util.List;

public class SearchEngine {

    public AstroObject find(String searchInput) {
        // TODO: just a mock data - has to be removed and new object created from server response
        // TODO: return type of findAll() has been completely changed, this method must be fixed/removed.
        AstroObject result = new AstroObject(0);

        return result;
    }

    /* TODO: Choose between MAST Search services
             -> coords (ra, dec, [radius]) -> cone search
             -> id (name) -> lookup, then cone search

       TODO: Search for Vizier
       TODO: Search for Simbad

       TODO: change return type, now its only for MAST tables...
    */
    public static List<TableFromReqByPos> findAllByPosition(double ra, double dec, double radius) {
        List<TableFromReqByPos> results = new ArrayList<>();

        TableFromReqByPos res = MASTSearchEngine.findAllByPosition(ra, dec, radius);
        if (res != null) {
            results.add(res);
        }

        return results;
    }
}
