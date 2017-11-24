package com.example.ihp.mobileapplicationprog3210;

/**
 * Created by iHP on 11/22/2017.
 */

public class AccountUserLogData {

    private String username;
    private String timestamp;

    public AccountUserLogData(String username, String timestamp) {
        this.username = username;
        this.timestamp = timestamp;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
