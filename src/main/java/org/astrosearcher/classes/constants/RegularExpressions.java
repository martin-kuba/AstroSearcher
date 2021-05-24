package org.astrosearcher.classes.constants;

import java.util.regex.Pattern;

/**
 * Regular expressions used in the application.
 *
 * @author Ľuboslav Halama
 */
public final class RegularExpressions {

    public static final String DECIMAL        = "[+-]?\\d+(?:\\.\\d+)?";
    public static final String UNSIGN_FRUMBER = "(\\d+(?:\\.\\d+)?)";
    public static final String SIGN_FRUMBER = "([+-]?\\d+(?:\\.\\d+)?)";          // FRactaional nUMBER
    public static final String SEP          = "(?:(?:\\s*,\\s*)|\\s+)";           // SEParator
    public static final String OPT_FRUMBER  = "(?:" + SEP + SIGN_FRUMBER + ")?";  // OPTional FRUMBER

    public static final String POSITION_REGEX = "\\s*" + SIGN_FRUMBER + SEP + SIGN_FRUMBER + "\\s*";
    public static final String POSITION_INPUT_REGEX = "\\s*" + SIGN_FRUMBER + SEP + SIGN_FRUMBER + OPT_FRUMBER + "\\s*";

    public static final String IAU_INPUT_ReGEX = "[JB]" + UNSIGN_FRUMBER + SIGN_FRUMBER;



    // input from file
    public static final String FILE_STRICT_COLUMN_NAMES =
        MASTConstants.DEFAULT_RA_COLUMN_NAME + ", " + MASTConstants.DEFAULT_DEC_COLUMN_NAME;
    public static final String FILE_STRICT_COLUMN_NAMES_LINE =
            "^" + MASTConstants.DEFAULT_RA_COLUMN_NAME + ",\\s?" + MASTConstants.DEFAULT_DEC_COLUMN_NAME + "$";
    public static final String FILE_COORDINATES_LINE         = "^" + UNSIGN_FRUMBER + ",\\s?" + SIGN_FRUMBER + "$";

    // Sesame line with alias    //(?:?!</alias>)
    public static final String SESAME_XML_ALIAS = "\\s*<alias>(.*?)</alias>\\n";
    public static final String SESAME_XML_ONAME = "\\s*<oname>(.*?)</oname>\\n";
    public static final String SESAME_VALID_RESPONSE   = "[\\s\\S]*?" + SESAME_XML_ONAME + "(" + SESAME_XML_ALIAS + ")*[\\s\\S]*?";

    // Simbad ASCII response
    public static final String SIMBAD_ASCII_MEASURE_CAT = "(?:(\\w+):(\\d+)\\s*)";

    public static final String SIMBAD_ASCII_MEASURE_VAL = "([^\\|]+\\|)+\\n";
    public static final String SIMBAD_ASCII_MEASURE_CAT_UNCOUNTED = "(?:\\w+:\\d+\\s*)";
    public static final String SIMBAD_ASCII_MEASUREMENTS =
            "Measures \\((" + SIMBAD_ASCII_MEASURE_CAT_UNCOUNTED + "*)\\):\\n((?:(?:[^\\|\\n]+\\|)*\\n)*)";

    //language=regexp
    public static final String SIMBAD_ASCII = "(?:.*?\\n)*" + SIMBAD_ASCII_MEASUREMENTS + "(?:.*?\\n)*";



    public static boolean isPositionInput(String input) {
        Pattern pattern = Pattern.compile(RegularExpressions.POSITION_INPUT_REGEX);
        return pattern.matcher(input).matches();
    }

    public static boolean isIAUFormat(String input) {
        Pattern pattern = Pattern.compile(RegularExpressions.IAU_INPUT_ReGEX);
        return pattern.matcher(input).matches();
    }
}
