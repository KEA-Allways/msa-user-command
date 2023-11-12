package com.allways.repository;

import com.allways.domain.user.entity.User;
import com.allways.domain.user.repository.UserRepository;
import com.allways.domain.user.exception.MemberNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static com.allways.factory.UserFactory.createUser;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;
    @PersistenceContext
    EntityManager em;

    @Test
    void createAndReadTest(){
        User user=createUser();

        userRepository.save(user);
        clear();
        User foundMember = userRepository.findById(user.getUserSeq()).orElseThrow(MemberNotFoundException::new);
        assertThat(foundMember.getUserSeq()).isEqualTo(user.getUserSeq());
    }

    private void clear() {
        em.flush();
        em.clear();
    }

}
