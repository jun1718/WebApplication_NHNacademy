package com.nhnacademy.servlet.domain;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class Customer {
    private final Map<String, Integer> hopeFoods = new LinkedHashMap<>();
    private final Basket basket = new Basket();

    public void addHopeFoods(String foodName, Integer count) {
        hopeFoods.put(foodName, count);
    }

    public Map<String, Integer> getHopeFoods() {
        return hopeFoods;
    }

    public void addFoodAtBasket(Food food, Integer value) {
        basket.addFoodAtBasket(food, value);
    }

    public Map<Food, Integer> getBasket() {
        return basket.getBasket();
    }
}
