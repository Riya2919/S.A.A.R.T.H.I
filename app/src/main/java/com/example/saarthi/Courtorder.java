package com.example.saarthi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Courtorder extends AppCompatActivity {
    EditText editcnr, convictname, judgement, address;
    Spinner level;
    Button submit;
    DatabaseReference databaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courtorder);
        editcnr = findViewById(R.id.editCNR);
        convictname = findViewById(R.id.ConvictName);
        judgement = findViewById(R.id.editPunishment);
        address = findViewById(R.id.editCourtAddress);
        level = findViewById(R.id.spinnerCourtLevel);
        submit = findViewById(R.id.details);
        databaseRef = FirebaseDatabase.getInstance().getReference().child("court_orders");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cnr = editcnr.getText().toString().trim();
                String name = convictname.getText().toString().trim();
                String punishment = judgement.getText().toString().trim();
                String courtAddress = address.getText().toString().trim();
                String courtLevel = level.getSelectedItem().toString();

                // Create a unique key for the court order
                String courtOrderId = databaseRef.push().getKey();

                // Create a CourtOrder object
                Court courtOrder = new Court(cnr, name, punishment, courtAddress, courtLevel);

                // Save the court order in the database
                if (courtOrderId != null) {
                    databaseRef.child(courtOrderId).setValue(courtOrder);
                    Toast.makeText(Courtorder.this, "Court order saved successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Courtorder.this, "Failed to save court order", Toast.LENGTH_SHORT).show();
                }
                Intent i = new Intent(getApplicationContext(), judgehomepg.class);
                startActivity(i);
            }
        });
    }
}
