package com.allways.common.factory.blog;

import com.allways.domain.blog.entity.Blog;

public class BlogFactory {
    public static Blog createBlog() {
        return new Blog("newBlogName",
                "newBlogDescription",
                1L);
    }

    public static Blog createBlog(String blogName, String blogDescription, Long userSeq) {
        return new Blog(blogName, blogDescription, userSeq);
    }
}
