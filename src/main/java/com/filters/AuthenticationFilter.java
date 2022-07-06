package com.filters;

import com.auth.JWTService;

import com.auth.Payload;
import com.service.UserService;
import com.service.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/tariffs"})
public class AuthenticationFilter implements Filter {
    private static final Logger logger = LogManager.getLogger(AuthenticationFilter.class);


//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        Filter.super.init(filterConfig);
//    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {


    }


//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException {
//        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
//        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
//        HttpSession session = httpRequest.getSession();
//        JWTService jwtService = new JWTService();
//        UserService userService = new UserServiceImpl();
//
//        try {
//
//            String jwt = (String) session.getAttribute("token");
//
//            if (jwt != null && !jwt.isEmpty()) {
//                Payload payload = jwtService.verifyToken(jwt);
//                String phone= payload.getClaims().get("phone");
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


}
