package com.example.ihp.mobileapplicationprog3210;

/**
 * Created by iHP on 11/5/2017.
 */

public class UserDetailData {

    private String fullName;
    private String username;
    private String email;

    public UserDetailData(String fullName, String username, String email) {
        this.fullName = fullName;
        this.username = username;
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}


