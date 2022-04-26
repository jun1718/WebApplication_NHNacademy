package com.nhnacademy.servlet.service;

import com.nhnacademy.servlet.domain.Customer;
import com.nhnacademy.servlet.domain.Food;
import com.nhnacademy.servlet.repository.FoodStand;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MartService {
    public static boolean isOverCount(FoodStand foodStand, Customer customer) {
        return foodStand.isOverCount(customer);
    }

    public static void addFoodsAtBasketOfCustomerAndMinusFoodCountOfFoodStand(FoodStand foodStand, Customer customer) {
        for (Map.Entry<String, Integer> hopeEntry : customer.getHopeFoods().entrySet()) {
            Food food = foodStand.findFood(hopeEntry.getKey());

            customer.addFoodAtBasket(food, hopeEntry.getValue());
            food.setCount(food.getCount() - hopeEntry.getValue());

            log.debug("customer.getBasket : {}", customer.getBasket());
        }
    }
}
