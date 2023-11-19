package com.allways.domain.blog.service;

import com.allways.domain.blog.dto.BlogCreateRequest;
import com.allways.domain.blog.dto.BlogCreateResponse;
import com.allways.domain.blog.dto.BlogUpdateRequest;
import com.allways.domain.blog.entity.Blog;
import com.allways.domain.blog.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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