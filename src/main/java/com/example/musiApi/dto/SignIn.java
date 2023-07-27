package com.example.musiApi.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignIn {
    @NotNull
    private String signInEmail;
    @NotNull
    private String signInPassword;
}
