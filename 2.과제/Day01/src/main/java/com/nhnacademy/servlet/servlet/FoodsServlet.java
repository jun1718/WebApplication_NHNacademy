package com.nhnacademy.servlet.servlet;

import com.nhnacademy.servlet.domain.Food;
import com.nhnacademy.servlet.domain.NhnMart;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FoodsServlet extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        log.debug("FoodsServlet.init() called");
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        ServletContext servletContext = getServletContext();
        NhnMart mart = (NhnMart) servletContext.getAttribute("mart");

        if (mart == null) {
            resp.sendError(412, "사전조건 실패, 아직 마트 개장 안했습니다. 매대 초기화 되면 오세요.");
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
            "<form method=\"post\" action=\"/cart\">" +
            "    <table>\n" +
            "        <tr>\n" +
            "            <th>상품명</th>\n" +
            "            <th>가격</th>\n" +
            "            <th>수량</th>\n" +
            "            <th>선택</th>\n" +
            "            <th>갯수</th>\n" +
            "        </tr>\n";

        StringBuilder sb = new StringBuilder(basic);


        for (Map.Entry<String, Food> food : mart.getFoodStand().getFoods().entrySet()) {
            String foodName = getFoodName(food.getKey());

            sb.append("        <tr>\n");
            sb.append("            <td>" + foodName + "</td>\n");
            sb.append("            <td>" + food.getValue().getPrice() + "</td>\n");
            sb.append("            <td>" + food.getValue().getCount() + "</td>\n");
            sb.append("            <td><input type=\"checkbox\" name=\"class\"value=\"" + food.getValue().getName() + "\" /></td>\n");
            sb.append("            <td><input type=\"text\" name=\"count\" placeholder = \"수량보다 적거나 같아야합니다.)\" width = \"100%\"/></td>\n");
            sb.append("        </tr>\n");
            log.debug("{}", food.getValue().getName());
        }

        sb.append("        <tr>\n")
            .append("            <td colspan = \"5\"><input type=\"submit\" value=\"장바구니에 담기\"/></td>\n")
            .append("        </tr>\n")
            .append("    </table>\n")
            .append("</form>")
            .append("<a href = \"./\">초기화면 가기</a>")
            .append("</body>\n")
            .append("</html>");

        try(PrintWriter out = resp.getWriter()) {
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
}
