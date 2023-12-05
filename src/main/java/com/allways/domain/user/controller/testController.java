package com.allways.domain.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.allways.common.feign.blog.BlogFeignResponse;
import com.allways.common.feign.blog.BlogFeignService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class testController {

	private final BlogFeignService blogFeignService;

	@PostMapping("/api/user/test")
	@ResponseStatus(HttpStatus.OK)
	public BlogFeignResponse test() {
		System.out.println("test");
		return blogFeignService.queryBlog(1L);
	}
}
