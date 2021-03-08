package org.astrosearcher.classes;

import lombok.AccessLevel;
import lombok.Getter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Getter
public class PositionInput {

//    private final String INPUT_REGEX = "[\\s*(\\-?\\d+(?:.\\d+?:)?)\\s*]{2,3}";
//    private final String INPUT_REGEX = "(?:\\s*(\\-?\\d+(?:.\\d+)?)\\s*){2,3}";

    @Getter(AccessLevel.NONE)
    private final String FRUMBER     = "(-?\\d+(?:\\.\\d+)?)";        // FRactaional nUMBER
    @Getter(AccessLevel.NONE)
    private final String SEP         = "(?:(?:\\s*,\\s*)|\\s+)";      // SEParator
    @Getter(AccessLevel.NONE)
    private final String OPT_FRUMBER = "(?:" + SEP + FRUMBER + ")?";  // OPTional FRUMBER
    @Getter(AccessLevel.NONE)
    private final String INPUT_REGEX = "\\s*" + FRUMBER + SEP + FRUMBER + OPT_FRUMBER + "\\s*";

    private double ra     = 0;    // Right Ascension

    @Min(value = -90, message = "Declination must be higher or equal to -90")
    @Max(value = 90, message = "Declination must be lower or equal to 90")
    private double dec    = 0;    // Declination
    private double radius = 0.2;

    private String  message;
    private boolean successful = false;

    public PositionInput() {}

    public PositionInput(double ra, double dec, double radius) {
        this.ra = ra;
        this.dec = dec;
        this.radius = radius;
        successful = true;
    }

    public PositionInput(String inputToParse) {
        Pattern pattern = Pattern.compile(INPUT_REGEX);
        Matcher matcher = pattern.matcher(inputToParse);

        if (matcher.matches()) {

            ra = Double.parseDouble(matcher.group(1));
            if (ra < 0 || ra >= 360) {
                message = "Right Ascension value must be between 0 and 360!";
                return;
            }

            dec = Double.parseDouble(matcher.group(2));

            if (dec < -90 || dec > 90) {
                message = "Declination value must be between -90 and 90!";
                return;
            }

            // if there is radius given as well...
            if (matcher.group(3) != null) {
                radius = Double.parseDouble(matcher.group(3));
            }

            successful = true;
            return;
        }
        message = "Two or three integral/fractional numbers expected, separated by space or comma (like 1.0, 2.0)";

    }

    @Override
    public String toString() {
        return "\n    RA     :" + ra +
               "\n    Dec    :" + dec +
               "\n    radius :" + radius;
    }
}
