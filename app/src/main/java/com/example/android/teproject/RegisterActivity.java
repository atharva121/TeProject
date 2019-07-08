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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private EditText user, email, password, confirmPassword;
    private Button registerButton;
    private TextView registerToLogin;
    private FirebaseAuth mAuth;
    private Users users;
    private FirebaseUser mUser;
    private DatabaseReference firebaseReference, userReference;
    private String userid;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        user = findViewById(R.id.etRegisterUser);
        email = findViewById(R.id.etRegisterEmail);
        password = findViewById(R.id.etRegisterPassword);
        confirmPassword = findViewById(R.id.etRegisterConfirmPassword);
        registerButton = findViewById(R.id.btnRegister);
        registerToLogin = findViewById(R.id.tvRegisterToLogin);
        mAuth = FirebaseAuth.getInstance();
        firebaseReference = FirebaseDatabase.getInstance().getReference();
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!user.getText().toString().equals("")){
                    if (!email.getText().toString().equals("")){
                        if (!password.getText().toString().equals("") && password.getText().toString().length() >= 8){
                            if (!confirmPassword.getText().toString().equals("") && confirmPassword.getText().toString().equals(password.getText().toString())){
                                mAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()){
                                            try {
                                                mUser = mAuth.getCurrentUser();
                                                userid = mUser.getUid();
                                                firebaseReference.child("users").child(userid).child("user").setValue(user.getText().toString());
                                                firebaseReference.child("users").child(userid).child("email").setValue(email.getText().toString());
                                                Toast.makeText(RegisterActivity.this, "User registered successfully!", Toast.LENGTH_SHORT).show();
                                                Intent i = new Intent(RegisterActivity.this, MainActivity.class);
                                                startActivity(i);
                                            }catch (Exception e){

                                            }
                                        }else {
                                            Toast.makeText(RegisterActivity.this, "An error occurred!", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }else{
                                confirmPassword.setError("Field required or passwords do not match!");
                            }
                        }else{
                            password.setError("Field required or password is not above 8 characters!");
                        }
                    }else {
                        email.setError("Field required!");
                    }
                }else {
                    user.setError("Field required");
                }
            }
        });
        registerToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });
    }
}
