package org.example.hexlet.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.example.hexlet.model.User;

public class UserRepository {
    private static List<User> entities = new ArrayList<>();

    public static void save(User user) {
        user.setId((long) entities.size() + 1);
        entities.add(user);
    }

    public static List<User> search(String term) {
        var users = entities.stream()
                .filter(entity -> entity.getName().toLowerCase().contains(term.toLowerCase()))
                .toList();
        return users;
    }

    public static Optional<User> find(Long id) {
        var user = entities.stream()
                .filter(entity -> entity.getId().equals(id))
                .findAny();
        return user;
    }

    public static void delete(Long id) {
        entities.remove(id);
    }

    public static List<User> getEntities() {
        return entities;
    }
}