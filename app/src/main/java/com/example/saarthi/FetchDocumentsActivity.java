package com.example.saarthi;


import android.os.Bundle;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FetchDocumentsActivity extends AppCompatActivity {

    private WebView webView;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetch_documents);

        // Initialize WebView
        webView = findViewById(R.id.web_view);
        webView.getSettings().setJavaScriptEnabled(true);

        // Initialize the database reference to "VeriEvidence" child
        databaseReference = FirebaseDatabase.getInstance().getReference("VeriEvidence");

        // Fetch the PDF URL from the Realtime Database
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    String pdfUrl = childSnapshot.getValue(String.class);
                    if (pdfUrl != null) {
                        // Load and display the PDF
                        webView.setWebViewClient(new WebViewClient());
                        webView.loadUrl(pdfUrl);
                        break; // Display the first PDF found (you can modify this behavior)
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle database read error
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Clear WebView resources
        if (webView != null) {
            webView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            webView.clearHistory();

            ((ViewGroup) webView.getParent()).removeView(webView);
            webView.destroy();
        }
    }
}
