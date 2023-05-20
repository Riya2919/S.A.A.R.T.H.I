package com.example.saarthi;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity2 extends AppCompatActivity {

    myadapterpdf adapter;
    RecyclerView recview;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        recview = findViewById(R.id.recyclerview);

        recview.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<model> options =
                new FirebaseRecyclerOptions.Builder<model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("files"), model.class)
                        .build();

        adapter = new myadapterpdf(options);
        recview.setAdapter(adapter);
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