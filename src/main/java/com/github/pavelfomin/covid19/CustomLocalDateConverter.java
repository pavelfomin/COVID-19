package com.github.pavelfomin.covid19;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.bean.CsvDate;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CustomLocalDateConverter extends AbstractBeanField {

    private List<DateTimeFormatter> inputFormatters;
    private DateTimeFormatter outputFormatter;

    public CustomLocalDateConverter() {
    }

    @Override
    public void setField(Field field) {

        super.setField(field);
        //don't like overriding the setter but have no choice as the default constructor is called followed by the setter call
        parseCsvDateAnnotation(field);
    }

    protected CustomLocalDateConverter(String outputPattern, String ... inputPatters) {

        initialize(outputPattern, inputPatters);
    }

    protected CustomLocalDateConverter initialize(String outputPattern, String ... inputPatters) {

        inputFormatters = new ArrayList<>();
        for (String pattern : inputPatters) {
            inputFormatters.add(DateTimeFormatter.ofPattern(pattern));
        }

        outputFormatter = DateTimeFormatter.ofPattern(outputPattern);

        return this;
    }

    protected CustomLocalDateConverter parseCsvDateAnnotation(Field field) {

        if (field.isAnnotationPresent(CsvDate.class)) {
            CsvDate annotation = field.getAnnotation(CsvDate.class);
            String[] readFormats = annotation.value().split("\\|");
            String writeFormat = annotation.writeFormatEqualsReadFormat() ? readFormats[0] : annotation.writeFormat();
            return initialize(writeFormat, readFormats);
        } else {
            throw new IllegalArgumentException("@CsvDate annotation is required");
        }
    }

    @Override
    protected Object convert(String s) {

        Exception exception = null;
        for (DateTimeFormatter formatter : inputFormatters) {

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

        return outputFormatter.format((LocalDateTime) value);
    }
}