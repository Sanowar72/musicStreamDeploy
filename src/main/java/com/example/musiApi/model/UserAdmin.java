package com.example.musiApi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import static javax.persistence.GenerationType.AUTO;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserAdmin {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long userAdminId;
    private String userAdminName;
    private String userAdminMobNo;
    private String userAdminEmail;
    private String userAdminPassword;

}
