package com.example.saarthi;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class PredictCasePage extends AppCompatActivity {

    private EditText input1, input2, input3;
    private Button submitButton;
    private TextView outputTextView;
    ImageView back8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_predict_case_page);

        input1 = findViewById(R.id.inputEditText1);
        input2 = findViewById(R.id.inputEditText2);
        input3 = findViewById(R.id.inputEditText3);
        back8 = findViewById(R.id.back8);

        submitButton = findViewById(R.id.submitButton);
        outputTextView = findViewById(R.id.textView13);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userInputString1 = input1.getText().toString().trim();
                int userInput1 = Integer.parseInt(userInputString1);
                String userInputString2 = input2.getText().toString().trim();
                int userInput2 = Integer.parseInt(userInputString2);
                String userInputString3 = input3.getText().toString().trim();
                int userInput3 = Integer.parseInt(userInputString3);

                back8.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(getApplicationContext(), laymenhomepg.class);
                        startActivity(i);
                    }
                });
                // Create the JSON request payload
                String jsonRequest = "{\"Inputs\":{\"input1\":[{\"Crime\":" + userInput1 +
                        ",\"PeopleInvolved\":" + userInput2 + ",\"Place\":" + userInput3 +
                        ",\"Numofdays\":0}]},\"GlobalParameters\":{}}";

                // Perform the API call in a separate thread
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            // Set up the connection
                            URL url = new URL("http://620abf4e-88b6-4c3d-a636-643c54352362.eastus2.azurecontainer.io/score");
                            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                            connection.setRequestMethod("POST");
                            connection.setRequestProperty("Content-Type", "application/json");
                            connection.setRequestProperty("Authorization", "Bearer gA4ueUVqgc4jFFS2N5r7RYMcL5QWEsXu");
                            connection.setDoOutput(true);

                            // Send the JSON payload
                            DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
                            outputStream.writeBytes
                                    (jsonRequest);
                            outputStream.flush();
                            outputStream.close();

                            // Get the API response
                            int responseCode = connection.getResponseCode();
                            if (responseCode == HttpURLConnection.HTTP_OK) {
                                // Process the response
                                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                                StringBuilder response = new StringBuilder();
                                String line;
                                while ((line = reader.readLine()) != null) {
                                    response.append(line);
                                }
                                reader.close();

                                // Update the UI with the response
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        String scoredLabels = processResponse(response.toString());
                                        outputTextView.setText(scoredLabels);
                                    }
                                });
                            } else {
                                // Handle API call failure
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        outputTextView.setText("Error: " + responseCode);
                                    }
                                });
                            }

                            connection.disconnect();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
    }

    private String processResponse(String jsonResponse) {
        try {
            JSONObject jsonObject = new JSONObject(jsonResponse);
            JSONObject results = jsonObject.getJSONObject("Results");
            JSONArray webServiceOutput = results.getJSONArray("WebServiceOutput0");
            JSONObject outputObject = webServiceOutput.getJSONObject(0);
            double scoredLabels = outputObject.getDouble("Scored Labels");
            int roundedScore = (int) Math.round(scoredLabels); // Round the value to the nearest integer
            return String.valueOf(roundedScore);
        } catch (JSONException e) {
            e.printStackTrace();
            return "Error parsing response";
        }
    }
}