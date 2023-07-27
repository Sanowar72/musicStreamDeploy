package com.example.musiApi.repo;

import com.example.musiApi.model.User;
import com.example.musiApi.model.UserAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
    User findByUserEmail(String userEmail);

}
