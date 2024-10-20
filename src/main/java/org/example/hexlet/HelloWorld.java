package org.example.hexlet;

import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import io.javalin.rendering.template.JavalinJte;
import static io.javalin.rendering.template.TemplateUtil.model;

import io.javalin.validation.ValidationException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringEscapeUtils;
import org.example.hexlet.controller.CoursesController;
import org.example.hexlet.controller.SessionsController;
import org.example.hexlet.controller.UsersController;
import org.example.hexlet.dto.MainPage;
import org.example.hexlet.dto.courses.BuildCoursePage;
import org.example.hexlet.dto.courses.CoursePage;
import org.example.hexlet.dto.courses.CoursesPage;
import org.example.hexlet.dto.users.BuildUserPage;
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

        app.get(NamedRoutes.helloPath(), ctx -> {
            var name = ctx.queryParamAsClass("name", String.class).getOrDefault("World");
            ctx.result("Hello, " + name + "!!!");
        });

//        Курсы

        app.get(NamedRoutes.coursesPath(), ctx -> {
            var term = ctx.queryParam("term");
            var description = ctx.queryParam("description");
            var courses = CourseRepository.getEntities().stream()
                        .sorted(Comparator.comparing(Course::getId))
                        .toList();

            String flash = ctx.consumeSessionAttribute("flash");

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
            page.setFlash(flash);
            ctx.render("courses/index.jte", model("page", page));
        });

        app.get(NamedRoutes.buildCoursePath(), CoursesController::build);

        app.get(NamedRoutes.coursePath("{id}"), CoursesController::show);

        app.post(NamedRoutes.coursesPath(), ctx -> {
            var name = ctx.formParam("name").trim();
            var description = ctx.formParam("description");

            try {
                var checkedName = ctx.formParamAsClass("name", String.class)
                        .check(value -> value.length() > 2, "Название курса должно быть длиннее 2 символов")
                        .get();

                var checkedDescription = ctx.formParamAsClass("description", String.class)
                        .check(value -> value.length() > 10, "Описание должно быть длинее 10 символов")
                        .get();

                var course = new Course(checkedName, checkedDescription);
                CourseRepository.save(course);

                ctx.sessionAttribute("flash", "Course has been created!");
                ctx.redirect(NamedRoutes.coursesPath());

            } catch (ValidationException e) {
                ctx.sessionAttribute("flash", "Course's creating has been failed!");
                String flash = ctx.consumeSessionAttribute("flash");
                var page = new BuildCoursePage(name, description, e.getErrors());
                page.setFlash(flash);
                ctx.render("courses/build.jte", model("page", page));
            }
        });

//        Пользователи

        app.get(NamedRoutes.usersPath(), ctx -> {
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

        app.get(NamedRoutes.buildUserPath(), UsersController::build);

        app.get(NamedRoutes.userPath("{id}"), UsersController::show);

        app.post(NamedRoutes.usersPath(), ctx -> {
            var name = ctx.formParam("name").trim();

            try {
                var passwordConfirmation = ctx.formParam("passwordConfirmation");
                var password = ctx.formParamAsClass("password", String.class)
                        .check(value -> value.equals(passwordConfirmation), "Пароли не совпадают")
                        .check(value -> value.length() > 6, "Длина пароля должна быть длинее 6 символов")
                        .get();

                var email = ctx.formParamAsClass("email", String.class)
                        .check(value -> {
                            for (var user : UserRepository.getEntities()) {
                                var emailForCheking = value.trim().toLowerCase();
                                if (emailForCheking.equals(user.getEmail())) {
                                    return false;
                                }
                            }
                            return true;
                        }, "Пользователь с таким email уже существует")
                        .get();

                var user = new User(name, email, password);
                UserRepository.save(user);
                ctx.redirect(NamedRoutes.usersPath());
            } catch (ValidationException e) {
                var page = new BuildUserPage(name, e.getErrors());
                ctx.render("users/build.jte", model("page", page));
            }
        });

//        Сессии

        app.get(NamedRoutes.buildSessionPath(), SessionsController::build);

        app.post(NamedRoutes.sessionsPath(), SessionsController::create);

        app.delete(NamedRoutes.sessionsPath(), SessionsController::destroy);

//        Главная

        app.get(NamedRoutes.mainPath(), ctx -> {
            var visited = Boolean.valueOf(ctx.cookie("visited"));
            String session = ctx.sessionAttribute("currentUser");

            var page = new MainPage(visited, session);

            ctx.render("index.jte", model("page", page));
            ctx.cookie("visited", String.valueOf(true));
        });

        app.start(7070);
    }
}
//    public static void show(Context ctx) {
//        var id = ctx.pathParamAsClass("id", Long.class).get();
//        var user = UserRepository.find(id)
//                .orElseThrow(() -> new NotFoundResponse("Entity with id = " + id + " not found"));
//    }

//        app.get(NamedRoutes.userPath("{id}"), ctx -> {
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

//        app.get(NamedRoutes.userPath("{id}"), ctx -> {
//            var userId = ctx.pathParamAsClass("id", Long.class).get();
//            var user = USERS.stream()
//                    .filter(u -> Objects.equals(u.getId(), userId))
//                    .findFirst()
//                    .orElseThrow(() -> new NotFoundResponse("User not found"));
//            var page = new UserPage(user);
//            ctx.render("users/show.jte", model("page", page));
//        });

//        app.get("/users/{id}/post/{postId}", ctx -> {
//            var userId = ctx.pathParam("id");
//            var postId = ctx.pathParam("postId");
//            ctx.result("User ID: " + userId + " Post ID: " + postId);
//        });

//        app.get("/courses/{courseId}/lessons/{id}", ctx -> {
//            var courseId = ctx.pathParam("courseId");
//            var lessonId = ctx.pathParam("id");
//            ctx.result("Course ID: " + courseId + " Lesson ID: " + lessonId);
//        });


