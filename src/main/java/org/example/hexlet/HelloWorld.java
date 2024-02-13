package org.example.hexlet;

import io.javalin.Javalin;
import org.apache.commons.text.StringEscapeUtils;

import java.util.Collections;

import org.example.hexlet.dto.courses.CoursesPage;
import org.example.hexlet.dto.users.UserPage;
import org.example.hexlet.model.Course;
import org.example.hexlet.dto.courses.CoursePage;
import org.example.hexlet.model.User;
import org.example.hexlet.repository.CourseRepository;
import org.owasp.html.HtmlPolicyBuilder;
import org.owasp.html.PolicyFactory;

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

        app.get("/users/{id}", ctx -> {
            var id = ctx.pathParam("id");
//            var escapedId = StringEscapeUtils.escapeHtml4(id);
//            PolicyFactory policy = new HtmlPolicyBuilder()
//                    .allowElements("a")
//                    .allowUrlProtocols("https")
//                    .allowAttributes("href").onElements("a")
//                    .requireRelNofollowOnLinks()
//                    .toFactory();
//            String safeHTML = policy.sanitize(id);
            var user = new User(id);
            var page = new UserPage(user);
            ctx.render("users/show.jte", Collections.singletonMap("page", page));
        });

        app.get("/", ctx -> {
            ctx.render("layout/example.jte");
        });

        app.start(7070); // Стартуем веб-сервер
    }
}
