package ru.javawebinar.topjava;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.web.meal.MealRestController;
import ru.javawebinar.topjava.web.user.AdminRestController;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;

public class SpringMain {
    public static void main(String[] args) {
        // java 7 Automatic resource management
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            System.out.println();
            System.out.println("Beans : " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            AdminRestController adminUserController = appCtx.getBean(AdminRestController.class);
            adminUserController.create(new User(null, "user", "pass@gmail.com", "pass", Role.ROLE_ADMIN));

            System.out.println();

            System.out.println(adminUserController.getAll());
            adminUserController.getAll().forEach(System.out::println);
            System.out.println();


            MealRestController mealRestController = appCtx.getBean(MealRestController.class);
            mealRestController.getAll().forEach(System.out::println);

            System.out.println();
            mealRestController.delete(1);
            mealRestController.create(new Meal(LocalDateTime.of(2018, Month.MAY, 31, 20, 0), "test meal", 5555));
            System.out.println();

            mealRestController.getAll().forEach(System.out::println);

        }
    }
}
