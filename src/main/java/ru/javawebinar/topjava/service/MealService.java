package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;

import java.time.LocalTime;
import java.util.List;

/**
 * Created by Johann Stolz 02.07.2018
 */
public interface MealService {
    List<MealWithExceed> getMealWithExceed(LocalTime startTime, LocalTime endTime, int caloriesPerDay);

    Meal getMealById(int id);

    void deleteMealById(int id);

    void createMeal(Meal meal);

    void updateMeal(Meal meal);
}
