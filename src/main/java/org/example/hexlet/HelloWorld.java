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
import org.example.hexlet.repository.CourseRepository;
import org.example.hexlet.repository.UserRepository;
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
            ctx.render("courses/build.jte");
        });

        app.get("/courses/{courseId}/lessons/{id}", ctx -> {
            var courseId = ctx.pathParam("courseId");
            var lessonId = ctx.pathParam("id");
            ctx.result("Course ID: " + courseId + " Lesson ID: " + lessonId);
        });

        app.get("/courses/{id}", ctx -> {
            var courseId = ctx.pathParamAsClass("id", Long.class).get();
            var course = CourseRepository.find(courseId).get();
            var page = new CoursePage(course);
            ctx.render("courses/show.jte", model("page", page));
        });

        app.get("/courses", ctx -> {
            var term = ctx.queryParam("term");
            var description = ctx.queryParam("description");
            var courses = CourseRepository.getEntities().stream()
                        .sorted(Comparator.comparing(Course::getId))
                        .toList();

            if (term != null) {
                courses = CourseRepository.search(term);
            }

            if (description != null) {
                courses = courses.stream()
                        .filter(course -> course.getDescription().toLowerCase().contains(description.toLowerCase()))
                        .toList();
            }

            var header = "Курсы по програмированию";

            var page = new CoursesPage(courses, header, term, description);
            ctx.render("courses/index.jte", model("page", page));
        });

        app.post("/courses", ctx -> {
            var name = ctx.formParam("name").trim();
            var description = ctx.formParam("description");

            var course = new Course(name, description);
            CourseRepository.save(course);
            ctx.redirect("/courses");
        });

        app.get("/users/build", ctx -> {
            ctx.render("users/build.jte");
        });

        app.get("/users/{id}/post/{postId}", ctx -> {
            var userId = ctx.pathParam("id");
            var postId = ctx.pathParam("postId");
            ctx.result("User ID: " + userId + " Post ID: " + postId);
        });

        app.get("/users/{id}", ctx -> {
            var id = ctx.pathParamAsClass("id", Long.class).get();
            var user = UserRepository.find(id).get();
            var page = new UserPage(user);
            ctx.render("users/show.jte", model("page", page));
        });

        app.get("/users", ctx -> {
            var term = ctx.queryParam("term");
            var users = UserRepository.getEntities().stream()
                    .sorted(Comparator.comparing(User::getId))
                    .toList();

            if (term != null) {
                users = UserRepository.search(term);
            }

            var page = new UsersPage(users, term);
            ctx.render("users/index.jte", model("page", page));
        });

        app.post("/users", ctx -> {
            var name = ctx.formParam("name").trim();
            var email = ctx.formParam("email").trim().toLowerCase();
            var password = ctx.formParam("password");
            var passwordConfirmation = ctx.formParam("passwordConfirmation");

            var user = new User(name, email, password);
            UserRepository.save(user);
            ctx.redirect("/users");
        });

        app.get("/", ctx -> ctx.render("index.jte"));

        app.start(7070);
    }
}
//    public static void show(Context ctx) {
//        var id = ctx.pathParamAsClass("id", Long.class).get();
//        var user = UserRepository.find(id)
//                .orElseThrow(() -> new NotFoundResponse("Entity with id = " + id + " not found"));
//    }

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


