package com.github.pavelfomin.covid19;

import com.github.pavelfomin.covid19.model.DailyStatistic;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static com.github.pavelfomin.covid19.model.AggregationType.Country;
import static com.github.pavelfomin.covid19.model.AggregationType.State;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DailyStatisticAggregatorTest {

    private DailyStatisticAggregator aggregator = new DailyStatisticAggregator();

    @Test
    void filter() {

        List<DailyStatistic> source = new ArrayList<>();

        source.add(new DailyStatistic("US", "Minnesota"));
        source.add(new DailyStatistic("US", "Minnesota"));
        source.add(new DailyStatistic("US", "Minnesota"));
        source.add(new DailyStatistic("Italy", ""));
        source.add(new DailyStatistic("Spain", ""));

        List<DailyStatistic> filtered = aggregator.filter(source, "US", "Minnesota");
        assertEquals(3, filtered.size());

        filtered = aggregator.filter(source, "Italy", "");
        assertEquals(1, filtered.size());

        filtered = aggregator.filter(source, "Spain", "");
        assertEquals(1, filtered.size());

        filtered = aggregator.filter(source, "Invalid", "");
        assertEquals(0, filtered.size());
    }

    @Test
    void aggregateByCountryAndState() {

        List<DailyStatistic> source = new ArrayList<>();

        LocalDateTime date = LocalDateTime.now();
        LocalDateTime expectedDate = date.truncatedTo(ChronoUnit.DAYS);
        source.add(new DailyStatistic("US", "Minnesota", date, 1, 0, 3, 4));
        source.add(new DailyStatistic("US", "Minnesota", date, 2, 1, 5, 6));
        source.add(new DailyStatistic("US", "Minnesota", date, 3, 0, 7, 8));
        source.add(new DailyStatistic("US", "Wisconsin", date, 7, 0, 5, 2));
        source.add(new DailyStatistic("Italy", "", date, 10, 20, 30, 40));
        source.add(new DailyStatistic("Spain", "", date, 11, 22, 33, 44));

        List<DailyStatistic> aggregated = aggregator.aggregateByCountryAndStateAsList(source, State);

        assertNotNull(aggregated);
        assertEquals(4, aggregated.size());

        DailyStatistic mn = aggregated.stream().filter(s -> s.getCountry().equals("US") && s.getState().equals("Minnesota")).findFirst().get();
        assertEquals(6, mn.getConfirmed());
        assertEquals(1, mn.getDeaths());
        assertEquals(15, mn.getRecovered());
        assertEquals(18, mn.getActive());
        assertEquals(expectedDate, mn.getUpdated());

        DailyStatistic wi = aggregated.stream().filter(s -> s.getCountry().equals("US") && s.getState().equals("Wisconsin")).findFirst().get();
        assertEquals(7, wi.getConfirmed());
        assertEquals(0, wi.getDeaths());
        assertEquals(5, wi.getRecovered());
        assertEquals(2, wi.getActive());
        assertEquals(expectedDate, wi.getUpdated());

        DailyStatistic italy = aggregated.stream().filter(s -> s.getCountry().equals("Italy") && s.getState().equals("")).findFirst().get();
        assertEquals(10, italy.getConfirmed());
        assertEquals(20, italy.getDeaths());
        assertEquals(30, italy.getRecovered());
        assertEquals(40, italy.getActive());
        assertEquals(expectedDate, italy.getUpdated());

        DailyStatistic spain = aggregated.stream().filter(s -> s.getCountry().equals("Spain") && s.getState().equals("")).findFirst().get();
        assertEquals(11, spain.getConfirmed());
        assertEquals(22, spain.getDeaths());
        assertEquals(33, spain.getRecovered());
        assertEquals(44, spain.getActive());
        assertEquals(expectedDate, spain.getUpdated());
    }

    @Test
    void aggregateByCountry() {

        List<DailyStatistic> source = new ArrayList<>();

        LocalDateTime date = LocalDateTime.now();
        LocalDateTime expectedDate = date.truncatedTo(ChronoUnit.DAYS);
        source.add(new DailyStatistic("US", "Minnesota", date, 1, 0, 3, 4));
        source.add(new DailyStatistic("US", "Minnesota", date, 2, 1, 5, 6));
        source.add(new DailyStatistic("US", "Minnesota", date, 3, 0, 7, 8));
        source.add(new DailyStatistic("US", "Wisconsin", date, 7, 0, 5, 2));
        source.add(new DailyStatistic("Italy", "", date, 10, 20, 30, 40));
        source.add(new DailyStatistic("Spain", "", date, 11, 22, 33, 44));

        List<DailyStatistic> aggregated = aggregator.aggregateByCountryAndStateAsList(source, Country);

        assertNotNull(aggregated);
        assertEquals(3, aggregated.size());

        DailyStatistic us = aggregated.stream().filter(s -> s.getCountry().equals("US") && s.getState().equals("US")).findFirst().get();
        assertEquals(13, us.getConfirmed());
        assertEquals(1, us.getDeaths());
        assertEquals(20, us.getRecovered());
        assertEquals(20, us.getActive());
        assertEquals(expectedDate, us.getUpdated());

        DailyStatistic italy = aggregated.stream().filter(s -> s.getCountry().equals("Italy") && s.getState().equals("Italy")).findFirst().get();
        assertEquals(10, italy.getConfirmed());
        assertEquals(20, italy.getDeaths());
        assertEquals(30, italy.getRecovered());
        assertEquals(40, italy.getActive());
        assertEquals(expectedDate, italy.getUpdated());

        DailyStatistic spain = aggregated.stream().filter(s -> s.getCountry().equals("Spain") && s.getState().equals("Spain")).findFirst().get();
        assertEquals(11, spain.getConfirmed());
        assertEquals(22, spain.getDeaths());
        assertEquals(33, spain.getRecovered());
        assertEquals(44, spain.getActive());
        assertEquals(expectedDate, spain.getUpdated());
    }
}