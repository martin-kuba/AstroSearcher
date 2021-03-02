package org.astrosearcher.classes;

import lombok.Getter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
public class PositionInput {

//    private final String INPUT_REGEX = "[\\s*(\\-?\\d+(?:.\\d+?:)?)\\s*]{2,3}";
//    private final String INPUT_REGEX = "(?:\\s*(\\-?\\d+(?:.\\d+)?)\\s*){2,3}";

    private final String FIGIT     = "(-?\\d+(?:\\.\\d+)?)";      // Fractional dIGIT
    private final String SEP       = "(?:(?:\\s*,\\s*)|\\s+)";    // SEParator
    private final String OPT_FIGIT = "(?:" + SEP + FIGIT + ")?";  // OPTional FIGIT
    private final String INPUT_REGEX = "\\s*" + FIGIT + SEP + FIGIT + OPT_FIGIT + "\\s*";

    private double ra     = 0;    // Right Ascension
    private double dec    = 0;    // Declination
    private double radius = 0.2;

    private boolean successful = false;

    public PositionInput(String inputToParse) {
        Pattern pattern = Pattern.compile(INPUT_REGEX);
        Matcher matcher = pattern.matcher(inputToParse);

        if (matcher.matches()) {

            ra = Double.parseDouble(matcher.group(1));
            dec = Double.parseDouble(matcher.group(2));

            // if there is radius given as well...
//            System.out.println("groups: " +  matcher.groupCount());
//            System.out.println(matcher.group(0));
//            System.out.println(matcher.group(1));
//            System.out.println(matcher.group(2));
//            System.out.println(matcher.group(3));
            if ( matcher.group(3) != null ) {
                radius = Double.parseDouble(matcher.group(3));
            }

            successful = true;

//            System.out.println("    RA:     " + ra);
//            System.out.println("    Dec:    " + dec);
//            System.out.println("    Radius: " + radius);
        } else {
//            System.out.println("    Groups count:     " + matcher.groupCount());
        }
    }
}
