package com.wizy.android.student.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.UiModeManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.wizy.android.student.App;
import com.wizy.android.student.helper.AppConstants;

@SuppressLint("Registered")
public class BaseActivity extends AppCompatActivity {
    private UiModeManager uiModeManager;
    protected App app;
    private boolean paused;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app = App.getContext();
        uiModeManager = (UiModeManager) getSystemService(UI_MODE_SERVICE);
        if (!isNightMode()) {
            applyLightNavigation();
        } else {
            getWindow().setNavigationBarColor(Color.parseColor("#303030"));
        }
    }

    private void applyLightNavigation() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            getWindow().setNavigationBarColor(Color.parseColor("#FAFAFA"));
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
                    | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    protected boolean isNightMode() {
        return uiModeManager.getNightMode() == UiModeManager.MODE_NIGHT_YES;
    }

    protected void toggleNightMode() {
        if (isNightMode()) {
            uiModeManager.setNightMode(UiModeManager.MODE_NIGHT_NO);
        } else {
            uiModeManager.setNightMode(UiModeManager.MODE_NIGHT_YES);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        paused = false;
    }

    @Override
    protected void onPause() {
        super.onPause();
        paused = true;
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    public boolean isPaused() {
        return paused;
    }

    protected boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return (activeNetworkInfo != null && activeNetworkInfo.isConnected());
    }

    protected boolean isPermission(@NonNull Context context, @NonNull String permission) {

        return ContextCompat.checkSelfPermission(context,
                permission) == PackageManager.PERMISSION_GRANTED;
    }

    protected void askPermission(@NonNull Activity context, @NonNull String permission, String... morePermissions) {
        if (morePermissions.length > 0) {
            ActivityCompat.requestPermissions(context,
                    morePermissions,
                    AppConstants.REQUEST_PERMISSION_CODE);
        } else {

            ActivityCompat.requestPermissions(context,
                    new String[]{permission},
                    AppConstants.REQUEST_PERMISSION_CODE);
        }


    }

    private void hideSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }

    protected void setError(TextView view, String msg) {
        view.setError(msg);
        view.requestFocus();
    }
}
