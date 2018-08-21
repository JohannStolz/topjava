package ru.javawebinar.topjava.web.meal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.TestUtil;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.AbstractControllerTest;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.USER_ID;
import static ru.javawebinar.topjava.web.json.JsonUtil.writeValue;

/**
 * Created by Johann Stolz 20.08.2018
 */
public class MealRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = MealRestController.REST_URL + '/';

    @Autowired
    private MealService service;

    @Test
    public void testGetAll() throws Exception {
        List<MealWithExceed> withExceeded = MealsUtil.getWithExceeded(service.getAll(SecurityUtil.authUserId()), SecurityUtil.authUserCaloriesPerDay());
        mockMvc.perform(get(REST_URL))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(writeValue(withExceeded)));

    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + MEAL1.getId()))
                .andDo(print())
                .andExpect(status().isOk());
        List<Meal> meals = new ArrayList<>(MEALS);
        meals.remove(MEAL1);
        assertMatch(service.getAll(USER_ID), meals);
    }

    @Test
    public void testUpdate() throws Exception {
        Meal updated = MealTestData.getUpdated();
        mockMvc.perform(put(REST_URL + updated.getId())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(writeValue(updated)))
                .andDo(print())
                .andExpect(status().isOk());
        assertMatch(service.get(MEAL1_ID, USER_ID), updated);
    }

    @Test
    public void testCreate() throws Exception {
        Meal created = getCreated();
        ResultActions actionCreated = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(writeValue(created)))
                .andDo(print())
                .andExpect(status().isCreated());

        Meal restored = TestUtil.readFromJson(actionCreated, Meal.class);

        created.setId(restored.getId());

        List<Meal> meals = new ArrayList<>(MEALS);

        meals.add(0, created);

        assertMatch(service.getAll(USER_ID), meals);
    }

    @Test
    public void testGetOne() throws Exception {
        mockMvc.perform(get(REST_URL + MEAL1_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(writeValue(MEAL1)));
    }

    @Test
    public void testGetBetween() throws Exception {
        LocalDateTime startDateTime = LocalDateTime.of(2015, Month.MAY, 30, 6, 0);
        LocalDateTime endDateTime = LocalDateTime.of(2015, Month.MAY, 31, 15, 0);
        List<Meal> meals = service.getBetweenDates(startDateTime.toLocalDate(), endDateTime.toLocalDate(), SecurityUtil.authUserId());
        List<MealWithExceed> mealWithExceeds = MealsUtil.getFilteredWithExceeded(meals, SecurityUtil.authUserCaloriesPerDay(), startDateTime.toLocalTime(), endDateTime.toLocalTime());
        mockMvc.perform(post(REST_URL + "between")
                .contentType(MediaType.TEXT_HTML_VALUE)
                .param("startDate", startDateTime.toLocalDate().toString())
                .param("startTime", startDateTime.toLocalTime().toString())
                .param("endDate", endDateTime.toLocalDate().toString())
                .param("endTime", endDateTime.toLocalTime().toString()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(writeValue(mealWithExceeds)));

    }

}
       