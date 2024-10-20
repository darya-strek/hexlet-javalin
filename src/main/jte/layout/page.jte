@import org.example.hexlet.NamedRoutes
@import org.example.hexlet.dto.BasePage
@import gg.jte.Content
@import org.example.hexlet.dto.courses.CoursesPage

@param BasePage page = null
@param Content content
@param Content footer = null

<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Darya's site</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    </head>
    <body>
        @if(page != null && page.getFlash() != null)
            @if(page.getFlash().contains("Course has been created!"))
                <div class="alert alert-success" role="alert">
                  <p>${page.getFlash()}</p>
                </div>
            @else
                <div class="alert alert-danger" role="alert">
                  <p>${page.getFlash()}</p>
                </div>
            @endif
        @endif

        <h1>Darya's first test site</h1>
        <p>
          <a href="${NamedRoutes.mainPath()}">Main page</a><span>    </span>
          <a href="${NamedRoutes.usersPath()}">Users</a><span>    </span>
          <a href="${NamedRoutes.coursesPath()}">Courses</a><span>    </span>
          <a href="${NamedRoutes.buildSessionPath()}">Login</a>
        </p>
        <div>
            ${content}
        </div>
        @if (footer != null)
          ${footer}
        @endif
    </body>
</html>