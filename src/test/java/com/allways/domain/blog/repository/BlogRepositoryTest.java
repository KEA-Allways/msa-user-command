package com.allways.domain.blog.repository;

import com.allways.common.factory.blog.BlogFactory;
import com.allways.domain.blog.entity.Blog;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // 실제 데이터베이스 사용
@SpringBootTest
@ActiveProfiles("test")
class BlogRepositoryTest {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private EntityManager entityManager;

    @AfterEach
    void tearDown() {
        blogRepository.deleteAll();
    }

    @Test
    @Transactional
    void updateByBlogSeqTest() {
        // Given
        String newBlogName = "UpdateBlogName";
        String newBlogDescription = "UpdateBlogDescription";

        Blog blog = BlogFactory.createBlog();
        entityManager.persist(blog);

        // When
        blogRepository.updateByBlogSeq(blog.getBlogSeq(), newBlogName, newBlogDescription);
        entityManager.flush();
        entityManager.clear();

        // Then
        Blog updatedBlog = blogRepository.findById(blog.getBlogSeq()).orElse(null);
        assertThat(updatedBlog).isNotNull();
        assertThat(updatedBlog.getBlogName()).isEqualTo(newBlogName);
        assertThat(updatedBlog.getBlogDescription()).isEqualTo(newBlogDescription);

        blogRepository.deleteById(updatedBlog.getBlogSeq());
    }
}
