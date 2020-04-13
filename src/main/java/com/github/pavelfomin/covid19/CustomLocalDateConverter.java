package com.github.pavelfomin.covid19;

import com.opencsv.bean.AbstractBeanField;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CustomLocalDateConverter extends AbstractBeanField {

    private static final String[] PATTERNS = {"yyyy-MM-dd HH:mm:ss", "M/d/yy H:mm"};
    private List<DateTimeFormatter> formatters = new ArrayList<>();

    public CustomLocalDateConverter() {

        for (String pattern : PATTERNS) {
            formatters.add(DateTimeFormatter.ofPattern(pattern));
        }
    }

    @Override
    protected Object convert(String s) {

        Exception exception = null;
        for (DateTimeFormatter formatter : formatters) {

            try {
                return LocalDateTime.parse(s, formatter);
            } catch (Exception e) {
                exception = e;
            }
        }

        throw new IllegalArgumentException("Failed to parse date", exception);
    }

    @Override
    protected String convertToWrite(Object value) {

        return formatters.get(0).format((LocalDateTime)value);
    }
}