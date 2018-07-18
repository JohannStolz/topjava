package ru.javawebinar.topjava.repository.jdbc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.ValidationUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

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

    private void getAllForAnyUser(int userId, List<Meal> meals) {
        List<Meal> all = repository.getAll(userId);
        assertThat(all.size()).isEqualTo(meals.size());
        assertThat(all).containsAll(meals);
    }

    @Test
    public void getAllForUser() throws Exception {
        getAllForAnyUser(userId, MealTestData.getAllData(userId));
    }

    @Test
    public void getAllForAdmin() throws Exception {
        getAllForAnyUser(adminId, MealTestData.getAllData(adminId));
    }

    @Test
    public void getOwnMeal() throws Exception {
        Meal meal = repository.get(expectedMealForUser.getId(), userId);
        assertThat(meal).isEqualTo(expectedMealForUser);
    }

    @Test
    public void deleteOwnMeal() throws Exception {
        assertThat((repository.delete(expectedMealForUser.getId(), userId))).isTrue();
    }

    @Test(expected = NotFoundException.class)
    public void deleteForeignMeal() throws Exception {
        //assertThat((repository.delete(expectedMealForUser.getId(), adminId))).isFalse();
        ValidationUtil.checkNotFound((repository.delete(expectedMealForUser.getId(), adminId)), String.valueOf(adminId));
    }

    @Test(expected = NotFoundException.class)
    public void updateForeignMeal() throws Exception {
        ValidationUtil.checkNotFound((repository.save(expectedMealForUser, adminId)), String.valueOf(adminId));
    }

    @Test(expected = NotFoundException.class)
    public void getForeignMeal() throws Exception {
        ValidationUtil.checkNotFound((repository.get(expectedMealForUser.getId(), adminId)), String.valueOf(adminId));
    }

    @Test
    public void save() throws Exception {
        Meal meal = new Meal(savedMeal);
        Meal savedTestMeal = repository.save(meal, userId);
        assertThat(savedTestMeal).isEqualToIgnoringGivenFields(savedMeal, "id");
        assertThat(savedTestMeal.getId()).isNotNull();
        meal = repository.get(savedTestMeal.getId(), userId);
        assertThat(meal).isEqualTo(savedTestMeal);
    }

    @Test(expected = DuplicateKeyException.class)
    public void saveDuplicateMeal() throws Exception {
        Meal mealForSave = new Meal(LocalDateTime.of(2018, 5, 30, 9, 0),
                "duplicate", 200);
        repository.save(mealForSave, userId);
    }
    @Test(expected = DuplicateKeyException.class)
    public void saveDuplicateDateMeal() throws Exception {
        Meal mealForSave = new Meal(LocalDateTime.of(2012, 5, 30, 9, 0),
                "duplicateOne", 200);
        Meal anotherMealForSave = new Meal(LocalDateTime.of(2012, 5, 30, 9, 0),
                "duplicateTwo", 88);
        repository.save(mealForSave, userId);
        repository.save(anotherMealForSave, userId);
    }

}
