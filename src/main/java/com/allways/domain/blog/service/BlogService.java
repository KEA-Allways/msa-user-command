package com.allways.domain.blog.service;

import com.allways.domain.blog.dto.BlogCreateRequest;
import com.allways.domain.blog.dto.BlogUpdateRequest;
import com.allways.domain.blog.repository.BlogRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class BlogService {
    private final BlogRepository blogRepository;

    // 토큰에서 얻은 userSeq
    public void createBlog(BlogCreateRequest req, Long userSeq) {
        blogRepository.save(req.toEntity(req, userSeq));
    }

    public void updateBlog(Long userSeq, BlogUpdateRequest req) {
        blogRepository.updateByUserSeq(userSeq, req.getBlogName(), req.getBlogDescription());
    }

    public void deleteBlog(Long userSeq) {
        blogRepository.deleteByUserSeq(userSeq);
    }
}