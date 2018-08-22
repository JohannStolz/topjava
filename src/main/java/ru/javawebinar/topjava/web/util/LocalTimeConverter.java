package ru.javawebinar.topjava.web.util;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Created by Johann Stolz 22.08.2018
 */
public class LocalTimeConverter implements Converter<String, LocalTime> {
    @Override
    public LocalTime convert(String source) {
        if (source == null) {
            return null;
        } else {
            return LocalTime.parse(source);
        }
    }
}
