package com.allways.domain.user.repository;

import com.allways.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);//1

    Optional<User> findByNickname(String nickname);//2

    boolean existsByEmail(String email); // 3

    boolean existsByNickname(String nickname); // 4

    @Modifying
    @Query("UPDATE User u SET u.password = :password, u.nickname = :nickname, u.profileImgSeq = :profileImgSeq WHERE u.userSeq = :userSeq")
    void updateByUserSeq(Long userSeq, String password, String nickname,String profileImgSeq);

    @Modifying
    @Query("UPDATE User u SET  u.nickname = :nickname, u.profileImgSeq = :profileImgSeq WHERE u.userSeq = :userSeq")
    void updateByUserSeqWithoutPassword(Long userSeq,  String nickname,String profileImgSeq);
}