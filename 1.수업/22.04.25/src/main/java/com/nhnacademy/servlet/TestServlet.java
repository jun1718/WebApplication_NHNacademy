package com.nhnacademy.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j
public class TestServlet extends HttpServlet {
//    private static final Logger LOGGER = LoggerFactory.getLogger(TestServlet.class);
    private String title;
    private String name;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        log.error("service() called");
        super.service(req, resp);
    }

    @Override
    public void destroy() {
        log.error("destroy() called");
        super.destroy();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        log.debug("init() called");
        super.init(config);

        title = config.getInitParameter("title");
        name = config.getInitParameter("name");

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {

        try (PrintWriter out = resp.getWriter()) {
//        String title = getServletConfig().getInitParameter("title");
//        String name = getServletConfig().getInitParameter("name");
            out.println("Hello, Servlet" + title + " " + name);

        } catch (IOException ex) {
            log.error("", ex);
        }

    }
}
