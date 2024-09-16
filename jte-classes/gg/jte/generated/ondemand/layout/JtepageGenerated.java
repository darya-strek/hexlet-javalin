package gg.jte.generated.ondemand.layout;
import gg.jte.Content;
import org.example.hexlet.dto.courses.CoursesPage;
public final class JtepageGenerated {
	public static final String JTE_NAME = "layout/page.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,3,3,3,12,12,12,13,13,13,14,14,16,16,26,26,26,28,28,29,29,29,30,30,32,32,32,3,4,5,5,5,5};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, CoursesPage page, Content content, Content footer) {
		jteOutput.writeContent("\r\n<!doctype html>\r\n<html lang=\"en\">\r\n    <head>\r\n        <meta charset=\"utf-8\" />\r\n        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\" />\r\n        ");
		if (page != null && page.getHeader() != null) {
			jteOutput.writeContent("\r\n            <title>");
			jteOutput.setContext("title", null);
			jteOutput.writeUserContent(page.getHeader());
			jteOutput.writeContent("</title>\r\n        ");
		} else {
			jteOutput.writeContent("\r\n            <title>Darya's site</title>\r\n        ");
		}
		jteOutput.writeContent("\r\n    </head>\r\n    <body>\r\n        <h1>Darya's first test site</h1>\r\n        <p>\r\n          <a href=\"/\">Main page  </a>\r\n          <a href=\"/users\">Users  </a>\r\n          <a href=\"/courses\">Courses</a>\r\n        </p>\r\n        <div>\r\n            ");
		jteOutput.setContext("div", null);
		jteOutput.writeUserContent(content);
		jteOutput.writeContent("\r\n        </div>\r\n        ");
		if (footer != null) {
			jteOutput.writeContent("\r\n          ");
			jteOutput.setContext("body", null);
			jteOutput.writeUserContent(footer);
			jteOutput.writeContent("\r\n        ");
		}
		jteOutput.writeContent("\r\n    </body>\r\n</html>");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		CoursesPage page = (CoursesPage)params.getOrDefault("page", null);
		Content content = (Content)params.get("content");
		Content footer = (Content)params.getOrDefault("footer", null);
		render(jteOutput, jteHtmlInterceptor, page, content, footer);
	}
}
