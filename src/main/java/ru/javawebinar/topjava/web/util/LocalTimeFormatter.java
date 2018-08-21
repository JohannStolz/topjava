package ru.javawebinar.topjava.web.util;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.time.LocalTime;
import java.util.Locale;

/**
 * Created by Johann Stolz 21.08.2018
 */
public class LocalTimeFormatter implements Formatter<LocalTime> {
    @Override
    public LocalTime parse(String text, Locale locale) throws ParseException {
        if (text.isEmpty() || text == null) {
            return null;
        } else {
            return LocalTime.parse(text);
        }

    }

    @Override
    public String print(LocalTime object, Locale locale) {
        if (object == null) {
            return null;
        } else {
            return object.toString();
        }
    }

}
