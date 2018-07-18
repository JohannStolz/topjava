package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.DateTimeUtil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Johann Stolz 17.07.2018
 */
public class MealTestData {
    public static final int ADMIN_ID = UserTestData.ADMIN_ID;
    public static final int USER_ID = UserTestData.USER_ID;

    public static final Meal SAVED_MEAL = new Meal(LocalDateTime.of(2012, 5, 30, 5, 0),
            "SAVE MEAL", 7000);
    public static final Meal EXPECTED_MEAL_ADMIN = new Meal(100011, LocalDateTime.of(2018, 5, 31, 21, 17, 2),
            "Admin late dinner", 200);
    public static final Meal EXPECTED_MEAL_USER = new Meal(100006, LocalDateTime.of(2018, 5, 31, 10, 20),
            "User heavy lunch", 1700);

    public static void assertMatchListInAnyOrder(List<Meal> firstMeal, List<Meal> secondMeal) {
        assertThat(firstMeal).containsExactlyInAnyOrder(secondMeal.toArray(new Meal[secondMeal.size()]));
    }


    private static List<Meal> allUsersMeals = Arrays.asList(
            new Meal(100002, LocalDateTime.of(2018, 5, 30, 9, 0), "User breakfast", 200),
            new Meal(100003, LocalDateTime.of(2018, 5, 30, 12, 0, 1), "User dinner", 700),
            new Meal(100004, LocalDateTime.of(2018, 5, 31, 19, 30), "User supper", 1500),
            new Meal(100005, LocalDateTime.of(2018, 5, 31, 22, 0), "User lite supper", 700),
            new Meal(100006, LocalDateTime.of(2018, 5, 31, 10, 20), "User heavy lunch", 1700)

    );

    private static List<Meal> allAdminMeals = Arrays.asList(
            new Meal(100007, LocalDateTime.of(2018, 5, 30, 9, 0), "Admin breakfast", 100),
            new Meal(100008, LocalDateTime.of(2018, 5, 30, 11, 45), "Admin lunch", 200),
            new Meal(100009, LocalDateTime.of(2015, 5, 30, 17, 34), "Admin dinner", 1501),
            new Meal(100010, LocalDateTime.of(2018, 5, 30, 21, 17, 2), "Admin late dinner", 200),
            new Meal(100011, LocalDateTime.of(2018, 5, 31, 10, 56), "Admin late breakfast", 170),
            new Meal(100012, LocalDateTime.of(2018, 5, 31, 14, 18), "Admin  late lunch", 216),
            new Meal(100013, LocalDateTime.of(2018, 5, 31, 19, 51), "Admin lite supper", 17),
            new Meal(100014, LocalDateTime.of(2018, 5, 31, 22, 3), "Admin supper", 190)
    );


    public static List<Meal> getAllData(int userId) {
        if (userId == ADMIN_ID) {
            return new ArrayList<>(allAdminMeals);
        }
        if (userId == USER_ID) {
            return new ArrayList<>(allUsersMeals);
        } else return Collections.emptyList();
    }

    public static List<Meal> getBetween(LocalDateTime startTime, LocalDateTime endTime, int userId) {
        if (userId == ADMIN_ID) {
            return allAdminMeals.stream()
                    .filter(meal -> DateTimeUtil.isBetween(meal.getDateTime(), startTime, endTime))
                    .collect(Collectors.toList());
        }
        if (userId == USER_ID) {
            return allUsersMeals.stream()
                    .filter(meal -> DateTimeUtil.isBetween(meal.getDateTime(), startTime, endTime))
                    .collect(Collectors.toList());
        } else return Collections.emptyList();
    }

}
