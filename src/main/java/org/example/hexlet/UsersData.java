package org.example.hexlet;

import org.example.hexlet.model.User;
import org.example.hexlet.repository.UserRepository;

import net.datafaker.Faker;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class UsersData {
    private static final long ITEMS_COUNT = 5;
    private static long idCounter = ITEMS_COUNT;

    public static List<User> getUsers() {
        Random random = new Random(123);
        Faker faker = new Faker(new Locale("en"), random);

        List<Long> ids = LongStream
                .range(1, ITEMS_COUNT + 1)
                .boxed()
                .collect(Collectors.toList());
        Collections.shuffle(ids, random);

        List<User> users = new ArrayList<>();

        for (int i = 0; i < ITEMS_COUNT; i++) {
            var id = ids.get(i);
            var name = faker.name().name();
            var email = faker.internet().emailAddress();
            var password =faker.internet().password();
            User user = new User(name, email, password);

            UserRepository.save(user);

//            user.setId(id);
//            users.add(user);
        }
        return UserRepository.getEntities();
    }

    public static long getNextId() {
        long id = ++idCounter;
        return id;
    }
}
