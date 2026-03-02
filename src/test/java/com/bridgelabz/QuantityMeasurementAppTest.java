package com.bridgelabz;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {

    @Test
    void testEquality_FeetToFeet() {
        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(1.0, Length.LengthUnit.FEET);
        assertEquals(l1, l2);
    }

    @Test
    void testEquality_FeetToInches() {
        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(12.0, Length.LengthUnit.INCHES);
        assertEquals(l1, l2);
    }

    @Test
    void testConversion_FeetToInches() {
        double result = Length.convert(1.0, Length.LengthUnit.FEET, Length.LengthUnit.INCHES);
        assertEquals(12.0, result, 0.0001);
    }

    @Test
    void testConversion_YardsToFeet() {
        double result = Length.convert(1.0, Length.LengthUnit.YARDS, Length.LengthUnit.FEET);
        assertEquals(3.0, result, 0.0001);
    }

    @Test
    void testAddition_SameUnit_FeetPlusFeet() {
        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(2.0, Length.LengthUnit.FEET);
        assertEquals(new Length(3.0, Length.LengthUnit.FEET), l1.add(l2));
    }

    @Test
    void testAddition_CrossUnit_FeetPlusInches() {
        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(12.0, Length.LengthUnit.INCHES);
        assertEquals(new Length(2.0, Length.LengthUnit.FEET), l1.add(l2));
    }

    @Test
    void testAddition_Commutativity() {
        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(12.0, Length.LengthUnit.INCHES);

        assertEquals(l1.add(l2), l2.add(l1).convertTo(Length.LengthUnit.FEET));
    }

    @Test
    void testAddition_WithZero() {
        Length l1 = new Length(5.0, Length.LengthUnit.FEET);
        Length l2 = new Length(0.0, Length.LengthUnit.INCHES);

        assertEquals(new Length(5.0, Length.LengthUnit.FEET), l1.add(l2));
    }

    @Test
    void testAddition_NullSecondOperand() {
        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        assertThrows(IllegalArgumentException.class, () -> l1.add(null));
    }
}