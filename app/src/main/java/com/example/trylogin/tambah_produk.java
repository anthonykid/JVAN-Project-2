package com.example.trylogin;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.trylogin.API.ApiClient;
import com.example.trylogin.API.ApiInterface;
import com.example.trylogin.Model.Produk.ResponProduk;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class tambah_produk extends AppCompatActivity {
    Spinner ketspiner;

    private String nama,kate,ket,harga,picture;
    private EditText nmpd,hrg,keterangan;
    private CircleImageView mPicture;
    private Button bttsp;
    private FloatingActionButton mFabChoosePic;

    private Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_produk);

        nmpd = findViewById(R.id.nama_produk);
        hrg = findViewById(R.id.harga);
        keterangan = findViewById(R.id.ket);
        bttsp = findViewById(R.id.btsimpan);
        mPicture = findViewById(R.id.picture);
        mFabChoosePic = findViewById(R.id.fabChoosePic);


        mFabChoosePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseFile();
            }
        });



        bttsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                nama = nmpd.getText().toString();
                harga = hrg.getText().toString();
                kate = ketspiner.getSelectedItem().toString();
                ket = keterangan.getText().toString();
                picture = getStringImage(bitmap);
                RequestOptions requestOptions = new RequestOptions();
                Glide.with(tambah_produk.this)
                        .load(picture)
                        .apply(requestOptions)
                        .into(mPicture);

                String picture = null;
                if (bitmap == null) {
                    picture = "";
                } else {
                    picture = getStringImage(bitmap);
                }

                if(nama.trim().equals("")){
                    nmpd.setError("ISI NAMA PRODUK DULU");
                }
                else if(ket.trim().equals("")){
                    keterangan.setError("ISI KETERANGAN");
                }
                else {
                    tmbhproduk();
                    setupSpinner();
                }


            }
        });

        setupSpinner();

    }

    //gambar
    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    private void chooseFile() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {

                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);

                mPicture.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    //endgambar

    public void setupSpinner() {
        ketspiner = findViewById(R.id.spkat);
            String[] items = new String[]{"Elektronik", "Fashion", "Books", "Sport","Traveling","Lainlain"};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        //set the spinners adapter to the previously created one.
        ketspiner.setAdapter(adapter);
    }

    private void tmbhproduk(){
        ApiInterface arddata = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponProduk> smpdata = arddata.tmbproduk(nama,harga,kate,ket,picture);


        String picture = null;
        if (bitmap == null) {
            picture = "";
        } else {
            picture = getStringImage(bitmap);
        }


        smpdata.enqueue(new Callback<ResponProduk>() {
            @Override
            public void onResponse(Call<ResponProduk> call, Response<ResponProduk> response) {
                int id_produk = response.body().getId_produk();
                String msg = response.body().getMsg();

                Toast.makeText(tambah_produk.this, "Alert : "+msg, Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ResponProduk> call, Throwable t) {
                Toast.makeText(tambah_produk.this, "GAGAL MENGHUBUNGI SERVER"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }




}