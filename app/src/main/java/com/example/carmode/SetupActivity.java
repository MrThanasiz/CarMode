package com.example.carmode;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;

import java.util.ArrayList;
import java.util.List;

public class SetupActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.carmode.MESSAGE";
    public static final List<ResolveInfo> allowedApps = new ArrayList<ResolveInfo>();
    private Switch appSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (WelcomeActivity.FULL_SCREEN_FLAG){
            FullScreen.hideSystemUI(getWindow());
        }

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra(WelcomeActivity.EXTRA_MESSAGE);

        List<ResolveInfo> appList = getInstalledAppList(this);
        ScrollView scroll  = new ScrollView((this));

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        TextView setupText = new TextView((this));
        setupText.setText(R.string.settingsTop);
        linearLayout.addView(setupText);

        for(final ResolveInfo item : appList) {
            Switch appSwitch = new Switch(this);
            appSwitch.setId(ViewCompat.generateViewId());
            final String appName = item.loadLabel(this.getPackageManager()).toString();
            Drawable appIcon = item.loadIcon(this.getPackageManager());
            appSwitch.setText(appName);
            appSwitch.setButtonDrawable(appIcon);
            linearLayout.addView(appSwitch);
            appSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                         allowedApps.add(item);
                    } else{
                        allowedApps.remove(item);
                    }
                }
            });
        }

        Button saveSettings = new Button((this));
        saveSettings.setText(R.string.saveSettings);
        linearLayout.addView(saveSettings);

        scroll.addView(linearLayout);
        setContentView(scroll);
    }

    public List<ResolveInfo> getInstalledAppList(Context context){
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> pkgAppsList = context.getPackageManager().queryIntentActivities( mainIntent, 0);
        return pkgAppsList;
    }

    public void MainActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        String message = "passing a msg";
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}