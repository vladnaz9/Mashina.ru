package servlets.controller;

import models.User;
import org.apache.commons.io.IOUtils;
import services.UserService;
import services.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

@MultipartConfig
public class DirUploadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uploadDir = getServletConfig().getInitParameter("uploadDir");
        Part file = request.getPart("filename");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        System.out.println(user);
        String filename = UUID.randomUUID().toString() +
                "-" +
                file.getSubmittedFileName();
        UserService userService = (UserService) request.getServletContext().getAttribute("userService");
        userService.setImage(filename, user.getId());
        user.setProfilePhoto(filename);
        IOUtils.copyLarge(
                file.getInputStream(),
                new FileOutputStream(uploadDir +
                        File.separator + filename
                )
        );

    }

}
