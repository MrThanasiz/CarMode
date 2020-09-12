package com.example.carmode;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.carmode.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    /** Called when the user taps the Send button */
    public void setupActivity(View view) {
        Intent intent = new Intent(this, SetupActivity.class);
        String message = "passing a msg";
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void goToAllowedApps(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}