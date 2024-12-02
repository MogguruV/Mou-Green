package com.example.mougreen;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class VideoPlayer extends AppCompatActivity {

    private VideoView videoPlayerMedia;
    private TextView judul;
    private MediaController mediaController;
    private Uri url;
    private ImageButton tombolKembali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_video_player);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inisialisasi komponen
        mediaController = new MediaController(this);

        tombolKembali = findViewById(R.id.tombolKembaliVideo);
        videoPlayerMedia = findViewById(R.id.videoPlayerContent);
        judul = findViewById(R.id.judulVideo);

        // Atur MediaController
        videoPlayerMedia.setMediaController(mediaController);
        mediaController.setAnchorView(videoPlayerMedia);

        // Set URL video
        url = Uri.parse("https://firebasestorage.googleapis.com/v0/b/mou-green.firebasestorage.app/o/Video%20Animasi%20Cara%20Pengelolaan%20Sampah%20Sampahku%20Tanggung%20Jawabku_720pHF.mp4?alt=media&token=bff4e059-37f8-4136-94e9-da5f875e9219");
        videoPlayerMedia.setVideoURI(url);
        videoPlayerMedia.start();

        // Set judul video
        judul.setText("Cara Pengelolaan Sampah");

        // Tombol kembali ke menu utama
        tombolKembali.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), EdukasiMenu.class);
            startActivity(intent);
            finish();
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Pause video ketika aktivitas dijeda
        if (videoPlayerMedia.isPlaying()) {
            videoPlayerMedia.pause();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        // Hentikan video ketika aktivitas tidak terlihat
        videoPlayerMedia.stopPlayback();
    }
}
