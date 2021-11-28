package com.trevorthompson;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestLevenshtein {
    protected Levenshtein levenshtein;
    TestLevenshtein() {
        this.levenshtein = new Levenshtein();
    }

    @Test
    public void testSingleDifferenceDistance() {
        String a = "string a";
        String b = "string b";

        assertEquals(1, levenshtein.getDistance(a, b));
    }

    @Test
    public void testIgnoresCapitalization() {
        String a = "string a";
        String b = "String B";

        assertEquals(1, levenshtein.getDistance(a, b));
    }

    @Test
    public void testCanIgnoreCapitalization() {
        String a = "string a";
        String b = "String B";

        levenshtein.setIgnoreCapitalization(false);

        assertEquals(2, levenshtein.getDistance(a, b));

        levenshtein.setIgnoreCapitalization(true);
    }

    @Test
    public void testCanUseMaximumDistanceChecking() {
        String a = "string aaaaa";
        String b = "stbing bbbbb";

        assertEquals(2, levenshtein.getDistance(a, b, 2));
    }

    @Test
    public void testComparingEmptyString() {
        String a = "";
        String b = "b";

        assertEquals(1, levenshtein.getDistance(a, b));
    }

    @Test
    public void testMaxHigherThanStringLengthRuns() {
        String a = "string a";
        String b = "string b";

        assertEquals(1, levenshtein.getDistance(a, b, 1000));
    }

    @Test
    public void testCanSetMaxLengthChecking() {
        String a = "nineteen characters a";
        String b = "nineteen characters b";

        assertEquals(0, levenshtein.getDistance(a, b, -1, 19));
    }
}
