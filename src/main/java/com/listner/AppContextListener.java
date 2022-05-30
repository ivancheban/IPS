package com.listner;

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

        //create database connection from init parameters and set it to context
        //DBConnectionManager dbManager = new DBConnectionManager(url, u, p);
        // ctx.setAttribute("DBManager", dbManager);
        // System.out.println("Database connection initialized for Application.");
    }
}
