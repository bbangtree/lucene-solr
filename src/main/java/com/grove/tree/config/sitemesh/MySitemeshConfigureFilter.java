package com.grove.tree.config.sitemesh;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;

public class MySitemeshConfigureFilter extends ConfigurableSiteMeshFilter {

	@Override
	protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
		builder.setMimeTypes("text/html", "application/xhtml+xml", "application/vnd.wap.xhtml+xml");
		
		// Map default decorator. This shall be applied to all paths if no other paths match.
		builder.addDecoratorPath("/*", "/WEB-INF/views/decorators/front/default.jsp");
		
		// Map multiple decorators to the a single path.
//		builder.addDecoratorPath("/admin/*", "/WEB-INF/views/decorators/admin/default.jsp");
		
		
		// Exclude path from decoration.
/*		builder.addExcludedPath("/admin/daum/*")
			.addExcludedPath("/admin/login.do")
			.addExcludedPath("/admin/login_check.do")
			.addExcludedPath("/admin/logout.do");
*/

		
	}
}
