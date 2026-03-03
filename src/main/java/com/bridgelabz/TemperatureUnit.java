package com.bridgelabz;

/**
 * Temperature units with non-linear (affine) conversion.
 * Base unit: CELSIUS
 */
public enum TemperatureUnit implements IMeasurable {

    CELSIUS {
        @Override
        public double convertToBaseUnit(double value) {
            return value; // base unit
        }

        @Override
        public double convertFromBaseUnit(double baseValue) {
            return baseValue;
        }
    },

    FAHRENHEIT {
        @Override
        public double convertToBaseUnit(double value) {
            return (value - 32) * 5.0 / 9.0;
        }

        @Override
        public double convertFromBaseUnit(double baseValue) {
            return (baseValue * 9.0 / 5.0) + 32;
        }
    },

    KELVIN {
        @Override
        public double convertToBaseUnit(double value) {
            return value - 273.15;
        }

        @Override
        public double convertFromBaseUnit(double baseValue) {
            return baseValue + 273.15;
        }
    };

    @Override
    public double getConversionFactor() {
        return 1.0; // not used for affine conversions
    }

    @Override
    public String getUnitName() {
        return this.name();
    }
}