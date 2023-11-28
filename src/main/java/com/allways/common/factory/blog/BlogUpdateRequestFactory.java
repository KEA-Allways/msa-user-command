package com.allways.common.factory.blog;

import com.allways.domain.blog.dto.BlogUpdateRequest;

public class BlogUpdateRequestFactory {
    public static BlogUpdateRequest createBlogUpdateRequest() {
        return new BlogUpdateRequest("newBlogName", "newBlogDescription");
    }

    public static BlogUpdateRequest createBlogUpdateRequest(String blogName, String blogDescription) {
        return new BlogUpdateRequest(blogName, blogDescription);
    }
}
