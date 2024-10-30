package com.Bai1.identity_service.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.Size;

import com.Bai1.identity_service.validator.DobConstraint;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {
    @Size(min = 4, message = "USERNAME_INVALID")
    String userName;

    @Size(min = 8, message = "INVALID_PASSWORD")
    String passWord;

    String firstName;
    String lastName;

    @DobConstraint(min = 16, message = "INVALID_DOB")
    LocalDate dob;
}
