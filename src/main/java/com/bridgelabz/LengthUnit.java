package com.bridgelabz;

/**
 * Standalone LengthUnit enum (UC8 Refactor)
 * Responsible ONLY for unit conversions.
 * Base unit = FEET
 */
public enum LengthUnit {

    FEET(1.0),
    INCHES(1.0 / 12.0),
    YARDS(3.0),
    CENTIMETERS(1.0 / 30.48);

    private final double toFeetFactor;

    LengthUnit(double toFeetFactor) {
        this.toFeetFactor = toFeetFactor;
    }

    public double getConversionFactor() {
        return toFeetFactor;
    }

    /**
     * Convert value in THIS unit → FEET (base unit)
     */
    public double convertToBaseUnit(double value) {
        return value * toFeetFactor;
    }

    /**
     * Convert FEET (base unit) → THIS unit
     */
    public double convertFromBaseUnit(double baseValue) {
        return baseValue / toFeetFactor;
    }
}