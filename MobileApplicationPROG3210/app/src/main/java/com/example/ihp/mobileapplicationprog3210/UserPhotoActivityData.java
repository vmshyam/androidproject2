package com.example.ihp.mobileapplicationprog3210;

/**
 * This Java file is related to 'AdminPhotoDatabaseActivity'
 * The purpose of this class is to store user photo information
 *      to be used to display user photo information to admin.
 */

public class UserPhotoActivityData {

    private String username;

    private String photoName;

    private String photoDateStamp;

    private byte[] photoImage;

    public UserPhotoActivityData(String username, String photoName, String photoDateStamp, byte[] photoImage) {
        this.username = username;
        this.photoName = photoName;
        this.photoDateStamp = photoDateStamp;
        this.photoImage = photoImage;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }

    public String getPhotoDateStamp() {
        return photoDateStamp;
    }

    public void setPhotoDateStamp(String photoDateStamp) {
        this.photoDateStamp = photoDateStamp;
    }

    public byte[] getPhotoImage() {
        return photoImage;
    }

    public void setPhotoImage(byte[] photoImage) {
        this.photoImage = photoImage;
    }
}
