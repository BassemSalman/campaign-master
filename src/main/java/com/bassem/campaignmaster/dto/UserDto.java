package com.bassem.campaignmaster.dto;

import com.bassem.campaignmaster.validation.annotation.ValidPhoneNumber;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder

@NoArgsConstructor
@AllArgsConstructor

public class UserDto {
    @Null
    private Long id;

    @NotBlank
    private String username;

    @ValidPhoneNumber
    private String phoneNumber;
}
