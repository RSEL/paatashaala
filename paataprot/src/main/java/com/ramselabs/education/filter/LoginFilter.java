package com.ramselabs.education.filter;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ramselabs.education.controller.LoginController;

/**
 * Filter checks if LoginBean has loginIn property set to true.
 * If it is not set then request is being redirected to the login.xhml page.
 *
 *
 *
 */
public class LoginFilter implements Filter {
 
    /**
     * Checks if user is logged in. If not it redirects to the login.xhtml page.
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // Get the loginController from session attribute
        LoginController loginController = (LoginController)((HttpServletRequest)request).getSession().getAttribute("loginController");
         HttpServletRequest req=(HttpServletRequest)request;
        
         HttpServletResponse res=(HttpServletResponse)response;
        if (loginController == null || !loginController.isLoggedIn()) {
        	String contextPath = req.getContextPath();
        	 
                res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
                res.setHeader("Pragma", "no-cache"); // HTTP 1.0.
                res.setDateHeader("Expires", 0); // Proxies.
                res.sendRedirect(contextPath + "/views/FrontPage.xhtml");
          }
         
        chain.doFilter(request, response);
             
    }
 
    public void init(FilterConfig config) throws ServletException {
       System.out.println("filter-init");
    }
 
    public void destroy() {
        // Nothing to do here!
    }  
     
}