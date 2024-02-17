package org.example.hexlet;

import io.javalin.Javalin;
import org.apache.commons.text.StringEscapeUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.example.hexlet.dto.courses.CoursesPage;
import org.example.hexlet.dto.users.UserPage;
import org.example.hexlet.dto.users.UsersPage;
import org.example.hexlet.model.Course;
import org.example.hexlet.dto.courses.CoursePage;
import org.example.hexlet.model.User;
import org.example.hexlet.repository.CourseRepository;
import org.example.hexlet.repository.UserRepository;
import org.owasp.html.HtmlPolicyBuilder;
import org.owasp.html.PolicyFactory;

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
            ctx.render("courses/build.jte");
        });

        app.post("/courses", ctx -> {
            var name = ctx.formParam("name");
            var description = ctx.formParam("description");

            var course = new Course(name, description);
            CourseRepository.save(course);
            ctx.redirect("/courses");
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
            ctx.render("users/build.jte");
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
            var password = ctx.formParam("password").trim();
            var passwordConfirmation = ctx.formParam("passwordConfirmation");

            var user = new User(name, email, password);
            UserRepository.save(user);
            ctx.redirect("/users");
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
