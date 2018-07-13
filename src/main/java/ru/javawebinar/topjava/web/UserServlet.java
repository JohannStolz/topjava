package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.util.exception.NotFoundException;
import ru.javawebinar.topjava.web.meal.MealRestController;
import ru.javawebinar.topjava.web.user.ProfileRestController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.slf4j.LoggerFactory.getLogger;

public class UserServlet extends HttpServlet {
    private static final Logger log = getLogger(UserServlet.class);

    private ProfileRestController controller;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            controller = appCtx.getBean(ProfileRestController.class);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("forward to users");
        if (AuthorizedUser.getUser() != null) {
            request.setAttribute("loginUserName", AuthorizedUser.getUser().getName());
        }
        request.getRequestDispatcher("/users.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("do post");
        AuthorizedUser.setUser(new User(Integer.parseInt(request.getParameter("value")), "mockUser", "mock@mail.com", "password", Role.ROLE_USER));
        response.sendRedirect("meals");
    }
}


       /* if (AuthorizedUser.getUser() != null)
            request.setAttribute("loginUserName", AuthorizedUser.getUser().getName());

        String action = request.getParameter("action");
        if (action != null) {
            String password = request.getParameter("password");
            String email = request.getParameter("email");


            if (action.equals("register")) {
                try {
                    controller.getByMail(email);
                    log.info("user with email already exist", email);
                    request.getRequestDispatcher("/users.jsp").forward(request, response);
                } catch (NotFoundException e) {
                    String userName = request.getParameter("userName");
                    Integer calories = Integer.parseInt(request.getParameter("calories"));
                    User user = new User(null, userName, email, password, calories, true, null);
                    controller.create(user);
                    request.getRequestDispatcher("/users.jsp").forward(request, response);
                }
            }
            if (action.equals("login") && email != "" && password != "") {
                try {
                    User loggedInUser = controller.getByMail(email);
                    if (loggedInUser.getPassword().equals(password))
                        AuthorizedUser.setUser(loggedInUser);
                    request.getRequestDispatcher("/users.jsp").forward(request, response);
                } catch (NotFoundException e ) {
                    log.info("user with email not exist", email);
                }
            } else {
                request.getRequestDispatcher("/users.jsp").forward(request, response);
            }
        } */
