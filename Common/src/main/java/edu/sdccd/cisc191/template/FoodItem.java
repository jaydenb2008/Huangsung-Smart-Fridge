package edu.sdccd.cisc191.template;

public class FoodItem {
    private String name;
    private String foodType;
    private double quantityLeft;


    public FoodItem(String name, String foodType, double quantityLeft) {
        this.name = name;
        this.foodType = foodType;
        this.quantityLeft = quantityLeft;
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
}
