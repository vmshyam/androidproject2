package com.example.ihp.mobileapplicationprog3210;

/**
 *
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
