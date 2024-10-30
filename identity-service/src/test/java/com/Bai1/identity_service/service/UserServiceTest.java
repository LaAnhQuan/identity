package com.Bai1.identity_service.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;

import com.Bai1.identity_service.dto.request.UserCreationRequest;
import com.Bai1.identity_service.dto.response.UserResponse;
import com.Bai1.identity_service.entity.User;
import com.Bai1.identity_service.exception.AppException;
import com.Bai1.identity_service.repository.UserRepository;

@SpringBootTest
@TestPropertySource("/test.properties")
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    private UserCreationRequest request;
    private UserResponse userResponse;
    private User user;
    private LocalDate dob;

    @BeforeEach
    void initData() {

        dob = LocalDate.of(1990, 1, 1);
        request = UserCreationRequest.builder()
                .userName("john")
                .firstName("John")
                .lastName("Doe")
                .passWord("12345678")
                .dob(dob)
                .build();

        userResponse = UserResponse.builder()
                .id("cf0600f538b3")
                .userName("john")
                .firstName("John")
                .lastName("Doe")
                .dob(dob)
                .build();
        user = User.builder()
                .id("cf0600f538b3")
                .userName("john")
                .firstName("John")
                .lastName("Doe")
                .dob(dob)
                .build();
    }

    @Test
    void createUser_validRequest_success() {

        // Given
        when(userRepository.existsByUserName(anyString())).thenReturn(false);
        when(userRepository.save(any())).thenReturn(user);

        // When
        var response = userService.createUser(request);

        // Then
        assertThat(response.getId()).isEqualTo("cf0600f538b3");
        assertThat(response.getUserName()).isEqualTo("john");
    }

    @Test
    void createUser_userExisted_fail() {

        // Given
        when(userRepository.existsByUserName(anyString())).thenReturn(true);

        // When
        var exception = assertThrows(AppException.class, () -> userService.createUser(request));
        // Then
        assertThat(exception.getErrorCode().getCode()).isEqualTo(1002);
    }

    @Test
    @WithMockUser(username = "john")
    void getMyInfo_valid_success() {
        when(userRepository.findByUserName(anyString())).thenReturn(Optional.of(user));

        var reponse = userService.getMYInfor();

        assertThat(reponse.getUserName()).isEqualTo("john");
        assertThat(reponse.getId()).isEqualTo("cf0600f538b3");
    }

    @Test
    @WithMockUser(username = "john")
    void getMyInfo_userNotFound_error() {
        when(userRepository.findByUserName(anyString())).thenReturn(Optional.ofNullable(null));

        // When
        var exception = assertThrows(AppException.class, () -> userService.getMYInfor());

        assertThat(exception.getErrorCode().getCode()).isEqualTo(1005);
    }
}
