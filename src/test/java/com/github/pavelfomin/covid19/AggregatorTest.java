package com.github.pavelfomin.covid19;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

class AggregatorTest {

    private Aggregator aggregator = new Aggregator();

    @Test
    void createFilter() {

        assertNull(aggregator.createFilter(null));
        assertNull(aggregator.createFilter(""));
        assertThat(aggregator.createFilter("US"), new LambdaMatcher<>(f -> "US".equals(f.getCountry()) && f.getState() == null, "US w/out state"));
        assertThat(aggregator.createFilter("United Kingdom_"), new LambdaMatcher<>(f -> "United Kingdom".equals(f.getCountry()) && "".equals(f.getState()), "UK w/ empty state"));
    }

}