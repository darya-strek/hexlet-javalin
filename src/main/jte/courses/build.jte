@import org.example.hexlet.dto.courses.BuildCoursePage

@param BuildCoursePage page

<!doctype html>
<html lang="en">
    <head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>New course</title>
    </head>
    <body>
        @if(page.getErrors() != null)
            <ul>
                @for(var validator : page.getErrors().values())
                    @for(var error : validator)
                        <li>${error.getMessage()}</li>
                    @endfor
                @endfor
            </ul>
        @endif

        <form action="/courses" method="post">
          <div>
            <label>
              Название
              <input type="text" name="name" value="${page.getName()}" />
            </label>
          </div>
          <div>
            <label>
              Описание
              <input type="text" required name="description" value="${page.getDescription()}" />
            </label>
          </div>
          <input type="submit" value="Сохранить" />
        </form>
    </body>
</html>