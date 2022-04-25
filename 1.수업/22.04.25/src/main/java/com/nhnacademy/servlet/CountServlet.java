package com.nhnacademy.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jsoup.Jsoup;

public class CountServlet extends HttpServlet {
    private int count;

    @Override
    public void init(ServletConfig config) throws ServletException {
        count = Integer.parseInt(config.getInitParameter("value"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {

        resp.setContentType("text/plain");
        count++;

        PrintWriter out = resp.getWriter();
        out.println("count : " + count);

    }
}
