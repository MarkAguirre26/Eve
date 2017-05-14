package com.android.eve;

import android.app.Activity;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class Splash extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        tools.setFullScreen(this);
variables.index = variables.avatarArray.length -1;
        tools.ReadPlayer(this);


    }


    public void cmd_thouchme_Clicked(View view) {
        try {
            if (userInfo.name.length() <= 0) {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
            } else {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
            overridePendingTransition(0, 0);
         //   finish();
        } catch (Exception e) {
            Log.e("error", e.getMessage());
            startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
        }

    }
}
