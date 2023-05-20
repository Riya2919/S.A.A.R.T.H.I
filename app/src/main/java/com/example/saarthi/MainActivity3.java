package com.example.saarthi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


import com.example.saarthi.databinding.ActivityMain3Binding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity3 extends AppCompatActivity {

    ActivityMain3Binding binding;
    String firstName, lastName, age, userName;
    FirebaseDatabase db;

    DatabaseReference reference;

    ImageView img1, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        img1 = (ImageView) findViewById(R.id.pdfUp);
        back = findViewById(R.id.back9);

        binding.registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                firstName = binding.firstName.getText().toString();
                lastName = binding.lastName.getText().toString();
                age = binding.age.getText().toString();
                userName = binding.userName.getText().toString();

                if(!firstName.isEmpty() && !lastName.isEmpty() && !age.isEmpty() && !userName.isEmpty()){
                    Users3 users = new Users3(firstName,lastName,age,userName);
                    db = FirebaseDatabase.getInstance();
                    reference = db.getReference("Users");
                    reference.child(userName).setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            binding.firstName.setText("");
                            binding.lastName.setText("");
                            binding.age.setText("");
                            binding.userName.setText("");
                            Toast.makeText(MainActivity3.this,"Success",Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), policehomepg.class);
                startActivity(i);
            }
        });
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity3.this, upload.class));
            }
        });
    }
}