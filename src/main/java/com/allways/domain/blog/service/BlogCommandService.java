package com.allways.domain.blog.service;

import com.allways.common.feign.user.UserFeignClient;
import com.allways.common.feign.user.UserFeignResponse;
import com.allways.domain.blog.dto.BlogCreateRequest;
import com.allways.domain.blog.dto.BlogCreateResponse;
import com.allways.domain.blog.dto.BlogUpdateRequest;
import com.allways.domain.blog.entity.Blog;
import com.allways.domain.blog.exception.BlogNotFoundException;
import com.allways.domain.blog.repository.BlogRepository;
import com.allways.domain.user.entity.User;
import com.allways.domain.user.exception.UserNotFoundException;
import com.allways.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class BlogCommandService {
    private final BlogRepository blogRepository;

    // 토큰에서 얻은 userSeq
    public BlogCreateResponse createBlog(BlogCreateRequest req, Long userSeq) {
        Blog blog = blogRepository.save(req.toEntity(req, userSeq));

        return new BlogCreateResponse(blog.getBlogSeq());
    }

    public void updateBlog(Long blogSeq, BlogUpdateRequest req) {
        blogRepository.updateById(blogSeq, req.getBlogName(), req.getBlogDescription());
    }

    public void deleteBlog(Long blogSeq) {
        blogRepository.deleteById(blogSeq);
    }
}