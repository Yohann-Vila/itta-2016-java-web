/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filter;

import business.User;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 *
 * @author Administrator
 */
public class adminFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);
        boolean allowRequest = false;
        if (null != session) {
            if (session.getAttribute("user") != null && ((User) session.getAttribute("user")).getSeclevel() == User.ADMINISTRATEUR) {
                allowRequest = true;
            }
        }
        if (!allowRequest) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp?redirect=/admincontroller");
            return;
        }
        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {
    }

}
