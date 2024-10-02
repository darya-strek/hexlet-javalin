@import org.example.hexlet.NamedRoutes
@import org.example.hexlet.dto.users.UsersPage
@param UsersPage page

@template.layout.page(
    content = @`
        <a href="${NamedRoutes.buildUserPath()}">New User</a>

        <form action="${NamedRoutes.usersPath()}" method="get">
            <p><span>Search by name:</span><input type="search" name="term" value="${page.getTerm()}">
            <input type="submit" value="Search">
            </p>
        </form>

        @if(page.getUsers().isEmpty())
            <p>Пока не добавлено ни одного пользователя</p>
        @else
            @for(var user : page.getUsers())
               <div>
                   <h4><a href="${NamedRoutes.userPath(user.getId())}">${user.getId()}</a>${user.getName()}</h4>
               </div>
            @endfor
        @endif
    `
)