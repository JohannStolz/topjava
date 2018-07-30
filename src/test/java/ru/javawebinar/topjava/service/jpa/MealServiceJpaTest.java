package ru.javawebinar.topjava.service.jpa;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.service.MealServiceAbstractTest;

import static ru.javawebinar.topjava.Profiles.JPA;

/**
 * Created by Johann Stolz 30.07.2018
 */
@ActiveProfiles(JPA)
public class MealServiceJpaTest extends MealServiceAbstractTest {
}
