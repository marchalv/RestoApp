package com.example.restoapp.models;

import java.util.ArrayList;

public class Order {
    private ArrayList<Meal> meals;

    public Order() {
        this.meals = new ArrayList<Meal>();
    }

    public void addMeal(Meal meal) {
        meals.add(meal);
    }

    public void removeMeal(Meal meal) {
        meals.remove(meal);
    }

    public ArrayList<Meal> getMeals() {
        return meals;
    }

    public void setMeals(ArrayList<Meal> meals) {
        this.meals = meals;
    }

}
