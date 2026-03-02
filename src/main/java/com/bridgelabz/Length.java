package com.bridgelabz;

import java.util.Objects;

/**
 * UC8 Refactored Length Class
 * Delegates all conversion logic to LengthUnit enum.
 */
public class Length {

    private final double value;
    private final LengthUnit unit;

    private static final double EPSILON = 1e-6;

    public Length(double value, LengthUnit unit) {
        if (unit == null)
            throw new IllegalArgumentException("Unit cannot be null");

        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Invalid numeric value");

        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public LengthUnit getUnit() {
        return unit;
    }

    /**
     * Convert this length to another unit
     */
    public Length convertTo(LengthUnit targetUnit) {
        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double baseFeet = unit.convertToBaseUnit(value);
        double converted = targetUnit.convertFromBaseUnit(baseFeet);

        return new Length(round(converted), targetUnit);
    }

    /**
     * UC6 — Add and return in THIS unit
     */
    public Length add(Length other) {
        return add(other, this.unit);
    }

    /**
     * UC7 — Add with explicit target unit
     */
    public Length add(Length other, LengthUnit targetUnit) {
        if (other == null)
            throw new IllegalArgumentException("Length cannot be null");

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        double sumBase = base1 + base2;

        double result = targetUnit.convertFromBaseUnit(sumBase);

        return new Length(round(result), targetUnit);
    }

    /**
     * Equality based on physical length
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Length)) return false;

        Length other = (Length) obj;

        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        return Math.abs(base1 - base2) < EPSILON;
    }

    @Override
    public int hashCode() {
        double base = unit.convertToBaseUnit(value);
        return Objects.hash(round(base));
    }

    @Override
    public String toString() {
        return round(value) + " " + unit;
    }

    /**
     * Round helper — ensures test stability
     */
    private double round(double val) {
        return Math.floor(val * 10000.0) / 10000.0;
    }
}