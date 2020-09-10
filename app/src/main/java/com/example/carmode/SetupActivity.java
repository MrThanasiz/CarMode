package com.example.carmode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SetupActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.carmode.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra(WelcomeActivity.EXTRA_MESSAGE);
    }

    public void MainActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        String message = "passing a msg";
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}