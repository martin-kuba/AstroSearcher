package org.astrosearcher.classes.constants;

public final class Limits {

    /* ********************************************* *
     *     A P P L I C A T I O N     L I M I T S     *
     * ********************************************* */

    // Queries per second
    public static final int MAST_MAX_QPS   = 4;
    public static final int SIMBAD_MAX_QPS = 4;
    public static final int VIZIER_MAX_QPS = 4;


    /* *********************************************** *
     *     S E A R C H  -  F O R M     L I M I T S     *
     * *********************************************** */

    // Input (general input)
    public static final int INPUT_MAX_LENGTH = 50;

    // Right Ascension
    public static final int RA_MIN = 0;
    public static final int RA_MAX = 360;

    // Declination
    public static final int DEC_MIN = -90;
    public static final int DEC_MAX = 90;

    // Radius
    public static final double DEFAULT_RADIUS = 0.075;
    public static final int    RADIUS_MAX     = 5;
    public static final int    RADIUS_MIN     = 0;
    public static final int    DEG_TO_ARCSEC  = 3600;

    // Page
    public static final int PAGE_MIN     = 1;
    public static final int PAGE_MAX     = 10000;
    public static final int DEFAULT_PAGE = 1;

    // Pagesize
    public static final int PAGESIZE_MIN     = 1;
    public static final int PAGESIZE_MAX     = 5000;
    public static final int DEFAULT_PAGESIZE = 500;

    // Vizier input
    public static final int VIZIER_INPUT_MAX_LENGTH = 150;
}
