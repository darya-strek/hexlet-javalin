@import org.example.hexlet.NamedRoutes
@import org.example.hexlet.dto.courses.CoursesPage
@param CoursesPage page

@template.layout.page(
    page = page,
    content = @`
        <h1>${page.getHeader()}</h1>
        <a href="${NamedRoutes.buildCoursePath()}">New Course</a>

        <form action="${NamedRoutes.coursesPath()}" method="get">
          <p><span>Search by name:</span><input type="search" name="term" value="${page.getTerm()}"></p>
          <p><span>Search by description:</span><input type="search" name="description" value="${page.getDescription()}"></p>
          <p><input type="submit" value="Search"></p>
        </form>

        @if(page.getCourses().isEmpty())
            <p>Пока не добавлено ни одного курса</p>
        @else
            @for(var course : page.getCourses())
               <div>
                   <h2><a href="${NamedRoutes.coursePath(course.getId())}">${course.getName()}</a></h2>
                   <p>${course.getDescription()}</p>
               </div>
            @endfor
        @endif
    `
)