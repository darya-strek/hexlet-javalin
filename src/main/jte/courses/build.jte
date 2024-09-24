<html lang="en">
    <head>
        <meta charset="utf-8" />
        <title>New course</title>
    </head>
    <body>
        <form action="/courses" method="post">
          <div>
            <label>
              Название курса
              <input type="text" name="name" />
            </label>
          </div>
          <div>
            <label>
              Описание
              <input type="description" required name="description" />
            </label>
          </div>
          <input type="submit" value="Зарегистрировать" />
        </form>
    </body>
</html>