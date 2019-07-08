package com.example.android.teproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private EditText email, password;
    private Button loginButton;
    private TextView loginToRegister;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private String userid,user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.etLoginEmail);
        password = findViewById(R.id.etAddress);
        loginButton = findViewById(R.id.btnLogin);
        loginToRegister = findViewById(R.id.tvLoginToRegister);
        mAuth = FirebaseAuth.getInstance();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!email.getText().toString().equals("")){
                    if (!password.getText().toString().equals("")) {

                        if (email.getText().toString().equals("Admin") && password.getText().toString().equals("jnec2018")) {


                            Intent i = new Intent(LoginActivity.this, Admin.class);
                            startActivity(i);
                        } else {

                            mAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        mUser = mAuth.getCurrentUser();
                                        userid = mUser.getUid();
                                        Toast.makeText(LoginActivity.this, "Successful login!", Toast.LENGTH_SHORT).show();
                                        Intent i = new Intent(LoginActivity.this, MainActivity.class);
                                        startActivity(i);
                                    } else {
                                        Toast.makeText(LoginActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                    }else{
                        password.setError("Field Required!");
                    }
                }else{
                    email.setError("Field Required!");
                }
            }
        });
        loginToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });
    }
}
