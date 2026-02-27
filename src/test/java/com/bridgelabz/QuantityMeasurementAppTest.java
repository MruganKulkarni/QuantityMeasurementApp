package com.bridgelabz;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.bridgelabz.QuantityMeasurementApp.Feet;
import com.bridgelabz.QuantityMeasurementApp.Inches;

public class QuantityMeasurementAppTest {

// ===================== FEET TESTS =====================

    @Test
    void givenSameFeetValues_shouldReturnTrue() {
        Feet f1 = new Feet(1.0);
        Feet f2 = new Feet(1.0);
        assertTrue(f1.equals(f2));
    }

    @Test
    void givenDifferentFeetValues_shouldReturnFalse() {
        Feet f1 = new Feet(1.0);
        Feet f2 = new Feet(2.0);
        assertFalse(f1.equals(f2));
    }

    @Test
    void givenFeetAndNull_shouldReturnFalse() {
        Feet f1 = new Feet(1.0);
        assertFalse(f1.equals(null));
    }

    @Test
    void givenFeetAndDifferentType_shouldReturnFalse() {
        Feet f1 = new Feet(1.0);
        assertFalse(f1.equals("1.0"));
    }

    @Test
    void givenSameFeetReference_shouldReturnTrue() {
        Feet f1 = new Feet(1.0);
        assertTrue(f1.equals(f1));
    }

// ===================== INCHES TESTS =====================

    @Test
    void givenSameInchesValues_shouldReturnTrue() {
        Inches i1 = new Inches(1.0);
        Inches i2 = new Inches(1.0);
        assertTrue(i1.equals(i2));
    }

    @Test
    void givenDifferentInchesValues_shouldReturnFalse() {
        Inches i1 = new Inches(1.0);
        Inches i2 = new Inches(2.0);
        assertFalse(i1.equals(i2));
    }

    @Test
    void givenInchesAndNull_shouldReturnFalse() {
        Inches i1 = new Inches(1.0);
        assertFalse(i1.equals(null));
    }

    @Test
    void givenInchesAndDifferentType_shouldReturnFalse() {
        Inches i1 = new Inches(1.0);
        assertFalse(i1.equals("1.0"));
    }

    @Test
    void givenSameInchesReference_shouldReturnTrue() {
        Inches i1 = new Inches(1.0);
        assertTrue(i1.equals(i1));
    }

}