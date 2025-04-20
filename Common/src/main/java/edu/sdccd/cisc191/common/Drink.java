package edu.sdccd.cisc191.common;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

import java.util.Date;

/**
 * This class inherits from the FoodItem class and describes the attributes and behavior of a drink object
 * boolean isOpened: true if the user has opened the drink, false if unopened
 * float quantityLeft: inherited from FoodItem but is used to describe the fraction of the drink remaining (i.e. 0.5f of the bottle of lemonade remaining)
 */
public class Drink extends FoodItem {

    //SimpleBooleanProperty used to notify UI changes
    private final BooleanProperty isOpened = new SimpleBooleanProperty(false);
    // Set false to display default drink is not opened

    public Drink(String name, String foodType, float quantityLeft, Date expirationDate, boolean isOpened) {
        super(name, foodType, quantityLeft, expirationDate);
        this.isOpened.set(isOpened);
    }

    public boolean isOpened() {
        return isOpened.get();
    }
    // .get() and.set() used for javaFX binding and helps checkboxes in UI work properly
    public void setOpened(boolean opened) {
        isOpened.set(opened);
    }
    public BooleanProperty isOpenedProperty() {
        return isOpened; }
}
