package com.example.mougreen;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterUsersAccount extends RecyclerView.Adapter<AdapterUsersAccount.MyViewHolder> {

    private final Context context;
    private final ArrayList<Users> usersArrayList;

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

        // Set nama, email, nomor rumah, dan username
        holder.nama.setText(users.getNamaUsers());
        holder.email.setText("Email : " + users.getEmailUsers());
        holder.noRumah.setText("No Rumah : " + users.getNomorRumahUsers());
        holder.username.setText("@"+users.getUsernameUsers());

        // Debugging log
        Log.d("AdapterUsersAccount", String.format(
                "Nama: %s, Email: %s, No Rumah: %s, Username: %s",
                users.getNamaUsers(), users.getEmailUsers(), users.getNomorRumahUsers(), users.getUsernameUsers()));
    }

    @Override
    public int getItemCount() {
        return usersArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nama, email, noRumah, username;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nama = itemView.findViewById(R.id.textUserName);
            email = itemView.findViewById(R.id.textEmail);
            noRumah = itemView.findViewById(R.id.textHouseNumber);
            username = itemView.findViewById(R.id.textUsernameHandle);
        }
    }
}
