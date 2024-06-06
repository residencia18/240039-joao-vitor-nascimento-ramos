package com.example.atividades.atividade13;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;


@TestMethodOrder(MethodOrderer.Random.class)
public class FactorialTest {

    @Test
    @DisplayName("Test calculate method with positive number")
    public void testCalculateWithPositiveNumber() {
        Factorial factorial = new Factorial();
        assertEquals(120, factorial.calculate(5));
    }

    @Test
    @DisplayName("Test calculate method with zero")
    public void testCalculateWithZero() {
        Factorial factorial = new Factorial();
        assertEquals(1, factorial.calculate(0));
    }

    @Test
    @DisplayName("Test calculate method with negative number")
    public void testCalculateWithNegativeNumber() {
        Factorial factorial = new Factorial();
        assertThrows(IllegalArgumentException.class, () -> factorial.calculate(-5));
    }
}
