package com.wizy.android.student.base;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.wizy.android.student.App;
import com.wizy.android.student.R;
import com.wizy.android.student.helper.CommonUtils;
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent;
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener;


public class BaseToolbarActivity extends BaseActivity {
    protected Toolbar toolbar;
    private FrameLayout contentView;
    private ImageView blurView;
    private ProgressBar progressBar;
    protected AppBarLayout appBarLayout;
    protected TabLayout tabLayout;
    protected FloatingActionButton btnBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_base_toolbar);
        toolbar = findViewById(R.id.toolbar);
        contentView = findViewById(R.id.childContent);
        progressBar = findViewById(R.id.mainLoadingBar);
        blurView = findViewById(R.id.blurView);
        tabLayout = findViewById(R.id.tabLayout);
        appBarLayout = findViewById(R.id.appBar);
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> onBackPressed());


        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null && getSupportParentActivityIntent() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }


    @Override
    public void setContentView(@LayoutRes int viewId) {
        contentView.addView(getLayoutInflater().inflate(viewId, null));
    }


    protected void showProgress() {

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        blurView.setImageBitmap(CommonUtils.blur(this, contentView, 20, 1f));
        blurView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        contentView.setVisibility(View.GONE);
    }


    protected void hideProgress() {
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        blurView.setImageResource(android.R.color.transparent);
        contentView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        blurView.setVisibility(View.GONE);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}
