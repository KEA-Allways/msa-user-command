package com.allways.common.factory.blog;

import com.allways.domain.blog.dto.BlogCreateRequest;

public class BlogCreateRequestFactory {
    public static BlogCreateRequest createBlogCreateRequest() {
        return new BlogCreateRequest("newBlogName", "newBlogDescription");
    }

    public static BlogCreateRequest createBlogCreateRequest(String blogName, String blogDescription) {
        return new BlogCreateRequest(blogName, blogDescription);
    }
}
