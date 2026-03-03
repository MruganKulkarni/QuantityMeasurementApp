package com.bridgelabz;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {

    private static final double EPSILON = 0.0001;

    // =======================
    // Volume Equality Tests
    // =======================

    @Test
    void testEquality_LitreToLitre_SameValue() {
        Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(1.0, VolumeUnit.LITRE);
        assertTrue(v1.equals(v2));
    }

    @Test
    void testEquality_LitreToMillilitre() {
        Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        assertTrue(v1.equals(v2));
    }

    @Test
    void testEquality_LitreToGallon() {
        Quantity<VolumeUnit> v1 = new Quantity<>(3.78541, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(1.0, VolumeUnit.GALLON);
        assertTrue(v1.equals(v2));
    }

    @Test
    void testEquality_VolumeVsLength() {
        Quantity<VolumeUnit> volume = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<LengthUnit> length = new Quantity<>(1.0, LengthUnit.FEET);
        assertFalse(volume.equals(length));
    }

    // =======================
    // Volume Conversion Tests
    // =======================

    @Test
    void testConversion_LitreToMillilitre() {
        Quantity<VolumeUnit> v = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> converted = v.convertTo(VolumeUnit.MILLILITRE);
        assertEquals(1000.0, converted.getValue(), EPSILON);
    }

    @Test
    void testConversion_GallonToLitre() {
        Quantity<VolumeUnit> v = new Quantity<>(1.0, VolumeUnit.GALLON);
        Quantity<VolumeUnit> converted = v.convertTo(VolumeUnit.LITRE);
        assertEquals(3.78541, converted.getValue(), EPSILON);
    }

    @Test
    void testConversion_MillilitreToGallon() {
        Quantity<VolumeUnit> v = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> converted = v.convertTo(VolumeUnit.GALLON);
        assertEquals(0.264172, converted.getValue(), EPSILON);
    }

    // =======================
    // Volume Addition Tests
    // =======================

    @Test
    void testAddition_LitrePlusMillilitre() {
        Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> result = v1.add(v2);
        assertEquals(2.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_ExplicitTargetUnit_Gallon() {
        Quantity<VolumeUnit> v1 = new Quantity<>(3.78541, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(3.78541, VolumeUnit.LITRE);
        Quantity<VolumeUnit> result = v1.add(v2, VolumeUnit.GALLON);
        assertEquals(2.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_WithNegativeValues() {
        Quantity<VolumeUnit> v1 = new Quantity<>(5.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(-2000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> result = v1.add(v2);
        assertEquals(3.0, result.getValue(), EPSILON);
    }
}