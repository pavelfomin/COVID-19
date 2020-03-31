package com.github.pavelfomin.covid19.model;

public class Filter {

    private String country;
    private String state;

    public Filter(String country, String state) {
        this.country = country;
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public String getState() {
        return state;
    }

    public boolean matches(String country, String state) {

        return country.equals(getCountry()) && (state == null || getState() == null || state.equals(getState()));
    }

}
