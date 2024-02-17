@import org.example.hexlet.dto.users.UsersPage
@param UsersPage page

@template.layout.page(
    content = @`
        <a href="/users/build">New User</a>
        <form action="/users" method="get">
            <input type="search" name="term" value="${page.getTerm()}" />
            <input type="submit" value="Search" />
        </form>
        @if(page.getUsers().isEmpty())
            <p>No any users</p>
        @else
            <table class="table table-striped">
                @for(var user : page.getUsers())
                    <tr>
                        <td>
                            <a href="/users/${user.getId()}">${user.getId()}</a>
                        </td>
                        <td>
                            ${user.getName()}
                        </td>
                        <td>
                            ${user.getEmail()}
                        </td>
                    </tr>
                @endfor
            </table>
        @endif
    `
)