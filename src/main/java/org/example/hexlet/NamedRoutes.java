package org.example.hexlet;

public class NamedRoutes {

    // /users
    public static String usersPath() {
        return "/users";
    }

    public static String buildUserPath() {
        return "/users/build";
    }

    public static String userPath(Long id) {
        return userPath(String.valueOf(id));
    }

    public static String userPath(String id) {
        return "/users/" + id;
    }

    // /courses

    public static String coursesPath() {
        return "/courses";
    }

    public static String buildCoursePath() {
        return "/courses/build";
    }

    public static String coursePath(Long id) {
        return coursePath(String.valueOf(id));
    }

    public static String coursePath(String id) {
        return "/courses/" + id;
    }

    // /sessions

    public static String buildSessionPath() {
        return "/sessions/build";
    }

    public static String sessionsPath() {
        return "/sessions";
    }

    // /hello and /

    public static String helloPath() {
        return "/hello";
    }

    public static String mainPath() {
        return "/";
    }
}
