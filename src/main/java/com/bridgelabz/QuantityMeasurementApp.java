package com.bridgelabz;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        // ============================
        // LENGTH DEMONSTRATION
        // ============================

        Quantity<LengthUnit> length1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> length2 = new Quantity<>(12.0, LengthUnit.INCHES);

        System.out.println("Length Equality: " + length1.equals(length2));

        Quantity<LengthUnit> lengthSum = length1.add(length2);
        System.out.println("Length Addition Result: " +
                lengthSum.getValue() + " " + lengthSum.getUnit().getUnitName());


        // ============================
        // WEIGHT DEMONSTRATION
        // ============================

        Quantity<WeightUnit> weight1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> weight2 = new Quantity<>(1000.0, WeightUnit.GRAM);

        System.out.println("Weight Equality: " + weight1.equals(weight2));

        Quantity<WeightUnit> weightSum = weight1.add(weight2);
        System.out.println("Weight Addition Result: " +
                weightSum.getValue() + " " + weightSum.getUnit().getUnitName());


        // ============================
        // VOLUME DEMONSTRATION (UC11)
        // ============================

        Quantity<VolumeUnit> volume1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> volume2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> volume3 = new Quantity<>(1.0, VolumeUnit.GALLON);

        System.out.println("Volume Equality (1L == 1000mL): " + volume1.equals(volume2));

        Quantity<VolumeUnit> converted = volume3.convertTo(VolumeUnit.LITRE);
        System.out.println("1 Gallon in Litres: " +
                converted.getValue() + " " + converted.getUnit().getUnitName());

        Quantity<VolumeUnit> volumeSum = volume1.add(volume2);
        System.out.println("Volume Addition Result: " +
                volumeSum.getValue() + " " + volumeSum.getUnit().getUnitName());

        Quantity<VolumeUnit> explicitTarget =
                volume1.add(volume3, VolumeUnit.MILLILITRE);

        System.out.println("Volume Addition in mL: " +
                explicitTarget.getValue() + " " + explicitTarget.getUnit().getUnitName());
    }
}