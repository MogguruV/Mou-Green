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

public class TentangSampah extends AppCompatActivity {

    ImageButton tombolKembali, tombolOrganik, tombolAnorganik, tombolB3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tentang_sampah);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tombolKembali = findViewById(R.id.tombolKembaliSampah);
        tombolOrganik = findViewById(R.id.tombolSampahOrganik);
        tombolAnorganik = findViewById(R.id.tombolSampahAnorganik);
        tombolB3 = findViewById(R.id.tombolSampahB3);

        tombolKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainMenu.class);
                startActivity(intent);
                finish();
            }
        });

        tombolOrganik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TentangSampahOrganik.class);
                startActivity(intent);
                finish();
            }
        });

        tombolAnorganik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TentangSampahAnorganik.class);
                startActivity(intent);
                finish();
            }
        });

        tombolB3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TentangSampahB3.class);
                startActivity(intent);
                finish();
            }
        });


    }
}