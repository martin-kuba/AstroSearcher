package org.astrosearcher.classes.constants.messages;

import org.astrosearcher.classes.constants.Limits;

public final class ValidationMSG {

    // Input (general input)
    public static final String INPUT_MAX_LENGTH_VALIDATION_MSG
            = "Maximal length of general search input is " + Limits.INPUT_MAX_LENGTH;

    // Page
    public static final String PAGE_MIN_VALIDATION_MSG = "Minimal page is " + Limits.PAGE_MIN;
    public static final String PAGE_MAX_VALIDATION_MSG = "Maximal page is " + Limits.PAGE_MAX;

    // Pagesize
    public static final String PAGESIZE_MIN_VALIDATION_MSG = "Minimal pagesize is " + Limits.PAGESIZE_MIN;
    public static final String PAGESIZE_MAX_VALIDATION_MSG = "Maximal pagesize is " + Limits.PAGESIZE_MAX;

    // Radius
    public static final String RADIUS_MIN_VALIDATION_MSG = "Minimal radius is " + Limits.RADIUS_MIN;
    public static final String RADIUS_MAX_VALIDATION_MSG = "Maximal radius is " + Limits.RADIUS_MAX;

    // Vizier
    public static final String VIZIER_INPUT_MAX_LENGTH_VALIDATION_MSG =
            "Maximum length of input for Vizier is " + Limits.VIZIER_INPUT_MAX_LENGTH;
}
