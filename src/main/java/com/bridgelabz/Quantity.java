package com.bridgelabz;

import java.util.Objects;

public class Quantity<U extends Enum<U> & IMeasurable> {

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

    // ==============================
    // CONVERSION
    // ==============================

    public Quantity<U> convertTo(U targetUnit) {
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        double baseValue = unit.convertToBaseUnit(value);
        double converted = targetUnit.convertFromBaseUnit(baseValue);

        return new Quantity<>(round(converted), targetUnit);
    }

    // ==============================
    // ADDITION
    // ==============================

    public Quantity<U> add(Quantity<U> other) {
        return add(other, this.unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {
        validateArithmeticOperands(other, targetUnit, true);

        double baseResult = performBaseArithmetic(other, ArithmeticOperation.ADD);
        double finalValue = targetUnit.convertFromBaseUnit(baseResult);

        return new Quantity<>(round(finalValue), targetUnit);
    }

    // ==============================
    // SUBTRACTION
    // ==============================

    public Quantity<U> subtract(Quantity<U> other) {
        return subtract(other, this.unit);
    }

    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {
        validateArithmeticOperands(other, targetUnit, true);

        double baseResult = performBaseArithmetic(other, ArithmeticOperation.SUBTRACT);
        double finalValue = targetUnit.convertFromBaseUnit(baseResult);

        return new Quantity<>(round(finalValue), targetUnit);
    }

    // ==============================
    // DIVISION
    // ==============================

    public double divide(Quantity<U> other) {
        validateArithmeticOperands(other, null, false);
        return performBaseArithmetic(other, ArithmeticOperation.DIVIDE);
    }

    // ==============================
    // CENTRALIZED VALIDATION
    // ==============================

    private void validateArithmeticOperands(
            Quantity<U> other,
            U targetUnit,
            boolean targetRequired) {

        if (other == null) {
            throw new IllegalArgumentException("Other quantity cannot be null");
        }

        if (!(this.unit instanceof ArithmeticCapable)) {
            throw new UnsupportedOperationException(
                    "Arithmetic operations not supported for measurement category"
            );
        }

        // ✅ FIXED CATEGORY CHECK
        if (!this.unit.getDeclaringClass().equals(other.unit.getDeclaringClass())) {
            throw new IllegalArgumentException("Cannot operate on different measurement categories");
        }

        if (!Double.isFinite(this.value) || !Double.isFinite(other.value)) {
            throw new IllegalArgumentException("Invalid numeric value");
        }

        if (targetRequired && targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }
    }

    // ==============================
    // CENTRALIZED BASE ARITHMETIC
    // ==============================

    private double performBaseArithmetic(
            Quantity<U> other,
            ArithmeticOperation operation) {

        double base1 = this.unit.convertToBaseUnit(this.value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        return operation.compute(base1, base2);
    }

    // ==============================
    // ARITHMETIC OPERATION ENUM
    // ==============================

    private enum ArithmeticOperation {

        ADD {
            @Override
            double compute(double a, double b) {
                return a + b;
            }
        },

        SUBTRACT {
            @Override
            double compute(double a, double b) {
                return a - b;
            }
        },

        DIVIDE {
            @Override
            double compute(double a, double b) {
                if (b == 0.0) {
                    throw new ArithmeticException("Cannot divide by zero");
                }
                return a / b;
            }
        };

        abstract double compute(double a, double b);
    }

    // ==============================
    // EQUALITY
    // ==============================

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Quantity<?> other)) return false;

        // ✅ FIXED CATEGORY CHECK
        if (!this.unit.getDeclaringClass().equals(other.unit.getDeclaringClass())) {
            return false;
        }

        double base1 = this.unit.convertToBaseUnit(this.value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        return Double.compare(round(base1), round(base2)) == 0;
    }

    @Override
    public int hashCode() {
        double base = unit.convertToBaseUnit(value);

        // ✅ FIXED HASH CATEGORY CHECK
        return Objects.hash(round(base), unit.getDeclaringClass());
    }

    @Override
    public String toString() {
        return round(value) + " " + unit.getUnitName();
    }

    private double round(double value) {
        return Math.round(value * 10000.0) / 10000.0;
    }
}