package com.oosd.springmvc_mxh.config.interceptor;

import com.oosd.springmvc_mxh.util.HttpServletResponseUtils;
import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jspecify.annotations.NonNull;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class NoCacheInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(
			@NonNull HttpServletRequest request,
			@NonNull HttpServletResponse response,
			@NonNull Object handler
	) throws Exception {
		HttpServletResponseUtils.noCache(response);
		return true;
	}

	@Override
	public void postHandle(
			@NonNull HttpServletRequest request,
			@NonNull HttpServletResponse response,
			@NonNull Object handler,
			@Nullable ModelAndView modelAndView
	) throws Exception {
		// TODO
	}

	@Override
	public void afterCompletion(
			@NonNull HttpServletRequest request,
			@NonNull HttpServletResponse response,
			@NonNull Object handler,
			@Nullable Exception ex
	) throws Exception {
		// TODO
	}

}