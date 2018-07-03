package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Johann Stolz 30.06.2018
 */
public class MealDaoImpl implements MealDao {

    private static ConcurrentMap<Integer, Meal> pantry = new ConcurrentSkipListMap<>();
    private static AtomicInteger countId = new AtomicInteger();

    static {
        Meal meal0 = new Meal(LocalDateTime.of(2018, Month.MAY, 30, 10, 0), "Завтрак", 500);
        putInPantry(meal0);
        Meal meal1 = new Meal(LocalDateTime.of(2018, Month.MAY, 30, 13, 0), "Обед", 1000);
        putInPantry(meal1);
        Meal meal2 = new Meal(LocalDateTime.of(2018, Month.MAY, 30, 20, 0), "Ужин", 500);
        putInPantry(meal2);
        Meal meal3 = new Meal(LocalDateTime.of(2018, Month.MAY, 31, 10, 0), "Завтрак", 1000);
        putInPantry(meal3);
        Meal meal4 = new Meal(LocalDateTime.of(2018, Month.MAY, 31, 13, 0), "Обед", 500);
        putInPantry(meal4);
        Meal meal5 = new Meal(LocalDateTime.of(2018, Month.MAY, 31, 20, 0), "Ужин", 510);
        putInPantry(meal5);
    }

    static boolean putInPantry(Meal meal) {
        try {
            meal.setId(countId.getAndIncrement());
            pantry.put(meal.getId(), meal);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Meal> getMealList() {
        return new ArrayList<>(pantry.values());
    }

    @Override
    public Meal getMealById(int id) {
        return pantry.get(id);
    }

    @Override
    public void deleteMealById(int id) {
        pantry.remove(id);
    }

    @Override
    public void createMeal(Meal meal) {
        putInPantry(meal);
     }

    @Override
    public void updateMeal(Meal meal) {
        pantry.put(meal.getId(), meal);
    }
}
