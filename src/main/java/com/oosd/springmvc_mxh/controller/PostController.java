package com.oosd.springmvc_mxh.controller;

import com.oosd.springmvc_mxh.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.SQLException;

@Controller
@RequestMapping("post")
public class PostController {

	private final PostService postService;

	@Autowired
	public PostController(PostService postService) {
		this.postService = postService;
	}

	@PostMapping("create")
	public String createPost(
			@SessionAttribute("username") String username,
			@RequestParam String title,
			@RequestParam String body,
			RedirectAttributes redirectAttributes
	) throws SQLException {
		boolean success = postService.createPost(username, title, body);
		redirectAttributes.addFlashAttribute("createPostSuccess", success);
		return "redirect:/index";
	}

}

//@PostMapping("update")
//public String updatePost(
//		HttpSession httpSession,
//		@RequestParam int id,
//		@RequestParam String title,
//		@RequestParam String body
//) throws SQLException {
//	String username = (String) httpSession.getAttribute("username");
//	postService.updatePost(username, id, title, body);
//	return "redirect:/index";
//}
//
//@PostMapping("delete")
//public String deletePost(
//		HttpSession httpSession,
//		@RequestParam int id
//) throws SQLException {
//	String username = (String) httpSession.getAttribute("username");
//	postService.deletePost(username, id);
//	return "redirect:/index";
//}