package org.example.expert;

import org.example.expert.config.JwtUtil;
import org.example.expert.config.PasswordEncoder;
import org.example.expert.domain.auth.dto.request.SignupRequest;
import org.example.expert.domain.auth.service.AuthService;
import org.example.expert.domain.common.exception.InvalidRequestException;
import org.example.expert.domain.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
public class ExpertApplicationTests {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private AuthService authService;

    @Test
    public void signup_DuplicateEmail_ShouldNotCallPasswordEncoder() {
        // Given
        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setEmail("existing@example.com");
        signupRequest.setPassword("password123");
        signupRequest.setUserRole("USER");

        given(userRepository.existsByEmail(signupRequest.getEmail())).willReturn(true);

        // When
        Throwable thrown = catchThrowable(() -> authService.signup(signupRequest));

        // Then
        assertThat(thrown)
                .isInstanceOf(InvalidRequestException.class)
                .hasMessageContaining("이미 존재하는 이메일입니다.");
        then(passwordEncoder).should(never()).encode(anyString());
    }
}