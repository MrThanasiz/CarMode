package com.example.carmode;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.carmode.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (WelcomeActivity.FULL_SCREEN_FLAG){
            FullScreen.hideSystemUI(getWindow());
        }
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
                final ImageView appButton = new ImageView(this);
                appButton.setId(ViewCompat.generateViewId());
                final String appName = allowed_item.loadLabel(this.getPackageManager()).toString();
                Drawable appIcon = allowed_item.loadIcon(this.getPackageManager());
                appButton.setImageDrawable(appIcon);
                linearLayout.addView(appButton);

                appButton.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        String appPackageName = allowed_item.toString().substring(20).split("/")[0]; // not the best way...
                        Log.d("Package Name",appPackageName);
                        runApp(v,appPackageName);
                    }
                });
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


    public void runApp(View view,String pkgName) {
        startNewActivity(this, pkgName);
    }

    public void startNewActivity(Context context, String packageName) {
        Intent intent = context.getPackageManager().getLaunchIntentForPackage(packageName);
        if (intent == null) {
            // Bring user to the market or let them choose an app?
            intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("market://details?id=" + packageName));
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }


}