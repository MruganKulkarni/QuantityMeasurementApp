package com.bridgelabz;

import java.util.Objects;

public class Length {

    private final double value;
    private final LengthUnit unit;

    public enum LengthUnit {

        FEET(1.0),
        INCHES(1.0 / 12.0),
        YARDS(3.0),
        CENTIMETERS(0.0328084);

        final double toFeetFactor;

        LengthUnit(double factor) {
            this.toFeetFactor = factor;
        }
    }

    public Length(double value, LengthUnit unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Invalid value");
        }

        this.value = value;
        this.unit = unit;
    }

    private double toFeet() {
        return value * unit.toFeetFactor;
    }

    public Length convertTo(LengthUnit targetUnit) {
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        double feetValue = toFeet();
        double convertedValue = feetValue / targetUnit.toFeetFactor;

        return new Length(convertedValue, targetUnit);
    }

    public static double convert(double value, LengthUnit source, LengthUnit target) {
        if (source == null || target == null) {
            throw new IllegalArgumentException("Units cannot be null");
        }
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Invalid numeric value");
        }

        double valueInFeet = value * source.toFeetFactor;
        return valueInFeet / target.toFeetFactor;
    }

    public Length add(Length other) {
        if (other == null) {
            throw new IllegalArgumentException("Cannot add null length");
        }

        double sumFeet = this.toFeet() + other.toFeet();
        double resultValue = sumFeet / this.unit.toFeetFactor;

        return new Length(resultValue, this.unit);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Length other)) return false;

        double epsilon = 1e-6;
        return Math.abs(this.toFeet() - other.toFeet()) < epsilon;
    }

    @Override
    public int hashCode() {
        return Objects.hash(toFeet());
    }

    @Override
    public String toString() {
        return value + " " + unit;
    }
}