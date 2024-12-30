package com.example.mougreen;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class FiturLaporan extends AppCompatActivity {

    private ImageButton tombolKembali, tombolKirim, tombolUploadImage;
    private Uri imageUri; // URI gambar yang dipilih
    private FirebaseFirestore db;
    private StorageReference storageRef;
    private EditText inputTextLaporan, inputTextNamaLaporan;
    private ProgressDialog progressDialog; // Progress dialog untuk loading

    private final ActivityResultLauncher<Intent> activityResultLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        // Ambil URI gambar dari galeri
                        imageUri = result.getData().getData();
                        tombolKirim.setEnabled(true);
                        showMessage("Gambar berhasil dipilih.");
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_fitur_laporan);

        // Mengatur window insets untuk kompatibilitas tampilan layar penuh
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inisialisasi view
        tombolKembali = findViewById(R.id.tombolKembaliLaporan);
        tombolKirim = findViewById(R.id.tombolKirimLaporan);
        tombolUploadImage = findViewById(R.id.tombolKirimFileLaporan);
        inputTextLaporan = findViewById(R.id.inputTextKomentarLaporan);
        inputTextNamaLaporan = findViewById(R.id.inputTextNamaLaporan);

        // Inisialisasi Firebase
        db = FirebaseFirestore.getInstance();
        storageRef = FirebaseStorage.getInstance().getReference();

        // Progress Dialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Mengirim laporan...");
        progressDialog.setCancelable(false);

        // Tombol kembali ke menu utama
        tombolKembali.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), MainMenu.class));
            finish();
        });

        // Tombol untuk memilih gambar dari galeri
        tombolUploadImage.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            activityResultLauncher.launch(intent);
        });

        // Tombol untuk mengirim laporan
        tombolKirim.setOnClickListener(v -> {
            String namaLaporan = inputTextNamaLaporan.getText().toString().trim();
            String isiLaporan = inputTextLaporan.getText().toString().trim();

            if (namaLaporan.isEmpty() || isiLaporan.isEmpty()) {
                showMessage("Nama dan laporan tidak boleh kosong!");
                return;
            }

            if (imageUri == null) {
                showMessage("Pilih gambar terlebih dahulu!");
                return;
            }

            // Tampilkan loading indicator
            progressDialog.show();

            // Mengunggah gambar ke Firebase Storage
            String fileName = "images/" + System.currentTimeMillis() + ".jpg";
            StorageReference fileRef = storageRef.child(fileName);
            fileRef.putFile(imageUri)
                    .addOnSuccessListener(taskSnapshot -> fileRef.getDownloadUrl().addOnSuccessListener(uri -> {
                        // URL gambar yang diunggah
                        String downloadUrl = uri.toString();

                        // Simpan data laporan ke Firestore
                        Laporan laporan = new Laporan(namaLaporan, isiLaporan, downloadUrl);
                        db.collection("laporan")
                                .add(laporan)
                                .addOnSuccessListener(documentReference -> {
                                    progressDialog.dismiss();
                                    inputTextNamaLaporan.setText("");
                                    inputTextLaporan.setText("");
                                    tombolKirim.setEnabled(false);
                                    imageUri = null;
                                    showMessage("Laporan berhasil dikirim!");
                                    startActivity(new Intent(getApplicationContext(), MainMenu.class));
                                    finish();
                                })
                                .addOnFailureListener(e -> {
                                    progressDialog.dismiss();
                                    Log.e("FiturLaporan", "Gagal menyimpan laporan: ", e);
                                    showMessage("Gagal menyimpan laporan: " + e.getMessage());
                                });
                    }))
                    .addOnFailureListener(e -> {
                        progressDialog.dismiss();
                        Log.e("FiturLaporan", "Gagal mengunggah gambar: ", e);
                        showMessage("Gagal mengunggah gambar: " + e.getMessage());
                    });
        });
    }

    // Fungsi sederhana untuk menampilkan pesan
    private void showMessage(String message) {
        Toast.makeText(FiturLaporan.this, message, Toast.LENGTH_SHORT).show();
    }
}
