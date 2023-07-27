package com.example.musiApi.repo;

import com.example.musiApi.model.UserAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAdminRepo extends JpaRepository<UserAdmin,Long> {
    UserAdmin findByUserAdminEmail(String userAdminEmail);
}
