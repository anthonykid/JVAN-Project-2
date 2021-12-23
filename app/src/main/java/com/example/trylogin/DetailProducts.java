package com.example.trylogin;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

public class DetailProducts extends AppCompatActivity implements View.OnClickListener{

    private String idproduk;
    private String imgurl;
    private String keterangan;
    private String namaproduk;
    private String hargaproduk;
    ImageView PictureDetail;
    TextView btnBuy, namaDetail, ketDetail, hargaDetail, imgDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_products);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Intent intent = getIntent();
        idproduk = intent.getStringExtra("id");
        namaproduk = intent.getStringExtra("nama");
        keterangan = intent.getStringExtra("keterangan");
        hargaproduk = intent.getStringExtra("harga");
        imgurl = intent.getStringExtra("imgurl");

        namaDetail = findViewById(R.id.namaDetail);
        ketDetail = findViewById(R.id.ketDetail);
        hargaDetail = findViewById(R.id.hargaDetail);
        PictureDetail = findViewById(R.id.pictureDetail);
        btnBuy = findViewById(R.id.btnBuy);

        namaDetail.setText(namaproduk);
        ketDetail.setText(keterangan);
        hargaDetail.setText(hargaproduk);

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.skipMemoryCache(true);
        requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE);
        requestOptions.placeholder(R.drawable.ic_baseline_add_24);
        requestOptions.error(R.drawable.ic_baseline_add_24);

        Glide.with(DetailProducts.this)
                .load(imgurl)
                .apply(requestOptions)
                //.override(width,height)
                //.centerCrop()
                .into(PictureDetail);

        setToButton(btnBuy);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnBuy:
                Toast.makeText(this, "Coming Soon!", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public static void buttonEffect(View button) {
        button.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.getBackground().setColorFilter(0xe0f47521, PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        v.getBackground().clearColorFilter();
                        v.invalidate();
                        break;
                    }
                }
                return false;
            }
        });
    }

    public void setToButton(View view){
        buttonEffect(view);
        view.setOnClickListener(this);
    }
}