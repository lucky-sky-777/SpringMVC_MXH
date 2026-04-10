package com.oosd.springmvc_mxh.dto;

import com.oosd.springmvc_mxh.entity.Post;

public record PostDto(
		String formatted_created_at,
		String username,
		Post post
) {}