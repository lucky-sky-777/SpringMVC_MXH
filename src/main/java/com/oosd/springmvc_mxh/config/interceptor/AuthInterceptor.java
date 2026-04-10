package com.oosd.springmvc_mxh.config.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.jspecify.annotations.NonNull;
import org.springframework.web.servlet.HandlerInterceptor;

@SuppressWarnings({"SpellCheckingInspection"})
public class AuthInterceptor implements HandlerInterceptor {

	public enum Mode {
		REQUIRE_SIGNIN,
		NOT_REQUIRE_SIGNIN
	}

	private final Mode mode;

	public AuthInterceptor(Mode mode) {
		this.mode = mode;
	}

	@Override
	public boolean preHandle(
			@NonNull HttpServletRequest request,
			@NonNull HttpServletResponse response,
			@NonNull Object handler
	) throws Exception {
		HttpSession session = request.getSession(false);
		boolean isSignedIn = session != null && session.getAttribute("username") != null;

		if (mode == Mode.REQUIRE_SIGNIN && !isSignedIn) {
			response.sendRedirect("/index");
			return false;
		}

		if (mode == Mode.NOT_REQUIRE_SIGNIN && isSignedIn) {
			response.sendRedirect("/index");
			return false;
		}

		return true;
	}

}