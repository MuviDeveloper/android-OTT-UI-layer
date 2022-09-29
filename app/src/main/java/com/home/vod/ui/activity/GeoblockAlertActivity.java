package com.home.vod.ui.activity;

import static com.home.vod.preferences.LanguagePreference.BUTTON_OK;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_BUTTON_OK;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_GEO_BLOCKED_ALERT;
import static com.home.vod.preferences.LanguagePreference.GEO_BLOCKED_ALERT;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.home.vod.R;
import com.home.vod.preferences.LanguagePreference;

public class GeoblockAlertActivity extends AppCompatActivity {

    private TextView msg;
    private Button btnRetry;
    private LanguagePreference languagePreference;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_geoblock_alert);

        languagePreference = LanguagePreference.getLanguagePreference((this));


        msg = findViewById(R.id.msg);
        btnRetry = findViewById(R.id.btnRetry);

        msg.setText(languagePreference.getTextofLanguage(GEO_BLOCKED_ALERT, DEFAULT_GEO_BLOCKED_ALERT));
        btnRetry.setText(languagePreference.getTextofLanguage(BUTTON_OK, DEFAULT_BUTTON_OK));


        btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}
