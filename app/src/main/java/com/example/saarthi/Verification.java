package com.example.saarthi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Verification extends AppCompatActivity {

    private DatabaseReference databaseReference;

    private Button firButton;
    private Button identityProofButton;
    private Button addressProofButton;
    private Button witnessStmtButton;
    private Button medicalReportsButton;
    private Button supporting;
    private Button financialDocumentButton;
    private Button submitButton;
    private Button backButton;

    private static final int PICK_PDF_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("VeriEvidence");

        firButton = findViewById(R.id.firButton);
        supporting = findViewById(R.id.supportingEvidenceButton);
        identityProofButton = findViewById(R.id.identityProofButton);
        addressProofButton = findViewById(R.id.addressProofButton);
        witnessStmtButton = findViewById(R.id.witnessStatementButton);
        medicalReportsButton = findViewById(R.id.medicalReportsButton);
        financialDocumentButton = findViewById(R.id.financialDocumentButton);
        submitButton = findViewById(R.id.submitButton);
        backButton = findViewById(R.id.backButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), policehomepg.class);
                startActivity(i);
            }
        });
        supporting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser("Evidence document");
            }
        });
        firButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser("FIR");
            }
        });

        identityProofButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser("Identity Proof");
            }
        });

        addressProofButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser("Address Proof");
            }
        });

        witnessStmtButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser("Witness Statement");
            }
        });

        medicalReportsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser("Medical Reports");
            }
        });

        financialDocumentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser("Financial Document");
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform any other required actions before uploading files
                uploadFilesToDatabase();
            }
        });
    }

    private void openFileChooser(String fileType) {
        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select " + fileType + " PDF"), PICK_PDF_REQUEST);
    }

    private void uploadFilesToDatabase() {
        uploadFile("FIR", firButton);
        uploadFile("Identity Proof", identityProofButton);
        uploadFile("Address Proof", addressProofButton);
        uploadFile("Witness Statement", witnessStmtButton);
        uploadFile("Medical Reports", medicalReportsButton);
        uploadFile("Financial Document", financialDocumentButton);
        // Add more uploadFile calls for additional buttons/documents if needed
    }

    private void uploadFile(String fileType, Button button) {
        Uri fileUri = (Uri) button.getTag();

        if (fileUri != null) {
            StorageReference storageReference = FirebaseStorage.getInstance().getReference()
                    .child("VeriEvidence")
                    .child(fileType)
                    .child(fileUri.getLastPathSegment());

            UploadTask uploadTask = storageReference.putFile(fileUri);
            uploadTask.addOnSuccessListener(taskSnapshot -> {
                // Get the download URL of the uploaded file
                storageReference.getDownloadUrl().addOnSuccessListener(uri -> {
                    String downloadUrl = uri.toString();
                    String fileId = databaseReference.push().getKey();
                    if (fileId != null) {
                        databaseReference.child(fileId).setValue(downloadUrl);
                    }
                });
            }).addOnFailureListener(e -> {
                // Handle file upload failure
                String errorMessage = e.getMessage();
                Toast.makeText(getApplicationContext(), "File upload failed! Error: " + errorMessage, Toast.LENGTH_SHORT).show();
            });
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri fileUri = data.getData();

            // Store the selected file URI in the respective button's tag
            switch (requestCode) {
                case PICK_PDF_REQUEST:
                    // Update the button ID for each case
                    firButton.setTag(fileUri);
                    identityProofButton.setTag(fileUri);
                    addressProofButton.setTag(fileUri);
                    witnessStmtButton.setTag(fileUri);
                    medicalReportsButton.setTag(fileUri);
                    supporting.setTag(fileUri);
                    financialDocumentButton.setTag(fileUri);
                    // Add more cases for additional buttons/documents
                    break;
            }
        }
    }
}
