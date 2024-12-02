package com.example.mougreen;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class Login extends AppCompatActivity {

    private ImageButton loginbutton;
    private EditText emailOrUsernameInput, passwordInput;
    private FirebaseAuth mAuth;
    private ImageView toRegisterMenu;
    private FirebaseFirestore db;


    private void checkUserRoleIsLogin(String uid) {
        db.collection("users").document(uid)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        DocumentSnapshot document = task.getResult();
                        if (Objects.equals(document.getString("Role"), "Admin")){
                            Intent intent = new Intent(getApplicationContext(), Admin.class);
                            startActivity(intent);
                            finish();
                        }
                        else{
                            Intent intent = new Intent(getApplicationContext(), MainMenu.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            checkUserRoleIsLogin(currentUser.getUid());
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        emailOrUsernameInput = findViewById(R.id.usernameoremail);
        passwordInput = findViewById(R.id.password);
        mAuth = FirebaseAuth.getInstance();
        loginbutton = findViewById(R.id.loginbutton);
        toRegisterMenu = findViewById(R.id.donthaveaccounttext);
        db = FirebaseFirestore.getInstance();

        toRegisterMenu.setOnClickListener(new View.OnClickListener(){
            @Override
                    public void onClick(View v){
                Intent i = new Intent( Login.this, RegisterActivity.class);
            startActivity(i);
            finish();}
        });

        // Login Memakai Google belum dibuat!

        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailorusername, password;
                emailorusername = String.valueOf(emailOrUsernameInput.getText());
                password = String.valueOf(passwordInput.getText());

                if (TextUtils.isEmpty(emailorusername)){
                    Toast.makeText(Login.this, "Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    Toast.makeText(Login.this, "Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.signInWithEmailAndPassword(emailorusername, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Toast.makeText(Login.this, "Login Berhasil",
                                            Toast.LENGTH_SHORT).show();
                                     FirebaseUser users = mAuth.getCurrentUser();
                                        if (users != null){
                                            checkUserRole(users.getUid());
                                        }
                                        else{
                                            Toast.makeText(Login.this, "Role tidak ada",
                                                    Toast.LENGTH_SHORT).show();
                                        }


                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(Login.this, "Login Gagal",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });


    }

    private void checkUserRole(String uid) {
        db.collection("users").document(uid)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        DocumentSnapshot document = task.getResult();
                        if (Objects.equals(document.getString("Role"), "Admin")){
                            Intent intent = new Intent(getApplicationContext(), Admin.class);
                            startActivity(intent);
                            finish();
                        }
                        else{
                            Intent intent = new Intent(getApplicationContext(), MainMenu.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                });
    }
}