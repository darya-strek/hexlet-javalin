@import org.example.hexlet.NamedRoutes
@import org.example.hexlet.dto.courses.BuildCoursePage
@param BuildCoursePage page

@template.layout.page(
    content = @`
        @if(page.getErrors() != null)
            <ul>
                @for(var validator : page.getErrors().values())
                    @for(var error : validator)
                        <li>${error.getMessage()}</li>
                    @endfor
                @endfor
            </ul>
        @endif

        <form action="${NamedRoutes.coursesPath()}" method="post">
          <div>
            <label>
              Название курса
              <input type="text" name="name" value="${page.getName()}" />
            </label>
          </div>
          <div>
            <label>
              Описание
              <input type="description" required name="description" value="${page.getDescription()}" />
            </label>
          </div>
          <input type="submit" value="Зарегистрировать" />
        </form>
    `
)