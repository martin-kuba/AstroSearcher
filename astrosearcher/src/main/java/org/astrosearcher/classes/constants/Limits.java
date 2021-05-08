package org.astrosearcher.classes.constants;

public final class Limits {

    // Right Ascension
    public static final int RA_MIN = 0;
    public static final int RA_MAX = 360;

    // Declination
    public static final int DEC_MIN = -90;
    public static final int DEC_MAX = 90;

    // Radius
    public static final double DEFAULT_RADIUS = 0.075;
    public static final double RADIUS_MIN     = 0.0;
    public static final int    DEG_TO_ARCSEC  = 3600;

    // Page
    public static final int PAGE_MIN = 1;
    public static final int DEFAULT_PAGE = 1;

    // Pagesize
    public static final int PAGESIZE_MIN = 1;
    public static final int DEFAULT_PAGESIZE = 500;

    // Queries per second
    public static final int MAST_MAX_QPS   = 4;
    public static final int SIMBAD_MAX_QPS = 4;
    public static final int VIZIER_MAX_QPS = 4;

}
