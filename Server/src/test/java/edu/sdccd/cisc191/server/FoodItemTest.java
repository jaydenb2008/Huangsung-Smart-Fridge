package edu.sdccd.cisc191.server;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class FoodItemTest {
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
}
