package com.nirban.user.service.dto.requestDto;


import com.nirban.user.service.entities.Rating;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private String userId;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    private String email;

    @NotBlank(message = "About is required")
    @Size(min = 10, message = "About must be at least 10 characters long")
    private String about;

    private List<Rating> ratings;
}
