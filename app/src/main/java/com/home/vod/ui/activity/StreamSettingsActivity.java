package com.home.vod.ui.activity;

/**
 * @author Debashish
 */

import static com.home.vod.preferences.LanguagePreference.ALWAYS_PLAY_BEST_RESOLUTION;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_ALWAYS_PLAY_BEST_RESOLUTION;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_NOTIFY_WHEN_STREAMING_ON_MOBILE_DATA;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_STREAM;
import static com.home.vod.preferences.LanguagePreference.NOTIFY_WHEN_STREAMING_ON_MOBILE_DATA;
import static com.home.vod.preferences.LanguagePreference.STREAM;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;

import com.home.vod.R;
import com.home.vod.handlers.ToolbarTitleHandler;
import com.home.vod.helper.LocaleLanguageHelper;
import com.home.vod.preferences.LanguagePreference;
import com.home.vod.preferences.PreferenceManager;

public class StreamSettingsActivity extends AppCompatActivity {

    public Toolbar mActionBarToolbar;
    public ImageView toolbarimage;
    public ToolbarTitleHandler toolbarTitleHandler;
    LanguagePreference languagePreference;
    PreferenceManager preferenceManager;
    TextView txtAlwaysPlayBestResolution,txtNotifyWhenStreamingOnMobileData;
    SwitchCompat switchAlwaysPlayBestResolution,switchNotifyWhenStreamingOnMobileData;
    String alwaysPlayBestResolution="1";
    String notifyStreamingOnMobileData="1";

    /*
     * @Desc: This override method is used to called before the onCreate(),
     *       which help the resources to update Activity.
     * */
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleLanguageHelper.onAttach(base));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stream_settings);

        languagePreference = LanguagePreference.getLanguagePreference(this);
        preferenceManager = PreferenceManager.getPreferenceManager(this);
        toolbarimage = (ImageView) findViewById(R.id.toolbarimage);

        mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar);
        /*Set Toolbar*/
        toolbarTitleHandler = new ToolbarTitleHandler(this);
        mActionBarToolbar.setTitle(languagePreference.getTextofLanguage(STREAM, DEFAULT_STREAM));
        mActionBarToolbar.setTitleTextColor(getResources().getColor(R.color.toolbarTitleColor));
        setSupportActionBar(mActionBarToolbar);
        /*
         * @Desc: This for setting up the toolbar back arrow icon change
         * */
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            Configuration config = getResources().getConfiguration();
            if(config.getLayoutDirection() == View.LAYOUT_DIRECTION_RTL) {
                //in Right To Left layout
                mActionBarToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_action_arrow_right));
            }else {
                mActionBarToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
            }

        }else {
            mActionBarToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
        }
        mActionBarToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        // To set Id to action bar back button
        setIdToActionBarBackButton(mActionBarToolbar);

        switchAlwaysPlayBestResolution=findViewById(R.id.resolution_toggle);
        switchAlwaysPlayBestResolution.setOnCheckedChangeListener(bestResolutionListener);

        alwaysPlayBestResolution=preferenceManager.getPlayBestResolutionFromPref();
        //Toast.makeText(this, alwaysPlayBestResolution, Toast.LENGTH_SHORT).show();

        if(alwaysPlayBestResolution.equals("1")){
            switchAlwaysPlayBestResolution.setChecked(true);
        }
        else{
            switchAlwaysPlayBestResolution.setChecked(false);
        }

        switchNotifyWhenStreamingOnMobileData=findViewById(R.id.mobile_data_toggle);
        switchNotifyWhenStreamingOnMobileData.setOnCheckedChangeListener(mobileDataListener);

        notifyStreamingOnMobileData=preferenceManager.getNotifyStreamingOnMobileDataFromPref();
        //Toast.makeText(this, notifyStreamingOnMobileData, Toast.LENGTH_SHORT).show();

        if(notifyStreamingOnMobileData.equals("1")){
            switchNotifyWhenStreamingOnMobileData.setChecked(true);
        }
        else{
            switchNotifyWhenStreamingOnMobileData.setChecked(false);
        }


        txtAlwaysPlayBestResolution=findViewById(R.id.best_resolution);
        txtAlwaysPlayBestResolution.setText(languagePreference.getTextofLanguage(ALWAYS_PLAY_BEST_RESOLUTION, DEFAULT_ALWAYS_PLAY_BEST_RESOLUTION));

        txtNotifyWhenStreamingOnMobileData=findViewById(R.id.notify_mobile_data);
        txtNotifyWhenStreamingOnMobileData.setText(languagePreference.getTextofLanguage(NOTIFY_WHEN_STREAMING_ON_MOBILE_DATA, DEFAULT_NOTIFY_WHEN_STREAMING_ON_MOBILE_DATA));
    }


    private final CompoundButton.OnCheckedChangeListener bestResolutionListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if(isChecked) {

                preferenceManager.setPlayBestResolutiontoPref("1");
            }
            else {
                preferenceManager.setPlayBestResolutiontoPref("0");
            }
        }
    };

    private final CompoundButton.OnCheckedChangeListener mobileDataListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if(isChecked) {

                preferenceManager.setNotifyStreamingOnMobileDatatoPref("1");
            }
            else {
                preferenceManager.setNotifyStreamingOnMobileDatatoPref("0");
            }
        }
    };

    private void setIdToActionBarBackButton(Toolbar mActionBarToolbar) {
        for (int i = 0; i < mActionBarToolbar.getChildCount(); i++) {
            View v = mActionBarToolbar.getChildAt(i);
            if (v instanceof ImageButton) {
                ImageButton b = (ImageButton) v;
                b.setId(R.id.back);

            }
        }
    }

}
