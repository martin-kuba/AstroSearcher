package org.astrosearcher.classes.constants;

public final class RegularExpressions {

    public static final String FRUMBER     = "(-?\\d+(?:\\.\\d+)?)";        // FRactaional nUMBER
    public static final String SEP         = "(?:(?:\\s*,\\s*)|\\s+)";      // SEParator
    public static final String OPT_FRUMBER = "(?:" + SEP + FRUMBER + ")?";  // OPTional FRUMBER
    public static final String INPUT_REGEX = "\\s*" + FRUMBER + SEP + FRUMBER + OPT_FRUMBER + "\\s*";
}
