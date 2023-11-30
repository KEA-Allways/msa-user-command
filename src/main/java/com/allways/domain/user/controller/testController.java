package com.allways.domain.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.allways.common.feign.blog.BlogFeignClient;
import com.allways.common.feign.blog.BlogFeignResponse;
import com.allways.common.feign.blog.BlogFeignService;
import com.allways.common.response.Response;
import com.allways.domain.user.dto.UserUpdateRequest;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class testController {

	private final BlogFeignService blogFeignService;

	@PostMapping("/api/user/test/{user_seq}")
	@ResponseStatus(HttpStatus.OK)
	public BlogFeignResponse test (@PathVariable(value = "user_seq") Long userSeq) {
		return blogFeignService.queryBlog(userSeq);
	}
}
