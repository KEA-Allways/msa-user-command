package com.allways.domain.user.service;

import com.allways.domain.user.config.TokenHelper;
import com.allways.domain.user.dto.AccessTokenResponse;
import com.allways.domain.user.dto.SignInRequest;
import com.allways.domain.user.dto.SignInResponse;
import com.allways.domain.user.dto.SignUpRequest;
import com.allways.domain.user.entity.User;
import com.allways.domain.user.exception.AuthenticationEntryPointException;
import com.allways.domain.user.exception.LoginFailureException;
import com.allways.domain.user.exception.UserEmailAlreadyExistsException;
import com.allways.domain.user.exception.UserNicknameAlreadyExistsException;
import com.allways.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SignCommandService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenHelper accessTokenHelper;
    private final TokenHelper refreshTokenHelper;

    @Transactional
    //생성일 삭제일 추가
    public void signUp(SignUpRequest req) {
        validateSignUpInfo(req);
        userRepository.save(SignUpRequest.toEntity(req, passwordEncoder));
    }

    @Transactional(readOnly = true)
    public SignInResponse signIn(SignInRequest req) {
        //member 없으면 LoginFailureException
        User user = userRepository.findByEmail(req.getEmail()).orElseThrow(LoginFailureException::new);
        //비밀번호 검사
        validatePassword(req, user);

        //id를 subject 저장
        String subject = createSubject(user);
        //id를 통해서 토큰 생성이고
        String accessToken = accessTokenHelper.createToken(subject);
        String refreshToken = refreshTokenHelper.createToken(subject);
        return new SignInResponse(accessToken, refreshToken);
    }
    private void validatePassword(SignInRequest req, User user) {
        if(!passwordEncoder.matches(req.getPassword(), user.getPassword())) {
            //비밀번호 다르면 exception
            throw new LoginFailureException();
        }
    }

    private String createSubject(User user) {
        return String.valueOf(user.getUserSeq());
    }

    private void validateSignUpInfo(SignUpRequest req) {
        if(userRepository.existsByEmail(req.getEmail()))
            throw new UserEmailAlreadyExistsException(req.getEmail());
        if(userRepository.existsByNickname(req.getNickname()))
            throw new UserNicknameAlreadyExistsException(req.getNickname());
    }

    public AccessTokenResponse createNewAccessToken(String refreshToken){
        validateRefreshToken(refreshToken);
        String subject = refreshTokenHelper.extractSubject(refreshToken);
        String accessToken = accessTokenHelper.createToken(subject);
        return new AccessTokenResponse(accessToken);
    }

    private void validateRefreshToken(String refreshToken) {
        if(!refreshTokenHelper.validate(refreshToken)){
            throw new AuthenticationEntryPointException();
        }
    }
}
