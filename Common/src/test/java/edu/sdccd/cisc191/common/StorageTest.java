package edu.sdccd.cisc191.common;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StorageTest {
    @Test
    void testStorage() {
        Storage testFridge = new Storage(3);

        FoodItem tomatoes = new FoodItem("Tomatoes", "Vegetable", 3.0f, FoodItem.convertToDate("03-23-2025"));
        FoodItem eggs = new FoodItem("Eggs", "Poultry", 12.0f, FoodItem.convertToDate("03-16-2025"));
        Drink milk = new Drink("Milk", "Dairy", 0.5f, Drink.convertToDate("04-16-2025"), true);
        Drink lemonade = new Drink("Lemonade", "Juice", 1.0f, Drink.convertToDate("05-02-2025"), false);

        testFridge.addFood(tomatoes);
        testFridge.addFood(eggs);
        testFridge.addFood(milk);

        Assertions.assertEquals(3, testFridge.getItemCount());
        Assertions.assertEquals("Eggs", testFridge.getFoodItem(1).getName());

        testFridge.removeFood("Eggs");
        Assertions.assertEquals(2, testFridge.getItemCount());
        Assertions.assertEquals("Milk", testFridge.getFoodItem(1).getName());
        assertNull(testFridge.getFoodItem(2));

        testFridge.addFood(eggs);
        testFridge.addFood(lemonade);
        Assertions.assertEquals(4, testFridge.getItemCount());
        Assertions.assertEquals(8, testFridge.getFridgeSize());

        testFridge.removeFood("Bacon");
        Assertions.assertEquals(4, testFridge.getItemCount());
    }
}
