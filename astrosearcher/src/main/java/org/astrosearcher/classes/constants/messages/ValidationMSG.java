package org.astrosearcher.classes.constants.messages;

import org.astrosearcher.classes.constants.Limits;
import org.astrosearcher.classes.constants.RegularExpressions;

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
    public static final String RADIUS_NULL_VALIDATION_MSG       = "Radius must be specified.";
    public static final String RADIUS_NOT_NUMBER_VALIDATION_MSG = "Radius must be a number";
    public static final String RADIUS_MIN_VALIDATION_MSG        = "Minimal radius is " + Limits.RADIUS_MIN_STRING;
    public static final String RADIUS_MAX_VALIDATION_MSG        = "Maximal radius is " + Limits.RADIUS_MAX_STRING;

    // Vizier
    public static final String VIZIER_INPUT_MAX_LENGTH_VALIDATION_MSG =
            "Maximum length of input for Vizier is " + Limits.VIZIER_INPUT_MAX_LENGTH;

    // File
    public static final String FILE_EMPTY_VALIDATION_MSG = "File cannot be empty.";
    public static final String FILE_INVALID_FIRST_ROW_VALIDATION_MSG =
            "File must contain line: '" + RegularExpressions.FILE_STRICT_COLUMN_NAMES + "' at the beginning.";
    public static final String FILE_INVALID_COORDINATES_LINE =
            "(File) lines with coordinates mu be in format: '<number>,[space][+|-]<number>'";
    public static final String FILE_FAILED_TO_OPEN = "File failed to open.";
    public static final String NO_FILE_UPLOADED    = "No file has been uploaded.";
}
