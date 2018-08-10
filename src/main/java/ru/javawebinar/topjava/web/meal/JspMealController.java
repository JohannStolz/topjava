package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.time.LocalDateTime;

import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalDate;
import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalTime;

/**
 * Created by Johann Stolz 07.08.2018
 */

@Controller
@RequestMapping("/meals")
public class JspMealController extends AbstractMealController {

    private final MealService mealService;

    @Autowired
    public JspMealController(MealService mealService) {
        this.mealService = mealService;
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") int id, Model model) {
        log.debug(" @GetMapping(\"/update/{id}\"), userId={}, mealId={}", SecurityUtil.authUserId(), id);
        Meal meal = get(id);
        model.addAttribute("meal", meal);
        model.addAttribute("update", SecurityUtil.authUserId());
        return "mealForm";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id, Model model) {
        mealService.delete(id, SecurityUtil.authUserId());
        return "redirect:/meals";
    }

    @PostMapping("/save")
    public String save(@RequestParam(value = "dateTime") String dateTime
            , @RequestParam(value = "description") String description
            , @RequestParam(value = "calories") String calories
            , Model model) {
        log.debug("SaveMeal post request.");
        Meal meal = new Meal(LocalDateTime.parse(dateTime), description, Integer.valueOf(calories));
        if (model.containsAttribute("update")) {
            mealService.update(meal, SecurityUtil.authUserId());
        } else {
            mealService.create(meal, SecurityUtil.authUserId());
        }
        return "redirect:/meals";
    }

    @PostMapping({"/filter"})
    public String filter(
            @RequestParam(value = "startDate") String startDate
            , @RequestParam(value = "endDate") String endDate
            , @RequestParam(value = "startTime") String startTime
            , @RequestParam(value = "endTime") String endTime
            , Model model) {
        log.debug("@PostMapping({\"\", \"/filter\"})");
        model.addAttribute("meals", getBetween(
                parseLocalDate(startDate)
                , parseLocalTime(startTime)
                , parseLocalDate(endDate)
                , parseLocalTime(endTime)));

        return "meals";
    }

    @GetMapping("/create")
    public String create(Model model) {
        log.debug("@PostMapping(\"/create\")");
        model.addAttribute("meal", new Meal());
        return "mealForm";
    }

}

