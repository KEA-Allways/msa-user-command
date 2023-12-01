package com.allways.domain.blog.repository;

import com.allways.domain.blog.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface BlogRepository extends JpaRepository<Blog, Long> {

    @Modifying
    @Query("UPDATE Blog b SET b.blogName = :blogName, b.blogDescription = :blogDescription  WHERE b.userSeq= :userSeq")
    void updateByUserSeq(Long userSeq, String blogName, String blogDescription);

    void deleteByUserSeq(Long userSeq);
}