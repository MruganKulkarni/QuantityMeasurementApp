package com.bridgelabz;

import java.util.Objects;

public class Quantity<U extends IMeasurable> {

    private final double value;
    private final U unit;

    public Quantity(double value, U unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Invalid numeric value");
        }
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public U getUnit() {
        return unit;
    }

    // Convert to another unit of same category
    public Quantity<U> convertTo(U targetUnit) {
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        double baseValue = unit.convertToBaseUnit(value);
        double converted = targetUnit.convertFromBaseUnit(baseValue);

        return new Quantity<>(round(converted), targetUnit);
    }

    // Add and return in first operand's unit
    public Quantity<U> add(Quantity<U> other) {
        return add(other, this.unit);
    }

    // Add and return in specified target unit
    public Quantity<U> add(Quantity<U> other, U targetUnit) {
        if (other == null || targetUnit == null) {
            throw new IllegalArgumentException("Invalid input");
        }

        if (!this.unit.getClass().equals(other.unit.getClass())) {
            throw new IllegalArgumentException("Cannot add different measurement categories");
        }

        double baseValue1 = this.unit.convertToBaseUnit(this.value);
        double baseValue2 = other.unit.convertToBaseUnit(other.value);

        double sumBase = baseValue1 + baseValue2;

        double finalValue = targetUnit.convertFromBaseUnit(sumBase);

        return new Quantity<>(round(finalValue), targetUnit);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Quantity<?> other)) return false;

        if (!this.unit.getClass().equals(other.unit.getClass())) {
            return false;
        }

        double base1 = this.unit.convertToBaseUnit(this.value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        return Double.compare(round(base1), round(base2)) == 0;
    }

    @Override
    public int hashCode() {
        double base = unit.convertToBaseUnit(value);
        return Objects.hash(round(base), unit.getClass());
    }

    @Override
    public String toString() {
        return round(value) + " " + unit.getUnitName();
    }

    private double round(double value) {
        return Math.floor(value * 10000.0) / 10000.0;
    }
}