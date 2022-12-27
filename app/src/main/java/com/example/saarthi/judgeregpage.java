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

public class judgeregpage extends AppCompatActivity {
    TextView login;
    Button reg;
    EditText judemail, judgepass;

    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_judgeregpage);
        login = findViewById(R.id.jlogin);
        reg = findViewById(R.id.jreg);

        judemail = findViewById(R.id.judgeregemail);
        judgepass = findViewById(R.id.judgeregpass);
        mAuth = FirebaseAuth.getInstance();

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createUser();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), judgelogin.class);
                startActivity(i);
            }
        });
    }
    private void createUser(){
        String email = judemail.getText().toString();
        String password = judgepass.getText().toString();

        if(TextUtils.isEmpty(email)){
            judemail.setError("Email cannot be empty.");
            judemail.requestFocus();
        }else if(TextUtils.isEmpty(password)){
            judgepass.setError("Password cannot be empty.");
            judgepass.requestFocus();
        }
        else{
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(getApplicationContext(), "User registered successfully!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), judgelogin.class));
                    }else{
                        Toast.makeText(getApplicationContext(), "Registration error" + task.getException().getMessage(), Toast.LENGTH_LONG).show();

                    }
                }
            });
        }
    }
}