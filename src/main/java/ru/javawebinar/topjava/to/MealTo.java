package ru.javawebinar.topjava.to;

import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by Johann Stolz 03.09.2018
 */
public class MealTo extends BaseTo implements Serializable {
    private static final long serialVersionUID = 1L;


    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @NotNull
    private LocalDateTime dateTime;

    @NotBlank
    @Size(max = 255, message = "too long description, max 255 chars")
    private String description;

    @Range(min = 5, max = 2000, message = "wrong value, value must be from 5 to 2000")
    private int calories;

    public MealTo() {
    }

    public MealTo(Integer id, LocalDateTime dateTime, String description, int calories) {
        super(id);
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }
}
