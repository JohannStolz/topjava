package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.dao.MealDaoImpl;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalTime;
import java.util.List;

/**
 * Created by Johann Stolz 02.07.2018
 */
public class MealServiceImpl implements MealService {
    MealDao mealDao = new MealDaoImpl();

    @Override
    public List<MealWithExceed> getMealWithExceed(LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        List<MealWithExceed> mealWithExceeds = MealsUtil.getFilteredWithExceeded(mealDao.getMealList(), startTime, endTime, caloriesPerDay);
        return mealWithExceeds;
    }

    @Override
    public Meal getMealById(int id) {
        return mealDao.getMealById(id);
    }

    @Override
    public void deleteMealById(int id) {
         mealDao.deleteMealById(id);
    }

    @Override
    public void createMeal(Meal meal) {
        mealDao.createMeal(meal);
    }

    @Override
    public void updateMeal(Meal meal) {
       mealDao.updateMeal(meal);
    }
}
