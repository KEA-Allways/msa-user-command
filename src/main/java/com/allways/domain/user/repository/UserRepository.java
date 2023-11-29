package com.allways.domain.user.repository;

import com.allways.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByNickname(String nickname);

    @Modifying
    @Query("UPDATE User u SET u.password = :password, u.nickname = :nickname, u.email = :email, u.profileImgSeq = :profileImgSeq WHERE u.userSeq = :userSeq")
    void updateByUserSeq(Long userSeq, String password, String nickname, String email, String profileImgSeq);
}