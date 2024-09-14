package gg.jte.generated.ondemand.courses;
import org.example.hexlet.dto.courses.CoursePage;
public final class JteshowGenerated {
	public static final String JTE_NAME = "courses/show.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,1,1,10,10,10,10,11,11,11,14,14,14,1,1,1,1};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, CoursePage page) {
		jteOutput.writeContent("\r\n<html>\r\n    <head>\r\n        <meta charset=\"utf-8\" />\r\n        <title>Хекслет</title>\r\n    </head>\r\n    <body>\r\n        <main>\r\n            <h1>");
		jteOutput.setContext("h1", null);
		jteOutput.writeUserContent(page.getCourse().getName());
		jteOutput.writeContent("</h1>\r\n            <p>");
		jteOutput.setContext("p", null);
		jteOutput.writeUserContent(page.getCourse().getDescription());
		jteOutput.writeContent("</p>\r\n        </main>\r\n    </body>\r\n</html>");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		CoursePage page = (CoursePage)params.get("page");
		render(jteOutput, jteHtmlInterceptor, page);
	}
}
