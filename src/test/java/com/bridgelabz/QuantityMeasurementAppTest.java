package com.bridgelabz;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    // ---------- UC3/UC4/UC5 tests ----------
    @Test
    void testEquality_FeetToFeet() {
        assertEquals(
                new Length(1, Length.LengthUnit.FEET),
                new Length(1, Length.LengthUnit.FEET)
        );
    }

    @Test
    void testEquality_FeetToInches() {
        assertEquals(
                new Length(1, Length.LengthUnit.FEET),
                new Length(12, Length.LengthUnit.INCHES)
        );
    }

    @Test
    void testConversion_FeetToInches() {
        assertEquals(12,
                Length.convert(1, Length.LengthUnit.FEET, Length.LengthUnit.INCHES),
                0.01);
    }

    // ---------- UC6 Addition ----------
    @Test
    void testAddition_SameUnit() {
        Length l1 = new Length(1, Length.LengthUnit.FEET);
        Length l2 = new Length(2, Length.LengthUnit.FEET);
        assertEquals(new Length(3, Length.LengthUnit.FEET), l1.add(l2));
    }

    @Test
    void testAddition_CrossUnit() {
        Length l1 = new Length(1, Length.LengthUnit.FEET);
        Length l2 = new Length(12, Length.LengthUnit.INCHES);
        assertEquals(new Length(2, Length.LengthUnit.FEET), l1.add(l2));
    }

    // ---------- ⭐ UC7 Explicit Target Unit ----------
    @Test
    void testAddition_TargetFeet() {
        Length l1 = new Length(1, Length.LengthUnit.FEET);
        Length l2 = new Length(12, Length.LengthUnit.INCHES);

        Length result = l1.add(l2, Length.LengthUnit.FEET);
        assertEquals(new Length(2, Length.LengthUnit.FEET), result);
    }

    @Test
    void testAddition_TargetInches() {
        Length l1 = new Length(1, Length.LengthUnit.FEET);
        Length l2 = new Length(12, Length.LengthUnit.INCHES);

        Length result = l1.add(l2, Length.LengthUnit.INCHES);
        assertEquals(new Length(24, Length.LengthUnit.INCHES), result);
    }

    @Test
    void testAddition_TargetYards() {
        Length l1 = new Length(1, Length.LengthUnit.FEET);
        Length l2 = new Length(12, Length.LengthUnit.INCHES);

        Length result = l1.add(l2, Length.LengthUnit.YARDS);

        assertEquals(
                new Length(2, Length.LengthUnit.FEET),
                result.convertTo(Length.LengthUnit.FEET)
        );
    }

    @Test
    void testAddition_TargetNull() {
        Length l1 = new Length(1, Length.LengthUnit.FEET);
        Length l2 = new Length(1, Length.LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class,
                () -> l1.add(l2, null));
    }
}