@import org.example.hexlet.dto.MainPage
@param MainPage page

@template.layout.page(
    content = @`
        <main>
            <p>This is Main page</p>
        </main>
        <div>
            <h1>Привет, Хекслет!</h1>
            @if(!page.isVisited())
                Это сообщение показывается только один раз. Если вы хотите увидеть его снова, сотрите куки
            @endif
        </div>
    `,
    footer = @`
        <p>Welcome to my <a href="https://github.com/darya-strek">GitHub</a></p>
    `
)

