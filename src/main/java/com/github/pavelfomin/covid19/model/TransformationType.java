package com.github.pavelfomin.covid19.model;

import com.github.pavelfomin.covid19.CSVWriter;
import com.github.pavelfomin.covid19.RotatedMappingStrategy;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

import java.util.ArrayList;
import java.util.Comparator;

public enum TransformationType {
    None((writer, stats) -> {

        HeaderColumnNameMappingStrategy<DailyStatistic> strategy = new HeaderColumnNameMappingStrategy<>();
        strategy.setType(DailyStatistic.class);
        strategy.setColumnOrderOnWrite(Comparator.comparing(o -> Column.valueOf(o).getOrder()));

        StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(writer).withMappingStrategy(strategy).build();
        beanToCsv.write(new ArrayList<>(stats.values()));
    }),

    Rotate((writer, stats) -> {

        StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(writer).withMappingStrategy(new RotatedMappingStrategy(stats)).build();
        beanToCsv.write(stats.keySet());
    });

    private CSVWriter csvWriter;

    TransformationType(CSVWriter csvWriter) {
        this.csvWriter = csvWriter;
    }

    public CSVWriter getCsvWriter() {
        return csvWriter;
    }
}
