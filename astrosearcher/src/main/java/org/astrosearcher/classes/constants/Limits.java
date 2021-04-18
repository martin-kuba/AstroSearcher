package org.astrosearcher.classes.constants;

public final class Limits {

    // Application parameters
    public static final boolean DEBUG = false;
    public static final boolean DEBUG_DISPLAY_MAST_RESULTS   = false;
    public static final boolean DEBUG_DISPLAY_SIMBAD_RESULTS = false;

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


    // TODO: rename constants
    // unassigned
    public static final double alpha_zero = 282.25;
    public static final double delta_zero = 62.6;
    public static final double longitude_zero = 33;

    public static final double alpha_zero_rad = 282.25 * Math.PI / 180;
    public static final double delta_zero_rad = 62.6 * Math.PI / 180;
    public static final double longitude_zero_rad = 33 * Math.PI / 180;

}
