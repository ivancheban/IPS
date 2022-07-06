//package com.filters;
//
//
//
//
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//
//@WebFilter(urlPatterns = {"/user/cabinet"})
//public class AuthenticationFilter implements Filter {
//    private static final Logger logger = LogManager.getLogger(AuthenticationFilter.class);
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException {
//        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
//        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
//        HttpSession session = httpRequest.getSession();
//
//
//
//        try {
//
//            String jwt = (String) session.getAttribute("token");
//
//            if (jwt != null && !jwt.isEmpty()) {
//
//
//
//                filterChain.doFilter(servletRequest, servletResponse);
//
//            } else {
//                httpResponse.sendRedirect("/login");
//            }
//
//
//        } catch (final Exception e) {
//            logger.debug("Failed logging in with security token");
//            httpResponse.sendRedirect("/login.jsp");
//        }
//    }
//}
