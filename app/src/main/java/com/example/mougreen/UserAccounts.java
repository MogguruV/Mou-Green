package com.example.mougreen;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class UserAccounts extends AppCompatActivity {

    private ImageButton kembali;
    private RecyclerView recyclerView;
    private ArrayList<Users> usersArrayList;
    private AdapterUsersAccount adapterUsers;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_accounts);

        // Inisialisasi komponen UI
        kembali = findViewById(R.id.userAccountKembali);
        recyclerView = findViewById(R.id.rvUsers);


        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Inisialisasi Firestore dan adapter
        db = FirebaseFirestore.getInstance();
        usersArrayList = new ArrayList<>();
        adapterUsers = new AdapterUsersAccount(this, usersArrayList);
        recyclerView.setAdapter(adapterUsers);

        // Ambil data users dari Firestore
        AmbilUsers();

        // Tombol kembali ke halaman sebelumnya
        kembali.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), Admin.class);
            startActivity(intent);
            finish();
        });
    }

    private void AmbilUsers() {
        // Tampilkan ProgressBar
        db.collection("users").orderBy("namaUsers")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if (error != null) {
                            Log.e("Firestore Error", error.getMessage() != null ? error.getMessage() : "Unknown error");
                            return;
                        }

                        if (value != null) {
                            usersArrayList.clear(); // Bersihkan data lama
                            for (DocumentChange dc : value.getDocumentChanges()) {
                                if (dc.getType() == DocumentChange.Type.ADDED) {
                                    usersArrayList.add(dc.getDocument().toObject(Users.class));
                                }
                            }
                            adapterUsers.notifyDataSetChanged(); // Update RecyclerView
                        }
                    }
                });
    }
}
