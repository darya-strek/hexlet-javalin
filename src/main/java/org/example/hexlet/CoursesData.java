package org.example.hexlet;

import org.example.hexlet.model.Course;

//import net.datafaker.Faker;

import java.util.*;
//import java.util.stream.Collectors;
//import java.util.stream.LongStream;

public class CoursesData {
    private static final long ITEMS_COUNT = 15;
    private static long idCounter = ITEMS_COUNT;

    public static List<Course> getCourses() {

        var course1 = new Course("Java", "Description of Java Course");
        course1.setId(1L);
        var course2 = new Course("PHP", "PHP Course");
        course2.setId(2L);
        var course3 = new Course("JavaScript", "Description of JavaScript");
        course3.setId(3L);
        var course4 = new Course("Python", "Python Course");
        course4.setId(4L);
        var course5 = new Course("Go", "Description of Go Course");
        course5.setId(5L);
        var course6 = new Course("C++", "C++");
        course6.setId(6L);
        var course7 = new Course("Ruby", "Description of Ruby Course");
        course7.setId(7L);
        var course8 = new Course("C#", "C# Course");
        course8.setId(8L);
        var course9 = new Course("SQL", "Description of SQL");
        course9.setId(9L);
        var course10 = new Course("HTML", "HTML");
        course10.setId(10L);

        var courses = new ArrayList<Course>();
        courses.add(course1);
        courses.add(course2);
        courses.add(course3);
        courses.add(course4);
        courses.add(course5);
        courses.add(course6);
        courses.add(course7);
        courses.add(course8);
        courses.add(course9);
        courses.add(course10);

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

        return courses;
    }

    public static long getNextId() {
        long id = ++idCounter;
        return id;
    }
}
