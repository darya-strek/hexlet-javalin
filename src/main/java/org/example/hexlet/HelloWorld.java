package org.example.hexlet;

import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import io.javalin.rendering.template.JavalinJte;
import static io.javalin.rendering.template.TemplateUtil.model;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringEscapeUtils;
import org.example.hexlet.dto.courses.CoursePage;
import org.example.hexlet.dto.courses.CoursesPage;
import org.example.hexlet.dto.users.UserPage;
import org.example.hexlet.dto.users.UsersPage;
import org.example.hexlet.model.Course;
import org.example.hexlet.model.User;
import org.owasp.html.HtmlPolicyBuilder;
import org.owasp.html.PolicyFactory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class HelloWorld {

    private static final List<User> USERS = UsersData.getUsers();
    private static final List<Course> COURSES = CoursesData.getCourses();

    public static void main(String[] args) {
        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });

        app.get("/hello", ctx -> {
            var name = ctx.queryParamAsClass("name", String.class).getOrDefault("World");
            ctx.result("Hello, " + name + "!!!");
        });

        app.get("/courses/build", ctx -> {
        });

        app.get("/courses/{courseId}/lessons/{id}", ctx -> {
            var courseId = ctx.pathParam("courseId");
            var lessonId = ctx.pathParam("id");
            ctx.result("Course ID: " + courseId + " Lesson ID: " + lessonId);
        });

        app.get("/courses/{id}", ctx -> {
            var courseId = ctx.pathParamAsClass("id", Long.class).get();
            var course = COURSES.stream()
                    .filter(u -> Objects.equals(u.getId(), courseId))
                    .findFirst()
                    .orElseThrow(() -> new NotFoundResponse("User not found"));
            var page = new CoursePage(course);
            ctx.render("courses/show.jte", model("page", page));
        });

        app.get("/courses", ctx -> {
            var term = ctx.queryParam("term");
            var description = ctx.queryParam("description");
            var courses = COURSES;
            if (term != null) {
                courses.stream()
                        .sorted(Comparator.comparing(Course::getId))
                        .filter(course -> StringUtils.containsIgnoreCase(course.getName(), term))
                        .collect(Collectors.toList());
            } else if (description != null) {
                courses.stream()
                        .sorted(Comparator.comparing(Course::getId))
                        .filter(course -> StringUtils.containsIgnoreCase(course.getDescription(), description))
                        .collect(Collectors.toList());
            } else {
                courses.stream()
                        .sorted(Comparator.comparing(Course::getId))
                        .collect(Collectors.toList());
            }

            var header = "Курсы по програмированию";

            var page = new CoursesPage(courses, header, term, description);
            ctx.render("courses/index.jte", model("page", page));
        });

        app.get("/users/{id}/post/{postId}", ctx -> {
            var userId = ctx.pathParam("id");
            var postId = ctx.pathParam("postId");
            ctx.result("User ID: " + userId + " Post ID: " + postId);
        });

        app.get("/users/{id}", ctx -> {
            var id = ctx.pathParamAsClass("id", Long.class).get();
            var user = new User(id, "May", "Petters", "e@mail.com");
            var page = new UserPage(user);
            ctx.render("users/show.jte", model("page", page));
        });

//        app.get("/users/{id}", ctx -> {
//            var id = ctx.pathParam("id");
//            PolicyFactory policy = new HtmlPolicyBuilder()
//                    .allowElements("a")
//                    .allowUrlProtocols("https")
//                    .allowAttributes("href").onElements("a")
//                    .requireRelNofollowOnLinks()
//                    .toFactory();
//            String safeHTML = policy.sanitize(id);
//            ctx.contentType("html");
//            ctx.result(safeHTML);
//        });

//        app.get("/users/{id}", ctx -> {
//            var userId = ctx.pathParamAsClass("id", Long.class).get();
//            var user = USERS.stream()
//                    .filter(u -> Objects.equals(u.getId(), userId))
//                    .findFirst()
//                    .orElseThrow(() -> new NotFoundResponse("User not found"));
//            var page = new UserPage(user);
//            ctx.render("users/show.jte", model("page", page));
//        });

        app.get("/users", ctx -> {
            var users = USERS.stream()
                    .sorted(Comparator.comparing(User::getId))
                    .collect(Collectors.toList());
            var page = new UsersPage(users);
            ctx.render("users/index.jte", model("page", page));
        });

        app.get("/", ctx -> ctx.render("index.jte"));

        app.start(7070);
    }

//    public static void show(Context ctx) {
//        var id = ctx.pathParamAsClass("id", Long.class).get();
//        var user = UserRepository.find(id)
//                .orElseThrow(() -> new NotFoundResponse("Entity with id = " + id + " not found"));
//    }
}

