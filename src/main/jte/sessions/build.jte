@import org.example.hexlet.NamedRoutes

@template.layout.page(
    content = @`
            <form action="${NamedRoutes.sessionsPath()}" method="post">
              <input type="text" placeholder="name" name="name" />
              <input type="password" placeholder="password" name="password" />
              <input type="submit" value="Войти" />
            </form>
    `
)