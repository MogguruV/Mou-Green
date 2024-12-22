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

public class AdapterUsersAccount extends RecyclerView.Adapter<AdapterUsersAccount.MyViewHolder> {

    Context context;
    ArrayList<Users> usersArrayList;

    public AdapterUsersAccount(Context context, ArrayList<Users> usersArrayList) {
        this.context = context;
        this.usersArrayList = usersArrayList;
    }

    @NonNull
    @Override
    public AdapterUsersAccount.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.item_users, parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterUsersAccount.MyViewHolder holder, int position) {

        Users users = usersArrayList.get(position);

        // Set nama dan kritik
        holder.nama.setText(users.getNamaUsers());
        holder.email.setText(users.getEmailUsers());
        holder.no_rumah.setText(users.getNomorRumahUsers());
        holder.username.setText(users.getUsernameUsers());


        // Debug untuk memeriksa data
        Log.d("AdapterLaporan", "Nama: " + users.getNamaUsers() + ", Email: " + users.getEmailUsers()+ ", No Rumah: " + users.getNomorRumahUsers()
        + ", Username: " + users.getUsernameUsers());
    }

    @Override
    public int getItemCount() {
        return usersArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView nama, email, no_rumah, username;
        //ImageView urlGambarLaporan;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            nama = itemView.findViewById(R.id.textUserName);
            email = itemView.findViewById(R.id.textEmail);
            no_rumah = itemView.findViewById(R.id.textHouseNumber);
            username = itemView.findViewById(R.id.textUsernameHandle);
            //urlGambarLaporan = itemView.findViewById(R.id.imageContentLaporan);
        }
    }
}
