package com.allways.domain.template.repository;

import com.allways.domain.template.domain.Template;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TemplateRepository extends JpaRepository<Template, Long> {
    @Query("select t from Template t where t.userSeq = :userSeq")
    List<Template> findAllByUserSeq(Long userSeq);

    @Query("select t from Template t where t.templateSeq =:templateSeq")
    Template findByTemplateSeq(Long templateSeq);
}
