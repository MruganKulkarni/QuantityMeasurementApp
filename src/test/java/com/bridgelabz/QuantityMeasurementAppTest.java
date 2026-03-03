package com.bridgelabz;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {

    private static final double EPSILON = 0.0001;

    // =====================================================
    // UC11 - EXISTING VOLUME TESTS (UNCHANGED)
    // =====================================================

    @Test
    void testEquality_LitreToMillilitre() {
        Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        assertTrue(v1.equals(v2));
    }

    @Test
    void testConversion_GallonToLitre() {
        Quantity<VolumeUnit> v = new Quantity<>(1.0, VolumeUnit.GALLON);
        assertEquals(3.78541, v.convertTo(VolumeUnit.LITRE).getValue(), EPSILON);
    }

    @Test
    void testAddition_LitrePlusMillilitre() {
        Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        assertEquals(2.0, v1.add(v2).getValue(), EPSILON);
    }

    // =====================================================
    // UC12 - SUBTRACTION TESTS
    // =====================================================

    @Test
    void testSubtraction_SameUnit() {
        Quantity<LengthUnit> l1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> l2 = new Quantity<>(5.0, LengthUnit.FEET);
        assertEquals(5.0, l1.subtract(l2).getValue(), EPSILON);
    }

    @Test
    void testSubtraction_CrossUnit() {
        Quantity<LengthUnit> l1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> l2 = new Quantity<>(6.0, LengthUnit.INCHES);
        assertEquals(9.5, l1.subtract(l2).getValue(), EPSILON);
    }

    @Test
    void testSubtraction_ExplicitTargetUnit() {
        Quantity<LengthUnit> l1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> l2 = new Quantity<>(6.0, LengthUnit.INCHES);
        assertEquals(114.0, l1.subtract(l2, LengthUnit.INCHES).getValue(), EPSILON);
    }

    @Test
    void testSubtraction_ResultNegative() {
        Quantity<WeightUnit> w1 = new Quantity<>(2.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> w2 = new Quantity<>(5.0, WeightUnit.KILOGRAM);
        assertEquals(-3.0, w1.subtract(w2).getValue(), EPSILON);
    }

    @Test
    void testSubtraction_ResultZero() {
        Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        assertEquals(0.0, v1.subtract(v2).getValue(), EPSILON);
    }

    @Test
    void testSubtraction_NullOperand() {
        Quantity<LengthUnit> l1 = new Quantity<>(10.0, LengthUnit.FEET);
        assertThrows(IllegalArgumentException.class, () -> l1.subtract(null));
    }

    @Test
    void testSubtraction_CrossCategory() {
        Quantity<LengthUnit> l = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<WeightUnit> w = new Quantity<>(5.0, WeightUnit.KILOGRAM);
        assertThrows(IllegalArgumentException.class, () -> l.subtract((Quantity) w));
    }

    @Test
    void testSubtraction_NonCommutative() {
        Quantity<LengthUnit> l1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> l2 = new Quantity<>(5.0, LengthUnit.FEET);

        assertEquals(5.0, l1.subtract(l2).getValue(), EPSILON);
        assertEquals(-5.0, l2.subtract(l1).getValue(), EPSILON);
    }

    @Test
    void testSubtraction_Immutability() {
        Quantity<LengthUnit> l1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> l2 = new Quantity<>(5.0, LengthUnit.FEET);

        l1.subtract(l2);

        assertEquals(10.0, l1.getValue(), EPSILON);
        assertEquals(5.0, l2.getValue(), EPSILON);
    }

    // =====================================================
    // UC12 - DIVISION TESTS
    // =====================================================

    @Test
    void testDivision_SameUnit() {
        Quantity<LengthUnit> l1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> l2 = new Quantity<>(2.0, LengthUnit.FEET);
        assertEquals(5.0, l1.divide(l2), EPSILON);
    }

    @Test
    void testDivision_CrossUnit() {
        Quantity<LengthUnit> l1 = new Quantity<>(24.0, LengthUnit.INCHES);
        Quantity<LengthUnit> l2 = new Quantity<>(2.0, LengthUnit.FEET);
        assertEquals(1.0, l1.divide(l2), EPSILON);
    }

    @Test
    void testDivision_RatioLessThanOne() {
        Quantity<VolumeUnit> v1 = new Quantity<>(5.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(10.0, VolumeUnit.LITRE);
        assertEquals(0.5, v1.divide(v2), EPSILON);
    }

    @Test
    void testDivision_NonCommutative() {
        Quantity<WeightUnit> w1 = new Quantity<>(10.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> w2 = new Quantity<>(5.0, WeightUnit.KILOGRAM);

        assertEquals(2.0, w1.divide(w2), EPSILON);
        assertEquals(0.5, w2.divide(w1), EPSILON);
    }

    @Test
    void testDivision_ByZero() {
        Quantity<LengthUnit> l1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> l2 = new Quantity<>(0.0, LengthUnit.FEET);

        assertThrows(ArithmeticException.class, () -> l1.divide(l2));
    }

    @Test
    void testDivision_CrossCategory() {
        Quantity<LengthUnit> l = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<WeightUnit> w = new Quantity<>(5.0, WeightUnit.KILOGRAM);

        assertThrows(IllegalArgumentException.class, () -> l.divide((Quantity) w));
    }

    @Test
    void testDivision_Immutability() {
        Quantity<LengthUnit> l1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> l2 = new Quantity<>(2.0, LengthUnit.FEET);

        l1.divide(l2);

        assertEquals(10.0, l1.getValue(), EPSILON);
        assertEquals(2.0, l2.getValue(), EPSILON);
    }
}