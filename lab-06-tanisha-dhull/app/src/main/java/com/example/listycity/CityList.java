package com.example.listycity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This is a class that keeps track of a list of city objects
 */
public class CityList {
    /**
     * Internal list that holds city objects.
     */
    private List<City> cities = new ArrayList<>();

    /**
     * This adds a city to the list if the city does not exist
     * @param city
     *      This is a candidate city to add
     * @throws IllegalArgumentException if the city already exists in the list
     */
    public void add(City city) {
        if (cities.contains(city)) {
            throw new IllegalArgumentException();
        }
        cities.add(city);
    }

    /**
     * This returns a sorted list of cities
     * @return
     *      Return the sorted list
     */
    public List<City> getCities() {
        List<City> list = cities;  // follow starter style
        Collections.sort(list);
        return list;
    }

    /**
            * Finds the index of a logically equal city (same name and province).
            * This uses an index scan so we don't depend on City.equals/hashCode.
            *
            * @param target the city to search for
            * @return index (>=0) when found, -1 otherwise
 */
    private int findIndex(final City target) {
        // IntStream keeps this concise and distinct from the classic for-each loop.
        return java.util.stream.IntStream.range(0, cities.size())
                .filter(i ->
                        cities.get(i).getCityName().equals(target.getCityName()) &&
                                cities.get(i).getProvinceName().equals(target.getProvinceName()))
                .findFirst()
                .orElse(-1);
    }

    /**
     * Checks whether a logically equal city already exists.
     *
     * @param city the city to check
     * @return true if present, false otherwise
     */
    public boolean hasCity(final City city) {
        return findIndex(city) >= 0;
    }

    /**
     * Removes the given city if it exists; otherwise throws an exception.
     *
     * @param city the city to remove
     * @throws IllegalArgumentException if the city is not in the list
     */
    public void delete(final City city) {
        final int idx = findIndex(city);
        if (idx < 0) {
            throw new IllegalArgumentException("City not found: "
                    + city.getCityName() + ", " + city.getProvinceName());
        }
        cities.remove(idx);
    }

    /**
     * Returns the number of cities currently stored.
     *
     * @return the count of cities
     */
    public int countCities() {
        return cities.size();
    }

}
