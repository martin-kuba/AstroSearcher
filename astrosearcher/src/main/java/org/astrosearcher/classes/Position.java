package org.astrosearcher.classes;

import lombok.Getter;
import org.astrosearcher.classes.constants.ExceptionMSG;
import org.astrosearcher.classes.constants.Limits;
import org.astrosearcher.classes.constants.RegularExpressions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
public class Position {
    private double ra;
    private double dec;

    public Position(double ra, double dec) {
        this.ra = ra;
        this.dec = dec;
    }

    public Position(String input) {
        Pattern pattern = Pattern.compile(RegularExpressions.POSITION_REGEX);
        Matcher matcher = pattern.matcher(input);

        if (matcher.matches()) {

            this.ra = Double.parseDouble(matcher.group(1));
            if (ra < Limits.RA_MIN || ra >= Limits.RA_MAX) {
                throw new IllegalArgumentException(ExceptionMSG.INVALID_RIGHT_ASCENSION_EXCEPTION);
            }

            this.dec = Double.parseDouble(matcher.group(2));
            if (dec < Limits.DEC_MIN || dec > Limits.DEC_MAX) {
                throw new IllegalArgumentException(ExceptionMSG.INVALID_DECLINATION_EXCEPTION);
            }
            return;
        }
        throw new IllegalArgumentException(ExceptionMSG.INVALID_POSITION_INPUT_EXCEPTION);
    }

    public static boolean isPosition(String input) {
        return Pattern.compile(RegularExpressions.POSITION_REGEX).matcher(input).matches();
    }

    @Override
    public String toString() {
        return ra + " " + dec;
    }

}
