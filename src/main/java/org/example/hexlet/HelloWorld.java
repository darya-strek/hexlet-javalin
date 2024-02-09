package org.example.hexlet;

import io.javalin.Javalin;

import java.util.Collections;

import org.example.hexlet.dto.courses.CoursesPage;
import org.example.hexlet.model.Course;
import org.example.hexlet.dto.courses.CoursePage;
import org.example.hexlet.repository.CourseRepository;

public class HelloWorld {

    public static void main(String[] args) {

        Course php = new Course("php", "it's php");
        CourseRepository.save(php);
        Course java = new Course("java", "it's java");
        CourseRepository.save(java);
        Course python = new Course("python", "it's python");
        CourseRepository.save(python);

        // Создаем приложение
        var app = Javalin.create(config -> {
            config.plugins.enableDevLogging();
        });

        app.get("/courses/{id}", ctx -> {
            var id = ctx.pathParamAsClass("id", Long.class).get();
            var course = CourseRepository.find(id).get();
            var page = new CoursePage(course);
            ctx.render("courses/show.jte", Collections.singletonMap("page", page));
        });

        app.get("/courses", ctx -> {
            var courses = CourseRepository.getEntities();
            var header = "There are courses of programming";
            var page = new CoursesPage(courses, header);
            ctx.render("courses/index.jte", Collections.singletonMap("page", page));
        });

        app.start(7070); // Стартуем веб-сервер
    }
}
