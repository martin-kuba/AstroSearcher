package org.astrosearcher.classes;

import lombok.AccessLevel;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Getter
public class PositionInput {

    @Getter
    private class Position {
        private double ra;
        private double dec;

        public Position(double ra, double dec) {
            this.ra = ra;
            this.dec = dec;
        }

        @Override
        public String toString() {
            return ra + " " + dec;
        }

    }

    @Getter(AccessLevel.NONE)
    private static final String FRUMBER     = "(-?\\d+(?:\\.\\d+)?)";        // FRactaional nUMBER
    @Getter(AccessLevel.NONE)
    private static final String SEP         = "(?:(?:\\s*,\\s*)|\\s+)";      // SEParator
    @Getter(AccessLevel.NONE)
    private static final String OPT_FRUMBER = "(?:" + SEP + FRUMBER + ")?";  // OPTional FRUMBER
    @Getter(AccessLevel.NONE)
    private static final String INPUT_REGEX = "\\s*" + FRUMBER + SEP + FRUMBER + OPT_FRUMBER + "\\s*";

    private Position position;

    private static final double DEFAULT_RADIUS = 0.2;
    private double radius;

    private String  message;
    private boolean successful = false;

    public PositionInput(double ra, double dec, double radius) {

        this.position = new Position(ra, dec);
        this.radius = radius;
        successful = true;
    }

    public PositionInput(String inputToParse) {
        Pattern pattern = Pattern.compile(INPUT_REGEX);
        Matcher matcher = pattern.matcher(inputToParse);

        if (matcher.matches()) {

            double ra = Double.parseDouble(matcher.group(1));
            if (ra < 0 || ra >= 360) {
                throw new IllegalArgumentException("Right Ascension value must be between 0 and 360!");
//                message = "Right Ascension value must be between 0 and 360!";
//                return;
            }

            double dec = Double.parseDouble(matcher.group(2));
            if (dec < -90 || dec > 90) {
                throw new IllegalArgumentException("Declination value must be between -90 and 90!");
//                message = "Declination value must be between -90 and 90!";
//                return;
            }

            radius = matcher.group(3) != null ? Double.parseDouble(matcher.group(3)) : DEFAULT_RADIUS;
            position = new Position(ra, dec);
            successful = true;

            return;
        }
        throw new IllegalArgumentException(
                "Two or three integral/fractional numbers expected, separated by space or comma (like 1.0, 2.0)"
        );
//        message = "Two or three integral/fractional numbers expected, separated by space or comma (like 1.0, 2.0)";

    }

    public static boolean isPositionInput(String input) {
        Pattern pattern = Pattern.compile(INPUT_REGEX);
        return pattern.matcher(input).matches();
    }

    public Map<String, Double> getAsMap() {
        Map<String, Double> converted = new HashMap<>();
        converted.put("ra", position.getRa());
        converted.put("dec", position.getDec());
        converted.put("radius", radius);
        return converted;
    }

    public double getRa() {
        return position.getRa();
    }

    public double getDec() {
        return position.getDec();
    }

    @Override
    public String toString() {
        return position + " " + radius;
    }
}
