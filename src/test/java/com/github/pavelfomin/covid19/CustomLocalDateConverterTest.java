package com.github.pavelfomin.covid19;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CustomLocalDateConverterTest {

    private CustomLocalDateConverter converter = new CustomLocalDateConverter();

    @Test
    void convert() {

        assertEquals(LocalDateTime.of(2020, 03, 25, 23, 33, 19), converter.convert("2020-03-25 23:33:19"));
        assertEquals(LocalDateTime.of(2020, 03, 30, 22, 52, 00), converter.convert("03/30/20 22:52"));
        assertEquals(LocalDateTime.of(2020, 03, 30, 22, 52, 00), converter.convert("3/30/20 22:52"));
        assertEquals(LocalDateTime.of(2020, 03, 04, 22, 52, 00), converter.convert("3/4/20 22:52"));
        assertEquals(LocalDateTime.of(2020, 03, 04, 02, 52, 00), converter.convert("3/4/20 2:52"));

        assertThrows(IllegalArgumentException.class, () -> converter.convert("3-30-2020 22:52"));
    }

    @Test
    void convertToWrite() {

        assertEquals("2020-03-25 23:33:19", converter.convertToWrite(LocalDateTime.of(2020, 03, 25, 23, 33, 19)));
    }
}