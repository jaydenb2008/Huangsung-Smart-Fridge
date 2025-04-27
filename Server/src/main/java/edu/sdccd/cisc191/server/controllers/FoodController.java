package edu.sdccd.cisc191.server.controllers;

import edu.sdccd.cisc191.common.FoodItem;
import edu.sdccd.cisc191.server.repositories.FoodRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FoodController {
    @Autowired
    private FoodRepo foodRepo;

    @GetMapping(value = "/")
    public String getPage() {
        return "welcome";
    }

    @GetMapping(value = "/fooditems")
    public List<FoodItem> getFoodItems() {
        return foodRepo.findAll();
    }

    @PostMapping(value = "/save")
    public String saveFoodItem(@RequestBody FoodItem food) {
        foodRepo.save(food);
        return "Saved...";
    }

    @PutMapping(value = "update/{id}")
    public String updateFoodItem(@PathVariable long id, @RequestBody FoodItem food) {
        FoodItem updatedFood = foodRepo.findById(id).get();
        updatedFood.setName(food.getName());
        updatedFood.setFoodType(food.getFoodType());
        updatedFood.setExpirationDate(food.getExpirationDate());
        updatedFood.setQuantityLeft(food.getQuantityLeft());
        foodRepo.save(updatedFood);
        return "Updated...";
    }

    @DeleteMapping(value = "delete/{id}")
    public String deleteFoodItem(@PathVariable long id) {
        FoodItem deletedFood = foodRepo.findById(id).get();
        foodRepo.delete(deletedFood);
        return "Deleted " + deletedFood.getName();
    }
}
