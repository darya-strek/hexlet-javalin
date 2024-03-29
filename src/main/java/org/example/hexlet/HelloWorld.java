package org.example.hexlet;

import io.javalin.Javalin;
import io.javalin.validation.ValidationException;
import org.apache.commons.text.StringEscapeUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.example.hexlet.dto.courses.BuildCoursePage;
import org.example.hexlet.dto.courses.CoursesPage;
import org.example.hexlet.dto.users.BuildUserPage;
import org.example.hexlet.dto.users.UserPage;
import org.example.hexlet.dto.users.UsersPage;
import org.example.hexlet.model.Course;
import org.example.hexlet.dto.courses.CoursePage;
import org.example.hexlet.model.User;
import org.example.hexlet.repository.CourseRepository;
import org.example.hexlet.repository.UserRepository;
import org.owasp.html.HtmlPolicyBuilder;
import org.owasp.html.PolicyFactory;

import javax.xml.validation.Validator;

public class HelloWorld {

    public static void main(String[] args) {

        Course php = new Course("PHP", "PHP its wow");
        CourseRepository.save(php);
        Course java = new Course("Java", "Java for backend");
        CourseRepository.save(java);
        Course python = new Course("Python", "Python for frontend and backend");
        CourseRepository.save(python);
        Course sql = new Course("SQL", "SQL is the best");
        CourseRepository.save(sql);
        Course javascript = new Course("JavaScript", "JavaScript for frontend");
        CourseRepository.save(javascript);

        User user1 = new User("John Wilson", "email.example@gmail.com", "1234");
        UserRepository.save(user1);


        // Создаем приложение
        var app = Javalin.create(config -> {
            config.plugins.enableDevLogging();
        });

        app.get("/courses/build", ctx -> {
            var page = new BuildCoursePage();
            ctx.render("courses/build.jte", Collections.singletonMap("page", page));
        });

        app.post("/courses", ctx -> {
            var name = ctx.formParam("name");
            var description = ctx.formParam("description");

            try {
                var valName = ctx.formParamAsClass("name", String.class)
                        .check(n -> n.length() > 2, "Название курса должно быть длиннее 2 символов")
                        .get();

                var valDescription = ctx.formParamAsClass("description", String.class)
                        .check(d -> d.length() > 10, "Описание курса должно быть длиннее 10 символов")
                        .get();

                var course = new Course(name, description);
                CourseRepository.save(course);
                ctx.redirect("/courses");

            } catch (ValidationException e) {
                var page = new BuildCoursePage(name, description, e.getErrors());
                ctx.render("courses/build.jte", Collections.singletonMap("page", page));
            }
        });

        app.get("/courses/{id}", ctx -> {
            var id = ctx.pathParamAsClass("id", Long.class).get();
            var course = CourseRepository.find(id).get();
            var page = new CoursePage(course);
            ctx.render("courses/show.jte", Collections.singletonMap("page", page));
        });

        app.get("/courses", ctx -> {
            var term = ctx.queryParam("term");
            var header = "There are courses of programming";
            List<Course> courses;
            if (term != null) {
                courses =  CourseRepository.getEntities()
                        .stream()
                        .filter(c -> {
                            var name = c.getName().toLowerCase();
                            var description = c.getDescription().toLowerCase();
                            var termNormalized = term.toLowerCase();
                            return name.contains(termNormalized) || description.contains(termNormalized);
                        })
                        .toList();
            } else {
                courses = CourseRepository.getEntities();
            }
            var page = new CoursesPage(courses, header, term);
            ctx.render("courses/index.jte", Collections.singletonMap("page", page));
        });

        app.get("/users/build", ctx -> {
            var page = new BuildUserPage();
            ctx.render("users/build.jte", Collections.singletonMap("page", page));
        });

        app.get("/users/{id}", ctx -> {
            var id = ctx.pathParamAsClass("id", Long.class).get();

//            var escapedId = StringEscapeUtils.escapeHtml4(id);
//            PolicyFactory policy = new HtmlPolicyBuilder()
//                    .allowElements("a")
//                    .allowUrlProtocols("https")
//                    .allowAttributes("href").onElements("a")
//                    .requireRelNofollowOnLinks()
//                    .toFactory();
//            String safeHTML = policy.sanitize(id);
            var user = UserRepository.find(id).get();
            var page = new UserPage(user);
            ctx.render("users/show.jte", Collections.singletonMap("page", page));
        });

        app.post("/users", ctx -> {
            var name = ctx.formParam("name").trim();
            var email = ctx.formParam("email").trim().toLowerCase();

            try {
                var valName = ctx.formParamAsClass("name", String.class)
                        .check(n -> n.length() > 1, "Имя должно быть длиннее 1 символа")
                        .get();

                var passwordConfirmation = ctx.formParam("passwordConfirmation");
                var password = ctx.formParamAsClass("password", String.class)
                        .check(v -> v.equals(passwordConfirmation), "Пароли не совпадают")
                        .check(v -> v.length() > 7, "Пароль должен быть равен или длиннее 8 символов")
                        .get();

                var user = new User(name, email, password);
                UserRepository.save(user);
                ctx.redirect("/users");
            } catch (ValidationException e) {
                var page = new BuildUserPage(name, email, e.getErrors());
                ctx.render("users/build.jte", Collections.singletonMap("page", page));
            }
        });

        app.get("/users", ctx -> {
            var term = ctx.queryParam("term");
            List<User> users;
            if (term != null) {
                users =  UserRepository.getEntities()
                        .stream()
                        .filter(u -> {
                            var name = u.getName().toLowerCase();
                            var termNormalized = term.toLowerCase();
                            return name.contains(termNormalized);
                        })
                        .toList();
            } else {
                users = UserRepository.getEntities();
            }
            var page = new UsersPage(users, term);
            ctx.render("users/index.jte", Collections.singletonMap("page", page));
        });



        app.get("/", ctx -> {
            ctx.render("layout/example.jte");
        });

        app.start(7070); // Стартуем веб-сервер
    }
}
