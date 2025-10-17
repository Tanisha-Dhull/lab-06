package com.example.listycity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CityListTest {

    private CityList mockCityList() {
        CityList cityList = new CityList();
        cityList.add(mockCity());
        return cityList;
    }

    private City mockCity() {
        return new City("Edmonton", "Alberta");
    }

    @Test
    void testAdd() {
        CityList cityList = mockCityList();
        assertEquals(1, cityList.getCities().size());
        City city = new City("Regina", "Saskatchewan");
        cityList.add(city);
        assertEquals(2, cityList.getCities().size());
        assertTrue(cityList.getCities().contains(city));
    }

    @Test
    void testAddException() {
        CityList cityList = mockCityList();
        City city = new City("Yellowknife", "Northwest Territories");
        cityList.add(city);
        assertThrows(IllegalArgumentException.class, () -> cityList.add(city));
    }

    @Test
    void testGetCities() {
        CityList cityList = mockCityList();
        // first should be Edmonton (from mockCity) initially
        assertEquals(0, mockCity().compareTo(cityList.getCities().get(0)));

        // add a city that sorts before Edmonton
        City city = new City("Charlottetown", "Prince Edward Island");
        cityList.add(city);

        // now Charlottetown should be first, Edmonton second
        assertEquals(0, city.compareTo(cityList.getCities().get(0)));
        assertEquals(0, mockCity().compareTo(cityList.getCities().get(1)));
    }

    // --- Participation Exercise tests ---

    @Test
    void testHasCity_presentAndAbsent() {
        CityList list = new CityList();
        list.add(new City("Edmonton", "Alberta"));

        // Logical equality (same name + province, different instance) → true
        assertTrue(list.hasCity(new City("Edmonton", "Alberta")));

        // Different name or province → false
        assertFalse(list.hasCity(new City("Calgary", "Alberta")));
        assertFalse(list.hasCity(new City("Edmonton", "Saskatchewan")));
    }

    @Test
    void testDelete_removesWhenPresent_andThrowsWhenAbsent() {
        CityList list = new CityList();
        list.add(new City("Regina", "Saskatchewan"));
        list.add(new City("Whitehorse", "Yukon"));
        assertEquals(2, list.countCities());

        // Remove by logical equality (new instance)
        list.delete(new City("Regina", "Saskatchewan"));
        assertEquals(1, list.countCities());
        assertFalse(list.hasCity(new City("Regina", "Saskatchewan")));
        assertTrue(list.hasCity(new City("Whitehorse", "Yukon"))); // still there

        // Deleting an absent city must throw
        assertThrows(IllegalArgumentException.class,
                () -> list.delete(new City("Regina", "Saskatchewan")));
    }

    @Test
    void testCountCities_tracksAddsAndDeletes() {
        CityList list = new CityList();
        assertEquals(0, list.countCities());

        list.add(new City("Iqaluit", "Nunavut"));
        list.add(new City("Yellowknife", "Northwest Territories"));
        assertEquals(2, list.countCities());

        list.delete(new City("Iqaluit", "Nunavut"));
        assertEquals(1, list.countCities());
    }

}

