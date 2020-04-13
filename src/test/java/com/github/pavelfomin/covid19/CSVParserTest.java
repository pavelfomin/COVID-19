package com.github.pavelfomin.covid19;

import com.github.pavelfomin.covid19.model.DailyStatistic;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CSVParserTest {
    private static final Logger logger = LoggerFactory.getLogger(CSVParserTest.class);

    private CSVParser parser = new CSVParser();

    @Test
    void read() {

        InputStream input = getClass().getClassLoader().getResourceAsStream("data/test.csv");

        List<DailyStatistic> stats = parser.read(new InputStreamReader(input));

        assertNotNull(stats);
        assertEquals(87 + 3, stats.size());
    }

    @Test
    void readDifferentDateFormats() {

        InputStream input = getClass().getClassLoader().getResourceAsStream("data/test-different-date-format.csv");

        List<DailyStatistic> stats = parser.read(new InputStreamReader(input));

        assertNotNull(stats);
        assertEquals(2, stats.size());
    }

}