package com.github.pavelfomin.covid19.model;

import com.github.pavelfomin.covid19.CustomLocalDateConverter;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;
import com.opencsv.bean.CsvDate;

import java.time.LocalDateTime;

public class DailyStatistic {

    @CsvCustomBindByName(column = "Last_Update", required = true, converter = CustomLocalDateConverter.class)
    @CsvDate("yyyy-MM-dd HH:mm:ss|M/d/yy H:mm")
    private LocalDateTime updated;

    @CsvBindByName(column = "Country_Region", required = true)
    private String country;

    @CsvBindByName(column = "Province_State")
    private String state;

    @CsvBindByName(column = "Confirmed", required = true)
    private int confirmed;

    @CsvBindByName(column = "Deaths", required = true)
    private int deaths;

    @CsvBindByName(column = "Recovered", required = true)
    private int recovered;

    @CsvBindByName(column = "Active", required = true)
    private int active;

    public DailyStatistic() {
    }

    public DailyStatistic(String country, String state) {

        this.country = country;
        this.state = state;
    }

    public DailyStatistic(String country, String state, LocalDateTime updated, int confirmed, int deaths, int recovered, int active) {

        this.country = country;
        this.state = state;
        this.updated = updated;
        this.confirmed = confirmed;
        this.deaths = deaths;
        this.recovered = recovered;
        this.active = active;
    }

    public int getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(int confirmed) {
        this.confirmed = confirmed;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getRecovered() {
        return recovered;
    }

    public void setRecovered(int recovered) {
        this.recovered = recovered;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    @Override
    public String toString() {
        return "DailyStatistic{" +
                "state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", updated=" + updated +
                ", confirmed=" + confirmed +
                ", deaths=" + deaths +
                ", recovered=" + recovered +
                ", active=" + active +
                '}';
    }
}
