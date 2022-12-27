package com.example.saarthi;

import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

public class NextHearing extends AppCompatActivity {
    ImageView back3;
    Button update;
    DatePicker datePicker;
    private TimePicker timePicker;

    DatabaseReference databaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next_hearing);
        back3 = findViewById(R.id.back3);
        update = findViewById(R.id.dateupdate);
        datePicker = findViewById(R.id.datePicker);
        databaseRef = FirebaseDatabase.getInstance().getReference();
        timePicker = findViewById(R.id.timePicker);


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int day = datePicker.getDayOfMonth();
                int month = datePicker.getMonth();
                int year = datePicker.getYear();

                int hour = timePicker.getCurrentHour();
                int minute = timePicker.getCurrentMinute();

                // Format the date and time as needed (e.g., as a string or in a specific format)
                String formattedDate = String.format("%02d/%02d/%04d %02d:%02d", day, month + 1, year, hour, minute);

                // Update the date and time in the Firebase Realtime Database
                databaseRef.child("hearings").child("date").setValue(formattedDate)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                Toast.makeText(NextHearing.this, "Date and time updated successfully", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(NextHearing.this, "Failed to update date and time", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });


        back3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), judgehomepg.class);
                startActivity(i);
            }
        });
    }
}
