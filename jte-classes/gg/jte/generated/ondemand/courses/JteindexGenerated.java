package gg.jte.generated.ondemand.courses;
import org.example.hexlet.dto.courses.CoursesPage;
public final class JteindexGenerated {
	public static final String JTE_NAME = "courses/index.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,1,1,3,3,6,6,7,7,7,9,9,11,11,12,12,14,14,14,14,14,14,14,15,15,15,17,17,18,18,19,19,19,19,19,1,1,1,1};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, CoursesPage page) {
		jteOutput.writeContent("\r\n");
		gg.jte.generated.ondemand.layout.JtepageGenerated.render(jteOutput, jteHtmlInterceptor, page, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\r\n        <h1>");
				jteOutput.setContext("h1", null);
				jteOutput.writeUserContent(page.getHeader());
				jteOutput.writeContent("</h1>\r\n        <a href=\"/courses/build\">New Course</a>\r\n        ");
				if (page.getCourses().isEmpty()) {
					jteOutput.writeContent("\r\n            <p>Пока не добавлено ни одного курса</p>\r\n        ");
				} else {
					jteOutput.writeContent("\r\n            ");
					for (var course : page.getCourses()) {
						jteOutput.writeContent("\r\n               <div>\r\n                   <h2><a href=\"/courses/");
						jteOutput.setContext("a", "href");
						jteOutput.writeUserContent(course.getId());
						jteOutput.setContext("a", null);
						jteOutput.writeContent("\">");
						jteOutput.setContext("a", null);
						jteOutput.writeUserContent(course.getName());
						jteOutput.writeContent("</a></h2>\r\n                   <p>");
						jteOutput.setContext("p", null);
						jteOutput.writeUserContent(course.getDescription());
						jteOutput.writeContent("</p>\r\n               </div>\r\n            ");
					}
					jteOutput.writeContent("\r\n        ");
				}
				jteOutput.writeContent("\r\n    ");
			}
		}, null);
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		CoursesPage page = (CoursesPage)params.get("page");
		render(jteOutput, jteHtmlInterceptor, page);
	}
}
