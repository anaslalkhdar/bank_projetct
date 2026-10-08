package org.sid.bank_account_service.web;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class HomeRedirectFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String accept = request.getHeader("Accept");
        if ("GET".equals(request.getMethod())
                && "/".equals(request.getRequestURI())
                && accept != null
                && accept.contains("text/html")) {
            response.sendRedirect("/swagger-ui.html");
            return;
        }
        filterChain.doFilter(request, response);
    }
}
