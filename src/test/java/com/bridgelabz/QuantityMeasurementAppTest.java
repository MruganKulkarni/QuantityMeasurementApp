package com.bridgelabz;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    private static final double EPS = 1e-6;

    @Test
    void testConversion_FeetToInches() {
        assertEquals(12.0,
                Length.convert(1.0, LengthUnit.FEET, LengthUnit.INCH),
                EPS);
    }

    @Test
    void testConversion_InchesToFeet() {
        assertEquals(2.0,
                Length.convert(24.0, LengthUnit.INCH, LengthUnit.FEET),
                EPS);
    }

    @Test
    void testConversion_YardsToInches() {
        assertEquals(36.0,
                Length.convert(1.0, LengthUnit.YARDS, LengthUnit.INCH),
                EPS);
    }

    @Test
    void testConversion_CentimetersToInches() {
        assertEquals(1.0,
                Length.convert(2.54, LengthUnit.CENTIMETERS, LengthUnit.INCH),
                EPS);
    }

    @Test
    void testRoundTripConversion() {
        double original = 5.0;
        double converted = Length.convert(original,
                LengthUnit.FEET,
                LengthUnit.INCH);

        double back = Length.convert(converted,
                LengthUnit.INCH,
                LengthUnit.FEET);

        assertEquals(original, back, EPS);
    }

    @Test
    void testZeroConversion() {
        assertEquals(0.0,
                Length.convert(0.0, LengthUnit.FEET, LengthUnit.INCH),
                EPS);
    }

    @Test
    void testNegativeConversion() {
        assertEquals(-12.0,
                Length.convert(-1.0, LengthUnit.FEET, LengthUnit.INCH),
                EPS);
    }

    @Test
    void testSameUnitConversion() {
        assertEquals(5.0,
                Length.convert(5.0, LengthUnit.FEET, LengthUnit.FEET),
                EPS);
    }

    @Test
    void testInvalidUnitThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> Length.convert(1.0, null, LengthUnit.FEET));
    }

    @Test
    void testNaNThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> Length.convert(Double.NaN,
                        LengthUnit.FEET,
                        LengthUnit.INCH));
    }

    @Test
    void testEqualityStillWorks() {
        Length feet = new Length(1.0, LengthUnit.FEET);
        Length inches = new Length(12.0, LengthUnit.INCH);

        assertTrue(feet.equals(inches));
    }
}