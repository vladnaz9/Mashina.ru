package servlets;

import models.User;
import services.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("views/profile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user;
        user = (User) req.getSession().getAttribute("user");
        req.setAttribute("user", user);
        UserService userService = (UserService) req.getServletContext().getAttribute("userService");
        String email = user.getEmail();
        if (userService.getUser(email).getEmail().equals(email)) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("views/profile.jsp");
            dispatcher.forward(req, resp);
        } else {
            RequestDispatcher dispatcher = req.getRequestDispatcher("views/main.jsp");
            dispatcher.include(req,resp);
        }
    }
}
