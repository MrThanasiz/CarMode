package com.example.carmode;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.carmode.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        if (SetupActivity.allowedApps.isEmpty()){
            TextView allowedAppsText = new TextView((this));
            allowedAppsText.setText(R.string.allowedAppsEmpty);
            linearLayout.addView(allowedAppsText);
        }
        else {
            TextView allowesAppsText = new TextView((this));
            allowesAppsText.setText(R.string.allowedAppsHeadline);
            linearLayout.addView(allowesAppsText);

            for(final ResolveInfo allowed_item : SetupActivity.allowedApps) {
                ImageView appButton = new ImageView(this);
                appButton.setId(ViewCompat.generateViewId());
                final String appName = allowed_item.loadLabel(this.getPackageManager()).toString();
                Drawable appIcon = allowed_item.loadIcon(this.getPackageManager());
                appButton.setImageDrawable(appIcon);
                linearLayout.addView(appButton);
            }
        }
        setContentView(linearLayout);
    }

    public void setupActivity(View view) {
        Intent intent = new Intent(this, SetupActivity.class);
        String message = "passing a msg";
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}