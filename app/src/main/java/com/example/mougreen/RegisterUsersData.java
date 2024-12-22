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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterUsersData extends AppCompatActivity {

    private EditText usernameInput, namaInput, noRumahInput;
    private ImageButton signupButton, toLoginWarga;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private String currentUserUUID, emailUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register_users_data);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        usernameInput = findViewById(R.id.username);
        namaInput = findViewById(R.id.namaWarga);
        noRumahInput = findViewById(R.id.nomorRumah);
        toLoginWarga = findViewById(R.id.tologinmenu2);
        signupButton = findViewById(R.id.signup);
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        currentUserUUID = mAuth.getUid();
        emailUser = getIntent().getExtras().getString("email");


        toLoginWarga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });

        if (currentUserUUID == null) {
            Toast.makeText(this, "User UID tidak ditemukan.", Toast.LENGTH_SHORT).show();
            return;
        }

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username, nama, noRumah, email, role;
                username = String.valueOf(usernameInput.getText());
                nama = String.valueOf(namaInput.getText());
                noRumah = String.valueOf(noRumahInput.getText());
                email = emailUser;
                role = "user";

                if (TextUtils.isEmpty(username)){
                    Toast.makeText(RegisterUsersData.this, "Enter Username", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(nama)){
                    Toast.makeText(RegisterUsersData.this, "Enter Nama", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(noRumah)){
                    Toast.makeText(RegisterUsersData.this, "Enter Nomor Rumah", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Create a new user with a first and last name
                Map<String, Object> user = new HashMap<>();
                user.put("emailUsers" , email);
                user.put("usernameUsers" , username);
                user.put("namaUsers", nama);
                user.put("nomorRumahUsers", noRumah);
                user.put("roleUsers", role);

                db.collection("users").document(currentUserUUID)
                        .set(user)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                mAuth.signOut();
                                Intent intent = new Intent(getApplicationContext(), Login.class);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(RegisterUsersData.this, "Data tidak bisa disimpan",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });





    }
}