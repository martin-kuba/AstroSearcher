package org.astrosearcher.enums.simbad;

/**
 * This enum class represents several possible arguments in URL request for Simbad server (only subset of all
 * possible arguments - not all are needed by this application).
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

    // Cross-match

    CATALOG1("cat1"),
    CATALOG1_RA_COL("colRA1"),
    CATALOG1_DEC_COL("colDec1"),

    CATALOG2("cat2"),
    CATALOG2_RA_COL("colRA2"),
    CATALOG2_DEC_COL("colDec2"),

    MAX_DISTANCE("distMaxArcsec"),
    MAX_RECORDS("MAXREC"),
    RESPONSE_FORMAT("RESPONSEFORMAT"),
    REQUEST_TYPE("REQUEST");

    private String urlName;

    SimbadArgType(String urlName) {
        this.urlName = urlName;
    }

    @Override
    public String toString() {
        return urlName;
    }
}
