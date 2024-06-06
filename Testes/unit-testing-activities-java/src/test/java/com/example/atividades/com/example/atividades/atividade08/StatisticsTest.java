package com.example.atividades.atividade08;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;

import java.util.Arrays;
import java.util.Collections;

@TestMethodOrder(MethodOrderer.Random.class)
public class StatisticsTest {

    @Test
    @DisplayName("Test calculating average of a valid list of positive integers")
    public void testCalculateAverageValidList() {
        Statistics stats = new Statistics();
        assertEquals(3.0, stats.calculateAverage(Arrays.asList(1, 2, 3, 4, 5)), 0.001);
    }

    @Test
    @DisplayName("Test calculating average of a valid list with negative and positive integers")
    public void testCalculateAverageMixedList() {
        Statistics stats = new Statistics();
        assertEquals(0.0, stats.calculateAverage(Arrays.asList(-3, -1, 0, 1, 3)), 0.001);
    }

    @Test
    @DisplayName("Test calculating average of a valid list with one integer")
    public void testCalculateAverageSingleElementList() {
        Statistics stats = new Statistics();
        assertEquals(5.0, stats.calculateAverage(Arrays.asList(5)), 0.001);
    }

    @Test
    @DisplayName("Test calculating average of a null list")
    public void testCalculateAverageNullList() {
        Statistics stats = new Statistics();
        assertThrows(IllegalArgumentException.class, () -> stats.calculateAverage(null));
    }

    @Test
    @DisplayName("Test calculating average of an empty list")
    public void testCalculateAverageEmptyList() {
        Statistics stats = new Statistics();
        assertThrows(IllegalArgumentException.class, () -> stats.calculateAverage(Collections.emptyList()));
    }
}
