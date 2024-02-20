package gg.jte.generated.ondemand.users;
import org.example.hexlet.dto.users.BuildUserPage;
public final class JtebuildGenerated {
	public static final String JTE_NAME = "users/build.jte";
	public static final int[] JTE_LINE_INFO = {0,0,2,2,2,12,12,12,14,14,15,15,16,16,16,17,17,18,18,20,20,26,26,26,26,26,26,26,26,32,32,32,32,32,32,32,32,50};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, BuildUserPage page) {
		jteOutput.writeContent("\r\n<!doctype html>\r\n<html lang=\"en\">\r\n    <head>\r\n    <meta content='text/html; charset=UTF-8' http-equiv='Content-Type'/>\r\n    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\" />\r\n    <title>New user</title>\r\n    </head>\r\n    <body>\r\n        ");
		if (page.getErrors() != null) {
			jteOutput.writeContent("\r\n            <ul>\r\n                ");
			for (var validator : page.getErrors().values()) {
				jteOutput.writeContent("\r\n                    ");
				for (var error : validator) {
					jteOutput.writeContent("\r\n                        <li>");
					jteOutput.setContext("li", null);
					jteOutput.writeUserContent(error.getMessage());
					jteOutput.writeContent("</li>\r\n                    ");
				}
				jteOutput.writeContent("\r\n                ");
			}
			jteOutput.writeContent("\r\n            </ul>\r\n        ");
		}
		jteOutput.writeContent("\r\n\r\n        <form action=\"/users\" method=\"post\">\r\n          <div>\r\n            <label>\r\n              Имя\r\n              <input type=\"text\" name=\"name\"");
		if (gg.jte.runtime.TemplateUtils.isAttributeRendered(page.getName())) {
			jteOutput.writeContent(" value=\"");
			jteOutput.setContext("input", "value");
			jteOutput.writeUserContent(page.getName());
			jteOutput.setContext("input", null);
			jteOutput.writeContent("\"");
		}
		jteOutput.writeContent(" />\r\n            </label>\r\n          </div>\r\n          <div>\r\n            <label>\r\n              Email\r\n              <input type=\"email\" required name=\"email\"");
		if (gg.jte.runtime.TemplateUtils.isAttributeRendered(page.getEmail())) {
			jteOutput.writeContent(" value=\"");
			jteOutput.setContext("input", "value");
			jteOutput.writeUserContent(page.getEmail());
			jteOutput.setContext("input", null);
			jteOutput.writeContent("\"");
		}
		jteOutput.writeContent(" />\r\n            </label>\r\n          </div>\r\n          <div>\r\n            <label>\r\n              Пароль\r\n              <input type=\"password\" required name=\"password\" />\r\n            </label>\r\n          </div>\r\n          <div>\r\n            <label>\r\n              Подтверждение пароля\r\n              <input type=\"password\" required name=\"passwordConfirmation\" />\r\n            </label>\r\n          </div>\r\n          <input type=\"submit\" value=\"Зарегистрировать\" />\r\n        </form>\r\n    </body>\r\n</html>");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		BuildUserPage page = (BuildUserPage)params.get("page");
		render(jteOutput, jteHtmlInterceptor, page);
	}
}
