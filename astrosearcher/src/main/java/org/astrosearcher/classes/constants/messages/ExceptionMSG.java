package org.astrosearcher.classes.constants.messages;

/**
 * Messages for exceptions that might be thrown by this application..
 *
 * @author Ľuboslav Halama
 */
public final class ExceptionMSG {
    public static final String SELECT_POSITION_FOR_QUERY_BY_COORDS =
            "Select POSITION option for querying by coordinates.";

    public static final String EMPTY_SEARCH_INPUT =
            "Search input cannot be empty if you are trying to search by ID/coordinates!";

    public static final String NOT_DEFINED_SEARCH_OPTION =
            "There is not defined search option for: ";

    public static final String INVALID_RIGHT_ASCENSION_EXCEPTION =
            "Right Ascension value must be between 0 and 360!";

    public static final String INVALID_DECLINATION_EXCEPTION =
            "Declination value must be between -90 and 90!";

    public static final String INVALID_POSITION_INPUT_EXCEPTION =
            "Two integral/fractional numbers expected, separated by space or comma (like 1.0, 2.0)";

    public static final String INVALID_POSITION_AND_RADIUS_INPUT_EXCEPTION =
            "Two or three integral/fractional numbers expected, separated by space or comma (like 1.0, 2.0)";


    /* ********************************* *
     *   M A S T   E X C E P T I O N S   *
     * ********************************* */
    public static final String INVALID_MAST_FIELD_NAME_EXCEPTION =
            "Invalid field name in MAST response (null) or name has not been found at all.";

    public static final String MAST_FIELD_NAME_NOT_MATCHED_EXCEPTION =
            "Field name in MAST response was not found in pre-defined MAST-fields Enum class.";

    public static final String NO_SERVICE_PROVIDED_BY_MAST_EXCEPTION =
            "There is no service provided by MAST for searching by: ";



    /* ************************************* *
     *   S I M B A D   E X C E P T I O N S   *
     * ************************************* */
    public static final String INVALID_SIMBAD_FIELD_NAME_EXCEPTION =
            "Invalid field name in Simbad response (null) or name has not been found at all.";

    public static final String SIMBAD_FIELD_NAME_NOT_MATCHED_EXCEPTION =
            "Field name in Simbad response was not found in pre-defined Simbad-fields Enum class.";

    public static final String NO_SERVICE_PROVIDED_BY_SIMBAD_EXCEPTION =
            "There is no service provided by Simbad for searching by: ";

    /* ************************************* *
     *   V I Z I E R   E X C E P T I O N S   *
     * ************************************* */
    public static final String NO_SERVICE_PROVIDED_BY_VIZIER_EXCEPTION =
            "There is no service provided by Vizier for searching by: ";
}
