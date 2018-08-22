package ru.javawebinar.topjava.web.util;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by Johann Stolz 22.08.2018
 */
public class LocalDateConverter implements Converter<String, LocalDate> {

    @Override
    public LocalDate convert(String source) {
        if (source == null) {
            return null;
        } else {
            return LocalDate.parse(source);
        }
    }
}
