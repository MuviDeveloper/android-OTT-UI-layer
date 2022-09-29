package com.home.vod.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.home.vod.R;
import com.home.vod.util.Util;

public class Blank extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blank);
        if (Util.MianActivityDestoryed) {

            Util.MianActivityDestoryed = false;

            Intent startIntent = new Intent(Blank.this, MainActivity.class);
            startActivity(startIntent);
            finish();
        } else {
            Util.MianActivityDestoryed = false;
            finish();
        }


    }
}
