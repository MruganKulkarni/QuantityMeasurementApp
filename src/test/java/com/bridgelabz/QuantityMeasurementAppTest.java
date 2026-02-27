package com.bridgelabz;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.bridgelabz.QuantityMeasurementApp.Feet;

public class QuantityMeasurementAppTest {

    @Test
    public void givenSameFeetValues_shouldReturnTrue() {
        Feet feet1 = new Feet(1.0);
        Feet feet2 = new Feet(1.0);
        assertTrue(feet1.equals(feet2));
    }

    @Test
    public void givenDifferentFeetValues_shouldReturnFalse() {
        Feet feet1 = new Feet(1.0);
        Feet feet2 = new Feet(2.0);
        assertFalse(feet1.equals(feet2));
    }

    @Test
    public void givenNullComparison_shouldReturnFalse() {
        Feet feet = new Feet(1.0);
        assertFalse(feet.equals(null));
    }

    @Test
    public void givenDifferentClassComparison_shouldReturnFalse() {
        Feet feet = new Feet(1.0);
        String other = "1.0";
        assertFalse(feet.equals(other));
    }

    @Test
    public void givenSameReference_shouldReturnTrue() {
        Feet feet = new Feet(1.0);
        assertTrue(feet.equals(feet));
    }
}