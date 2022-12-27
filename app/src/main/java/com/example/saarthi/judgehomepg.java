package com.example.saarthi;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class judgehomepg extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView nv;
    Toolbar toolbar;
    ImageView criminalhistory, caseinfo, chargeSheet, nexthearing, judgement, back7;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference usersRef = database.getReference();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_judgehomepg);

        caseinfo = findViewById(R.id.caseinfo);
        judgement = findViewById(R.id.judgement);
        toolbar = findViewById(R.id.toolbar);
        criminalhistory = findViewById(R.id.criminalhistory);
        drawerLayout = findViewById(R.id.drawerlayout);
        nv = findViewById(R.id.navigation);
        chargeSheet = findViewById(R.id.chargeSheetDisplay);
        setSupportActionBar(toolbar);
        back7 = findViewById(R.id.back7);
        nexthearing = findViewById(R.id.nexthearing);



        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_open, R.string.navigation_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        back7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), laymenlogin.class);
                startActivity(i);
            }
        });

        judgement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Courtorder.class);
                startActivity(i);
            }
        });
        criminalhistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(judgehomepg.this, userlist.class);
                startActivity(i);
                finish();
            }
        });

        caseinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity2.class);
                startActivity(i);
                finish();
            }
        });

        chargeSheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usersRef.child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                                String age = childSnapshot.child("age").getValue(String.class);
                                String firstName = childSnapshot.child("firstName").getValue(String.class);
                                String lastName = childSnapshot.child("lastName").getValue(String.class);
                                String username = childSnapshot.child("userName").getValue(String.class);

                                // Display the fetched data in a dialog box
                                AlertDialog.Builder builder = new AlertDialog.Builder(judgehomepg.this);
                                builder.setTitle("User Information");
                                builder.setMessage("Age: " + age + "\nFirst Name: " + firstName + "\nLast Name: " + lastName + "\nUsername: " + username);
                                builder.setPositiveButton("OK", null);
                                builder.show();
                            }
                        } else {
                            // Handle case when the data doesn't exist
                            Toast.makeText(judgehomepg.this, "Data not found", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // Handle database error
                        Toast.makeText(judgehomepg.this, "Error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


        nexthearing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), NextHearing.class);
                startActivity(i);
            }
        });
    }
}