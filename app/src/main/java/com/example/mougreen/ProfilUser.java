package com.example.mougreen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
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
import com.google.firebase.BuildConfig;
import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class ProfilUser extends AppCompatActivity {

    private ImageView tombolLogout;
    private AuthManager authManager;
    private FirebaseUser user;
    private FirebaseAuth mAuth;
    private TextView emailProfile, usernameText;
    private ImageButton tombolKembali;
    private FirebaseFirestore db;
    private String userUUID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profil_user);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        emailProfile = findViewById(R.id.emailProfil);
        tombolLogout = findViewById(R.id.tombolLogout);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        tombolKembali = findViewById(R.id.tombolKembaliProfil);
        db = FirebaseFirestore.getInstance();
        usernameText = findViewById(R.id.usernameProfil);
        userUUID = mAuth.getUid();
        authManager = new AuthManager();


//        if (user == null){
//            Intent intent = new Intent(getApplicationContext(), Login.class);
//            startActivity(intent);
//            finish();
//        }
        if (user == null && !BuildConfig.DEBUG) {
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
                                    usernameText.setText(document.getString("usernameUsers"));
                            } else {
                                usernameText.setText("Pak Vincent");
                            }
                        }
                    });
            emailProfile.setText(user.getEmail());
        }

        tombolKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainMenu.class);
                startActivity(intent);
                finish();
            }
        });


//        tombolLogout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(ProfilUser.this, "Log Out Berhasil",
//                        Toast.LENGTH_SHORT).show();
//                FirebaseAuth.getInstance().signOut();
//                Intent intent = new Intent(getApplicationContext(), Login.class);
//                startActivity(intent);
//                finish();
//            }
        tombolLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ProfilUser.this, "Log Out Berhasil", Toast.LENGTH_SHORT).show();
                authManager.logout();
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });
    }
}