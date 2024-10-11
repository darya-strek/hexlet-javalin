@import org.example.hexlet.dto.MainPage
@import org.example.hexlet.NamedRoutes
@param MainPage page

@template.layout.page(
    content = @`
        <main>
            <p>This is Main page</p>
        </main>
        <div>
            <h1>Этот блок показывает нам куки</h1>
            @if(!page.isVisited())
                Это сообщение показывается только один раз. Если вы хотите увидеть его снова, сотрите куки
            @endif
        </div>
        <div>
            <a href="${NamedRoutes.buildSessionPath()}">Login</a>
            <h1>Этот блок показывает нам залогиненого пользователя</h1>
            @if(page.getCurrentUser() != null)
                Добро пожаловать, ${page.getCurrentUser()}.
                Чтобы разлогиниться, удалите куку JSESSIONID из браузера
            @endif
        </div>
    `,
    footer = @`
        <p>Welcome to my <a href="https://github.com/darya-strek">GitHub</a></p>
    `
)

