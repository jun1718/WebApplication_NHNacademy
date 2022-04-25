package com.nhnacademy.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jsoup.Jsoup;

public class HtmlServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        String html = req.getParameter("html");
        String[] class1 = req.getParameterValues("class");

        resp.setContentType("text/plain");


        PrintWriter out = resp.getWriter();
        out.println(Jsoup.parse(html).toString());

    }
}
