package com.example.saarthi;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class laymenreg extends AppCompatActivity {
    TextView login;
    Button reg;

    EditText laemail, lapass;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laymenreg);
        login = findViewById(R.id.llogin);
        reg = findViewById(R.id.lreg);
        laemail = findViewById(R.id.lemail);
        lapass = findViewById(R.id.lpass);

        mAuth = FirebaseAuth.getInstance();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), laymenlogin.class);
                startActivity(i);
            }
        });
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createUser();
            }
        });
    }
    private void createUser(){
        String email = laemail.getText().toString();
        String password = lapass.getText().toString();

        if(TextUtils.isEmpty(email)){
            laemail.setError("Email cannot be empty.");
            laemail.requestFocus();
        }else if(TextUtils.isEmpty(password)){
            lapass.setError("Password cannot be empty.");
            lapass.requestFocus();
        }
        else{
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(getApplicationContext(), "User registered successfully!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), laymenlogin.class));
                    }else{
                        Toast.makeText(getApplicationContext(), "Registration error" + task.getException().getMessage(), Toast.LENGTH_LONG).show();

                    }
                }
            });
        }
    }
}