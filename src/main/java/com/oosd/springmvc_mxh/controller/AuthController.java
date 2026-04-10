package com.oosd.springmvc_mxh.controller;

import com.oosd.springmvc_mxh.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.SQLException;

@SuppressWarnings({"SpellCheckingInspection"})
@Controller
@RequestMapping("auth")
public class AuthController {

	private final UserService userService;

	@Autowired
	public AuthController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("signin")
	public String signIn(
			HttpServletRequest request,
			@RequestParam String username,
			@RequestParam String password,
			RedirectAttributes redirectAttributes
	) throws SQLException {
		boolean success = userService.signIn(username, password);

		if (success) {
			request.getSession().setAttribute("username", username);
			redirectAttributes.addFlashAttribute("message", "Đăng nhập thành công");
		} else {
			redirectAttributes.addFlashAttribute("message", "Đăng nhập thất bại");
		}

		return "redirect:/signin";
	}

	@PostMapping("signup")
	public String signUp(
			@RequestParam String username,
			@RequestParam String password,
			@RequestParam String repassword,
			RedirectAttributes redirectAttributes
	) throws SQLException {
		boolean success = userService.signUp(username, password, repassword);

		if (success) {
			redirectAttributes.addFlashAttribute("message", "Đăng ký thành công");
		} else {
			redirectAttributes.addFlashAttribute("message", "Đăng ký thất bại");
		}

		return "redirect:/signup";
	}

	@GetMapping("signout")
	public String signOut(
			HttpSession httpSession,
			RedirectAttributes redirectAttributes
	) {
		httpSession.invalidate();
		redirectAttributes.addFlashAttribute("message", "Đăng xuất thành công");
		return "redirect:/index";
	}

}