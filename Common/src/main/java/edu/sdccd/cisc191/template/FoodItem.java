package edu.sdccd.cisc191.template;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class FoodItem {
    private String name;
    private String foodType;
    private double quantityLeft;
    private String expirationDate;


    public FoodItem(String name, String foodType, double quantityLeft, String expirationDate) {
        this.name = name;
        this.foodType = foodType;
        this.quantityLeft = quantityLeft;
        this.expirationDate = expirationDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public double getQuantityLeft() {
        return quantityLeft;
    }

    public void setQuantityLeft(double quantityLeft) {
        this.quantityLeft = quantityLeft;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public boolean isDateValid(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");

        try {
            LocalDate expiration = LocalDate.parse(date, formatter);
            System.out.println("This is a valid date");
            return true;
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date!");
        }

        return false;
    }
}
