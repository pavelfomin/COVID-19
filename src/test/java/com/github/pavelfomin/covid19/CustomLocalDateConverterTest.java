package com.github.pavelfomin.covid19;

import com.opencsv.bean.CsvDate;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

class CustomLocalDateConverterTest {

    private CustomLocalDateConverter converter = new CustomLocalDateConverter("yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "M/d/yy H:mm");

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

        assertEquals("2020-03-25", converter.convertToWrite(LocalDateTime.of(2020, 03, 25, 23, 33, 19)));
    }

    @Test
    void parseCsvDateAnnotation() throws NoSuchFieldException {

        converter = spy(converter);

        converter.parseCsvDateAnnotation(TestWithAnnotationAndMultipleReadFormats.class.getDeclaredField("updated"));
        verify(converter).initialize("yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm:ss", "M/d/yy H:mm");

        converter.parseCsvDateAnnotation(TestWithAnnotationAndReadAndWriteFormat.class.getDeclaredField("updated"));
        verify(converter).initialize("yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss");

        assertThrows(IllegalArgumentException.class, () -> converter.parseCsvDateAnnotation(TestWithoutAnnotation.class.getDeclaredField("updated")));
    }

    static class TestWithAnnotationAndMultipleReadFormats {

        @CsvDate(value = "yyyy-MM-dd HH:mm:ss|M/d/yy H:mm")
        private LocalDateTime updated;

    }
    static class TestWithAnnotationAndReadAndWriteFormat {

        @CsvDate(value = "yyyy-MM-dd HH:mm:ss", writeFormat = "yyyy-MM-dd", writeFormatEqualsReadFormat = false)
        private LocalDateTime updated;

    }

    static class TestWithoutAnnotation {

        private LocalDateTime updated;
    }
}
