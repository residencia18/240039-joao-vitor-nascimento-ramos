package com.example.atividades.atividade11;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;


@TestMethodOrder(MethodOrderer.Random.class)
public class CustomSortTest {

    @Test
    @DisplayName("Test customSort method with positive numbers")
    public void testCustomSortWithPositiveNumbers() {
        CustomSort customSort = new CustomSort();
        List<Integer> numbers = Arrays.asList(5, 3, 9, 1, 7);
        List<Integer> sortedNumbers = customSort.customSort(numbers);
        List<Integer> expected = Arrays.asList(9, 7, 5, 3, 1);
        assertEquals(expected, sortedNumbers);
    }

    @Test
    @DisplayName("Test customSort method with negative numbers")
    public void testCustomSortWithNegativeNumbers() {
        CustomSort customSort = new CustomSort();
        List<Integer> numbers = Arrays.asList(-5, -3, -9, -1, -7);
        List<Integer> sortedNumbers = customSort.customSort(numbers);
        List<Integer> expected = Arrays.asList(-1, -3, -5, -7, -9);
        assertEquals(expected, sortedNumbers);
    }

    @Test
    @DisplayName("Test customSort method with mixed numbers")
    public void testCustomSortWithMixedNumbers() {
        CustomSort customSort = new CustomSort();
        List<Integer> numbers = Arrays.asList(-5, 3, -9, 1, -7);
        List<Integer> sortedNumbers = customSort.customSort(numbers);
        List<Integer> expected = Arrays.asList(3, 1, -5, -7, -9);
        assertEquals(expected, sortedNumbers);
    }

    @Test
    @DisplayName("Test customSort method with empty list")
    public void testCustomSortWithEmptyList() {
        CustomSort customSort = new CustomSort();
        List<Integer> numbers = Arrays.asList();
        List<Integer> sortedNumbers = customSort.customSort(numbers);
        assertTrue(sortedNumbers.isEmpty());
    }
}
