package com.nhnacademy.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NowServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        Date date = new Date();
        log.debug("doGet called");
        log.debug("{}", date);

        try (PrintWriter out = resp.getWriter()) {
            out.println(date);

        } catch (IOException ex) {
            log.error("", ex);
        }
    }
}
