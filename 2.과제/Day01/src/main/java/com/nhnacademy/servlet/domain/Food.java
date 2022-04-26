package com.nhnacademy.servlet.domain;

public class Food {
    private final String name;
    private final int price;
    private int count;

    public Food(String name, int price, int count) {
        this.name = name;
        this.price = price;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return this.count;
    }

    @Override
    public String toString() {
        return "Food{" +
            "name='" + name + '\'' +
            ", price=" + price +
            ", count=" + count +
            '}';
    }
}
