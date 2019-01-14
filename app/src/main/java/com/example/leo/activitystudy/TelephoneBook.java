package com.example.leo.activitystudy;

public class TelephoneBook {
    private String Name;
    private int ImageId;
    private String TelphoneNum;

    public TelephoneBook(String Name,int ImageId,String TelNum)
    {
        this.Name = Name;
        this.ImageId = ImageId;
        this.TelphoneNum = TelNum;
    }

    public String getName() {
        return Name;
    }

    public void setTelphoneNum(String telphoneNum) {
        TelphoneNum = telphoneNum;
    }

    public String getTelphoneNum() {
        return TelphoneNum;
    }

    public void setImageId(int imageId) {
        ImageId = imageId;
    }

    public int getImageId() {
        return ImageId;
    }
}
