package com.example.triviaapp.feature.splash;


import android.os.Bundle;
import android.os.Handler;

import com.example.triviaapp.R;
import com.example.triviaapp.baseui.BaseActivity;
import com.example.triviaapp.feature.firstpage.FirstPageActivity;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                switchActivity(FirstPageActivity.class);
                finish();
            }
        },5000);
    }
}
