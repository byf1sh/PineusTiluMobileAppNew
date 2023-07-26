package com.example.myapplication;

public class EntertainmentDataClass {

    private String dataTitle;
    private String dataAvail;
    private String dataImage;

    public String getDataTitle() {
        return dataTitle;
    }

    public String getDataAvail() {
        return dataAvail;
    }

    public String getDataImage() {
        return dataImage;
    }

    public EntertainmentDataClass(String dataTitle, String dataAvail, String dataImage) {
        this.dataTitle = dataTitle;
        this.dataAvail = dataAvail;
        this.dataImage = dataImage;
    }
    public EntertainmentDataClass(){

    }
}
