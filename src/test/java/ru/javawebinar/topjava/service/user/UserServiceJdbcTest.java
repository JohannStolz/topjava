package ru.javawebinar.topjava.service.user;

import org.springframework.test.context.ActiveProfiles;

import static ru.javawebinar.topjava.Profiles.JDBC;

/**
 * Created by Johann Stolz 30.07.2018
 */
@ActiveProfiles(JDBC)
public class UserServiceJdbcTest extends UserServiceAbstractTest {

}
