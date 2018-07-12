package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository

public class InMemoryMealRepositoryImpl implements MealRepository {
    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach((meal) -> save(meal, 1));
    }

    @Override
    public Meal save(Meal meal, int userId) {
        meal.setUserId(userId);
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            repository.put(meal.getId(), meal);
            return meal;
        }
        // treat case: update, but absent in storage
        return repository.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public boolean delete(int id, int userId) {
        System.out.println(repository.values());  /// TEST DATA
        if (repository.containsKey(id)&&repository.get(id).getUserId() == userId) {
            repository.remove(id);
            return true;
        }
        else return false;
    }

    @Override
    public Meal get(int id, int userId) {
        if (repository.containsKey(id)&&repository.get(id).getUserId() == userId) {
            return repository.get(id);
        }
        else return null;
    }

    @Override
    public Collection<Meal> getAll(int userId) {
        return repository.values();
    }
}

