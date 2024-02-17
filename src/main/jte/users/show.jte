@import org.example.hexlet.dto.users.UserPage

@param UserPage page

@template.layout.page(
    content = @`
        <h2>${page.getUser().getName()}</h2>
        <p><i>User's ID: </i>${page.getUser().getId()}</p>
        <p><i>Name: </i>${page.getUser().getName()}</p>
        <p><i>Email: </i>${page.getUser().getEmail()}</p>
    `,
    footer = null
)