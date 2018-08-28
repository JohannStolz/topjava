package ru.javawebinar.topjava.web.meal;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE;
import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME;
import static org.springframework.format.annotation.DateTimeFormat.ISO.TIME;

/**
 * Created by Johann Stolz 27.08.2018
 */
@RestController
@RequestMapping("/ajax/profile/meals/")
public class MealAjaxController extends AbstractMealController {

    @Override
    @GetMapping
    public List<MealWithExceed> getAll() {
        return super.getAll();
    }


    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }


    @PostMapping
    public void createOrUpdate(
            @RequestParam Integer id,
            @RequestParam @DateTimeFormat(iso = DATE_TIME) LocalDateTime dateTime,
            @RequestParam String description,
            @RequestParam Integer calories) {
        Meal meal = new Meal(id, dateTime, description, calories);
        if (meal.isNew())
            super.create(meal);
    }

    @PostMapping(value = "/filter")
    public List<MealWithExceed> filtered(
            @RequestParam(required = false) @DateTimeFormat(iso = DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DATE) LocalDate endDate,
            @RequestParam(required = false) @DateTimeFormat(iso = TIME) LocalTime startTime,
            @RequestParam(required = false) @DateTimeFormat(iso = TIME) LocalTime endTime
    ) {
        return super.getBetween(startDate, startTime, endDate, endTime);
    }
}
