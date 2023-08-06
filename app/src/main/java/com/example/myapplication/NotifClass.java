package com.example.myapplication;

public class NotifClass {

    private String dataMain;
    private String dataChild;
    private String dataImg;
    private String dataTanggal;
    private String dataHarga;
    private String dataLokasi;
    private long dataTimestamp;
    private String dataDeck;
    private String dataTanggalakhir;
    private String dataJml;

    public String getDataJml() {
        return dataJml;
    }

    public void setDataJml(String dataJml) {
        this.dataJml = dataJml;
    }

    public String getDataDeck() {
        return dataDeck;
    }

    public void setDataDeck(String dataDeck) {
        this.dataDeck = dataDeck;
    }

    public String getDataTanggalakhir() {
        return dataTanggalakhir;
    }

    public void setDataTanggalakhir(String dataTanggalakhir) {
        this.dataTanggalakhir = dataTanggalakhir;
    }

    public long getDataTimestamp() {
        return dataTimestamp;
    }

    public void setDataTimestamp(long dataTimestamp) {
        this.dataTimestamp = dataTimestamp;
    }

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

    public NotifClass(String dataMain, String dataChild, String dataImg, String dataTanggal, String dataHarga, String dataLokasi, long dataTimestamp, String dataDeck, String dataTanggalakhir, String dataJml) {
        this.dataMain = dataMain;
        this.dataChild = dataChild;
        this.dataImg = dataImg;
        this.dataTanggal = dataTanggal;
        this.dataHarga = dataHarga;
        this.dataLokasi = dataLokasi;
        this.dataTimestamp = dataTimestamp;
        this.dataDeck = dataDeck;
        this.dataTanggalakhir = dataTanggalakhir;
        this.dataJml = dataJml;

    }

    public NotifClass() {

    }
}
