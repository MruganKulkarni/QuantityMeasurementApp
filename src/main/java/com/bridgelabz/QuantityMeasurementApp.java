package com.bridgelabz;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(12.0, Length.LengthUnit.INCHES);

        System.out.println("Are equal? " + l1.equals(l2));

        double inches = Length.convert(1.0, Length.LengthUnit.FEET, Length.LengthUnit.INCHES);
        System.out.println("1 foot in inches = " + inches);

        Length sum = l1.add(l2);
        System.out.println("Sum = " + sum);
    }
}