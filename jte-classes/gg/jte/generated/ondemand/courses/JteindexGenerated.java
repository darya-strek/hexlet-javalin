package gg.jte.generated.ondemand.courses;
import org.example.hexlet.dto.courses.CoursesPage;
public final class JteindexGenerated {
	public static final String JTE_NAME = "courses/index.jte";
	public static final int[] JTE_LINE_INFO = {0,0,2,2,2,4,4,7,7,8,8,8,11,11,11,11,11,11,11,11,14,14,16,16,17,17,19,19,19,19,19,19,19,20,20,20,22,22,23,23,24,24,24,24,26,26,26,27};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, CoursesPage page) {
		jteOutput.writeContent("\r\n");
		gg.jte.generated.ondemand.layout.JtepageGenerated.render(jteOutput, jteHtmlInterceptor, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\r\n        <h1>");
				jteOutput.setContext("h1", null);
				jteOutput.writeUserContent(page.getHeader());
				jteOutput.writeContent("</h1>\r\n        <a href=\"/courses/build\">New Course</a>\r\n        <form action=\"/courses\" method=\"get\">\r\n          <input type=\"search\" name=\"term\"");
				if (gg.jte.runtime.TemplateUtils.isAttributeRendered(page.getTerm())) {
					jteOutput.writeContent(" value=\"");
					jteOutput.setContext("input", "value");
					jteOutput.writeUserContent(page.getTerm());
					jteOutput.setContext("input", null);
					jteOutput.writeContent("\"");
				}
				jteOutput.writeContent(" />\r\n          <input type=\"submit\" value=\"Search\" />\r\n        </form>\r\n                ");
				if (page.getCourses().isEmpty()) {
					jteOutput.writeContent("\r\n                    <p>No any courses</p>\r\n                ");
				} else {
					jteOutput.writeContent("\r\n                    ");
					for (var course : page.getCourses()) {
						jteOutput.writeContent("\r\n                       <div>\r\n                           <h2><a href=\"/courses/");
						jteOutput.setContext("a", "href");
						jteOutput.writeUserContent(course.getId());
						jteOutput.setContext("a", null);
						jteOutput.writeContent("\">");
						jteOutput.setContext("a", null);
						jteOutput.writeUserContent(course.getName());
						jteOutput.writeContent("</a></h2>\r\n                           <p>");
						jteOutput.setContext("p", null);
						jteOutput.writeUserContent(course.getDescription());
						jteOutput.writeContent("</p>\r\n                       </div>\r\n                    ");
					}
					jteOutput.writeContent("\r\n                ");
				}
				jteOutput.writeContent("\r\n    ");
			}
		}, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\r\n        <p><a href=\"https://github.com/darya-strek\">This is my GitHub</a></p>\r\n    ");
			}
		});
		jteOutput.writeContent("\r\n");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		CoursesPage page = (CoursesPage)params.get("page");
		render(jteOutput, jteHtmlInterceptor, page);
	}
}
