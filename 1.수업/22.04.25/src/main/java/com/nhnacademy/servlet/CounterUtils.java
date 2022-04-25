package com.nhnacademy.servlet;

import java.util.Optional;
import javax.servlet.ServletContext;

public class CounterUtils {
    public CounterUtils() {
        throw new IllegalArgumentException("Util");
    }

    public static void increaseCounter(ServletContext context) {
        Integer count = Optional.ofNullable((Integer) context.getAttribute("counter")).orElse(0);
        count++;
        context.setAttribute("counter", count);
    }
}
