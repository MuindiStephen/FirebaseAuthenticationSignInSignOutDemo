package com.stevemuindi.firebaseauthenticationdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.window.SplashScreen;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        // FirebaseAuth auth = FirebaseAuth.getInstance();
        //FirebaseUser user = auth.getCurrentUser();

      new Handler().postDelayed(new Runnable() {
          @Override
          public void run() {
              Intent intent = new Intent(SplashActivity.this, SignUpActivity.class);
              startActivity(intent);
              finish();
          }
      }, 3500);
    }
}