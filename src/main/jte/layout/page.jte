@import gg.jte.Content
@import org.example.hexlet.dto.courses.CoursesPage
@import org.example.hexlet.dto.courses.CoursePage
@import org.example.hexlet.dto.users.UserPage

@param Content content
@param Content footer = null

<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <title>Hexlet Javalin Example</title>
    </head>
    <body>
        <h2>This is my first page</h2>
        <p>
            <a href="/">Main</a>
            <a href="/users">Users</a>
            <a href="/courses">Courses</a>
        </p>
        <div class="content">
            ${content}
        </div>
        @if (footer != null)
            <div class="footer">
                ${footer}
            </div>
        @endif
    </body>
</html>