package com.github.pavelfomin.covid19;

import com.github.pavelfomin.covid19.model.Column;
import com.github.pavelfomin.covid19.model.DailyStatistic;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvException;

import java.io.Reader;
import java.io.Writer;
import java.util.Comparator;
import java.util.List;

public class CSVParser {

    public List<DailyStatistic> read(Reader reader)  {

        return new CsvToBeanBuilder(reader).withType(DailyStatistic.class).build().parse();
    }

    public void write(Writer writer, List<DailyStatistic> stats) throws CsvException {

        HeaderColumnNameMappingStrategy<DailyStatistic> strategy = new HeaderColumnNameMappingStrategy<>();
        strategy.setType(DailyStatistic.class);
        strategy.setColumnOrderOnWrite(Comparator.comparing(o -> Column.valueOf((String) o).getOrder()));

        StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(writer).withMappingStrategy(strategy).build();
        beanToCsv.write(stats);
    }

}
