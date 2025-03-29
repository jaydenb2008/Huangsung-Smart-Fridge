package edu.sdccd.cisc191.template;

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
//Why i made this change.... marking isOpened as final ensures that it cannot be reassigned after instalization
//since isOpened is only set once in the constructor and is never reassigned using final makes the intent clear
//when a variable is final future people helping wiht the code know it wont be changed after its set
//this helps avoid bugs where someone might mistakenly try to do "isOpened = new SimpleBooleanProperty(true); //  "
//This would cause an error.



    public Drink(String name, String foodType, float quantityLeft, Date expirationDate, boolean isOpened) {
        super(name, foodType, quantityLeft, expirationDate);
        this.isOpened.set(isOpened);
    }
    //I suggest adding an ovveride here because it prevenets accidental meathod mismatches
    //if Food Item (the parent class) already has an isOpened() meathod, this meathod in drink is actually overriding it
    //adding @override ensures the compiler checks that the meathod truly overrides something
    //if you accidentally mispell the emathod name or change its parameters, the conpiler will throw an error, helping prevent subtle bugs.
    //In general just improves code clearity it will signal to anyone reading the code that this meathoe dis not new but is instead modifying behaiviro from the parent class


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
