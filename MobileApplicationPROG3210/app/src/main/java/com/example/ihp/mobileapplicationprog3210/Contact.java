package com.example.ihp.mobileapplicationprog3210;

/**
 * This Java file is related to 'LoginActivity', 'RegisterActivity'
 * The purpose of this class is used to store each user information
 *      that is created within the app.
 */

public class Contact {

    String givenName, userName, emailAddress, password;


    public void setGivenName (String name){
        this.givenName = name;
    }

    public String getGivenName () {
        return this.givenName;
    }

    public void setUserName (String username){
        this.userName = username;
    }

    public String getUserName (){
        return this.userName;
    }

    public void setEmailAdress (String email){
        this.emailAddress = email;
    }

    public String getEmailAddress (){
        return this.emailAddress;
    }

    public void setPassword (String password){
        this.password = password;
    }

    public String getPassword () {
        return this.password;
    }
}
