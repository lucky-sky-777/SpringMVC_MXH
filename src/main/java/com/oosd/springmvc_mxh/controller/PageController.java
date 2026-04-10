package com.oosd.springmvc_mxh.controller;

import com.oosd.springmvc_mxh.dto.PostDto;
import com.oosd.springmvc_mxh.entity.Post;
import com.oosd.springmvc_mxh.service.FollowService;
import com.oosd.springmvc_mxh.service.PostService;
import com.oosd.springmvc_mxh.service.UserService;
import com.oosd.springmvc_mxh.util.DateTimeUtils;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"unused", "SpellCheckingInspection"})
@Controller
public class PageController {

	private final PostService postService;
	private final UserService userService;
	private final FollowService followService;

	@Autowired
	public PageController(
			PostService postService,
			UserService userService,
			FollowService followService
	) {
		this.postService = postService;
		this.userService = userService;
		this.followService = followService;
	}

	@GetMapping("/")
	public String rootPage() {
		return "redirect:/index";
	}

	@GetMapping("index")
	public String indexPage(
			HttpSession httpSession,
			Model model
	) {
		Object username = httpSession.getAttribute("username");
		boolean isSignedIn = username != null;
		if (isSignedIn) {
			model.addAttribute("username", username.toString());
		}
		model.addAttribute("isSignedIn", isSignedIn);
		return "index";
	}

	@GetMapping("signin")
	public String signInPage(
			HttpSession session
	) {
		return "signin";
	}

	@GetMapping("signup")
	public String signUpPage(
			HttpSession session
	) {
		return "signup";
	}

	@GetMapping("profile")
	public String profilePage(
			@SessionAttribute("username") String username
	) {
		return "redirect:/profile/" + username;
	}

	@GetMapping("profile/{target_username}")
	public String profilePageWithPath(
			HttpSession httpSession,
			@PathVariable String target_username,
			@SessionAttribute("username") String username,
			Model model
	) throws SQLException {
		List<Post> postList = postService.getAllPost(userService.getUserId(target_username));
		List<PostDto> postDtos = new ArrayList<>();
		postList.forEach(item -> {
			postDtos.add(
					new PostDto(
							DateTimeUtils.formatTimestamp(item.getCreatedAt(), "HH:mm, dd/MM/yyyy"),
							target_username,
							item
					)
			);
		});
		model.addAttribute("postDtos", postDtos);
		model.addAttribute("username", target_username);
		boolean isSelf = username.equals(target_username);
		model.addAttribute("isSelf", isSelf);
		if (!isSelf) {
			boolean isFollowed = followService.isFollowed(username, target_username);
			model.addAttribute("isFollowed", isFollowed);
		}
		return "profile";
	}

	@GetMapping("search")
	public String searchPage() {
		return "search";
	}

}