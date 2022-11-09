package com.example.deliveryapp.payload;

import lombok.Data;
import lombok.NonNull;

@Data
public class LoginDTO {
    @NonNull
    private String userName;

    @NonNull
    private String password;
}
