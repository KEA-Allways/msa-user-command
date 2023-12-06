package com.allways.domain.blog.service;

import com.allways.common.factory.blog.BlogCreateRequestFactory;
import com.allways.common.factory.blog.BlogUpdateRequestFactory;
import com.allways.domain.blog.dto.BlogCreateRequest;
import com.allways.domain.blog.dto.BlogUpdateRequest;
import com.allways.domain.blog.entity.Blog;
import com.allways.domain.blog.repository.BlogRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class BlogServiceTest {
    @Mock private BlogRepository blogRepository;
    @InjectMocks private BlogService blogService;
    @Captor private ArgumentCaptor<Blog> BlogArgumentCaptor;

    @Test
    void createBlogTest() {
        // Given
        BlogCreateRequest createRequest = BlogCreateRequestFactory.createBlogCreateRequest();
        Long userSeq = 1L;

        // When
        blogService.createBlog(createRequest, userSeq);

        // Then
        verify(blogRepository).save(BlogArgumentCaptor.capture());

        Blog savedBlog = BlogArgumentCaptor.getValue();

        assertEquals(createRequest.getBlogName(), savedBlog.getBlogName());
        assertEquals(createRequest.getBlogDescription(), savedBlog.getBlogDescription());
    }

    @Test
    void updateBlogTest() {
        // Given
        Long blogSeq = 1L;
        BlogUpdateRequest updateRequest = BlogUpdateRequestFactory
                .createBlogUpdateRequest();

        // When
        blogService.updateBlog(blogSeq, updateRequest);

        // Then
        verify(blogRepository).updateByUserSeq(
                blogSeq, updateRequest.getBlogName(),
                updateRequest.getBlogDescription()
        );
    }

    @Test
    void deleteBlogTest() {
        // Given
        Long blogSeq = 1L;

        // When
        blogService.deleteBlog(blogSeq);

        // Then
        verify(blogRepository).deleteById(blogSeq);
    }
}
