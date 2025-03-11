package edu.sdccd.cisc191.template;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HuangsungTest {
    @Test
    void instantiateFoodItem() {
        FoodItem eggs = new FoodItem("Eggs", "Poultry", 12);

        assertFalse(eggs.getName().isEmpty());
        assertFalse(eggs.getFoodType().isEmpty());
        assertTrue(eggs.getQuantityLeft() > 0);
    }

    @Test
    void instantiateDrink() {
        Drink milk = new Drink("Milk", "Dairy", 0.5, true);

        assertInstanceOf(FoodItem.class, milk);

        assertFalse(milk.getName().isEmpty());
        assertFalse(milk.getFoodType().isEmpty());

        if (!milk.isOpened()) {
            assertEquals(1, milk.getQuantityLeft());
        } else {
            assertFalse(milk.getQuantityLeft() > 1);
            assertFalse(milk.getQuantityLeft() < 0);
        }

    }
}