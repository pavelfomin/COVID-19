package com.github.pavelfomin.covid19;

import com.github.pavelfomin.covid19.model.AggregationType;
import com.github.pavelfomin.covid19.model.DailyStatistic;
import com.github.pavelfomin.covid19.model.Filter;

import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Aggregator {

    private CSVParser parser = new CSVParser();
    private DailyStatisticAggregator dailyStatisticAggregator = new DailyStatisticAggregator();

    private void aggregate(String filename, List<Filter> filters, AggregationType aggregationType) throws Exception {

        List<DailyStatistic> stats = parser.read(new FileReader(filename));

        Predicate<DailyStatistic> predicate = source -> {
            boolean result = false;
            for (Filter filter : filters) {
                result = result || filter.matches(source.getCountry(), source.getState());
            }
            return result;
        };

        stats = dailyStatisticAggregator.filter(stats, predicate);
        stats = dailyStatisticAggregator.aggregateByCountryAndState(stats, aggregationType);

        OutputStreamWriter writer = new OutputStreamWriter(System.out);
        parser.write(writer, stats);
        writer.flush();
        writer.close();
    }

    /**
     * Creates a Filter instance from Country[_State].
     *
     * @param argument Country[_State]
     * @return
     */
    protected Filter createFilter(String argument) {

        if (argument != null) {
            String[] split = argument.split("_", 2);

            if (!"".equals(split[0])) {
                return new Filter(split[0], split.length > 1 ? split[1] : null);
            }
        }

        return null;
    }

    protected List<Filter> createFilters(String[] args) {

        List<Filter> filters = new ArrayList<>();

        for (String arg : args) {
            filters.add(createFilter(arg));
        }

        return filters;
    }

    public static void main(String[] args) throws Exception {

        if (args.length < 3) {
            usage();
            System.exit(1);
        }

        String[] filterArgs = Arrays.copyOfRange(args, 2, args.length);

        System.err.println(String.format("Processing file: %s, aggregation type: %s, filters: %s",
                args[0],
                args[1],
                Arrays.asList(filterArgs))
        );

        AggregationType aggregationType = AggregationType.valueOf(args[1]);

        Aggregator aggregator = new Aggregator();
        List<Filter> filters = aggregator.createFilters(filterArgs);
        aggregator.aggregate(args[0], filters, aggregationType);
    }

    private static void usage() {

        System.err.println("Usage: " + Aggregator.class.getName() + " <daily report csv> <aggregation type: (Country|State)> <Country[_State]...");
        System.err.println("Example 1: " + Aggregator.class.getName() + " csse_covid_19_data/csse_covid_19_daily_reports/03-25-2020.csv State US_Minnesota US_Texas US_Wisconsin US_Oklahoma");
        System.err.println("Example 2: " + Aggregator.class.getName() + " csse_covid_19_data/csse_covid_19_daily_reports/03-25-2020.csv Country Italy Spain Germany Russia \"United Kingdom\"");
    }
}
