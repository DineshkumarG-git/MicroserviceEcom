package org.dinesh.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;



public class AuthFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest serverRequest ,ServletResponse serverResponse ,FilterChain chain) throws ServletException, IOException {
        var request  = (HttpServletRequest) serverRequest;
        var response = (HttpServletResponse) serverResponse;
         //request.getCookies()

        System.out.println("hello");
     //   chain.doFilter(request,response);
    }



}
