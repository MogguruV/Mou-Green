package com.example.mougreen;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class UserAccounts extends AppCompatActivity {

    ImageButton kembali;
    RecyclerView recyclerView;
    ArrayList<Users> usersArrayList;
    AdapterUsersAccount adapterUsers;
    FirebaseFirestore db;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_accounts);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Menampilkan Progress Dialog saat mengambil data
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Sedang memuat users...");
        progressDialog.show();

        // Inisialisasi komponen UI
        kembali = findViewById(R.id.userAccountKembali);
        recyclerView = findViewById(R.id.rvUsers);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = FirebaseFirestore.getInstance();
        usersArrayList = new ArrayList<>();
        adapterUsers = new AdapterUsersAccount(this, usersArrayList); // Context lebih aman

        recyclerView.setAdapter(adapterUsers);

        // Ambil data users dari Firestore
        AmbilUsers();

        // Tombol kembali ke halaman sebelumnya
        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Admin.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void AmbilUsers() {
        // Ambil data laporan dari koleksi Firestore dan urutkan berdasarkan 'nama'
        db.collection("users").orderBy("nama")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if (error != null) {
                            if (progressDialog.isShowing())
                                progressDialog.dismiss();
                            Log.e("Firestore Error", error.getMessage());
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

                        // Hentikan progress dialog jika data selesai dimuat
                        if (progressDialog.isShowing())
                            progressDialog.dismiss();
                    }
                });
    }
}