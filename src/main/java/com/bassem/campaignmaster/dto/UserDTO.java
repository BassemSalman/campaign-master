package com.bassem.campaignmaster.dto;

import com.bassem.campaignmaster.validation.annotation.ValidPhoneNumber;
import jakarta.validation.constraints.NotNull;
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

public class UserDTO {
    @Null
    private Long id;

    @NotNull
    private String username;

    @ValidPhoneNumber
    private String phoneNumber;
}
