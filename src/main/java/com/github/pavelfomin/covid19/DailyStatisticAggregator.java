package com.github.pavelfomin.covid19;

import com.github.pavelfomin.covid19.model.AggregationType;
import com.github.pavelfomin.covid19.model.DailyStatistic;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class DailyStatisticAggregator {

    public List<DailyStatistic> filter(List<DailyStatistic> stats, String country, String state) {

        return filter(stats, s -> country.equals(s.getCountry()) && state.equals(s.getState()));
    }

    public List<DailyStatistic> filter(List<DailyStatistic> stats, Predicate<DailyStatistic> predicate) {

        return stats
                .stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

    public List<DailyStatistic> aggregateByCountryAndStateAsList(List<DailyStatistic> stats, AggregationType aggregationType) {

        Map<String, DailyStatistic> aggregated = aggregateByCountryAndState(stats, aggregationType);
        return new ArrayList<>(aggregated.values());
    }

    public Map<String, DailyStatistic> aggregateByCountryAndState(List<DailyStatistic> stats, AggregationType aggregationType) {

        Map<String, DailyStatistic> aggregated = new TreeMap<>();
        Function<DailyStatistic, String> aggregationKeyFunction = aggregationType.getAggregationKeyFunction();

        for (DailyStatistic stat : stats) {
            String key = aggregationKeyFunction.apply(stat);
            DailyStatistic dailyStatistic = aggregated.get(key);
            if (dailyStatistic == null) {
                aggregated.put(key, stat);
                stat.setUpdated(stat.getUpdated().truncatedTo(ChronoUnit.DAYS));
                if (aggregationType == AggregationType.Country) {
                    stat.setState(stat.getCountry());
                }
            } else {
                dailyStatistic.setActive(dailyStatistic.getActive() + stat.getActive());
                dailyStatistic.setConfirmed(dailyStatistic.getConfirmed() + stat.getConfirmed());
                dailyStatistic.setDeaths(dailyStatistic.getDeaths() + stat.getDeaths());
                dailyStatistic.setRecovered(dailyStatistic.getRecovered() + stat.getRecovered());
            }
        }

        return aggregated;
    }

}
