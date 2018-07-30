package ru.javawebinar.topjava.service.jdbc;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.service.MealServiceAbstractTest;

import static ru.javawebinar.topjava.Profiles.JDBC;

/**
 * Created by Johann Stolz 30.07.2018
 */
@ActiveProfiles(JDBC)
public class MealServiceJdbcTest extends MealServiceAbstractTest {
}
