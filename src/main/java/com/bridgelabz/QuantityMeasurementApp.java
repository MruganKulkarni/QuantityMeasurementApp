package com.bridgelabz;

public class QuantityMeasurementApp {

    public static boolean compareLength(double value1, LengthUnit unit1,
                                        double value2, LengthUnit unit2) {

        Length length1 = new Length(value1, unit1);
        Length length2 = new Length(value2, unit2);

        return length1.equals(length2);
    }

    public static void main(String[] args) {

        System.out.println(
                "1 foot == 12 inch ? " +
                        compareLength(1.0, LengthUnit.FEET,
                                12.0, LengthUnit.INCH)
        );
    }
}