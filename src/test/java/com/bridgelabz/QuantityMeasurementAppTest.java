package com.bridgelabz;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {

    // ---------- LENGTH TESTS ----------

    @Test
    void testLengthEquality() {
        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12.0, LengthUnit.INCHES);

        assertEquals(q1, q2);
    }

    @Test
    void testLengthConversion() {
        Quantity<LengthUnit> q = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> result = q.convertTo(LengthUnit.INCHES);

        assertEquals(new Quantity<>(12.0, LengthUnit.INCHES), result);
    }

    @Test
    void testLengthAddition_DefaultUnit() {
        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12.0, LengthUnit.INCHES);

        assertEquals(new Quantity<>(2.0, LengthUnit.FEET), q1.add(q2));
    }

    @Test
    void testLengthAddition_TargetYards() {
        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12.0, LengthUnit.INCHES);

        assertEquals(new Quantity<>(0.6666, LengthUnit.YARDS), q1.add(q2, LengthUnit.YARDS));
    }

    // ---------- WEIGHT TESTS ----------

    @Test
    void testWeightEquality() {
        Quantity<WeightUnit> q1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> q2 = new Quantity<>(1000.0, WeightUnit.GRAM);

        assertEquals(q1, q2);
    }

    @Test
    void testWeightConversion() {
        Quantity<WeightUnit> q = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> result = q.convertTo(WeightUnit.GRAM);

        assertEquals(new Quantity<>(1000.0, WeightUnit.GRAM), result);
    }

    @Test
    void testWeightAddition_DefaultUnit() {
        Quantity<WeightUnit> q1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> q2 = new Quantity<>(1000.0, WeightUnit.GRAM);

        assertEquals(new Quantity<>(2.0, WeightUnit.KILOGRAM), q1.add(q2));
    }

    @Test
    void testWeightAddition_TargetGram() {
        Quantity<WeightUnit> q1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> q2 = new Quantity<>(1000.0, WeightUnit.GRAM);

        assertEquals(new Quantity<>(2000.0, WeightUnit.GRAM), q1.add(q2, WeightUnit.GRAM));
    }

    // ---------- CROSS CATEGORY SAFETY ----------

    @Test
    void testLengthVsWeight_NotEqual() {
        Quantity<LengthUnit> length = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<WeightUnit> weight = new Quantity<>(1.0, WeightUnit.KILOGRAM);

        assertNotEquals(length, weight);
    }
}