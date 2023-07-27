package com.example.musiApi.service;

import com.example.musiApi.dto.PasswordEncoder;
import com.example.musiApi.dto.PasswordMatcher;
import com.example.musiApi.dto.SignIn;
import com.example.musiApi.dto.SignUpOutput;
import com.example.musiApi.model.UserAdmin;
import com.example.musiApi.repo.UserAdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAdminService {
    private final UserAdminRepo userAdminRepo;
    private final PasswordEncoder passwordEncoder;
    private final PasswordMatcher passwordMatcher;

    @Autowired
    public UserAdminService(UserAdminRepo userRepo, PasswordEncoder passwordEncoder,PasswordMatcher passwordMatcher) {
        this.userAdminRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.passwordMatcher=passwordMatcher;
    }
    public boolean verifyUserCredentials(String userEmail, String userPassword) {
        UserAdmin existingUser = userAdminRepo.findByUserAdminEmail(userEmail);
        if (existingUser == null) {
            return false; // User not found
        }

        String hashedPassword = passwordEncoder.encodePassword(userPassword);
        String storedPassword = existingUser.getUserAdminPassword();
        return passwordMatcher.passwordMatcher(hashedPassword, storedPassword);
    }

    public SignUpOutput addAdmin(UserAdmin userAdmin) {
        boolean signUpStatus = true;
        String signUpMsg = null;
        String signUpEmail = userAdmin.getUserAdminEmail();
        UserAdmin existingUser = userAdminRepo.findByUserAdminEmail(signUpEmail);
        if (existingUser != null) {
            signUpStatus = false;
            signUpMsg = "Email already registered.";
            return new SignUpOutput(signUpStatus, signUpMsg);
        }
        String hashedPassword = passwordEncoder.encodePassword(userAdmin.getUserAdminPassword());
        userAdmin.setUserAdminPassword(hashedPassword);
        userAdminRepo.save(userAdmin);
        signUpMsg = "Success";
        return new SignUpOutput(signUpStatus, signUpMsg);
    }


    public SignUpOutput signin(SignIn signIn) {
        String signInEmail = signIn.getSignInEmail();
        UserAdmin existingUser = userAdminRepo.findByUserAdminEmail(signInEmail);
        if (existingUser == null) {
            return new SignUpOutput(false, "User not found.");
        }
        boolean passwordMatches = verifyUserCredentials(signInEmail, signIn.getSignInPassword());

        if (!passwordMatches) {
            return new SignUpOutput(false, "Invalid password.");
        }

        return new SignUpOutput(true, "Sign in successful.");
    }

    public List<UserAdmin> getAllAdmin() {
        return userAdminRepo.findAll();
    }
}
