package org.astrosearcher.classes.constants;

import com.fasterxml.jackson.annotation.JsonIgnore;

public final class MASTConstants {
    public static final String CONNECTION_URL = "https://mast.stsci.edu/api/v0/invoke?";
    public static final String REQUEST_PARAMS_PREFIX = "request=";
    public static final String DEFAULT_FORMAT = "json";

    public static final int DEFAULT_PAGE = 1;
    public static final int DEFAULT_PAGE_SIZE = 500;


    // Crossmatch
    public static final String DEC_COLUMN    = "decColumn";
    public static final String RA_COLUMN     = "raColumn";
    public static final String RADIUS_COLUMN = "radius";
    public static final double DEFAULT_CROSSMATCH_RADIUS = 0.001;

    public static final String DEFAULT_DEC_COLUMN_NAME = "dec";
    public static final String DEFAULT_RA_COLUMN_NAME  = "ra";

    public static final String DEC_TYPE = "float";
    public static final String RA_TYPE = "float";
}
