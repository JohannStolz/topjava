package ru.javawebinar.topjava.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.AbstractNamedEntity;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryUserRepositoryImpl implements UserRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryUserRepositoryImpl.class);
    private AtomicInteger counter = new AtomicInteger(0);
    private Map<Integer, User> repository = new ConcurrentHashMap<>();

    @Override
    public boolean delete(int id) {
        log.info("deleteUserById {}", id);
        return repository.remove(id) != null;
    }

    @Override
    public User get(int id) {
        log.info("getUserById {}", id);
        return repository.get(id);
    }

    @Override
    public User getByEmail(String email) {
        log.info("getByEmail {}", email);
        return repository
                .values()
                .stream()
                .filter((entity) -> entity.getEmail().equals(email)).findFirst().orElse(null);
    }

    @Override
    public List<User> getAll() {
        log.info("getAll");
        return repository
                .values()
                .stream()
                .sorted(Comparator.comparing(AbstractNamedEntity::getName)).collect(Collectors.toList());// CHECK!!
    }

    @Override
    public User save(User user) {
        log.info("save {}", user);
        {
            if (user.isNew()) {
                user.setId(counter.incrementAndGet());
                repository.put(user.getId(), user);
                return user;
            }
            // treat case: update, but absent in storage
            return repository.computeIfPresent(user.getId(), (id, oldUser) -> user);
        }

    }
}
