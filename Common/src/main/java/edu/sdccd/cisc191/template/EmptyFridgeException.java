package edu.sdccd.cisc191.template;

/**
 * an exception used when the 2d array fridge does not have any food items in it
 * outputs a message notifying of empty fridge
 */
public class EmptyFridgeException extends Exception {
    public EmptyFridgeException(String message) {
        super(message);
    }
}
