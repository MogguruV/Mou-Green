package com.example.mougreen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Penjadwalan extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    ImageButton tombolKembali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_penjadwalan);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        bottomNavigationView = findViewById(R.id.menuBawahJadwal);
        bottomNavigationView.setSelectedItemId(R.id.bottom_penjadwalan);
        tombolKembali = findViewById(R.id.jadwalKembali);

        tombolKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainMenu.class);
                startActivity(i);
                finish();
            }
        });

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.bottom_home) {
                startActivity(new Intent(getApplicationContext(), MainMenu.class));
                finish();
                return true;
            } else if (itemId == R.id.bottom_penjadwalan) {
                // Aksi untuk menu Jadwal
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