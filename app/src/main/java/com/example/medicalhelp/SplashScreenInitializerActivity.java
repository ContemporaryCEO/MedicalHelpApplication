package com.example.medicalhelp;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

@SuppressLint("CustomSplashScreen")
public class SplashScreenInitializerActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
/*
        Новое событие для главной Activity
        Обработка Splash скрина
*/
        Intent intent
                = new Intent
                (this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}