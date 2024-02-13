@import org.example.hexlet.dto.users.UserPage

@param UserPage page

@template.layout.page(
    content = @`
        <h2>${page.getUser().getId()}</h2>
    `,
    footer = null
)