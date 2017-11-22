package com.inzenjer.forthepeople;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashhhhhhhhhhhhh);
        getSupportActionBar().hide();
        TextView txt = (TextView) findViewById(R.id.forthepeoplesplashh);
        Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce);
        txt.startAnimation(anim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(splash.this, Login.class);
                startActivity(i);
                finish();
            }
        }, 580);



    }
}
