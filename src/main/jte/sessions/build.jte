@import org.example.hexlet.NamedRoutes

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

            <form action="${NamedRoutes.sessionsPath()}" method="post">
              <input type="text" placeholder="Nickname" name="nickname" />
              <input type="password" placeholder="Password" name="password" />
              <input type="submit" value="Войти" />
            </form>
    `
)