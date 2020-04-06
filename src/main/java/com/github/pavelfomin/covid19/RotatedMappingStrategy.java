package com.github.pavelfomin.covid19;

import com.github.pavelfomin.covid19.model.DailyStatistic;
import com.opencsv.CSVReader;
import com.opencsv.bean.MappingStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.github.pavelfomin.covid19.model.Column.LAST_UPDATE;

public class RotatedMappingStrategy implements MappingStrategy<Set> {

    private final Map<String, DailyStatistic> stats;

    public RotatedMappingStrategy(Map<String, DailyStatistic> stats) {
        this.stats = stats;
    }

    @Override
    public void captureHeader(CSVReader reader) {
    }

    @Override
    public String[] generateHeader(Set bean) {

        ArrayList<String> list = new ArrayList<>(stats.keySet());
        list.add(0, LAST_UPDATE.toString());

        return list.toArray(new String[list.size()]);
    }

    @Override
    public Set populateNewBean(String[] line) {
        return null;
    }

    @Override
    public void setType(Class type) {
    }

    @Override
    public String[] transmuteBean(Set keys) {

        List<String> values = new ArrayList();
        for (Object key : keys) {
            values.add(String.valueOf(stats.get(key).getConfirmed()));
        }

        values.add(0, stats.get(keys.iterator().next()).getUpdated().toString());

        return values.toArray(new String[values.size()]);
    }
}