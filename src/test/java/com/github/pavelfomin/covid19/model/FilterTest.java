package com.github.pavelfomin.covid19.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FilterTest {

    @Test
    void matches() {

        assertFalse(new Filter(null, null).matches("", ""));
        assertFalse(new Filter("US", "Minnesota").matches("US", ""));

        assertTrue(new Filter("US", null).matches("US", ""));
        assertTrue(new Filter("US", null).matches("US", null));
        assertTrue(new Filter("US", "").matches("US", ""));
        assertTrue(new Filter("US", "").matches("US", null));
        assertTrue(new Filter("US", "Minnesota").matches("US", "Minnesota"));
    }
}