package com.bridgelabz;

public class QuantityMeasurementApp {

    /* -------------------- DEMONSTRATION METHODS -------------------- */

    public static boolean demonstrateLengthEquality(Length l1, Length l2) {
        return l1.equals(l2);
    }

    public static boolean demonstrateLengthComparison(double value1,
                                                      LengthUnit unit1,
                                                      double value2,
                                                      LengthUnit unit2) {

        Length l1 = new Length(value1, unit1);
        Length l2 = new Length(value2, unit2);

        return demonstrateLengthEquality(l1, l2);
    }

    /* -------- METHOD OVERLOADING DEMO -------- */

    public static Length demonstrateLengthConversion(double value,
                                                     LengthUnit fromUnit,
                                                     LengthUnit toUnit) {

        Length length = new Length(value, fromUnit);
        return length.convertTo(toUnit);
    }

    public static Length demonstrateLengthConversion(Length length,
                                                     LengthUnit toUnit) {

        return length.convertTo(toUnit);
    }

    public static void main(String[] args) {

        System.out.println("convert(1.0, FEET, INCH) = " +
                Length.convert(1.0, LengthUnit.FEET, LengthUnit.INCH));

        System.out.println("convert(3.0, YARDS, FEET) = " +
                Length.convert(3.0, LengthUnit.YARDS, LengthUnit.FEET));
    }
}