package org.astrosearcher.unit.input;

import org.astrosearcher.classes.PositionInput;
import org.astrosearcher.classes.constants.Limits;
import org.astrosearcher.classes.constants.messages.ExceptionMSG;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PositionInputTest {

    private final int     WHOLE_RADIUS   =  1;
    private final int     WHOLE_RA       =  5;
    private final int     WHOLE_DEC      = 42;

    private final double  DEFAULT_RADIUS = Limits.DEFAULT_RADIUS;
    private final double  FRACTIONAL_RA  =  5.0;
    private final double  FRACTIONAL_DEC = 42.358;

    private void assertEquals(PositionInput pi, double expRa, double expDec, double expRadius) {
        Assertions.assertEquals(expRa, pi.getRa());
        Assertions.assertEquals(expDec, pi.getDec());
        Assertions.assertEquals(expRadius, pi.getRadius());
    }

    private void assertThrowsIllegalArgumentException(String input) {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new PositionInput(input),
                ExceptionMSG.INVALID_POSITION_AND_RADIUS_INPUT_EXCEPTION
        );
    }

    @Test
    public void validWholeNumbersWithRadius() {
        wholeNumsSeparBySpacesShouldBeParsed(WHOLE_RA, WHOLE_DEC, WHOLE_RADIUS);
        wholeNumsSeparByCommaShouldBeParsed(WHOLE_RA, WHOLE_DEC, WHOLE_RADIUS);
        wholeNumsSeparByCombinedShouldBeParsed(WHOLE_RA, WHOLE_DEC, WHOLE_RADIUS);
    }

    public void wholeNumsSeparBySpacesShouldBeParsed(double expRA, double expDec, double expRadius) {
        assertEquals(new PositionInput(" 5 42 1"), expRA, expDec, expRadius);
        assertEquals(new PositionInput(" 5    42    1   "), expRA, expDec, expRadius);
        assertEquals(new PositionInput("5 42 1"), expRA, expDec, expRadius);
    }

    public void wholeNumsSeparByCommaShouldBeParsed(double expRA, double expDec, double expRadius) {
        assertEquals(new PositionInput("5,42,1"), expRA, expDec, expRadius);
    }

    public void wholeNumsSeparByCombinedShouldBeParsed(double expRA, double expDec, double expRadius) {
        assertEquals(new PositionInput("5, 42, 1"), expRA, expDec, expRadius);
        assertEquals(new PositionInput(" 5,42,1     "), expRA, expDec, expRadius);
        assertEquals(new PositionInput("5  ,   42  ,  1"), expRA, expDec, expRadius);
        assertEquals(new PositionInput(" 5,   42,   1   "), expRA, expDec, expRadius);
        assertEquals(new PositionInput(" 5  ,   42  ,  1  "), expRA, expDec, expRadius);
    }

    @Test
    public void validWholeNumbersWithoutRadius() {
        wholeNumsWithoutRadiusSeparByCombinedShouldBeParsed(WHOLE_RA, WHOLE_DEC, DEFAULT_RADIUS);
    }

    public void wholeNumsWithoutRadiusSeparByCombinedShouldBeParsed(double expRA, double expDec, double expRadius) {
        assertEquals(new PositionInput("5, 42"), expRA, expDec, expRadius);
        assertEquals(new PositionInput(" 5,42     "), expRA, expDec, expRadius);
        assertEquals(new PositionInput("5  ,   42  "), expRA, expDec, expRadius);
        assertEquals(new PositionInput(" 5,   42  "), expRA, expDec, expRadius);
        assertEquals(new PositionInput(" 5  ,   42    "), expRA, expDec, expRadius);
    }

    @Test
    public void validFractionalNumbersWithRadius() {
        fractNumsSeparBySpacesShouldBeParsed(FRACTIONAL_RA, FRACTIONAL_DEC, WHOLE_RADIUS);
        fractNumsSeparByCommaShouldBeParsed(FRACTIONAL_RA, FRACTIONAL_DEC, WHOLE_RADIUS);
        fractNumsSeparByCombinedShouldBeParsed(FRACTIONAL_RA, FRACTIONAL_DEC, WHOLE_RADIUS);
    }

    public void fractNumsSeparBySpacesShouldBeParsed(double expRA, double expDec, double expRadius) {
        assertEquals(new PositionInput(" 5.0 42.358 1"), expRA, expDec, expRadius);
        assertEquals(new PositionInput(" 5.0   42.358   1   "), expRA, expDec, expRadius);
        assertEquals(new PositionInput("5.0 42.358 1"), expRA, expDec, expRadius);
    }

    public void fractNumsSeparByCommaShouldBeParsed(double expRA, double expDec, double expRadius) {
        assertEquals(new PositionInput("5.0,42.358,1"), expRA, expDec, expRadius);
    }

    public void fractNumsSeparByCombinedShouldBeParsed(double expRA, double expDec, double expRadius) {
        assertEquals(new PositionInput("5.0, 42.358, 1"), expRA, expDec, expRadius);
        assertEquals(new PositionInput(" 5.0,42.358,1     "), expRA, expDec, expRadius);
        assertEquals(new PositionInput("5.0  ,   42.358  ,  1"), expRA, expDec, expRadius);
        assertEquals(new PositionInput(" 5.0,   42.358,   1   "), expRA, expDec, expRadius);
        assertEquals(new PositionInput(" 5.0  ,   42.358  ,  1  "), expRA, expDec, expRadius);
    }

    @Test
    public void validFractionalNumbersWithoutRadius() {
        fractNumsWithoutRadiusSeparByCombinedShouldBeParsed(FRACTIONAL_RA, FRACTIONAL_DEC, DEFAULT_RADIUS);
    }

    public void fractNumsWithoutRadiusSeparByCombinedShouldBeParsed(double expRA, double expDec, double expRadius) {
        assertEquals(new PositionInput("5.0, 42.358"), expRA, expDec, expRadius);
        assertEquals(new PositionInput(" 5.0,42.358     "), expRA, expDec, expRadius);
        assertEquals(new PositionInput("5.0  ,   42.358  "), expRA, expDec, expRadius);
        assertEquals(new PositionInput(" 5.0,   42.358   "), expRA, expDec, expRadius);
        assertEquals(new PositionInput(" 5.0  ,   42.358    "), expRA, expDec, expRadius);
    }

    @Test
    public void invalidFractionalNumbers() {
        assertThrowsIllegalArgumentException("5..0, 42.358");
        assertThrowsIllegalArgumentException("5., 42.358");
        assertThrowsIllegalArgumentException("5.0, 42.358.");
        assertThrowsIllegalArgumentException("--5.0, 42.358");
        assertThrowsIllegalArgumentException("--5.028.5, 42.358");
        assertThrowsIllegalArgumentException("5.0, 42.358 0.");
        assertThrowsIllegalArgumentException("5.0, 42.358, 0.");
        assertThrowsIllegalArgumentException("5.0, 42.358 0..");
        assertThrowsIllegalArgumentException(" -.5 , 42");
    }

    @Test
    public void invalidWholeNumbers() {
        assertThrowsIllegalArgumentException("--5, 42");
        assertThrowsIllegalArgumentException("  --5  42 ");
    }

    @Test
    public void invalidInput() {
        assertThrowsIllegalArgumentException("-");
        assertThrowsIllegalArgumentException(" -  ");
        assertThrowsIllegalArgumentException(" -.  ");
        assertThrowsIllegalArgumentException(" 5,, 42");
        assertThrowsIllegalArgumentException(" 5, , 42");
        assertThrowsIllegalArgumentException(" 5 , , 42");
        assertThrowsIllegalArgumentException(" -,5 , 42");
        assertThrowsIllegalArgumentException(" ,5 , 42");
        assertThrowsIllegalArgumentException(" 5 , 42,");
        assertThrowsIllegalArgumentException(" 5f 42");
        assertThrowsIllegalArgumentException("  *5.0 42 ");
        assertThrowsIllegalArgumentException("  +- ");
        assertThrowsIllegalArgumentException("  -+ ");
        assertThrowsIllegalArgumentException("  -+5 42 ");
        assertThrowsIllegalArgumentException("  a  b ");
        assertThrowsIllegalArgumentException("  a  b  c ");
        assertThrowsIllegalArgumentException("  0xff  0x2d   ");
        assertThrowsIllegalArgumentException("  0xff  0x2d  0x3e ");
    }

    @Test
    public void notEnoughNumbers() {
        assertThrowsIllegalArgumentException("");
        assertThrowsIllegalArgumentException("     ");
        assertThrowsIllegalArgumentException(" 42");
        assertThrowsIllegalArgumentException(" -42");
        assertThrowsIllegalArgumentException("  42.0 ");
        assertThrowsIllegalArgumentException("  -42.0 ");
    }

    @Test
    public void tooManyNumbers() {
        assertThrowsIllegalArgumentException(" 42 42  42   42");
        assertThrowsIllegalArgumentException(" -42 42  42.0 5");
        assertThrowsIllegalArgumentException("  42.0 1 1  111   1   1");
        assertThrowsIllegalArgumentException("  -42.0  -273.15 0  0  0  0    0   ");
    }
}
