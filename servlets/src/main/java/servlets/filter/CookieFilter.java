package servlets.filter;

import models.User;

import services.UserService;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebFilter(filterName = "cookieFilter", urlPatterns = {
        "/main", "/profile", "/basket",  "/catalog", "/editProfile",
        "/promo"})

public class CookieFilter implements Filter {

    public CookieFilter() {
    }

    @Override
    public void init(FilterConfig fConfig)  {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();

        String flag = "";
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("userEmail".equals(cookie.getName())) {
                    flag = cookie.getValue();
                }
            }
        }

        if (flag != null) {
            UserService userService = (UserService) req.getServletContext().getAttribute("userService");
            User user;
            user = userService.getUser(flag);
            if (user != null) {
                session.setAttribute("loginedUser", user.getEmail());
            }
        }

        chain.doFilter(request, response);
    }
}