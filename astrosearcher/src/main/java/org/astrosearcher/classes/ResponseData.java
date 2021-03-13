package org.astrosearcher.classes;

import lombok.Getter;
import lombok.Setter;
import org.astrosearcher.classes.mast.MastResponse;
import org.astrosearcher.classes.simbad.SimbadResponse;
import org.astrosearcher.classes.vizier.VizierResponse;


/**
 * Main class for representation of response data acquired from astronomical catalogues servers.
 *
 * Class provides access to data itself as well as basic methods for easier work with this data.
 *
 * @author Ľuboslav Halama
 */
@Getter
@Setter
public class ResponseData {
    private MastResponse mastResponse;
    private VizierResponse vizierResponse;
    private SimbadResponse simbadResponse;

    private String dataMSG;

    public ResponseData() {}

    public ResponseData(MastResponse mastResponse) {
        this.mastResponse = mastResponse;
    }

    public boolean containsMastResponse() {
        return mastResponse != null && !mastResponse.isEmpty();
    }

    public boolean containsVizierResponse() {
        return vizierResponse != null && !vizierResponse.isEmpty();
    }

    public boolean containsSimbadResponse() {
        return simbadResponse != null && !simbadResponse.isEmpty();
    }

    public boolean isEmpty() {
        return !containsMastResponse() && !containsVizierResponse() && !containsSimbadResponse();
    }
}
