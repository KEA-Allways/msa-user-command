package com.allways.domain.user.service;

import com.allways.domain.user.config.TokenHelper;
import com.allways.domain.user.dto.RefreshTokenResponse;
import com.allways.domain.user.dto.SignInRequest;
import com.allways.domain.user.dto.SignUpRequest;
import com.allways.domain.user.entity.User;
import com.allways.domain.user.exception.AuthenticationEntryPointException;
import com.allways.domain.user.exception.LoginFailureException;
import com.allways.domain.user.exception.MemberEmailAlreadyExistsException;
import com.allways.domain.user.exception.MemberNicknameAlreadyExistsException;
import com.allways.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SignService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenHelper accessTokenHelper;
    private final TokenHelper refreshTokenHelper;

    @Transactional
    public void signUp(SignUpRequest req) {
        validateSignUpInfo(req);
        userRepository.save(SignUpRequest.
                toEntity(req,
                        passwordEncoder));
    }
    private void validatePassword(SignInRequest req, User user) {
        if(!passwordEncoder.matches(req.getPassword(), user.getPassword())) {
            //비밀번호 다르면 exception
            throw new LoginFailureException();
        }
    }

    private String createSubject(User user) {
        return String.valueOf(user.getUserId());
    }


    private void validateSignUpInfo(SignUpRequest req) {
        if(userRepository.existsByEmail(req.getEmail()))
            throw new MemberEmailAlreadyExistsException(req.getEmail());
        if(userRepository.existsByNickname(req.getNickname()))
            throw new MemberNicknameAlreadyExistsException(req.getNickname());
    }

    public RefreshTokenResponse refreshToken(String rToken){
        validateRefreshToken(rToken);
        String subject =refreshTokenHelper.extractSubject(rToken);
        String accessToken = accessTokenHelper.createToken(subject);
        return new RefreshTokenResponse(accessToken);

    }

    private void validateRefreshToken(String rToken) {
        if(!refreshTokenHelper.validate(rToken)){
            throw new AuthenticationEntryPointException();
        }
    }
}
