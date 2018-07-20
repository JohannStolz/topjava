package ru.javawebinar.topjava.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Repository
@Transactional(readOnly = true)
public class JpaMealRepositoryImpl implements MealRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Meal save(Meal meal, int userId) {
        Objects.requireNonNull(meal);
        User user = em.getReference(User.class, userId);
        meal.setUser(user);
        if (meal.isNew()) {
            em.persist(meal);
            return meal;
        } else {
            if (get(meal.getId(), userId) == null) {
                return null;
            }
        }
        return em.merge(meal);
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
        int executeUpdate = em.createNamedQuery(Meal.DELETE)
                .setParameter("userId", userId)
                .setParameter("mealId", id)
                .executeUpdate();

        return executeUpdate != 0;
    }

    @Override
    public Meal get(int id, int userId) {
        Meal meal = em.find(Meal.class, id);
        if (meal == null || !meal.getUser().getId().equals(userId)) {
            return null;
        }
        return meal;
    }

    @Override
    public List<Meal> getAll(int userId) {
        List<Meal> meals = em.createNamedQuery(Meal.GET_ALL, Meal.class).setParameter("userId", userId).getResultList();
        for (Meal o : meals) {
            System.out.println(o.getUser());
        }
        return meals;
    }

    @Override
    public List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {

        return em.createNamedQuery(Meal.GET_BETWEEN, Meal.class)
                .setParameter("userId", userId)
                .setParameter("startDateTime", startDate)
                .setParameter("endDateTime", endDate)
                .getResultList();
    }
}