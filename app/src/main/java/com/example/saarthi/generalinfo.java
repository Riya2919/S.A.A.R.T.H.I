package com.example.saarthi;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class generalinfo extends AppCompatActivity {

    myadapterpdf adapter;
    RecyclerView recview;
    ImageView back;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generalinfo);

        recview = findViewById(R.id.generalinfo);
        back = findViewById(R.id.back1);


        recview.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<model> options =
                new FirebaseRecyclerOptions.Builder<model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("generalInfo"), model.class)
                        .build();

        adapter = new myadapterpdf(options);
        recview.setAdapter(adapter);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), laymenhomepg.class);
                startActivity(i);
            }
        });
    }


    @Override
    protected void onStart(){
        super.onStart();
        adapter.startListening();
    }


    @Override
    protected void onStop(){
        super.onStop();
        adapter.stopListening();
    }
}