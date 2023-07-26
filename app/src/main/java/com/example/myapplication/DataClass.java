package com.example.myapplication;

public class DataClass {

    private String dataTitle;
    private String dataAvail;
    private String dataImage;
    private String dataName;
    private String dataJml;



    public void setDataTitle(String dataTitle) {
        this.dataTitle = dataTitle;
    }

    public void setDataAvail(String dataAvail) {
        this.dataAvail = dataAvail;
    }

    public void setDataImage(String dataImage) {
        this.dataImage = dataImage;
    }

    public String getDataTitle() {
        return dataTitle;
    }

    public String getDataAvail() {
        return dataAvail;
    }

    public String getDataImage() {
        return dataImage;
    }

    public String getDataJml() {
        return dataJml;
    }

    public String getDataName() {
        return dataName;
    }
    public DataClass(String dataTitle, String dataAvail, String dataImage,String dataName,String dataJml) {
        this.dataTitle = dataTitle;
        this.dataAvail = dataAvail;
        this.dataImage = dataImage;
        this.dataJml = dataJml;
        this.dataName = dataName;
    }
    public DataClass(){

    }
}
