package com.example.listycity;

/**
 * This is a class that defines a City.
 */
public class City implements Comparable<City> {
    /**
     * The name of the city.
     */
    private String city;

    /**
     * The province or state of the city.
     */
    private String province;

    /**
     * Constructs a City with a name and province.
     * @param city the city name
     * @param province the province or state name
     */
    City(String city, String province){
        this.city = city;
        this.province = province;
    }

    /**
     * Gets the city name.
     * @return the city name
     */
    String getCityName(){
        return this.city;
    }

    /**
     * Gets the province name.
     * @return the province name
     */
    String getProvinceName(){
        return this.province;
    }

    /**
     * Compares this city with another city by city name.
     * @param other the other city to compare with
     * @return a negative integer, zero, or a positive integer as this city name
     *         is less than, equal to, or greater than the other city name
     */
    @Override
    public int compareTo(City other) {
        return this.city.compareTo(other.getCityName());
    }
}
