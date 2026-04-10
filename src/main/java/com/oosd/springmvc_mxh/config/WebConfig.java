package com.oosd.springmvc_mxh.config;

import com.oosd.springmvc_mxh.config.interceptor.AuthInterceptor;
import com.oosd.springmvc_mxh.config.interceptor.NoCacheInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry
				.addInterceptor(new NoCacheInterceptor())
				.addPathPatterns("/**");

		registry
				.addInterceptor(new AuthInterceptor(AuthInterceptor.Mode.NOT_REQUIRE_SIGNIN))
				.addPathPatterns("/signin");

		registry
				.addInterceptor(new AuthInterceptor(AuthInterceptor.Mode.REQUIRE_SIGNIN))
				.addPathPatterns(
						"/profile/**",
						"/search"
				);
	}

}