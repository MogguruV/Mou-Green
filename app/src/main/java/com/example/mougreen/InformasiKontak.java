package com.example.mougreen;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class InformasiKontak extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_informasi_kontak);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        bottomNavigationView = findViewById(R.id.menuBawahKontak);
        bottomNavigationView.setSelectedItemId(R.id.bottom_kontak);

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
                startActivity(new Intent(getApplicationContext(), FiturKritik.class));
                finish();
                return true;
            } else if (itemId == R.id.bottom_kontak) {
                // Aksi untuk menu Kontak
                return true;
            }
            return false;
        });
    }
}