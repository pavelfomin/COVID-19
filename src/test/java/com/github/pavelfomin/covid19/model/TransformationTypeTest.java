package com.github.pavelfomin.covid19.model;

import com.github.pavelfomin.covid19.CSVWriter;
import com.opencsv.exceptions.CsvException;
import org.junit.jupiter.api.Test;

import java.io.StringWriter;
import java.io.Writer;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TransformationTypeTest {

    @Test
    void none() throws CsvException {

        CSVWriter csvWriter = TransformationType.None.getCsvWriter();

        Map<String, DailyStatistic> source = new TreeMap<>();

        LocalDateTime date = LocalDateTime.of(2020, 3, 26, 0, 0);
        source.put("US_Minnesota", new DailyStatistic("US", "Minnesota", date, 1, 0, 3, 4));
        source.put("Italy", new DailyStatistic("Italy", "", date, 10, 20, 30, 40));
        source.put("Spain", new DailyStatistic("Spain", "", date, 11, 22, 33, 44));

        Writer writer = new StringWriter();

        csvWriter.write(writer, source);

        String csv = writer.toString();
        assertNotNull(csv);
        assertEquals("\"LAST_UPDATE\",\"COUNTRY_REGION\",\"PROVINCE_STATE\",\"CONFIRMED\",\"DEATHS\",\"RECOVERED\",\"ACTIVE\"\n" +
                        "\"2020-03-26 00:00:00\",\"Italy\",\"\",\"10\",\"20\",\"30\",\"40\"\n" +
                        "\"2020-03-26 00:00:00\",\"Spain\",\"\",\"11\",\"22\",\"33\",\"44\"\n" +
                        "\"2020-03-26 00:00:00\",\"US\",\"Minnesota\",\"1\",\"0\",\"3\",\"4\"\n",
                csv);
    }

    @Test
    void rotate() throws CsvException {

        CSVWriter csvWriter = TransformationType.Rotate.getCsvWriter();

        LocalDateTime date = LocalDateTime.of(2020, 3, 26, 0, 0);

        Map<String, DailyStatistic> stats = new TreeMap<>();
        stats.put("Minnesota", new DailyStatistic("US", "Minnesota", date, 1, 0, 3, 4));
        stats.put("Colorado", new DailyStatistic("US", "Colorado", date, 2, 0, 3, 4));

        Writer writer = new StringWriter();

        csvWriter.write(writer, stats);

        String csv = writer.toString();

        assertNotNull(csv);
        assertEquals("\"LAST_UPDATE\",\"Colorado\",\"Minnesota\"\n" +
                        "\"2020-03-26T00:00\",\"2\",\"1\"\n",
                csv);
    }
}