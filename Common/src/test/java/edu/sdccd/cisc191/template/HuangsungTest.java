package edu.sdccd.cisc191.template;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HuangsungTest {
    @Test
    void instantiateFoodItem() {
        FoodItem eggs = new FoodItem("Eggs", "Poultry", 12, "03-11-2025");

        assertFalse(eggs.getName().isEmpty());
        assertFalse(eggs.getFoodType().isEmpty());
        assertTrue(eggs.getQuantityLeft() > 0);

        assertTrue(eggs.isDateValid(eggs.getExpirationDate()));
        assertTrue(eggs.isExpired(eggs.getExpirationDate()));
    }

    @Test
    void instantiateDrink() {
        Drink milk = new Drink("Milk", "Dairy", 0.5, "2025-04-23", true);

        assertInstanceOf(FoodItem.class, milk);

        assertFalse(milk.getName().isEmpty());
        assertFalse(milk.getFoodType().isEmpty());

        assertFalse(milk.isDateValid(milk.getExpirationDate()));

        milk.setExpirationDate("04-23-2025");
        assertFalse(milk.isExpired(milk.getExpirationDate()));

        if (!milk.isOpened()) {
            assertEquals(1, milk.getQuantityLeft());
        } else {
            assertFalse(milk.getQuantityLeft() > 1);
            assertFalse(milk.getQuantityLeft() < 0);
        }

    }
}