package com.bridgelabz;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {

    @Test
    void testEquality_FeetToFeet() {
        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(1.0, LengthUnit.FEET);
        assertEquals(l1, l2);
    }

    @Test
    void testEquality_FeetToInches() {
        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(12.0, LengthUnit.INCHES);
        assertEquals(l1, l2);
    }

    @Test
    void testConversion_FeetToInches() {
        Length l = new Length(1.0, LengthUnit.FEET);
        Length result = l.convertTo(LengthUnit.INCHES);
        assertEquals(new Length(12.0, LengthUnit.INCHES), result);
    }

    @Test
    void testConversion_YardsToFeet() {
        Length l = new Length(1.0, LengthUnit.YARDS);
        Length result = l.convertTo(LengthUnit.FEET);
        assertEquals(new Length(3.0, LengthUnit.FEET), result);
    }

    @Test
    void testAddition_SameUnit() {
        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(2.0, LengthUnit.FEET);
        assertEquals(new Length(3.0, LengthUnit.FEET), l1.add(l2));
    }

    @Test
    void testAddition_CrossUnit() {
        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(12.0, LengthUnit.INCHES);
        assertEquals(new Length(2.0, LengthUnit.FEET), l1.add(l2));
    }

    @Test
    void testAddition_TargetUnit() {
        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(12.0, LengthUnit.INCHES);
        Length result = l1.add(l2, LengthUnit.INCHES);
        assertEquals(new Length(24.0, LengthUnit.INCHES), result);
    }

    @Test
    void testAddition_TargetYards() {
        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(12.0, LengthUnit.INCHES);
        Length result = l1.add(l2, LengthUnit.YARDS);
        assertEquals(new Length(0.6666, LengthUnit.YARDS), result);
    }

    @Test
    void testNullUnitThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> new Length(1.0, null));
    }
}