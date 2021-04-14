package org.astrosearcher.classes.constants;

import java.util.regex.Pattern;

public final class RegularExpressions {

    public static final String UNSIGN_FRUMBER = "(\\d+(?:\\.\\d+)?)";
    public static final String SIGN_FRUMBER = "([+-]?\\d+(?:\\.\\d+)?)";          // FRactaional nUMBER
    public static final String SEP          = "(?:(?:\\s*,\\s*)|\\s+)";           // SEParator
    public static final String OPT_FRUMBER  = "(?:" + SEP + SIGN_FRUMBER + ")?";  // OPTional FRUMBER

    public static final String POSITION_REGEX = "\\s*" + SIGN_FRUMBER + SEP + SIGN_FRUMBER + "\\s*";
    public static final String POSITION_INPUT_REGEX = "\\s*" + SIGN_FRUMBER + SEP + SIGN_FRUMBER + OPT_FRUMBER + "\\s*";

    public static final String IAU_INPUT_ReGEX = "[JB]" + UNSIGN_FRUMBER + SIGN_FRUMBER;


    // input from file
    public static final String FILE_STRICT_COLUMN_NAMES = "^ra,dec$";
    public static final String FILE_COLUMNS_NAMES = "([a-zA-Z]*)\\s*([a-zA-Z]*).*";

    // Sesame line with alias    //(?:?!</alias>)
    public static final String SESAME_XML_ALIAS = "\\s*<alias>(.*?)</alias>";
    public static final String SESAME_XML_ONAME = "\\s*<oname>(.*?)</oname>";
    public static final String SESAME_VALID_RESPONSE   = ".*?" + SESAME_XML_ONAME + "(" + SESAME_XML_ALIAS + ")*.*?";


    public static boolean isPositionInput(String input) {
        Pattern pattern = Pattern.compile(RegularExpressions.POSITION_INPUT_REGEX);
        return pattern.matcher(input).matches();
    }

    public static boolean isIAUFormat(String input) {
        Pattern pattern = Pattern.compile(RegularExpressions.IAU_INPUT_ReGEX);
        return pattern.matcher(input).matches();
    }
}
