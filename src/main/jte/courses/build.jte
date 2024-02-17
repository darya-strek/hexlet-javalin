<!doctype html>
<html lang="en">
    <head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>New course</title>
    </head>
    <body>

        <form action="/courses" method="post">
          <div>
            <label>
              Название
              <input type="text" name="name" />
            </label>
          </div>
          <div>
            <label>
              Описание
              <input type="text" required name="description" />
            </label>
          </div>
          <input type="submit" value="Сохранить" />
        </form>

    </body>
</html>