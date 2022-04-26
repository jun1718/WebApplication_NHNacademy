package com.nhnacademy.servlet.servlet;

import com.nhnacademy.servlet.domain.Customer;
import com.nhnacademy.servlet.domain.Food;
import com.nhnacademy.servlet.repository.FoodStand;
import com.nhnacademy.servlet.domain.NhnMart;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InitServlet extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        log.debug("InitServlet.init() called");
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        FoodStand foodStand = new FoodStand();

        foodStand.add(new Food("onion", Integer.parseInt(servletContext.getInitParameter("onion")), 2));
        foodStand.add(new Food("egg", Integer.parseInt(servletContext.getInitParameter("egg")), 5));
        foodStand.add(new Food("greenOnion", Integer.parseInt(servletContext.getInitParameter("greenOnion")), 10));
        foodStand.add(new Food("apple", Integer.parseInt(servletContext.getInitParameter("apple")), 20));

        NhnMart mart = new NhnMart(foodStand);
        servletContext.setAttribute("mart", mart);

        // 정책 : 식품 매대를 초기화하면 장바구니도 초기화된다.
        Customer customer = (Customer) servletContext.getAttribute("customer");
        if (customer != null) {
            customer.getBasket().clear();
        }
        resp.sendRedirect("/foods");
    }
}
