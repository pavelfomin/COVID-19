package com.github.pavelfomin.covid19.model;

import java.util.function.Function;

public enum AggregationType {
    State(statistic -> statistic.getCountry() + "_" + statistic.getState()),
    Country(statistic -> statistic.getCountry());

    private Function<DailyStatistic, String> aggregationKeyFunction;

    AggregationType(Function<DailyStatistic, String> aggregationKeyFunction) {
        this.aggregationKeyFunction = aggregationKeyFunction;
    }

    public Function<DailyStatistic, String> getAggregationKeyFunction() {
        return aggregationKeyFunction;
    }
}
