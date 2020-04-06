package com.github.pavelfomin.covid19;

import com.github.pavelfomin.covid19.model.DailyStatistic;
import com.opencsv.exceptions.CsvException;

import java.io.Writer;
import java.util.Map;

@FunctionalInterface
public interface CSVWriter {

    void write(Writer writer, Map<String, DailyStatistic> stats) throws CsvException;
}
