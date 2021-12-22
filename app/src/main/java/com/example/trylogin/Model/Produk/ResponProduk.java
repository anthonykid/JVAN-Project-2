package com.example.trylogin.Model.Produk;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponProduk {
    private int id_produk;
    private String msg;
    private List<DataProduk> data_produk;



    @SerializedName("picture")
    private String picture;
    @SerializedName("value")
    private String value;
    @SerializedName("message")
    private String massage;


    public int getId_produk() {
        return id_produk;
    }

    public void setId_produk(int id_produk) {
        this.id_produk = id_produk;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataProduk> getData_produk() {
        return data_produk;
    }

    public void setData_produk(List<DataProduk> data_produk) {
        this.data_produk = data_produk;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }
}
