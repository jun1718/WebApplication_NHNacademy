package com.nhnacademy.servlet.domain;

import java.util.LinkedHashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Basket {
    private final Map<Food, Integer> basket = new LinkedHashMap<>();

    public void addFoodAtBasket(Food food, Integer count) {
        basket.put(food, count);
    }

    public Map<Food, Integer> getBasket() {
        return basket;
    }
}
