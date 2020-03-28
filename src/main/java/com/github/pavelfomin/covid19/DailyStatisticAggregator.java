package com.github.pavelfomin.covid19;

import com.github.pavelfomin.covid19.model.DailyStatistic;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    public List<DailyStatistic> aggregateByCountryAndState(List<DailyStatistic> stats) {

        Map<String, DailyStatistic> aggregated = new HashMap<>();

        for (DailyStatistic stat : stats) {
            String key = stat.getCountry() + "_" + stat.getState();
            DailyStatistic dailyStatistic = aggregated.get(key);
            if (dailyStatistic == null) {
                aggregated.put(key, stat);
                stat.setUpdated(stat.getUpdated().truncatedTo(ChronoUnit.DAYS));
                if ("".equals(stat.getState())) {
                    stat.setState(stat.getCountry());
                }
            } else {
                dailyStatistic.setActive(dailyStatistic.getActive() + stat.getActive());
                dailyStatistic.setConfirmed(dailyStatistic.getConfirmed() + stat.getConfirmed());
                dailyStatistic.setDeaths(dailyStatistic.getDeaths() + stat.getDeaths());
                dailyStatistic.setRecovered(dailyStatistic.getRecovered() + stat.getRecovered());
            }
        }

        return new ArrayList<>(aggregated.values());
    }

}
