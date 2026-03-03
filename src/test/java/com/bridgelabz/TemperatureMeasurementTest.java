package com.bridgelabz;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TemperatureMeasurementTest {

    private static final double EPSILON = 0.0001;

    // ===============================
    // BASIC EQUALITY TESTS
    // ===============================

    @Test
    void testCelsiusToCelsiusEquality() {
        Quantity<TemperatureUnit> t1 = new Quantity<>(0.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> t2 = new Quantity<>(0.0, TemperatureUnit.CELSIUS);
        assertTrue(t1.equals(t2));
    }

    @Test
    void testCelsiusToFahrenheitEquality() {
        Quantity<TemperatureUnit> c = new Quantity<>(0.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> f = new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);
        assertTrue(c.equals(f));
    }

    @Test
    void testCelsiusToKelvinEquality() {
        Quantity<TemperatureUnit> c = new Quantity<>(0.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> k = new Quantity<>(273.15, TemperatureUnit.KELVIN);
        assertTrue(c.equals(k));
    }

    @Test
    void testNegativeFortyCrossover() {
        Quantity<TemperatureUnit> c = new Quantity<>(-40.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> f = new Quantity<>(-40.0, TemperatureUnit.FAHRENHEIT);
        assertTrue(c.equals(f));
    }

    @Test
    void testAbsoluteZero() {
        Quantity<TemperatureUnit> c = new Quantity<>(-273.15, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> k = new Quantity<>(0.0, TemperatureUnit.KELVIN);
        assertTrue(c.equals(k));
    }

    // ===============================
    // CONVERSION TESTS
    // ===============================

    @Test
    void testCelsiusToFahrenheitConversion() {
        Quantity<TemperatureUnit> c = new Quantity<>(100.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> result = c.convertTo(TemperatureUnit.FAHRENHEIT);
        assertEquals(212.0, result.getValue(), EPSILON);
    }

    @Test
    void testFahrenheitToCelsiusConversion() {
        Quantity<TemperatureUnit> f = new Quantity<>(212.0, TemperatureUnit.FAHRENHEIT);
        Quantity<TemperatureUnit> result = f.convertTo(TemperatureUnit.CELSIUS);
        assertEquals(100.0, result.getValue(), EPSILON);
    }

    @Test
    void testKelvinToCelsiusConversion() {
        Quantity<TemperatureUnit> k = new Quantity<>(273.15, TemperatureUnit.KELVIN);
        Quantity<TemperatureUnit> result = k.convertTo(TemperatureUnit.CELSIUS);
        assertEquals(0.0, result.getValue(), EPSILON);
    }

    @Test
    void testRoundTripConversion() {
        Quantity<TemperatureUnit> original =
                new Quantity<>(37.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> converted =
                original.convertTo(TemperatureUnit.FAHRENHEIT)
                        .convertTo(TemperatureUnit.CELSIUS);

        assertEquals(original.getValue(), converted.getValue(), EPSILON);
    }

    // ===============================
    // INEQUALITY TEST
    // ===============================

    @Test
    void testDifferentTemperaturesNotEqual() {
        Quantity<TemperatureUnit> t1 = new Quantity<>(50.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> t2 = new Quantity<>(100.0, TemperatureUnit.CELSIUS);
        assertFalse(t1.equals(t2));
    }

    // ===============================
    // UNSUPPORTED ARITHMETIC TESTS
    // ===============================

    @Test
    void testAdditionNotSupported() {
        Quantity<TemperatureUnit> t1 = new Quantity<>(100.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> t2 = new Quantity<>(50.0, TemperatureUnit.CELSIUS);

        assertThrows(UnsupportedOperationException.class,
                () -> t1.add(t2));
    }

    @Test
    void testSubtractionNotSupported() {
        Quantity<TemperatureUnit> t1 = new Quantity<>(100.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> t2 = new Quantity<>(50.0, TemperatureUnit.CELSIUS);

        assertThrows(UnsupportedOperationException.class,
                () -> t1.subtract(t2));
    }

    @Test
    void testDivisionNotSupported() {
        Quantity<TemperatureUnit> t1 = new Quantity<>(100.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> t2 = new Quantity<>(50.0, TemperatureUnit.CELSIUS);

        assertThrows(UnsupportedOperationException.class,
                () -> t1.divide(t2));
    }

    // ===============================
    // CROSS CATEGORY TESTS
    // ===============================

    @Test
    void testTemperatureVsLengthNotEqual() {
        Quantity<TemperatureUnit> temp =
                new Quantity<>(50.0, TemperatureUnit.CELSIUS);

        Quantity<LengthUnit> length =
                new Quantity<>(50.0, LengthUnit.FEET);

        assertFalse(temp.equals(length));
    }

    @Test
    void testTemperatureVsWeightNotEqual() {
        Quantity<TemperatureUnit> temp =
                new Quantity<>(50.0, TemperatureUnit.CELSIUS);

        Quantity<WeightUnit> weight =
                new Quantity<>(50.0, WeightUnit.KILOGRAM);

        assertFalse(temp.equals(weight));
    }

    @Test
    void testTemperatureVsVolumeNotEqual() {
        Quantity<TemperatureUnit> temp =
                new Quantity<>(50.0, TemperatureUnit.CELSIUS);

        Quantity<VolumeUnit> volume =
                new Quantity<>(50.0, VolumeUnit.LITRE);

        assertFalse(temp.equals(volume));
    }

    // ===============================
    // VALIDATION TESTS
    // ===============================

    @Test
    void testNullUnitRejected() {
        assertThrows(IllegalArgumentException.class,
                () -> new Quantity<>(100.0, null));
    }

    @Test
    void testSameReferenceEquality() {
        Quantity<TemperatureUnit> temp =
                new Quantity<>(25.0, TemperatureUnit.CELSIUS);

        assertTrue(temp.equals(temp));
    }

    @Test
    void testSymmetricEquality() {
        Quantity<TemperatureUnit> c =
                new Quantity<>(0.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> f =
                new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);

        assertTrue(c.equals(f));
        assertTrue(f.equals(c));
    }
}