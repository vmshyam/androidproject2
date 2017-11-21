package com.example.ihp.mobileapplicationprog3210;

/**
 * Created by iHP on 11/3/2017.
 */

public class UserPhotoData {

    private String photoName;
    private byte[] photoImage;
    private String photoDateStamp;

    public UserPhotoData(String photoName, String photoDateStamp, byte[] photoImage) {
        this.photoName = photoName;
        this.photoImage = photoImage;
        this.photoDateStamp = photoDateStamp;
    }

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }

    public byte[] getPhotoImage() {
        return photoImage;
    }

    public void setPhotoImage(byte[] photoImage) {
        this.photoImage = photoImage;
    }

    public String getPhotoDateStamp() {
        return photoDateStamp;
    }

    public void setPhotoDateStamp(String photoDateStamp) {
        this.photoDateStamp = photoDateStamp;
    }
}
