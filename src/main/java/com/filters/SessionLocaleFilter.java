package com.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebFilter(filterName = "SessionLocaleFilter", urlPatterns = {"/*"})
    public class SessionLocaleFilter implements Filter {
        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
                throws IOException, ServletException {

            HttpServletRequest req = (HttpServletRequest) request;
            HttpSession currentSession = req.getSession();
            String langReq = req.getParameter("lang");

            if (langReq != null) {
                currentSession.setAttribute("lang", langReq);
            } else {
                if (currentSession.getAttribute("lang") == null) {
                    currentSession.setAttribute("lang", "ua");
                }
            }
            chain.doFilter(request, response);
        }

        @Override
        public void destroy() {
        }

        @Override
        public void init(FilterConfig arg0) throws ServletException {
        }
    }

