@import org.example.hexlet.NamedRoutes
@import gg.jte.Content
@import org.example.hexlet.dto.courses.CoursesPage

@param CoursesPage page = null
@param Content content
@param Content footer = null

<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        @if(page != null && page.getHeader() != null)
            <title>${page.getHeader()}</title>
        @else
            <title>Darya's site</title>
        @endif
    </head>
    <body>
        <h1>Darya's first test site</h1>
        <p>
          <a href="${NamedRoutes.mainPath()}">Main page  </a>
          <a href="${NamedRoutes.usersPath()}">Users  </a>
          <a href="${NamedRoutes.coursesPath()}">Courses</a>
        </p>
        <div>
            ${content}
        </div>
        @if (footer != null)
          ${footer}
        @endif
    </body>
</html>