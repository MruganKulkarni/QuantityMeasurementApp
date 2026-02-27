package com.bridgelabz;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    @Test
    void testEquality_FeetToFeet_SameValue() {
        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(1.0, LengthUnit.FEET);
        assertTrue(l1.equals(l2));
    }

    @Test
    void testEquality_InchToInch_SameValue() {
        Length l1 = new Length(1.0, LengthUnit.INCH);
        Length l2 = new Length(1.0, LengthUnit.INCH);
        assertTrue(l1.equals(l2));
    }

    @Test
    void testEquality_FeetToInch_Equivalent() {
        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(12.0, LengthUnit.INCH);
        assertTrue(l1.equals(l2));
    }

    @Test
    void testEquality_InchToFeet_Equivalent() {
        Length l1 = new Length(12.0, LengthUnit.INCH);
        Length l2 = new Length(1.0, LengthUnit.FEET);
        assertTrue(l1.equals(l2));
    }

    @Test
    void testEquality_Feet_DifferentValue() {
        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(2.0, LengthUnit.FEET);
        assertFalse(l1.equals(l2));
    }

    @Test
    void testEquality_Inch_DifferentValue() {
        Length l1 = new Length(1.0, LengthUnit.INCH);
        Length l2 = new Length(2.0, LengthUnit.INCH);
        assertFalse(l1.equals(l2));
    }

    @Test
    void testEquality_NullComparison() {
        Length l1 = new Length(1.0, LengthUnit.FEET);
        assertFalse(l1.equals(null));
    }

    @Test
    void testEquality_SameReference() {
        Length l1 = new Length(1.0, LengthUnit.FEET);
        assertTrue(l1.equals(l1));
    }

    @Test
    void testInvalidUnit() {
        assertThrows(IllegalArgumentException.class, () ->
                new Length(1.0, null)
        );
    }
}