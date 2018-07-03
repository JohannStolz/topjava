package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

/**
 * Created by Johann Stolz 30.06.2018
 */
public interface MealDao {

    List<Meal> getMealList();

    Meal getMealById(int id);

    void deleteMealById(int id);

    void createMeal(Meal meal);

    void updateMeal(Meal meal);

}
