package org.astrosearcher.enums.cds.simbad;

/**
 * This enum class represents several possible arguments in URL request
 * for Simbad server (only subset of all possible arguments - not all
 * are needed by this application).
 *
 * @author Ľuboslav Halama
 */
public enum SimbadArgType {

    ID("Ident"),
    COORDINATES("Coord"),
    OUTPUT_LIMIT("output.max"),
    RADIUS("Radius"),
    RADIUS_UNIT("Radius.unit"),
    BIBCODE("bibcode"),

    MEASURES_DISP_T("mesdisplay"),
    MEASURES_CATS("list.mescat");

    private String urlName;

    SimbadArgType(String urlName) {
        this.urlName = urlName;
    }

    @Override
    public String toString() {
        return urlName;
    }
}
