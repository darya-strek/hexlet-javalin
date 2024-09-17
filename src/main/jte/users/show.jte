@import org.example.hexlet.dto.users.UserPage
@param UserPage page

@template.layout.page(
    content = @`
        <main>
          <h1>ID: ${page.getUser().getId()}</h1>
          <p>Name: ${page.getUser().getFirstName()} ${page.getUser().getLastName()}</p>
          <p>Email: ${page.getUser().getEmail()}</p>
        </main>
    `
)