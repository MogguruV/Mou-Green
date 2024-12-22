package com.example.mougreen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainMenu extends AppCompatActivity {

    private ImageButton tombolEdukasi, tombolProfil, tombolSampah, tombolLaporan, tombolVideo1;
    private FirebaseAuth mAuth;
    private TextView sambutan;
    private FirebaseUser user;
    private FirebaseFirestore db;
    private String userUUID;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_menu);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tombolEdukasi = findViewById(R.id.tombolEdukasi);
        tombolProfil = findViewById(R.id.fotoProfil);
        mAuth = FirebaseAuth.getInstance();
        sambutan = findViewById(R.id.kataSambutan);
        user = mAuth.getCurrentUser();
        db = FirebaseFirestore.getInstance();
        userUUID = mAuth.getUid();
        bottomNavigationView = findViewById(R.id.menuBawah);
        bottomNavigationView.setSelectedItemId(R.id.bottom_home);
        tombolSampah = findViewById(R.id.tombolTentangSampah);
        tombolLaporan = findViewById(R.id.tombolLaporan);
        tombolVideo1 = findViewById(R.id.toVideo1);

        if (user == null){
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();
        }
        else{
            db.collection("users").document(userUUID)
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                DocumentSnapshot document = task.getResult();
                                    sambutan.setText(String.format("Selamat Datang %s", document.getString("usernameUsers")));

                            } else {
                                sambutan.setText(String.format("Selamat Datang %s", user.getEmail()));
                            }
                        }
                    });
        }

        tombolEdukasi.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent( getApplicationContext(), EdukasiMenu.class);
                startActivity(i);
            finish();
            }
        });

        tombolProfil.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent( getApplicationContext(), ProfilUser.class);
                startActivity(i);
                finish();
            }
        });

        tombolSampah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), TentangSampah.class);
                startActivity(i);
                finish();
            }
        });

        tombolLaporan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), FiturLaporan.class);
                startActivity(i);
                finish();
            }
        });

        tombolVideo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), VideoPlayer.class);
                startActivity(i);
                finish();
            }
        });



        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.bottom_home) {
                // Aksi untuk menu Home
                return true;
            } else if (itemId == R.id.bottom_penjadwalan) {
                startActivity(new Intent(getApplicationContext(), Penjadwalan.class));
                finish();
                return true;
            } else if (itemId == R.id.bottom_kritik) {
                startActivity(new Intent(getApplicationContext(), FiturKritik.class));
                finish();
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