package org.example.hexlet;

import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import io.javalin.rendering.template.JavalinJte;
import static io.javalin.rendering.template.TemplateUtil.model;

import org.example.hexlet.dto.courses.CoursePage;
import org.example.hexlet.dto.courses.CoursesPage;
import org.example.hexlet.model.Course;

import java.util.ArrayList;
import java.util.List;

public class HelloWorld {
    public static void main(String[] args) {
        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });

        app.get("/hello", ctx -> {
            var name = ctx.queryParamAsClass("name", String.class).getOrDefault("World");
            ctx.result("Hello, " + name + "!");
        });

        app.get("/", ctx -> ctx.render("index.jte"));

        app.get("/courses/build", ctx -> {
        });

        app.get("/courses/{id}", ctx -> {
            var id = ctx.pathParam("id");
            var course = new Course("Java", "Java course");
            var page = new CoursePage(course);
            ctx.render("courses/show.jte", model("page", page));
        });

        app.get("/courses/{courseId}/lessons/{id}", ctx -> {
            var courseId = ctx.pathParam("courseId");
            var lessonId = ctx.pathParam("id");
            ctx.result("Course ID: " + courseId + " Lesson ID: " + lessonId);
        });

        app.get("/courses", ctx -> {
            List<Course> courses = List.of(new Course("Java", "description"),
                    new Course("PHP", "description"),
                    new Course("Python", "description"));
            var header = "Курсы по програмированию";
            var page = new CoursesPage(courses, header);
            ctx.render("courses/index.jte", model("page", page));
        });

        app.get("/users/{id}", ctx -> {
            ctx.result("User ID: " + ctx.pathParam("id"));
        });

        app.get("/users/{id}/post/{postId}", ctx -> {
            var userId = ctx.pathParam("id");
            var postId = ctx.pathParam("postId");
            ctx.result("User ID: " + userId + " Post ID: " + postId);
        });



        app.start(7070);
    }

//    public static void show(Context ctx) {
//        var id = ctx.pathParamAsClass("id", Long.class).get();
//        var user = UserRepository.find(id)
//                .orElseThrow(() -> new NotFoundResponse("Entity with id = " + id + " not found"));
//    }
}

