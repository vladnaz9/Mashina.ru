package servlets;

import models.User;
import services.UserService;
import utils.HashPassword;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        UserService userService = (UserService) req.getServletContext().getAttribute("userService");

        String userName = req.getParameter("userName");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String password_again = req.getParameter("password_again");
        String dop_inf = req.getParameter("dop_inf");
        System.out.println(userName + " " + email + " " + dop_inf + " " + password + " " + password_again);


        System.out.println("valid");
        password = HashPassword.getHash(password);

        if (userService.userIsExist(email)) {
            resp.sendRedirect("/login");
        } else {
            User user = User.builder()
                    .userName(userName)
                    .dop_inf(dop_inf)
                    .email(email)
                    .password(password)
                    .build();
            userService.addUser(user);
            req.getSession().setAttribute("user", user);
            resp.sendRedirect("/login");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("views/register.jsp").forward(request, response);
    }
}
