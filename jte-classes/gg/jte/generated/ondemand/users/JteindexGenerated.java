package gg.jte.generated.ondemand.users;
import org.example.hexlet.dto.users.UsersPage;
public final class JteindexGenerated {
	public static final String JTE_NAME = "users/index.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,1,1,3,3,5,5,8,8,8,8,8,8,8,8,11,11,13,13,15,15,18,18,18,18,18,18,18,21,21,21,24,24,24,27,27,29,29,30,30,30};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, UsersPage page) {
		jteOutput.writeContent("\r\n");
		gg.jte.generated.ondemand.layout.JtepageGenerated.render(jteOutput, jteHtmlInterceptor, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\r\n        <a href=\"/users/build\">New User</a>\r\n        <form action=\"/users\" method=\"get\">\r\n            <input type=\"search\" name=\"term\"");
				if (gg.jte.runtime.TemplateUtils.isAttributeRendered(page.getTerm())) {
					jteOutput.writeContent(" value=\"");
					jteOutput.setContext("input", "value");
					jteOutput.writeUserContent(page.getTerm());
					jteOutput.setContext("input", null);
					jteOutput.writeContent("\"");
				}
				jteOutput.writeContent(" />\r\n            <input type=\"submit\" value=\"Search\" />\r\n        </form>\r\n        ");
				if (page.getUsers().isEmpty()) {
					jteOutput.writeContent("\r\n            <p>No any users</p>\r\n        ");
				} else {
					jteOutput.writeContent("\r\n            <table class=\"table table-striped\">\r\n                ");
					for (var user : page.getUsers()) {
						jteOutput.writeContent("\r\n                    <tr>\r\n                        <td>\r\n                            <a href=\"/users/");
						jteOutput.setContext("a", "href");
						jteOutput.writeUserContent(user.getId());
						jteOutput.setContext("a", null);
						jteOutput.writeContent("\">");
						jteOutput.setContext("a", null);
						jteOutput.writeUserContent(user.getId());
						jteOutput.writeContent("</a>\r\n                        </td>\r\n                        <td>\r\n                            ");
						jteOutput.setContext("td", null);
						jteOutput.writeUserContent(user.getName());
						jteOutput.writeContent("\r\n                        </td>\r\n                        <td>\r\n                            ");
						jteOutput.setContext("td", null);
						jteOutput.writeUserContent(user.getEmail());
						jteOutput.writeContent("\r\n                        </td>\r\n                    </tr>\r\n                ");
					}
					jteOutput.writeContent("\r\n            </table>\r\n        ");
				}
				jteOutput.writeContent("\r\n    ");
			}
		}, null);
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		UsersPage page = (UsersPage)params.get("page");
		render(jteOutput, jteHtmlInterceptor, page);
	}
}
