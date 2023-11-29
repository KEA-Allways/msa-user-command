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

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class BlogCommandServiceTest {
    @Mock private BlogRepository blogRepository;
    @InjectMocks private BlogCommandService blogCommandService;
    @Captor private ArgumentCaptor<Blog> BlogArgumentCaptor;

    @Test
    void createBlogTest() {
        // Given
        BlogCreateRequest createRequest = BlogCreateRequestFactory.createBlogCreateRequest();
        Long userSeq = 1L;

        // When
        blogCommandService.createBlog(createRequest, userSeq);

        // Then
        verify(blogRepository, times(1)).save(BlogArgumentCaptor.capture());

        Blog savedBlog = BlogArgumentCaptor.getValue();

        assertEquals(createRequest.getBlogName(), savedBlog.getBlogName());
        assertEquals(createRequest.getBlogDescription(), savedBlog.getBlogDescription());
    }

    @Test
    void updateBlogTest() {
        // Given
        Long blogSeq = 1L;
        BlogUpdateRequest request = BlogUpdateRequestFactory.createBlogUpdateRequest();

        // When
        blogCommandService.updateBlog(blogSeq, request);

        // Then
        verify(blogRepository, times(1)).updateByBlogSeq(blogSeq, request.getBlogName(), request.getBlogDescription());
    }

    @Test
    void deleteBlogTest() {
        // Given
        Long blogSeq = 1L;

        // When
        blogCommandService.deleteBlog(blogSeq);

        // Then
        verify(blogRepository, times(1)).deleteById(blogSeq);
    }
}
