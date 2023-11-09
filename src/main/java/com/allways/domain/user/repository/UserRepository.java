package com.allways.domain.user.repository;

import com.allways.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long>{

    Optional<User> findByEmail (String email);//1
    Optional<User> findByNickname (String nickname);//2
    boolean existsByEmail(String email); // 3
    boolean existsByNickname(String nickname); // 4
}
