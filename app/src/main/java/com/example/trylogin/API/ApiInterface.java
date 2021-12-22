package com.example.trylogin.API;

import com.example.trylogin.Model.Login.Login;
import com.example.trylogin.Model.Produk.ResponProduk;
import com.example.trylogin.Model.Register.Register;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("login.php")
    Call<Login> loginResponse(
            @Field("username") String username,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("register.php")
    Call<Register> registerResponse(
            @Field("username") String username,
            @Field("name") String name,
            @Field("role") int role,
            @Field("password") String password
    );

    @GET("tampil_produk.php")
    Call<ResponProduk> tampilp();



    @GET("tampilel.php")
    Call<ResponProduk> katel();
    @GET("tampilfs.php")
    Call<ResponProduk> katfs();
    @GET("tampilbk.php")
    Call<ResponProduk> katb();
    @GET("tampilsp.php")
    Call<ResponProduk> katsp();
    @GET("tampiltv.php")
    Call<ResponProduk> kattv();
    @GET("tampilln.php")
    Call<ResponProduk> katln();


    @FormUrlEncoded
    @POST("tambah_produk.php")
    Call<ResponProduk> tmbproduk(
            @Field("nama_produk") String nama_produk,
            @Field("harga") String harga,
            @Field("kategori") String kategori,
            @Field("ket") String keterangan,
            @Field("picture") String picture);


    @FormUrlEncoded
    @POST("hapusproduk.php")
    Call<ResponProduk> hpsproduk(
            @Field("id_produk") int id_produk
    );


    @FormUrlEncoded
    @POST("tpkat.php")
    Call<ResponProduk> inproduk(
            @Field("key") String key,
            @Field("nama_produk") String name,
            @Field("harga") String species,
            @Field("kategori") String breed,
            @Field("ket") String birth,
            @Field("picture") String picture);
}
