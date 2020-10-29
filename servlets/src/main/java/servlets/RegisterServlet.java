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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        UserService userService = (UserService) request.getServletContext().getAttribute("userService");

        String userName = request.getParameter("userName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String password_again = request.getParameter("password_again");
        String dop_inf = request.getParameter("dop_inf");
        System.out.println(userName + " " + email + " " + dop_inf + " " + password + " " + password_again);


        System.out.println("valid");
        password = HashPassword.getHash(password);

        if (userService.userIsExist(email)) {
            response.sendRedirect("/login");
        } else {
            User user = User.builder()
                    .userName(userName)
                    .dop_inf(dop_inf)
                    .email(email)
                    .password(password)
                    .build();
            userService.addUser(user);
            request.getSession().setAttribute("user", user);
            response.sendRedirect("/login");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("views/register.jsp").forward(request, response);
    }
}
