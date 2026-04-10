package com.oosd.springmvc_mxh.util;

import jakarta.servlet.http.HttpServletResponse;

public final class HttpServletResponseUtils {

	public static void noCache(HttpServletResponse httpServletResponse) {
		httpServletResponse.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
		httpServletResponse.setHeader("Pragma", "no-cache");
		httpServletResponse.setDateHeader("Expires", 0);
	}

}