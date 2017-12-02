package com.example.ihp.mobileapplicationprog3210;

/**
 * This Java file is related to 'AdminUserDatabaseActivity'
 * The purpose of this class is to store user information
 *      of the user that have an account within the app.
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


