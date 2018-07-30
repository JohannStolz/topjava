package ru.javawebinar.topjava.service.datajpa;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.service.MealServiceAbstractTest;

import static ru.javawebinar.topjava.Profiles.DATAJPA;

/**
 * Created by Johann Stolz 30.07.2018
 */
@ActiveProfiles(DATAJPA)
public class MealServiceDataJpaTest extends MealServiceAbstractTest {
    
}
