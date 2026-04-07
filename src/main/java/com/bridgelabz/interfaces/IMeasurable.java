package com.bridgelabz;

/**
 * Interface that defines contract for all measurable unit types.
 * All unit enums (LengthUnit, WeightUnit, etc.) must implement this.
 */
public interface IMeasurable {

    /**
     * Returns the conversion factor relative to base unit.
     */
    double getConversionFactor();

    /**
     * Converts a value from this unit to base unit.
     */
    double convertToBaseUnit(double value);

    /**
     * Converts a value from base unit to this unit.
     */
    double convertFromBaseUnit(double baseValue);

    /**
     * Returns readable unit name.
     */
    String getUnitName();
}