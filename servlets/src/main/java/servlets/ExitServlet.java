package servlets;

import utils.DeleteCookies;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/logout")
public class ExitServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        DeleteCookies.delete(req, resp,"email", "password");

        req.getSession().setAttribute("user", null);
        req.getSession().setAttribute("order", null);

        resp.sendRedirect("/main");
    }
}