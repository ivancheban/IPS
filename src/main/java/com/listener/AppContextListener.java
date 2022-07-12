package com.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import static com.dao.DataSource.innitConfiguration;

@WebListener
public class AppContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext ctx = servletContextEvent.getServletContext();

        String url = ctx.getInitParameter("DBURL");
        String userName = ctx.getInitParameter("DBUSER");
        String password = ctx.getInitParameter("DBPWD");
        String driver = ctx.getInitParameter("DBDRIVER");

        innitConfiguration(driver, url, userName, password);

        String path = ctx.getRealPath("/WEB-INF/log4j2.log");
        System.setProperty("logFile", path);

        final Logger log = LogManager.getLogger(AppContextListener.class);
        log.debug("path = " + path);


    }
}
