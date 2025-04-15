package edu.sdccd.cisc191.common;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class FoodItemTest {
    @Test
    void instantiateDrink() {
        Drink milk = new Drink("Milk", "Dairy", 0.5f, Drink.convertToDate("04-16-2030"), true);

        assertTrue(milk instanceof FoodItem);

        Assertions.assertFalse(milk.getName().isEmpty());
        Assertions.assertFalse(milk.getFoodType().isEmpty());

        Assertions.assertFalse(milk.isExpired(milk.getExpirationDate()));

        milk.setExpirationDate(Drink.convertToDate("02-12-2025"));
        Assertions.assertTrue(milk.isExpired(milk.getExpirationDate()));

        if (!milk.isOpened()) {
            Assertions.assertEquals(1, milk.getQuantityLeft());
        } else {
            assertFalse(milk.getQuantityLeft() > 1);
            assertFalse(milk.getQuantityLeft() < 0);
        }
    }
}
