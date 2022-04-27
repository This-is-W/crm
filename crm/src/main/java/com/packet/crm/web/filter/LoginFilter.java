package com.packet.crm.web.filter;

import com.packet.crm.settings.domain.User;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        String path = request.getServletPath();

        //记得斜杠/不要漏了
        if ("/login.jsp".equals(path) || "/settings/user/login.do".equals(path)) {
        //不应该拦截，发出放行请求
            chain.doFilter(request, response);

        } else {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");

            if (user != null) {
                //不是null，说明有session记录，已经有登陆过的资源
                chain.doFilter(request, response);

            } else {
                //重定向
                response.sendRedirect(request.getContextPath() + "/login.jsp");

            }
        }
    }
}
