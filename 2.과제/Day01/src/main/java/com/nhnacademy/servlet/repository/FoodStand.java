package com.nhnacademy.servlet.repository;

import com.nhnacademy.servlet.domain.Customer;
import com.nhnacademy.servlet.domain.Food;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FoodStand {
    private final Map<String, Food> foods = new LinkedHashMap<>();

    public void add(Food food) {
        foods.put(food.getName(), food);
    }

    public boolean isOverCount(Customer customer) {
        log.debug("{}", customer.getHopeFoods());

        for (String hopeFood : customer.getHopeFoods().keySet()) {
            if (findFood(hopeFood).getCount() < customer.getHopeFoods().get(hopeFood)) return true;
        }
        return false;
    }

    public Food findFood(String hopeFood) {
        return foods.get(hopeFood);
    }

    public Map<String, Food> getFoods() {
        return foods;
    }

}
