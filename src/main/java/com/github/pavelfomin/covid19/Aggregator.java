package com.github.pavelfomin.covid19;

import com.github.pavelfomin.covid19.model.DailyStatistic;

import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.function.Predicate;

public class Aggregator {

    private CSVParser parser = new CSVParser();
    private DailyStatisticAggregator dailyStatisticAggregator = new DailyStatisticAggregator();

    private void aggregate(String filename) throws Exception {

        List<DailyStatistic> stats = parser.read(new FileReader(filename));

        Predicate<DailyStatistic> predicate = s ->
                "US".equals(s.getCountry()) && "Minnesota".equals(s.getState())
                || "US".equals(s.getCountry()) && "Texas".equals(s.getState())
                || "US".equals(s.getCountry()) && "Wisconsin".equals(s.getState())
                || "US".equals(s.getCountry()) && "Oklahoma".equals(s.getState())
//                || "Italy".equals(s.getCountry())
//                || "Spain".equals(s.getCountry())
//                || "Germany".equals(s.getCountry())
                ;

        stats = dailyStatisticAggregator.filter(stats, predicate);
        stats = dailyStatisticAggregator.aggregateByCountryAndState(stats);

        OutputStreamWriter writer = new OutputStreamWriter(System.out);
        parser.write(writer, stats);
        writer.flush();
        writer.close();
    }

    public static void main(String[] args) throws Exception {

        Aggregator aggregator = new Aggregator();
        aggregator.aggregate(args[0]);
    }
}
