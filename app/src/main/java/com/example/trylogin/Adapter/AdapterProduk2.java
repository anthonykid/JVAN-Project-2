package com.example.trylogin.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
import com.example.trylogin.DetailProducts;
import com.example.trylogin.MainActivity;
import com.example.trylogin.MainMenu;
import com.example.trylogin.MenuAdmin;
import com.example.trylogin.Model.Produk.DataProduk;
import com.example.trylogin.Model.Produk.ResponProduk;
import com.example.trylogin.R;
import com.example.trylogin.StaffMenu;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterProduk2 extends RecyclerView.Adapter<AdapterProduk2.HolderData>{
    private Context ctx;
    private List <DataProduk> list_produk;
    private int idproduk;
    String idLempar;
    private String imgurl;
    private String keterangan;
    private String namaproduk;
    private String hargaproduk;
    private String stkkk;
    public AdapterProduk2(Context ctx, List<DataProduk> list_produk) {
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
        holder.kategori.setText(dm.getKet());
        holder.ket.setText(dm.getPicture());
        holder.stkk.setText(String.valueOf(dm.getStok()));

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
        TextView id_produk,nama_produk,harga,kategori,ket,stkk;
        private CircleImageView mPicture;

        public HolderData(@NonNull View itemView) {
            super(itemView);
            id_produk = itemView.findViewById(R.id.id_pd);
            nama_produk = itemView.findViewById(R.id.nama_pd);
            harga = itemView.findViewById(R.id.hrg);
            kategori = itemView.findViewById(R.id.kat);
            ket = itemView.findViewById(R.id.kett);
            stkk = itemView.findViewById(R.id.stock_pd);
            mPicture = itemView.findViewById(R.id.picture);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    idproduk = Integer.valueOf(id_produk.getText().toString());
                    idLempar = String.valueOf(idproduk);
                    namaproduk = nama_produk.getText().toString();
                    hargaproduk = harga.getText().toString();
                    keterangan = kategori.getText().toString();
                    stkkk = stkk.getText().toString();
                    imgurl = ket.getText().toString();

                    Intent intent = new Intent(ctx, DetailProducts.class);
                    intent.putExtra("id",idLempar);
                    intent.putExtra("nama",namaproduk);
                    intent.putExtra("harga",hargaproduk);
                    intent.putExtra("keterangan",keterangan);
                    intent.putExtra("stock",stkkk);
                    intent.putExtra("imgurl",imgurl);
                    ctx.startActivity(intent);
                }
            });
        }
    }
}
