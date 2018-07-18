package ru.javawebinar.topjava.repository.jdbc;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.service.MealService;

/**
 * Created by Johann Stolz 18.07.2018
 */
@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
@RunWith(SpringRunner.class)
public class JdbcMealRepositoryTest {
    @Autowired
    MealRepository repository;

    private Meal savedMeal = new Meal(MealTestData.SAVED_MEAL);
    private Meal expectedMealForUser = new Meal(MealTestData.EXPECTED_MEAL_USER);
    private Meal expectedMealForAdmin = new Meal(MealTestData.EXPECTED_MEAL_ADMIN);

    private int userId = MealTestData.USER_ID;
    private int adminId = MealTestData.ADMIN_ID;

}
