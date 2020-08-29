package com.hello.hackers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class CheckActivity extends AppCompatActivity {
    Boolean loggedin;
    @Override
    protected void onStart() {
        super.onStart();
        startActivity(new Intent(CheckActivity.this,MainActivity.class));
            finish();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sp=getSharedPreferences("Shpr", Context.MODE_PRIVATE);
        loggedin=sp.getBoolean("logged",false);


    }
}