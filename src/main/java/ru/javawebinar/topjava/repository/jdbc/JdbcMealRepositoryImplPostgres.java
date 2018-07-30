package ru.javawebinar.topjava.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Created by Johann Stolz 30.07.2018
 */
@Repository
@Profile("postgres")

public class JdbcMealRepositoryImplPostgres extends AbstractJdbcMealRepositoryImpl {

    @Override
    protected LocalDateTime dateTimeHandler(LocalDateTime localDateTime) {
        return localDateTime;
    }
}
