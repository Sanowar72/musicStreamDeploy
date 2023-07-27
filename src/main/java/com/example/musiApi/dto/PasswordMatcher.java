package com.example.musiApi.dto;

import org.springframework.stereotype.Component;

@Component
public class PasswordMatcher {
    public static boolean passwordMatcher(String ExistingPassword,String InputPassword){
        return ExistingPassword.equals(InputPassword);
    }
}
