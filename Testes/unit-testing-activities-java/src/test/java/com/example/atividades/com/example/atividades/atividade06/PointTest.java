package com.example.atividades.atividade06;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.Random.class)
public class PointTest {

    @Test
    @DisplayName("Test distance to another point (positive coordinates)")
    public void testDistanceToAnotherPointPositive() {
        Point p1 = new Point(3, 4);
        Point p2 = new Point(0, 0);
        assertEquals(5.0, p1.distanceTo(p2), 0.001);
    }

    @Test
    @DisplayName("Test distance to the same point")
    public void testDistanceToSamePoint() {
        Point p1 = new Point(3, 4);
        assertEquals(0.0, p1.distanceTo(p1), 0.001);
    }

    @Test
    @DisplayName("Test distance to null point")
    public void testDistanceToNullPoint() {
        Point p1 = new Point(3, 4);
        assertThrows(IllegalArgumentException.class, () -> {
            p1.distanceTo(null);
        });
    }

    @Test
    @DisplayName("Test distance to another point (negative coordinates)")
    public void testDistanceToAnotherPointNegative() {
        Point p1 = new Point(-3, -4);
        Point p2 = new Point(0, 0);
        assertEquals(5.0, p1.distanceTo(p2), 0.001);
    }

    @Test
    @DisplayName("Test distance between two arbitrary points")
    public void testDistanceBetweenArbitraryPoints() {
        Point p1 = new Point(1, 2);
        Point p2 = new Point(4, 6);
        assertEquals(5.0, p1.distanceTo(p2), 0.001);
    }
}
