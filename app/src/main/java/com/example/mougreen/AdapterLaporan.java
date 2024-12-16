package com.example.mougreen;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class AdapterLaporan extends RecyclerView.Adapter<AdapterLaporan.MyViewHolder> {

    Context context;
    ArrayList<Laporan> laporanArrayList;

    public AdapterLaporan(Context context, ArrayList<Laporan> laporanArrayList) {
        this.context = context;
        this.laporanArrayList = laporanArrayList;
    }

    @NonNull
    @Override
    public AdapterLaporan.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.item_laporan, parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterLaporan.MyViewHolder holder, int position) {

        Laporan laporan = laporanArrayList.get(position);

        // Set nama dan kritik
        holder.nama.setText(laporan.getNama());
        holder.kritik.setText(laporan.getLaporan());

        // Gunakan Glide untuk memuat gambar dari URL
        Glide.with(context)
                .load(laporan.getImageUrl()) // URL gambar dari Firebase Storage
                .placeholder(R.drawable.admin_foto_profil) // Gambar sementara (opsional)
                .error(R.drawable.profil_foto_profil) // Gambar error jika URL gagal dimuat (opsional)
                .into(holder.urlGambarLaporan);

        // Debug untuk memeriksa data
        Log.d("AdapterLaporan", "Nama: " + laporan.getNama() + ", Laporan: " + laporan.getLaporan());
    }


    @Override
    public int getItemCount() {
        return laporanArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView nama, kritik;
        ImageView urlGambarLaporan;


        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            nama = itemView.findViewById(R.id.textUsernameLaporan);
            kritik = itemView.findViewById(R.id.textCommentLaporan);
            urlGambarLaporan = itemView.findViewById(R.id.imageContentLaporan);
        }
    }
}
