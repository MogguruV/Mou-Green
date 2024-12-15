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
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Cek_Laporan extends AppCompatActivity {


    ImageButton kembali;
    RecyclerView recyclerView;
    ArrayList<Kritik> kritikArrayList;
    AdapterKritik adapterKritik;
    FirebaseFirestore db;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cek_laporan);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Fetching...");
        progressDialog.show();

        kembali = findViewById(R.id.kritikKembali);

        recyclerView = findViewById(R.id.rvKritik);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = FirebaseFirestore.getInstance();
        kritikArrayList = new ArrayList<Kritik>();
        adapterKritik = new AdapterKritik(getApplicationContext(),kritikArrayList);

        recyclerView.setAdapter(adapterKritik);

        AmbilKritik();

        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Admin.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void AmbilKritik() {
        db.collection("kritik").orderBy("namaUser")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if (error != null) {
                            if (progressDialog.isShowing())
                                progressDialog.dismiss();
                            Log.e("Firestore Error", error.getMessage());
                            return;
                        }

                        kritikArrayList.clear(); // Bersihkan data lama sebelum menambahkan yang baru

                        for (DocumentChange dc : value.getDocumentChanges()) {
                            if (dc.getType() == DocumentChange.Type.ADDED) {
                                kritikArrayList.add(dc.getDocument().toObject(Kritik.class));
                            }
                        }

                        adapterKritik.notifyDataSetChanged(); // Beritahu adapter bahwa data berubah
                        if (progressDialog.isShowing())
                            progressDialog.dismiss();
                    }
                });
    }

}