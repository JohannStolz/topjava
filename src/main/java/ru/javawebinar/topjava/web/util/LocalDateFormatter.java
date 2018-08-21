package ru.javawebinar.topjava.web.util;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Locale;

/**
 * Created by Johann Stolz 21.08.2018
 */
public class LocalDateFormatter implements Formatter<LocalDate> {
    @Override
    public LocalDate parse(String text, Locale locale) throws ParseException {
        if (text.isEmpty() || text == null) {
            return null;
        } else {
            return LocalDate.parse(text);
        }

    }

    @Override
    public String print(LocalDate object, Locale locale) {
        if (object == null) {
            return null;
        } else {
            return object.toString();
        }
    }
}
