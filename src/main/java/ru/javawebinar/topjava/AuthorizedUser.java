package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.User;


/**
 * Created by Johann Stolz 13.07.2018
 */
public class AuthorizedUser {
    private static User user;

    public static int id() {
        return user.getId();
    }

    public static int getCaloriesPerDay() {
        return user.getCaloriesPerDay();
    }

    public static void setUser(User user) {
        AuthorizedUser.user = user;
    }

    public static User getUser() {
        return user;
    }
}
