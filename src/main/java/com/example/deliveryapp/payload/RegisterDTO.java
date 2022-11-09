package com.example.deliveryapp.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class RegisterDTO {
    private String fullName;

    @NotNull
    private String username;

    @NotNull
    private String password;

    private String phoneNumber;

    private List<Long> regionIds;
}
