package com.github.pavelfomin.covid19;

import com.github.pavelfomin.covid19.model.DailyStatistic;
import com.opencsv.exceptions.CsvException;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.Writer;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    void write() throws CsvException {

        List<DailyStatistic> source = new ArrayList<>();

        LocalDateTime date = LocalDateTime.of(2020, 3, 26, 0, 0);
        source.add(new DailyStatistic("US", "Minnesota", date, 1, 0, 3, 4));
        source.add(new DailyStatistic("Italy", "", date, 10, 20, 30, 40));
        source.add(new DailyStatistic("Spain", "", date, 11, 22, 33, 44));

        Writer writer = new StringWriter();

        parser.write(writer, source);

        String csv = writer.toString();
        assertNotNull(csv);
        assertEquals("\"LAST_UPDATE\",\"COUNTRY_REGION\",\"PROVINCE_STATE\",\"CONFIRMED\",\"DEATHS\",\"RECOVERED\",\"ACTIVE\"\n" +
                        "\"2020-03-26 00:00:00\",\"US\",\"Minnesota\",\"1\",\"0\",\"3\",\"4\"\n" +
                        "\"2020-03-26 00:00:00\",\"Italy\",\"\",\"10\",\"20\",\"30\",\"40\"\n" +
                        "\"2020-03-26 00:00:00\",\"Spain\",\"\",\"11\",\"22\",\"33\",\"44\"\n",
                csv);
    }
}