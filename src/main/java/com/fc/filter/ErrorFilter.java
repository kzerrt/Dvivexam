package com.fc.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author Florence
 * @since 2023/06/12
 */


public class ErrorFilter implements Filter {
    public static final String ERROR_URL = "/handleError";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            filterChain.doFilter(servletRequest,servletResponse);
        } catch (Exception e) {
            servletRequest.setAttribute(ERROR_URL, e);
            servletRequest.getRequestDispatcher(ERROR_URL).forward(servletRequest,servletResponse);
        }
    }
}
