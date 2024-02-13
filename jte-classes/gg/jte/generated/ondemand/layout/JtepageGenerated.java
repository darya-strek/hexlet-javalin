package gg.jte.generated.ondemand.layout;
import gg.jte.Content;
import org.example.hexlet.dto.courses.CoursesPage;
import org.example.hexlet.dto.courses.CoursePage;
import org.example.hexlet.dto.users.UserPage;
public final class JtepageGenerated {
	public static final String JTE_NAME = "layout/page.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,2,3,5,5,5,23,23,23,23,25,25,27,27,27,29,29,31};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, Content content, Content footer) {
		jteOutput.writeContent("\r\n<!doctype html>\r\n<html lang=\"en\">\r\n    <head>\r\n        <meta charset=\"utf-8\" />\r\n        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\" />\r\n        <title>Hexlet Javalin Example</title>\r\n    </head>\r\n    <body>\r\n        <h2>This is my first page</h2>\r\n        <p>\r\n            <a href=\"/\">Main</a>\r\n            <a href=\"/users\">Users</a>\r\n            <a href=\"/courses\">Courses</a>\r\n        </p>\r\n        <div class=\"content\">\r\n            ");
		jteOutput.setContext("div", null);
		jteOutput.writeUserContent(content);
		jteOutput.writeContent("\r\n        </div>\r\n        ");
		if (footer != null) {
			jteOutput.writeContent("\r\n            <div class=\"footer\">\r\n                ");
			jteOutput.setContext("div", null);
			jteOutput.writeUserContent(footer);
			jteOutput.writeContent("\r\n            </div>\r\n        ");
		}
		jteOutput.writeContent("\r\n    </body>\r\n</html>");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		Content content = (Content)params.get("content");
		Content footer = (Content)params.getOrDefault("footer", null);
		render(jteOutput, jteHtmlInterceptor, content, footer);
	}
}
