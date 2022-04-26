package com.nhnacademy.servlet.domain;

import com.nhnacademy.servlet.repository.FoodStand;

public class NhnMart {
    private final FoodStand foodStand;

    public NhnMart(FoodStand foodStand) {
        this.foodStand = foodStand;
    }

    public FoodStand getFoodStand() {
        return this.foodStand;
    }
}
