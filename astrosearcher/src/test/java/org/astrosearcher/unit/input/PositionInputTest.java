package org.astrosearcher.unit.input;

import org.astrosearcher.classes.PositionInput;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PositionInputTest {
    private final double DEFAULT_RADIUS = 0.2;
    private final boolean FAIL = false;

    private void assertEquals(PositionInput pi, double expRa, double expDec, double expRadius) {
        Assertions.assertEquals(expRa, pi.getRa());
        Assertions.assertEquals(expDec, pi.getDec());
        Assertions.assertEquals(expRadius, pi.getRadius());
    }

    private void assertEquals(PositionInput pi, boolean expIsSuccessful) {
        Assertions.assertEquals(expIsSuccessful, pi.isSuccessful());
    }

    @Test
    public void validWholeNumbersWithRadius() {
        double expRA     = 5;
        double expDec    = 42;
        double expRadius = 1;

        wholeNumsSeparBySpacesShouldBeParsed(expRA, expDec, expRadius);
        wholeNumsSeparByCommaShouldBeParsed(expRA, expDec, expRadius);
        wholeNumsSeparByCombinedShouldBeParsed(expRA, expDec, expRadius);
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
        double expRA     = 5;
        double expDec    = 42;
        double expRadius = 0.2;

        wholeNumsWithoutRadiusSeparByCombinedShouldBeParsed(expRA, expDec, expRadius);
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
        double expRA     = 5.0;
        double expDec    = 42.358;
        double expRadius = 1;

        fractNumsSeparBySpacesShouldBeParsed(expRA, expDec, expRadius);
        fractNumsSeparByCommaShouldBeParsed(expRA, expDec, expRadius);
        fractNumsSeparByCombinedShouldBeParsed(expRA, expDec, expRadius);
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
        double expRA     = 5.0;
        double expDec    = 42.358;
        double expRadius = 0.2;

        fractNumsWithoutRadiusSeparByCombinedShouldBeParsed(expRA, expDec, expRadius);
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
        assertEquals(new PositionInput("5..0, 42.358"), FAIL);
        assertEquals(new PositionInput("5., 42.358"), FAIL);
        assertEquals(new PositionInput("5.0, 42.358."), FAIL);
        assertEquals(new PositionInput("--5.0, 42.358"), FAIL);
        assertEquals(new PositionInput("--5.028.5, 42.358"), FAIL);
        assertEquals(new PositionInput("5.0, 42.358 0."), FAIL);
        assertEquals(new PositionInput("5.0, 42.358, 0."), FAIL);
        assertEquals(new PositionInput("5.0, 42.358 0.."), FAIL);
        assertEquals(new PositionInput(" -.5 , 42"), FAIL);
    }

    @Test
    public void invalidWholeNumbers() {
        assertEquals(new PositionInput("--5, 42"), FAIL);
        assertEquals(new PositionInput("  --5  42 "), FAIL);
    }

    @Test
    public void invalidInput() {
        assertEquals(new PositionInput("-"), FAIL);
        assertEquals(new PositionInput(" -  "), FAIL);
        assertEquals(new PositionInput(" -.  "), FAIL);
        assertEquals(new PositionInput(" 5,, 42"), FAIL);
        assertEquals(new PositionInput(" 5, , 42"), FAIL);
        assertEquals(new PositionInput(" 5 , , 42"), FAIL);
        assertEquals(new PositionInput(" -,5 , 42"), FAIL);
        assertEquals(new PositionInput(" ,5 , 42"), FAIL);
        assertEquals(new PositionInput(" 5 , 42,"), FAIL);
        assertEquals(new PositionInput(" 5f 42"), FAIL);
        assertEquals(new PositionInput("  *5.0 42 "), FAIL);
        assertEquals(new PositionInput("  +- "), FAIL);
        assertEquals(new PositionInput("  -+ "), FAIL);
        assertEquals(new PositionInput("  -+5 42 "), FAIL);
        assertEquals(new PositionInput("  a  b "), FAIL);
        assertEquals(new PositionInput("  a  b  c "), FAIL);
        assertEquals(new PositionInput("  0xff  0x2d   "), FAIL);
        assertEquals(new PositionInput("  0xff  0x2d  0x3e "), FAIL);
    }

    @Test
    public void notEnoughNumbers() {
        assertEquals(new PositionInput(""), FAIL);
        assertEquals(new PositionInput("     "), FAIL);
        assertEquals(new PositionInput(" 42"), FAIL);
        assertEquals(new PositionInput(" -42"), FAIL);
        assertEquals(new PositionInput("  42.0 "), FAIL);
        assertEquals(new PositionInput("  -42.0 "), FAIL);
    }

    @Test
    public void tooManyNumbers() {
        assertEquals(new PositionInput(" 42 42  42   42"), FAIL);
        assertEquals(new PositionInput(" -42 42  42.0 5"), FAIL);
        assertEquals(new PositionInput("  42.0 1 1  111   1   1"), FAIL);
        assertEquals(new PositionInput("  -42.0  -273.15 0  0  0  0    0   "), FAIL);
    }
}
