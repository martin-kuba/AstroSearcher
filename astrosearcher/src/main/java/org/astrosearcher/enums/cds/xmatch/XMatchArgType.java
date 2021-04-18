package org.astrosearcher.enums.cds.xmatch;

public enum XMatchArgType {
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

    XMatchArgType(String urlName) {
        this.urlName = urlName;
    }

    @Override
    public String toString() {
        return urlName;
    }
}
