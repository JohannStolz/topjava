package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * Created by Johann Stolz 18.07.2018
 */
@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))

public class MealServiceTest {
    @Autowired
    MealService service;

    private Meal savedMeal = new Meal(MealTestData.SAVED_MEAL);
    private Meal expectedMealForUser = new Meal(MealTestData.EXPECTED_MEAL_USER);
    private Meal expectedMealForAdmin = new Meal(MealTestData.EXPECTED_MEAL_ADMIN);

    private int userId = MealTestData.USER_ID;
    private int adminId = MealTestData.ADMIN_ID;

    @Test
    public void getAll() throws Exception {
        List<Meal> all = service.getAll(userId);
        List<Meal> allData = MealTestData.getAllData(userId);
        assertThat(all.containsAll(allData));
    }

    @Test
    public void create() throws Exception {

        Meal meal = new Meal(savedMeal);
        Meal savedTestMeal = service.create(meal, userId);
        assertThat(savedTestMeal.getId()).isNotNull();

        meal = service.get(savedTestMeal.getId(), userId);
        assertThat(meal).isEqualToIgnoringGivenFields(savedMeal, "id");
    }

    @Test
    public void updateOwn() throws Exception {
        Meal mealOrigin = service.get(expectedMealForUser.getId(), userId);
        mealOrigin.setDescription("NewTestMeal");
        Meal updateMeal = service.update(mealOrigin, userId);
        assertThat(updateMeal).isEqualTo(mealOrigin);
    }

    @Test(expected = NotFoundException.class)
    public void updateForeign() throws Exception {
        Meal meal = service.get(expectedMealForAdmin.getId(), adminId);
        meal.setDescription("NewForeignTestMeal");
        Meal updateMeal = service.update(meal, userId);
        assertThat(updateMeal).isEqualTo(meal);
    }

    @Test
    public void deleteOwn() throws Exception {
        service.delete(expectedMealForUser.getId(), userId);
    }

    @Test(expected = NotFoundException.class)
    public void deleteForeign() throws Exception {
        service.delete(expectedMealForAdmin.getId(), userId);
    }

    @Test
    public void getOwn() throws Exception {
        Meal ownMeal = service.get(expectedMealForUser.getId(), userId);
        assertThat(ownMeal).isEqualTo(expectedMealForUser);
    }

    @Test(expected = NotFoundException.class)
    public void getForeign() throws Exception {
        Meal foreignMeal = service.get(expectedMealForUser.getId(), adminId);
        assertThat(foreignMeal).isEqualTo(expectedMealForUser);
    }

    @Test
    public void getBetweenDateTimes() throws Exception {
        LocalDateTime startTime = LocalDateTime.of(LocalDate.of(2018, 5, 30), LocalTime.MIN);
        LocalDateTime endTime = LocalDateTime.of(LocalDate.of(2018, 5, 31), LocalTime.MAX);
        List<Meal> betweenDateTime = service.getBetweenDateTimes(startTime, endTime, userId);
        List<Meal> between = MealTestData.getBetween(startTime, endTime, userId);
        assertThat(betweenDateTime.containsAll(between));
    }

    @Test(expected = NotFoundException.class)
    public void notFoundDelete() throws Exception {
        service.delete(1, 1);
            }




}
