package org.example.hexlet.controller;

import io.javalin.http.Context;
import org.example.hexlet.NamedRoutes;

public class SessionsController {

    public static void build(Context ctx) {
        ctx.render("sessions/build.jte");
    }

    public static void create(Context ctx) {
        var nickname = ctx.formParam("name");
        // var password = ctx.formParam("password");

        // Здесь проверка пароля

        ctx.sessionAttribute("currentUser", nickname);
        ctx.redirect(NamedRoutes.mainPath());
    }

    public static void destroy(Context ctx) {
        ctx.sessionAttribute("currentUser", null);
        ctx.redirect(NamedRoutes.mainPath());
    }
}
