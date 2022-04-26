package com.nhnacademy.servlet.servlet;

import com.nhnacademy.servlet.domain.Customer;
import com.nhnacademy.servlet.domain.Food;
import com.nhnacademy.servlet.repository.FoodStand;
import com.nhnacademy.servlet.domain.NhnMart;
import com.nhnacademy.servlet.service.MartService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        resp.setCharacterEncoding("UTF-8");
        if (servletContext.getAttribute("mart") == null) {
            resp.sendError(412, "사전조건 실패, 아직 마트 개장 안했습니다. 매대 초기화 되면 오세요.");
            return;
        }

        Customer customer = (Customer) servletContext.getAttribute("customer");
        if (customer == null) {
            resp.sendError(412, "사전조건 실패, 장바구니에 먼저 담으세요.");
            return;
        }

        String basic = "<!DOCTYPE html>\n" +
            "<html lang=\"en\">\n" +
            "<head>\n" +
            "    <meta charset=\"UTF-8\">\n" +
            "    <title>상품목록</title>\n" +
            "    <style>\n" +
            "        table {\n" +
            "            text-align: center;\n" +
            "            margin-left: auto;\n" +
            "            margin-right: auto;\n" +
            "            border: 1px solid;\n" +
            "            border-collapse: collapse;\n" +
            "            width: 70%;\n" +
            "        }\n" +
            "        th, td {\n" +
            "            border: 1px solid;\n" +
            "        }\n" +
            "        input {\n" +
            "            margin-left: auto;\n" +
            "            margin-right: auto;\n" +
            "        }\n" +
            "    </style>" +
            "</head>\n" +
            "<body>\n" +
            "<form method=\"post\" action=\"/cart\">\n" +
            "    <table>\n" +
            "        <tr>\n" +
            "            <th>상품명</th>\n" +
            "            <th>가격</th>\n" +
            "            <th>장바구니에 담은 수량</th>\n" +
            "        </tr>\n";


        StringBuilder sb = new StringBuilder(basic);
        Integer price = 0;
        for (Map.Entry<Food, Integer> foodEntry : customer.getBasket().entrySet()) {
            Food food = foodEntry.getKey();
            Integer basketCount = foodEntry.getValue();
            price += food.getPrice() * basketCount;
            String foodName = getFoodName(food.getName());

            sb.append("        <tr>\n");
            sb.append("            <td>" + foodName + "</td>\n");
            sb.append("            <td>" + food.getPrice() + "</td>\n");
            sb.append("            <td>" + basketCount + "</td>\n");
            sb.append("        </tr>\n");
        }

        sb.append("        <tr>\n")
            .append("            <td colspan = \"3\">총액 : " + price + "원</td>\n")
            .append("        </tr>\n")
            .append("    </table>\n")
            .append("</form>\n")
            .append("<a href = \"./\">초기화면 가기</a>")
            .append("</body>\n")
            .append("</html>");
        try (PrintWriter out = resp.getWriter()) {
            out.println(sb);
        }
    }

    private String getFoodName(String food) {
        String foodName = "";
        if (food.equals("onion")) {
            foodName = "양파";
        } else if (food.equals("egg")) {
            foodName = "계란";
        } else if (food.equals("greenOnion")) {
            foodName = "대파";
        } else if (food.equals("apple")) {
            foodName = "사과";
        }

        return foodName;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        NhnMart mart = (NhnMart) servletContext.getAttribute("mart");
        FoodStand foodStand = mart.getFoodStand();

        Customer customer = getCustomer(req);
        servletContext.setAttribute("customer", customer);

        if (MartService.isOverCount(foodStand, customer)) {
            resp.sendError(412, "사전조건 실패, 당신이 고른 갯수만큼 수량이 없습니다.");
            return;
        }
        MartService.addFoodsAtBasketOfCustomerAndMinusFoodCountOfFoodStand(foodStand, customer);

        resp.sendRedirect("/cart");
    }

    private Customer getCustomer(HttpServletRequest req) {
        String[] checkedFoods = req.getParameterValues("class");
        String[] checkedFoodCounts = req.getParameterValues("count");
        List<Integer> checkedFoodCountsToList = new ArrayList<>();

        for (String count : checkedFoodCounts) {
            if (count.isEmpty()) continue;
            checkedFoodCountsToList.add(Integer.parseInt(count));
        }

        log.debug("{}, {}", Arrays.toString(checkedFoods), checkedFoodCountsToList);
        Customer customer = new Customer();
        for (int i = 0; i < checkedFoods.length; i++) {
            customer.addHopeFoods(checkedFoods[i], checkedFoodCountsToList.get(i));
        }

        log.debug("{}", customer.getHopeFoods());
        return customer;
    }
}
