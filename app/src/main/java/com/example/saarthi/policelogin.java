package com.example.saarthi;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class policelogin extends AppCompatActivity {
    Button login;
    EditText pemail, ppass;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_policelogin);
        login = findViewById(R.id.plogin);
        pemail = findViewById(R.id.pemail);
        ppass = findViewById(R.id.ppass);
        FirebaseAuth mAuth;
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), policehomepg.class);
                startActivity(i);
            }
        });
    }
    private void loginUser(){
        String email = pemail.getText().toString();
        String password = ppass.getText().toString();

        if(TextUtils.isEmpty(email)){
            pemail.setError("Email cannot be empty.");
            pemail.requestFocus();
        }else if(TextUtils.isEmpty(password)){
            ppass.setError("Password cannot be empty.");
            ppass.requestFocus();
        }
        else{
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(getApplicationContext(), "Logged in successfully!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), policehomepg.class));
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Login error" + task.getException().getMessage(), Toast.LENGTH_LONG).show();

                    }
                }
            });
        }
    }
}