package com.example.mougreen;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class FiturKritik extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private ImageButton tombolKirimKritik;
    private EditText inputNamaUser, inputEmailUser, inputNoTelpUser, inputKritikUser;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_fitur_kritik);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        bottomNavigationView = findViewById(R.id.menuBawahKritik);
        bottomNavigationView.setSelectedItemId(R.id.bottom_kritik);
        inputNamaUser = findViewById(R.id.inputTextNamaKritik);
        inputEmailUser = findViewById(R.id.inputTextEmailKritik);
        inputNoTelpUser = findViewById(R.id.inputNomorTeleponKritik);
        inputKritikUser = findViewById(R.id.inputTextKomentarKritik);
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        tombolKirimKritik = findViewById(R.id.tombolKirimKritik);

        tombolKirimKritik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nama, email, noTelp, kritik;
                nama = String.valueOf(inputNamaUser.getText());
                email = String.valueOf(inputEmailUser.getText());
                noTelp = String.valueOf(inputNoTelpUser.getText());
                kritik = String.valueOf(inputKritikUser.getText());

                if (TextUtils.isEmpty(nama)){
                    Toast.makeText(getApplicationContext(), "Enter Nama Anda", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(email)){
                    Toast.makeText(getApplicationContext(), "Enter Email Anda", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(noTelp)){
                    Toast.makeText(getApplicationContext(), "Enter Nomor Telepon Anda", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(kritik)){
                    Toast.makeText(getApplicationContext(), "Enter Pesan Kritik Anda", Toast.LENGTH_SHORT).show();
                    return;
                }

                Map<String, Object> pesanKritik = new HashMap<>();
                pesanKritik.put("Nama" , nama);
                pesanKritik.put("Email" , email);
                pesanKritik.put("No Telepon", noTelp);
                pesanKritik.put("Kritik", kritik);

                db.collection("kritik")
                        .add(pesanKritik)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                    Toast.makeText(getApplicationContext(), "Kritik Telah Dikirim",
                                            Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), MainMenu.class);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(), "Data tidak bisa disimpan",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });

            }
        });



        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.bottom_home) {
                startActivity(new Intent(getApplicationContext(), MainMenu.class));
                finish();
                return true;
            } else if (itemId == R.id.bottom_penjadwalan) {
                startActivity(new Intent(getApplicationContext(), Penjadwalan.class));
                finish();
                return true;
            } else if (itemId == R.id.bottom_kritik) {
                // Aksi untuk menu Kritik
                return true;
            } else if (itemId == R.id.bottom_kontak) {
                startActivity(new Intent(getApplicationContext(), InformasiKontak.class));
                finish();
                return true;
            }
            return false;
        });


        
    }
}