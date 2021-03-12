package org.astrosearcher.classes.constants;

import com.fasterxml.jackson.annotation.JsonIgnore;

public final class MASTConstants {
    public static final String CONNECTION_URL = "https://mast.stsci.edu/api/v0/invoke?";
    public static final String REQUEST_PARAMS_PREFIX = "request=";
    public static final String DEFAULT_FORMAT = "json";

    public static final int DEFAULT_PAGE = 1;
    public static final int DEFAULT_PAGE_SIZE = 500;
}
