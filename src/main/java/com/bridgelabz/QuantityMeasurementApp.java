package com.bridgelabz;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        // LENGTH
        Quantity<LengthUnit> length1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> length2 = new Quantity<>(6.0, LengthUnit.INCHES);

        System.out.println("Length Subtraction: " + length1.subtract(length2));
        System.out.println("Length Division: " + length1.divide(new Quantity<>(2.0, LengthUnit.FEET)));

        // WEIGHT
        Quantity<WeightUnit> weight1 = new Quantity<>(10.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> weight2 = new Quantity<>(5000.0, WeightUnit.GRAM);

        System.out.println("Weight Subtraction: " + weight1.subtract(weight2));
        System.out.println("Weight Division: " + weight1.divide(new Quantity<>(5.0, WeightUnit.KILOGRAM)));

        // VOLUME
        Quantity<VolumeUnit> volume1 = new Quantity<>(5.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> volume2 = new Quantity<>(2.0, VolumeUnit.LITRE);

        System.out.println("Volume Subtraction: " + volume1.subtract(volume2));
        System.out.println("Volume Division: " + volume1.divide(volume2));

        // ============================
// TEMPERATURE DEMONSTRATION (UC14)
// ============================

        Quantity<TemperatureUnit> temp1 =
                new Quantity<>(0.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> temp2 =
                new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);

        System.out.println("Temperature Equality (0C == 32F): "
                + temp1.equals(temp2));

        Quantity<TemperatureUnit> convertedTemp =
                temp1.convertTo(TemperatureUnit.KELVIN);

        System.out.println("0C in Kelvin: "
                + convertedTemp.getValue() + " "
                + convertedTemp.getUnit().getUnitName());

        try {
            temp1.add(new Quantity<>(10.0, TemperatureUnit.CELSIUS));
        } catch (UnsupportedOperationException e) {
            System.out.println("Temperature arithmetic blocked: " + e.getMessage());
        }
    }
}