package com.example.carmode;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.carmode.MESSAGE";
    public static Boolean FULL_SCREEN_FLAG = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_welcome);

        Switch onOff = findViewById(R.id.onOff);
        onOff.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    FullScreen.hideSystemUI(getWindow());
                    FULL_SCREEN_FLAG = true;
                }
                else{
                    FullScreen.showSystemUI(getWindow());
                    FULL_SCREEN_FLAG = false;
                }
            }
        });
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