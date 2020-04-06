package com.github.pavelfomin.covid19;

import com.github.pavelfomin.covid19.model.DailyStatistic;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.Reader;
import java.util.List;

public class CSVParser {

    public List<DailyStatistic> read(Reader reader) {

        return new CsvToBeanBuilder(reader).withType(DailyStatistic.class).build().parse();
    }

}
