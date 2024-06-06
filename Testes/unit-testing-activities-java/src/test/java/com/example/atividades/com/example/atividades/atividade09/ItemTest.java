package com.example.atividades.atividade09;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.Random.class)
public class ItemTest {

    private final Faker faker = new Faker();

    @Test
    @DisplayName("Test item creation with valid name")
    public void testItemCreation() {
        String itemName = faker.commerce().productName();
        Item item = new Item(itemName);
        assertEquals(itemName, item.getName());
    }
}
