package com.pvk.krishna.albumapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.pvk.krishna.albumapp.R;
import com.pvk.krishna.albumapp.activity.LoginActivity;


public class SplashActivity extends Activity {

    private Handler handler = new Handler();
    private static final int TIME_OUT=1*1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash_screen);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent loginIntent=new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(loginIntent);
                finish();
            }
        }, TIME_OUT);
    }
}
