package com.example.atividades.atividade09;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;

import java.util.List;

@TestMethodOrder(MethodOrderer.Random.class)
public class ItemCollectionTest {

    private final Faker faker = new Faker();

    @Test
    @DisplayName("Test adding a valid item")
    public void testAddValidItem() {
        ItemCollection itemCollection = new ItemCollection();
        String itemName = faker.commerce().productName();
        Item item = new Item(itemName);

        itemCollection.addItem(item);

        List<Item> items = itemCollection.getItems();
        assertEquals(1, items.size());
        assertEquals(itemName, items.get(0).getName());
    }

    @Test
    @DisplayName("Test adding a null item")
    public void testAddNullItem() {
        ItemCollection itemCollection = new ItemCollection();

        assertThrows(IllegalArgumentException.class, () -> itemCollection.addItem(null));
    }

    @Test
    @DisplayName("Test removing an existing item")
    public void testRemoveExistingItem() {
        ItemCollection itemCollection = new ItemCollection();
        String itemName = faker.commerce().productName();
        Item item = new Item(itemName);
        itemCollection.addItem(item);

        itemCollection.removeItem(item);

        List<Item> items = itemCollection.getItems();
        assertTrue(items.isEmpty());
    }

    @Test
    @DisplayName("Test removing a non-existing item")
    public void testRemoveNonExistingItem() {
        ItemCollection itemCollection = new ItemCollection();
        String itemName = faker.commerce().productName();
        Item item = new Item(itemName);

        assertThrows(IllegalArgumentException.class, () -> itemCollection.removeItem(item));
    }

    @Test
    @DisplayName("Test getting items from an empty collection")
    public void testGetItemsFromEmptyCollection() {
        ItemCollection itemCollection = new ItemCollection();

        List<Item> items = itemCollection.getItems();
        assertTrue(items.isEmpty());
    }
}
