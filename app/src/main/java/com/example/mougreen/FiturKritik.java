package com.example.mougreen;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class FiturKritik extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

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