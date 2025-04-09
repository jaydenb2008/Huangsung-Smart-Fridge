package edu.sdccd.cisc191.common;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HuangsungTest {

    @Test
    void instantiateDrink() {
        Drink milk = new Drink("Milk", "Dairy", 0.5f, Drink.convertToDate("04-16-2025"), true);

        assertTrue(milk instanceof FoodItem);

        assertFalse(milk.getName().isEmpty());
        assertFalse(milk.getFoodType().isEmpty());

        assertFalse(milk.isExpired(milk.getExpirationDate()));

        milk.setExpirationDate(Drink.convertToDate("02-12-2025"));
        assertTrue(milk.isExpired(milk.getExpirationDate()));

        if (!milk.isOpened()) {
            assertEquals(1, milk.getQuantityLeft());
        } else {
            assertFalse(milk.getQuantityLeft() > 1);
            assertFalse(milk.getQuantityLeft() < 0);
        }
    }

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

        assertEquals(3, testFridge.getItemCount());
        assertEquals("Eggs", testFridge.getFoodItem(1).getName());

        testFridge.removeFood("Eggs");
        assertEquals(2, testFridge.getItemCount());
        assertEquals("Milk", testFridge.getFoodItem(1).getName());
        assertNull(testFridge.getFoodItem(2));

        testFridge.addFood(eggs);
        testFridge.addFood(lemonade);
        assertEquals(4, testFridge.getItemCount());
        assertEquals(8, testFridge.getFridgeSize());

        testFridge.removeFood("Bacon");
        assertEquals(4, testFridge.getItemCount());
    }

    @Test
    void testNotifierThread() throws InterruptedException {
        Storage testFridge = new Storage(4);

        FoodItem tomatoes = new FoodItem("Tomatoes", "Vegetable", 3.0f, FoodItem.convertToDate("03-18-2025"));
        FoodItem eggs = new FoodItem("Eggs", "Poultry", 12.0f, FoodItem.convertToDate("03-16-2027"));
        Drink milk = new Drink("Milk", "Dairy", 0.5f, Drink.convertToDate("04-16-2024"), true);
        Drink lemonade = new Drink("Lemonade", "Juice", 1.0f, Drink.convertToDate("05-02-2028"), false);

        testFridge.addFood(tomatoes);
        testFridge.addFood(eggs);
        testFridge.addFood(milk);
        testFridge.addFood(lemonade);

        int notifierCount = testFridge.getFridgeSize();
        Notifier[] notifiers = new Notifier[notifierCount];

        FridgeManager dummyManager = new FridgeManager();  // Dummy FridgeManager
        Storage testFridge2 = new Storage(4);
        UI dummyUI = new UI(dummyManager, testFridge2);

        for (int i = 0; i < notifierCount; i++) {
            notifiers[i] = new Notifier("Expiration Notifier " + i, testFridge, dummyUI);
        }

        for (int i = 0; i < notifierCount; i++) {
            notifiers[i].start();
        }

        for (int i = 0; i < notifierCount; i++) {
            notifiers[i].join();
        }

        int expiredCount = 0;
        for (int i = 0; i < testFridge.getItemCount(); i++) {
            FoodItem item = testFridge.getFoodItem(i);
            if(item.isExpired(item.getExpirationDate())) {
                expiredCount++;
            }
        }

        assertEquals(2, expiredCount);
    }
}