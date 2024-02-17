package gg.jte.generated.ondemand.courses;
import org.example.hexlet.dto.courses.CoursePage;
public final class JteshowGenerated {
	public static final String JTE_NAME = "courses/show.jte";
	public static final int[] JTE_LINE_INFO = {0,0,2,2,2,4,4,7,7,8,8,8,9,9,9,10,10,10,14};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, CoursePage page) {
		jteOutput.writeContent("\r\n");
		gg.jte.generated.ondemand.layout.JtepageGenerated.render(jteOutput, jteHtmlInterceptor, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\r\n        <h1>");
				jteOutput.setContext("h1", null);
				jteOutput.writeUserContent(page.getCourse().getName());
				jteOutput.writeContent("</h1>\r\n        <p>");
				jteOutput.setContext("p", null);
				jteOutput.writeUserContent(page.getCourse().getDescription());
				jteOutput.writeContent("</p>\r\n    ");
			}
		}, null);
		jteOutput.writeContent("\r\n\r\n\r\n\r\n");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		CoursePage page = (CoursePage)params.get("page");
		render(jteOutput, jteHtmlInterceptor, page);
	}
}
