package com.allways.domain.blog.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
@Setter
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long blogSeq;

    @Column(nullable = false)
    private String blogName;

    @Column(nullable = false)
    private String blogDescription;

    @Column
    private Long userSeq;

    public Blog(String blogName, String blogDescription, Long userSeq) {
        this.blogName = blogName;
        this.blogDescription = blogDescription;
        this.userSeq = userSeq;
    }
}
