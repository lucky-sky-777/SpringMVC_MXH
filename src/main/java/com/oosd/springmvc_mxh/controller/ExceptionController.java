package com.oosd.springmvc_mxh.controller;

import com.oosd.springmvc_mxh.util.StackTraceBuilder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

	@ExceptionHandler(Exception.class)
	public String handle(Exception exception, Model model) {
		model.addAttribute("message", exception.getMessage());
		model.addAttribute("cause", exception.getCause());
		model.addAttribute("stacktrace", StackTraceBuilder.build(exception.getStackTrace()));
		return "error";
	}

}