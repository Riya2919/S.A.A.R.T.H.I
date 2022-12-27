package com.example.saarthi;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class laymenhomepg extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView nv;
    Toolbar toolbar;
    ImageView caseinfol, ginfo, hearing, courtorder, back, predict, documents;
    DatabaseReference databaseRef, ref2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laymenhomepg);
        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawerlayout);
        hearing = findViewById(R.id.hearinginfo);
        back = findViewById(R.id.back6);
        courtorder = findViewById(R.id.courtorder);
        databaseRef = FirebaseDatabase.getInstance().getReference();
        ref2 = FirebaseDatabase.getInstance().getReference();
        predict = findViewById(R.id.PredictCase);
        documents = findViewById(R.id.docs);
        nv = findViewById(R.id.navigation);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_open, R.string.navigation_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        caseinfol = findViewById(R.id.caseinfol);
        ginfo = findViewById(R.id.geninfo);
        predict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), PredictCasePage.class);
                startActivity(i);
            }
        });
        documents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), FetchDocumentsActivity.class);
                startActivity(i);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), laymenlogin.class);
                startActivity(i);
            }
        });
        caseinfol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity2.class);
                startActivity(i);
                finish();
            }
        });

        ginfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), generalinfo.class);
                startActivity(i);
                finish();
            }
        });

        hearing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchHearingDateTime();
            }
        });

        courtorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchCourtOrderDetails();
            }
        });
    }

    private void fetchHearingDateTime() {
        databaseRef.child("hearings").child("date").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String hearingDate = dataSnapshot.getValue(String.class);
                if (hearingDate != null) {
                    showHearingDialog("Hearing Date and Time", "Date and time: " + hearingDate);
                } else {
                    showHearingDialog("Hearing Date and Time", "No hearing date available");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                showHearingDialog("Hearing Date and Time", "Failed to fetch hearing date");
            }
        });
    }

    private void fetchCourtOrderDetails() {
        ref2.child("court_orders").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot courtOrderSnapshot : dataSnapshot.getChildren()) {
                    String courtOrderId = courtOrderSnapshot.getKey(); // Get the ID of the court order

                    String cnr = courtOrderSnapshot.child("cnr").getValue(String.class);
                    String convictName = courtOrderSnapshot.child("convictName").getValue(String.class);
                    String judgement = courtOrderSnapshot.child("punishment").getValue(String.class);
                    String courtAddress = courtOrderSnapshot.child("courtAddress").getValue(String.class);
                    String courtLevel = courtOrderSnapshot.child("courtLevel").getValue(String.class);

                    String message ="CNR: " + cnr + "\n"
                            + "Convict Name: " + convictName + "\n"
                            + "Judgement: " + judgement + "\n"
                            + "Court Address: " + courtAddress + "\n"
                            + "Court Level: " + courtLevel;

                    showCourtOrderDialog("Court Order Details", message);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                showCourtOrderDialog("Court Order Details", "Failed to fetch court order details");
            }
        });
    }


    private void showHearingDialog(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showCourtOrderDialog(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}



