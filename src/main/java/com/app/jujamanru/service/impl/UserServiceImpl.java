package com.app.jujamanru.service.impl;

import com.app.jujamanru.component.exception.CustomException;
import com.app.jujamanru.component.security.TokenProvider;
import com.app.jujamanru.domain.team.repository.TeamRepository;
import com.app.jujamanru.domain.user.converter.UserConverter;
import com.app.jujamanru.domain.user.converter.UserDtoConverter;
import com.app.jujamanru.domain.user.repository.UserRepository;
import com.app.jujamanru.dto.user.LoginRequest;
import com.app.jujamanru.dto.user.UserDto;
import com.app.jujamanru.dto.user.UserSaveRequest;
import com.app.jujamanru.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final UserRepository userRepository;
    private final TeamRepository teamRepository;
    private final TokenProvider tokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public UserDto getUser(String userId) {
        return userRepository.findById(userId)
                .map(UserDtoConverter::new)
                .map(UserDtoConverter::convert)
                .orElseThrow(() -> new CustomException(HttpStatus.BAD_REQUEST, "해당 유저는 존재하지 않습니다. ID : " + userId));
    }

    @Override
    @Transactional
    public UserDto login(LoginRequest request) {
        if (!userRepository.existsById(request.getUserId())) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "해당되는 유저가 없습니다. mail : " + request.getUserId());
        }
        var accessToken = this.generateToken(request.getUserId(), request.getPassword());

        return userRepository.findById(request.getUserId())
                .map(user -> new UserDtoConverter(user).convertForLogin(accessToken))
                .orElseThrow(() -> new CustomException(HttpStatus.BAD_REQUEST, "해당되는 유저가 없습니다. userId : " + request.getUserId()));
    }

    @Override
    @Transactional
    public UserDto signup(UserSaveRequest request) {
        if (userRepository.existsById(request.getUserId())) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "이미 존재하는 유저입니다. userId : " + request.getUserId());
        }

        var team = request.getTeamId() != null
                ? teamRepository.findById(request.getTeamId()).orElseThrow()
                : null;
        var user = new UserConverter(request)
                .convert()
                .changePassword(passwordEncoder.encode(request.getPassword()))
                .changeTeam(team);
        System.out.println(user.getPassword());
        var entity = userRepository.save(user);
        var accessToken = this.generateToken(request.getUserId(), request.getPassword());

        return new UserDtoConverter(entity).convertForLogin(accessToken);
    }

    private String generateToken(String mail, String password) {
        if (Objects.isNull(mail) || Objects.isNull(password)) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "메일 혹은 패스워드를 입력해주세요");
        }

        // 1. Login ID/PW 를 기반으로 AuthenticationToken 생성
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(mail, password);

        // 2. 실제로 검증 (사용자 비밀번호 체크) 이 이루어지는 부분
        //    authenticate 메서드가 실행이 될 때 CustomUserDetailsService 에서 만들었던 loadUserByUsername 메서드가 실행됨
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        return tokenProvider.generateTokenDto(authentication);
    }

    @Override
    @Transactional
    public Long changeTeam(String userId, Long teamId) {
        return userRepository.findById(userId)
                .map(user -> user.changeTeam(teamRepository.findById(teamId)
                            .orElseThrow(() -> new CustomException(HttpStatus.BAD_REQUEST, "해당 되는 팀이 없습니다. teamId : " + teamId))))
                .map(user -> userRepository.save(user).getTeam().getId())
                .orElseThrow(() -> new CustomException(HttpStatus.BAD_REQUEST, "해당되는 유저가 없습니다. userId : " + userId));
    }
}
