package com.example.trylogin;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.trylogin.API.ApiClient;
import com.example.trylogin.API.ApiInterface;
import com.example.trylogin.Adapter.AdapterProduk;
import com.example.trylogin.Model.Produk.DataProduk;
import com.example.trylogin.Model.Produk.ResponProduk;
import com.example.trylogin.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StaffMenu extends AppCompatActivity {


    private FloatingActionButton but_add;



    private RecyclerView tampildata;
    private RecyclerView.Adapter adp;
    private RecyclerView.LayoutManager lmdata;
    private List<DataProduk> listp = new ArrayList<>();
    private SwipeRefreshLayout rfdata;
    private ProgressBar pbdata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_menu);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        but_add = findViewById(R.id.add_produk);
        rfdata = findViewById(R.id.swprf);
        pbdata = findViewById(R.id.pbd);
        tampildata = findViewById(R.id.data_produk);
        lmdata = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        tampildata.setLayoutManager(lmdata);
        but_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StaffMenu.this, tambah_produk.class));
            }
        });



        rfdata.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                rfdata.setRefreshing(true);
                tp_data();
                rfdata.setRefreshing(false);
            }
        });


        //tp_data();

    }

    @Override
    protected void onResume() {
        super.onResume();
        tp_data();
    }



    public void tp_data(){
        pbdata.setVisibility(View.VISIBLE);

        ApiInterface arddata = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponProduk> tdata = arddata.tampilp();

        tdata.enqueue(new Callback<ResponProduk>() {
            @Override
            public void onResponse(Call<ResponProduk> call, Response<ResponProduk> response) {
                int id_produk = response.body().getId_produk();
                String msg = response.body().getMsg();

                listp = response.body().getData_produk();
                adp = new AdapterProduk(StaffMenu.this, listp);
                tampildata.setAdapter(adp);

                pbdata.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<ResponProduk> call, Throwable t) {
                Toast.makeText(StaffMenu.this, "GAGAL KONEK"+t.getMessage(), Toast.LENGTH_SHORT).show();

                pbdata.setVisibility(View.INVISIBLE);
            }
        });
    }
}