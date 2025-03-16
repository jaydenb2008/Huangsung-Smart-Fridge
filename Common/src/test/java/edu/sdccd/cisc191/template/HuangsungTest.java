package edu.sdccd.cisc191.template;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class HuangsungTest {

    @Test
    void instantiateDrink() {
        Drink milk = new Drink("Milk", "Dairy", 0.5f, Drink.convertToDate("04-16-2025"), true);

        assertInstanceOf(FoodItem.class, milk);

        assertFalse(milk.getName().isEmpty());
        assertFalse(milk.getFoodType().isEmpty());

        assertFalse(milk.isExpired(milk.getExpirationDate()));

        if (!milk.isOpened()) {
            assertEquals(1, milk.getQuantityLeft());
        } else {
            assertFalse(milk.getQuantityLeft() > 1);
            assertFalse(milk.getQuantityLeft() < 0);
        }

    }
}