package org.example.hexlet;

import org.example.hexlet.model.Course;
import org.example.hexlet.repository.CourseRepository;

//import net.datafaker.Faker;

import java.util.*;
//import java.util.stream.Collectors;
//import java.util.stream.LongStream;

public class CoursesData {
    private static final long ITEMS_COUNT = 15;
    private static long idCounter = ITEMS_COUNT;

    public static List<Course> getCourses() {

        var course1 = new Course("Java", "Description of Java Course");
        CourseRepository.save(course1);

        var course2 = new Course("PHP", "PHP Course");
        CourseRepository.save(course2);

        var course3 = new Course("JavaScript", "Description of JavaScript");
        CourseRepository.save(course3);

        var course4 = new Course("Python", "Python Course");
        CourseRepository.save(course4);

        var course5 = new Course("Go", "Description of Go Course");
        CourseRepository.save(course5);

        var courses = CourseRepository.getEntities();

        return courses;
    }

    public static long getNextId() {
        long id = ++idCounter;
        return id;
    }
}


//        Random random = new Random(123);
//        Faker faker = new Faker(new Locale("en"), random);
//
//        List<Long> ids = LongStream
//                .range(1, ITEMS_COUNT + 1)
//                .boxed()
//                .collect(Collectors.toList());
//        Collections.shuffle(ids, random);
//
//        List<Course> courses = new ArrayList<>();
//
//        for (int i = 0; i < ITEMS_COUNT; i++) {
//            var id = ids.get(i);
//            var courseName = faker.programmingLanguage().name();
//            var courseDescription = faker.text().text();
//            Course course = new Course(courseName, courseDescription);
//            course.setId(id);
//            courses.add(course);
//        }
//
//        var course6 = new Course("C++", "C++");
//        var course7 = new Course("Ruby", "Description of Ruby Course");
//        var course8 = new Course("C#", "C# Course");
//        var course9 = new Course("SQL", "Description of SQL");
//        var course10 = new Course("HTML", "HTML");
