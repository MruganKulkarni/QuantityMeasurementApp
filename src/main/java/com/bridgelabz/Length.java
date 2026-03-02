package com.bridgelabz;

import java.util.Objects;

public class Length {

    private final double value;
    private final LengthUnit unit;

    private static final double EPSILON = 0.0001;

    public enum LengthUnit {
        FEET(1.0),
        INCHES(1.0 / 12.0),
        YARDS(3.0),
        CENTIMETERS(0.0328084);

        private final double toFeetFactor;

        LengthUnit(double toFeetFactor) {
            this.toFeetFactor = toFeetFactor;
        }

        public double toFeet(double value) {
            return value * toFeetFactor;
        }

        public double fromFeet(double feetValue) {
            return feetValue / toFeetFactor;
        }
    }

    public Length(double value, LengthUnit unit) {
        if (!Double.isFinite(value) || unit == null) {
            throw new IllegalArgumentException("Invalid length");
        }
        this.value = value;
        this.unit = unit;
    }

    private double toFeet() {
        return unit.toFeet(value);
    }

    public Length convertTo(LengthUnit targetUnit) {
        if (targetUnit == null) throw new IllegalArgumentException();
        double feet = this.toFeet();
        double converted = targetUnit.fromFeet(feet);
        return new Length(converted, targetUnit);
    }

    public static double convert(double value, LengthUnit from, LengthUnit to) {
        if (!Double.isFinite(value) || from == null || to == null) {
            throw new IllegalArgumentException();
        }
        double feet = from.toFeet(value);
        return to.fromFeet(feet);
    }

    // UC6 — Add (result in first operand unit)
    public Length add(Length other) {
        if (other == null) throw new IllegalArgumentException();
        return add(other, this.unit);
    }

    // ⭐ UC7 — Add with explicit target unit
    public Length add(Length other, LengthUnit targetUnit) {
        if (other == null || targetUnit == null) {
            throw new IllegalArgumentException();
        }

        double sumFeet = this.toFeet() + other.toFeet();
        double resultValue = targetUnit.fromFeet(sumFeet);

        resultValue = round(resultValue);   // ⭐ FIX

        return new Length(resultValue, targetUnit);
    }
    private double round(double value) {
        return Math.round(value * 10000.0) / 10000.0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Length)) return false;
        Length other = (Length) o;
        return Math.abs(this.toFeet() - other.toFeet()) < EPSILON;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Math.round(toFeet() / EPSILON));
    }

    @Override
    public String toString() {
        return value + " " + unit;
    }
}