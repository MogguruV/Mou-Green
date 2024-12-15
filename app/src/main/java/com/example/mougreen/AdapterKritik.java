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

import java.util.ArrayList;

public class AdapterKritik extends RecyclerView.Adapter<AdapterKritik.MyViewHolder> {

    Context context;
    ArrayList<Kritik> kritikArrayList;

    public AdapterKritik(Context context, ArrayList<Kritik> kritikArrayList) {
        this.context = context;
        this.kritikArrayList = kritikArrayList;
    }

    @NonNull
    @Override
    public AdapterKritik.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.item, parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterKritik.MyViewHolder holder, int position) {

        Kritik kritik = kritikArrayList.get(position);

        holder.nama.setText(kritik.getNamaUser());
        holder.kritik.setText(kritik.getKritikUser());
        //holder.urlGambar.setText(kritik.urlGambar);

        // Debug untuk memeriksa data
        Log.d("AdapterKritik", "Nama: " + kritik.getNamaUser() + ", Kritik: " + kritik.getKritikUser());

    }

    @Override
    public int getItemCount() {
        return kritikArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView nama, kritik, urlGambar;



        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            nama = itemView.findViewById(R.id.textUsername);
            kritik = itemView.findViewById(R.id.textComment);
            //urlGambar = itemView.findViewById(R.id.imageProfile);
        }
    }
}
