package servlets;


import models.User;
import services.UserService;
import services.UserServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("editProfile")
@MultipartConfig
class EditProfileServlet extends HttpServlet {
    private UserService userService;



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user;
        user = (User) req.getSession().getAttribute("userService");
        req.setAttribute("user", user);
        if (user != null) {
            req.setAttribute("password", user.getPassword());
            req.setAttribute("name", user.getUserName());
            req.setAttribute("email", user.getEmail());
            req.getRequestDispatcher("/edit.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String emailUser = (String) session.getAttribute("userService");
        String nameUser = "";
        User us = null;
        us = userService.getUser(emailUser);
        nameUser = us.getUserName();


        String email = request.getParameter("email");
        String name = request.getParameter("name");
        User user = null;
        boolean hasError = false;
        String errorString = null;
        if (email == null || email.length() == 0 || name == null || name.length() == 0) {
            hasError = true;
            errorString = "Заполните все поля формы редактирования профиля";
        }


        if (email != null && (!email.equals(emailUser))) {
            user = userService.getUser(email.trim());
        }

        if (user != null) {
            hasError = true;
        }

        if (hasError) {
            response.sendRedirect("/editProfile");
        } else {
            Part file = request.getPart("filename");
            String imgName = "";
            if (name.equals(nameUser)) {
                user.setUserName(nameUser);
            } else {
                user.setUserName(name);
            }
            if (email.equals(emailUser)) {
                user.setEmail(emailUser);
            } else {
                user.setEmail(email);
                session.setAttribute("userServise", email);
                Cookie cookieUser = new Cookie("userEmail", email);
                cookieUser.setMaxAge(60 * 60 * 24 * 365);
                response.addCookie(cookieUser);
            }
            userService.update(user);

            response.sendRedirect("/profile");
        }
    }
}