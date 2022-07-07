package com.filters;

import com.auth.JWTService;
import com.auth.Payload;
import com.model.Role;
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

@WebFilter(urlPatterns = {"/admin.jsp","/user-list.jsp","/user-update.jsp","/tariff-update.jsp","/tariff-list.jsp","/subscriptions-list.jsp",
        "/subscription-update.jsp","/add/subscription","/add/tariff/service","/delete/subscription","/subscriptions","/update/subscription","/update/form",
        "/addTariff","/delete/tariff","/tariffs","/update-tariff/form","/update/tariff","/user/delete","/user/find","/user.do",
        "/user/update","/edit/form"})
public class AdminAuthenticationFilter implements Filter {
    private static Logger logger = LogManager.getLogger(AdminAuthenticationFilter.class);
    private static final int STATUS_CODE_UNAUTHORIZED = 401;
     @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        HttpSession session = httpRequest.getSession();
        JWTService jwtService = new JWTService();
        UserService userService = new UserServiceImpl();

        try {
            String jwt = (String) session.getAttribute("token");

            if (jwt != null && !jwt.isEmpty()) {
                Payload payload = jwtService.verifyToken(jwt);
                String phone = payload.getClaims().get("phoneNumber");
                Role role = userService.findByPhoneNumber(phone).getRole();

                if (role.equals(Role.ADMIN)){
                    filterChain.doFilter(servletRequest, servletResponse);
                }  else {
                    RequestDispatcher requestDispatcher = httpRequest.getRequestDispatcher("/index.jsp");
                    requestDispatcher.forward(servletRequest, servletResponse);
                }

            } else {
                httpResponse.sendRedirect("/login.jsp");
            }


        } catch (final Exception e) {
            logger.debug("Failed logging in with security token");
            httpResponse.sendRedirect("/login.jsp");
        }
    }
}
