
package com.example.trylogin;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.trylogin.API.ApiClient;
import com.example.trylogin.API.ApiInterface;
import com.example.trylogin.Adapter.AdapterProduk2;
import com.example.trylogin.Model.Produk.DataProduk;
import com.example.trylogin.Model.Produk.ResponProduk;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class kattv extends AppCompatActivity {





    private RecyclerView tampildata;
    private RecyclerView.Adapter adp;
    private RecyclerView.LayoutManager lmdata;
    private List<DataProduk> listp = new ArrayList<>();
    private SwipeRefreshLayout rfdata;
    private ProgressBar pbdata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perkat);

        rfdata = findViewById(R.id.swprf);
        pbdata = findViewById(R.id.pbd);
        tampildata = findViewById(R.id.data_produk);
        lmdata = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        tampildata.setLayoutManager(lmdata);




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
        Call<ResponProduk> tdata = arddata.kattv();

        tdata.enqueue(new Callback<ResponProduk>() {
            @Override
            public void onResponse(Call<ResponProduk> call, Response<ResponProduk> response) {
                int id_produk = response.body().getId_produk();
                String msg = response.body().getMsg();

                listp = response.body().getData_produk();
                adp = new AdapterProduk2(kattv.this, listp);
                tampildata.setAdapter(adp);

                pbdata.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<ResponProduk> call, Throwable t) {
                Toast.makeText(kattv.this, "GAGAL KONEK"+t.getMessage(), Toast.LENGTH_SHORT).show();

                pbdata.setVisibility(View.INVISIBLE);
            }
        });
    }
}