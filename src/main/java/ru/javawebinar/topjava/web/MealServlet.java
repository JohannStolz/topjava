package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.service.MealServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * Created by Johann Stolz 30.06.2018
 */

public class MealServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(MealServlet.class);
    MealService service = new MealServiceImpl();


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        log.debug("request to update/create page");
        String action = request.getParameter("action");

        if (action.equals("delete")) {
            int id = Integer.parseInt(request.getParameter("id"));
            service.deleteMealById(id);
            doGet(request, response);
        }
        if (action.equals("add")) {
            Meal meal = new Meal(null, "", 0);
            meal.setId(-1);
            request.setAttribute("meal", meal);
            request.getRequestDispatcher("/createOrUpdateMeal.jsp").forward(request, response);
        }

        if (action.equals("update")) {
            int id = Integer.parseInt(request.getParameter("id"));
            Meal mealById = service.getMealById(id);
            request.setAttribute("meal", mealById);
            request.getRequestDispatcher("/createOrUpdateMeal.jsp").forward(request, response);
        }

        if (action.equals("add/update")) {
            LocalDateTime localDateTime = LocalDateTime.parse(request.getParameter("dateTime"));
            String description = request.getParameter("description");
            int calories = Integer.parseInt(request.getParameter("calories"));
            int id = Integer.parseInt(request.getParameter("id"));
            Meal meal = new Meal(localDateTime, description, calories);
            if (id == -1) {
                service.createMeal(meal);
            } else {
                meal.setId(id);
                service.updateMeal(meal);
            }
            doGet(request, response);
        }

    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("request to meals get");
        List<MealWithExceed> mealWithExceeds = service.getMealWithExceed(LocalTime.MIN, LocalTime.MAX, 2000);
        request.setAttribute("MealWithExceedList", mealWithExceeds);
        request.getRequestDispatcher("/meals.jsp").forward(request, response);
    }
}
