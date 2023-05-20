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

public class policereg extends AppCompatActivity {
    TextView login;
    Button reg;
    EditText polemail, polpass;

    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_policereg);
        login = findViewById(R.id.plogin);
        reg = findViewById(R.id.preg);
        polemail = findViewById(R.id.polemail);
        polpass = findViewById(R.id.polpass);
        mAuth = FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), policelogin.class);
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
        String email = polemail.getText().toString();
        String password = polpass.getText().toString();

        if(TextUtils.isEmpty(email)){
            polemail.setError("Email cannot be empty.");
            polemail.requestFocus();
        }else if(TextUtils.isEmpty(password)){
            polpass.setError("Password cannot be empty.");
            polpass.requestFocus();
        }
        else{
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(getApplicationContext(), "User registered successfully!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), policelogin.class));
                    }else{
                        Toast.makeText(getApplicationContext(), "Registration error" + task.getException().getMessage(), Toast.LENGTH_LONG).show();

                    }
                }
            });
        }
    }
}