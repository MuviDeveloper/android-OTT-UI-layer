package com.home.vod.ui.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.home.vod.R;
import com.home.vod.preferences.PreferenceManager;

public class SuccessMessageActivity extends AppCompatActivity {
    String message,name;
    TextView name_return,thnaksTV,message_tv;
    ImageView close_pop;
    LinearLayout custom_layout;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.boma_contactus_message_dialog);
         name_return = findViewById(R.id.name_return);
         close_pop = findViewById(R.id.close_pop);
         thnaksTV = findViewById(R.id.thnaksTV);
         custom_layout = findViewById(R.id.custom_layout);
         message_tv = findViewById(R.id.message);
         Intent intent = getIntent();
         message = intent.getStringExtra("message");
         name = intent.getStringExtra("name");
         inits();
    }

    private void inits() {
        custom_layout.setVisibility(View.VISIBLE);
        Typeface submitTypeface = Typeface.createFromAsset(getAssets(),getResources().getString(R.string.regular_fonts));
        Typeface regularTypeface = Typeface.createFromAsset(getAssets(),getResources().getString(R.string.light_fonts));
        name_return.setTypeface(submitTypeface);
        name_return.setText(name);
        thnaksTV.setTypeface(submitTypeface);
        thnaksTV.setText("Thanks for logging a new query");
        message_tv.setTypeface(regularTypeface);
        message_tv.setText(message);
        close_pop.setOnClickListener(v-> leavePage());
    }

    private void leavePage() {
        PreferenceManager preferenceManager;
        preferenceManager = PreferenceManager.getPreferenceManager(this);
        preferenceManager.setFRAGMENTS_CHANGED("home");
        final Intent startIntent = new Intent(this, MainActivity.class);
        startIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(startIntent);
        finish();
    }
}
