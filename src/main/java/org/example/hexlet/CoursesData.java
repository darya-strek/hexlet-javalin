package org.example.hexlet;

import org.example.hexlet.model.Course;

import net.datafaker.Faker;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class CoursesData {
    private static final long ITEMS_COUNT = 15;
    private static long idCounter = ITEMS_COUNT;

    public static List<Course> getCourses() {
        Random random = new Random(123);
        Faker faker = new Faker(new Locale("en"), random);

        List<Long> ids = LongStream
                .range(1, ITEMS_COUNT + 1)
                .boxed()
                .collect(Collectors.toList());
        Collections.shuffle(ids, random);

        List<Course> courses = new ArrayList<>();

        for (int i = 0; i < ITEMS_COUNT; i++) {
            var id = ids.get(i);
            var courseName = faker.programmingLanguage().name();
            var courseDescription = faker.text().text();
            Course course = new Course(courseName, courseDescription);
            course.setId(id);
            courses.add(course);
        }

        return courses;
    }

    public static long getNextId() {
        long id = ++idCounter;
        return id;
    }
}
