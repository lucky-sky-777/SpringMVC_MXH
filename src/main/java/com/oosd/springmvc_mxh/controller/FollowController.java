package com.oosd.springmvc_mxh.controller;

import com.oosd.springmvc_mxh.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.SQLException;

@Controller
@RequestMapping("follow")
public class FollowController {

	private final FollowService followService;

	@Autowired
	public FollowController(FollowService followService) {
		this.followService = followService;
	}

	@PostMapping("{target_username}")
	public String follow(
			@SessionAttribute("username") String username,
			@PathVariable String target_username,
			RedirectAttributes redirectAttributes
	) throws SQLException {
		boolean success = followService.follow(username, target_username);
		redirectAttributes.addFlashAttribute("followSuccess", success);
		return "redirect:/profile/" + target_username;
	}

	@DeleteMapping("{target_username}")
	public String unfollow(
			@SessionAttribute("username") String username,
			@PathVariable String target_username,
			RedirectAttributes redirectAttributes
	) throws SQLException {
		boolean success = followService.unfollow(username, target_username);
		redirectAttributes.addFlashAttribute("unfollowSuccess", success);
		return "redirect:/profile/" + target_username;
	}

}