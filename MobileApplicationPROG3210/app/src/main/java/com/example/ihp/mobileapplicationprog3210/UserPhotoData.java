package com.example.ihp.mobileapplicationprog3210;

/**
 * This Java file is related to 'PhotosActivity'
 * The purpose of this class is to store user photo information
 *      to display to user of the photos added by the user.
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
