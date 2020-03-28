package com.github.pavelfomin.covid19.model;

public enum Column {
    LAST_UPDATE, COUNTRY_REGION, PROVINCE_STATE, CONFIRMED, DEATHS, RECOVERED, ACTIVE;

    public Integer getOrder() {

        return ordinal();
    }
}
