package com.example.saarthi;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class policehomepg extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView nv;
    Toolbar toolbar;
    ImageView record, charge, caseinfo, criminalhistory, verification, back;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_policehomepg);
        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawerlayout);
        nv = findViewById(R.id.navigation);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_open, R.string.navigation_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        record = findViewById(R.id.records);
        caseinfo = findViewById(R.id.pcaseinfo);
        verification = findViewById(R.id.verification);
        back = findViewById(R.id.back10);
        charge = findViewById(R.id.chargeSheet);
        criminalhistory = findViewById(R.id.pcriminalhistory);
        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), upload.class);
                startActivity(i);
                finish();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), policelogin.class);
                startActivity(i);
            }
        });
        verification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Verification.class);
                startActivity(i);
            }
        });
        criminalhistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), userlist.class);
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
        charge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),MainActivity3.class);
                startActivity(i);
                finish();
            }
        });
    }
}