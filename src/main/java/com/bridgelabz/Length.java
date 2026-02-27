package com.bridgelabz;

/**
 * Immutable value object representing a length measurement.
 * All conversions normalize to FEET as the base unit.
 */
public final class Length {

    private static final double EPSILON = 1e-6;

    private final double value;
    private final LengthUnit unit;

    public Length(double value, LengthUnit unit) {
        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Value must be finite");
        if (unit == null)
            throw new IllegalArgumentException("Unit cannot be null");

        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public LengthUnit getUnit() {
        return unit;
    }

    /* -------------------- STATIC CONVERSION API -------------------- */

    public static double convert(double value,
                                 LengthUnit source,
                                 LengthUnit target) {

        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Value must be finite");

        if (source == null || target == null)
            throw new IllegalArgumentException("Units cannot be null");

        double valueInFeet = source.toFeet(value);
        return valueInFeet / target.toFeet(1.0);
    }

    /* -------------------- INSTANCE CONVERSION -------------------- */

    public Length convertTo(LengthUnit targetUnit) {

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double convertedValue = convert(this.value, this.unit, targetUnit);

        return new Length(convertedValue, targetUnit);
    }

    /* -------------------- EQUALITY -------------------- */

    private double toBaseFeet() {
        return unit.toFeet(value);
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;

        if (!(obj instanceof Length other)) return false;

        return Math.abs(this.toBaseFeet() - other.toBaseFeet()) < EPSILON;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(toBaseFeet());
    }

    @Override
    public String toString() {
        return String.format("%.6f %s", value, unit);
    }
}