package com.example.trylogin.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.trylogin.API.ApiClient;
import com.example.trylogin.API.ApiInterface;
import com.example.trylogin.Model.Produk.DataProduk;
import com.example.trylogin.Model.Produk.ResponProduk;
import com.example.trylogin.StaffMenu;
import com.example.trylogin.R;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterProduk extends RecyclerView.Adapter<AdapterProduk.HolderData>{
    private Context ctx;
    private List <DataProduk> list_produk;
    private int idproduk;
    public AdapterProduk(Context ctx, List<DataProduk> list_produk) {
        this.ctx = ctx;
        this.list_produk = list_produk;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_produk, parent, false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        DataProduk dm = list_produk.get(position);

        int harga = dm.getHarga();
        Locale localeID = new Locale( "in" , "ID" );
        NumberFormat formatRupiah = NumberFormat. getCurrencyInstance (localeID);
        holder.harga.setText(formatRupiah.format((double) harga));

        holder.id_produk.setText(String.valueOf(dm.getId_produk()));
        holder.nama_produk.setText(dm.getNama_produk());
        holder.kategori.setText(dm.getKategori());
        holder.ket.setText(dm.getKet());

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.skipMemoryCache(true);
        requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE);
        requestOptions.placeholder(R.drawable.ic_baseline_add_24);
        requestOptions.error(R.drawable.ic_baseline_add_24);

        Glide.with(ctx)
                .load(list_produk.get(position).getPicture())
                .apply(requestOptions)
                .into(holder.mPicture);

    }

    @Override
    public int getItemCount() {
        return list_produk.size();
    }

    public class HolderData extends RecyclerView.ViewHolder{
        TextView id_produk,nama_produk,harga,kategori,ket;
        private CircleImageView mPicture;

        public HolderData(@NonNull View itemView) {
            super(itemView);
            id_produk = itemView.findViewById(R.id.id_pd);
            nama_produk = itemView.findViewById(R.id.nama_pd);
            harga = itemView.findViewById(R.id.hrg);
            kategori = itemView.findViewById(R.id.kat);
            ket = itemView.findViewById(R.id.kett);
            mPicture = itemView.findViewById(R.id.picture);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    AlertDialog.Builder msgg = new AlertDialog.Builder(ctx);
                    msgg.setMessage("PILIH OPSI");
                    msgg.setCancelable(true);

                    idproduk = Integer.parseInt(id_produk.getText().toString());

                    msgg.setPositiveButton("HAPUS", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            delproduk();
                            dialogInterface.dismiss();
                            ((StaffMenu) ctx).tp_data();
                        }
                    });
                    msgg.setNegativeButton("UPDATE", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });

                    msgg.show();
                    return false;
                }
            });
        }

        private void delproduk(){
            ApiInterface arddata = ApiClient.getClient().create(ApiInterface.class);
            Call<ResponProduk> deldt = arddata.hpsproduk(idproduk);


            deldt.enqueue(new Callback<ResponProduk>() {
                @Override
                public void onResponse(Call<ResponProduk> call, Response<ResponProduk> response) {
                    int id_produk = response.body().getId_produk();
                    String msg = response.body().getMsg();

                    Toast.makeText(ctx, ""+msg, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<ResponProduk> call, Throwable t) {
                    Toast.makeText(ctx, "GAGAL MENGHUBUNGI SERVER"+t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
