package com.example.myapplication;

public class NotifClass {

    private String dataMain;
    private String dataChild;
    private String dataImg;
    private String dataTanggal;
    private String dataHarga;
    private String dataLokasi;




    public String getDataMain() {
        return dataMain;
    }

    public String getDataChild() {
        return dataChild;
    }

    public String getDataImg() {
        return dataImg;
    }

    public String getDataTanggal() {
        return dataTanggal;
    }

    public String getDataHarga() {
        return dataHarga;
    }

    public String getDataLokasi() {
        return dataLokasi;
    }

    public NotifClass(String dataMain, String dataChild, String dataImg, String dataTanggal, String dataHarga, String dataLokasi) {
        this.dataMain = dataMain;
        this.dataChild = dataChild;
        this.dataImg = dataImg;
        this.dataTanggal = dataTanggal;
        this.dataHarga = dataHarga;
        this.dataLokasi = dataLokasi;

    }
    public NotifClass(){

    }
}
