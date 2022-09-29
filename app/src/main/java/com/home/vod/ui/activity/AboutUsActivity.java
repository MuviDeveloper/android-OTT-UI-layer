package com.home.vod.ui.activity;

import static com.home.vod.preferences.LanguagePreference.ABOUT_US;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_ABOUT_US;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_NO_DATA;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_NO_INTERNET_CONNECTION;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_SELECTED_LANGUAGE_CODE;
import static com.home.vod.preferences.LanguagePreference.NO_DATA;
import static com.home.vod.preferences.LanguagePreference.NO_INTERNET_CONNECTION;
import static com.home.vod.preferences.LanguagePreference.SELECTED_LANGUAGE_CODE;
import static com.home.vod.util.Constant.authTokenStr;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.home.apisdk.apiController.AboutUsAsync;
import com.home.apisdk.apiModel.AboutUsInput;
import com.home.vod.R;
import com.home.vod.handlers.ToolbarTitleHandler;
import com.home.vod.helper.LocaleLanguageHelper;
import com.home.vod.network.NetworkStatus;
import com.home.vod.preferences.LanguagePreference;
import com.home.vod.preferences.PreferenceManager;
import com.home.vod.ui.widgets.ProgressBarHandler;

import java.nio.charset.StandardCharsets;

public class AboutUsActivity extends AppCompatActivity implements AboutUsAsync.AboutUsListener {

    public ImageView toolbarimage;
    public Toolbar mActionBarToolbar;
    private TextView noInternetTextView;
    private RelativeLayout noInternet;
    private ToolbarTitleHandler toolbarTitleHandler;
    private ProgressBar progresBar;
    private WebView webView;
    private ProgressBarHandler pDialog;
    private AboutUsAsync asyncAboutUS;
    private LanguagePreference languagePreference;
    private PreferenceManager preferenceManager;

    private String about;
    private boolean returnValue = false;
    private String strpermalink = "";

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
        setContentView(R.layout.activity_about_us);

        languagePreference = LanguagePreference.getLanguagePreference(this);
        preferenceManager = PreferenceManager.getPreferenceManager(this);

        noInternet = findViewById(R.id.noInternet);
        progresBar = findViewById(R.id.progress_bar);
        noInternetTextView = findViewById(R.id.noInternetTextView);
        noInternetTextView.setText(languagePreference.getTextofLanguage(NO_INTERNET_CONNECTION, DEFAULT_NO_INTERNET_CONNECTION));
        noInternet.setVisibility(View.GONE);
        toolbarimage = (ImageView) findViewById(R.id.toolbarimage);
        mActionBarToolbar = findViewById(R.id.toolbar);
        toolbarTitleHandler = new ToolbarTitleHandler(this);

        Intent in = getIntent();
        Bundle b = in.getExtras();

        if (b != null) {
            String title = (String) b.get("title");
            strpermalink = (String) b.get("permalink");
            mActionBarToolbar.setTitle(title);
        }
        mActionBarToolbar.setTitleTextColor(getResources().getColor(R.color.toolbarTitleColor));
        setSupportActionBar(mActionBarToolbar);

        /*
         * @Desc: This for setting up the toolbar back arrow icon change
         * */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Configuration config = getResources().getConfiguration();
            if (config.getLayoutDirection() == View.LAYOUT_DIRECTION_RTL) {
                //in Right To Left layout
                mActionBarToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_action_arrow_right));
            } else {
                mActionBarToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
            }

        } else {
            mActionBarToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
        }
        mActionBarToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        setIdToActionBarBackButton(mActionBarToolbar);

        mActionBarToolbar.setTitleTextColor(getResources().getColor(R.color.toolbarTitleColor));


        webView = findViewById(R.id.aboutUsWebView);
        webView.setBackgroundColor(getResources().getColor(R.color.about_us_bg_color));
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAppCacheEnabled(true);

        webView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView webView, int progress) {
                progresBar.setVisibility(View.VISIBLE);
                if (progress == 100) {
                    progresBar.setVisibility(View.GONE);
                }
            }
        });

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                // Handle the error
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView webView, String url) {
                webView.loadUrl(url);
                return true;
            }
        });


        if (NetworkStatus.getInstance().isConnected(this)) {
            AboutUsInput aboutUsInput = new AboutUsInput();
            aboutUsInput.setAuthToken(authTokenStr);
            aboutUsInput.setLang_code(languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, DEFAULT_SELECTED_LANGUAGE_CODE));
            aboutUsInput.setPermalink(strpermalink);
            aboutUsInput.setCountryCode(preferenceManager.getCountryCodeFromPref());
            asyncAboutUS = new AboutUsAsync(aboutUsInput, this, this);
            asyncAboutUS.execute();
        } else {
            noInternet.setVisibility(View.VISIBLE);
        }


        webView.setFocusableInTouchMode(true);
        webView.requestFocus();


        webView.setOnKeyListener((v, keyCode, event) -> {
            if (event.getAction() == KeyEvent.ACTION_DOWN) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {

                    if (webView.canGoBack()) {
                        webView.goBack();
                        returnValue = true;
                    } else {
                        final Intent startIntent = new Intent(AboutUsActivity.this, SettingsActivity.class);


                        runOnUiThread(() -> {
                            startIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                            startActivity(startIntent);
                            finish();

                        });
                    }
                }
            }
            return returnValue;
        });

    }


    @Override
    public void onAboutUsPreExecuteStarted() {
        pDialog = new ProgressBarHandler(this);
        pDialog.show();
    }

    @Override
    public void onAboutUsPostExecuteCompleted(int status, String about) {

        try {
            if (pDialog != null && pDialog.isShowing()) {
                pDialog.hide();
                pDialog = null;
            }
        } catch (IllegalArgumentException ex) {

        }

        progresBar.setVisibility(View.GONE);
        if (status == 200) {

            try {
                int color = getResources().getColor(R.color.aboutustextcolor);
                String aboutUSTextColor = "#" + Integer.toHexString(color & 0x00FFFFFF);
                String text = "<html><head>"
                        + "<style type=\"text/css\" >body{color:" + aboutUSTextColor + ";}"
                        + "</style></head>"
                        + "<body style >"
                        + about
                        + "</body></html>";

                String base64 = android.util.Base64.encodeToString(text.getBytes(StandardCharsets.UTF_8), android.util.Base64.DEFAULT);
                webView.loadData(base64, "text/html; charset=utf-8", "base64");

                webView.setBackgroundColor(getResources().getColor(R.color.about_us_bg_color));
                webView.getSettings().setJavaScriptEnabled(true);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {

            noInternetTextView.setText(languagePreference.getTextofLanguage(NO_DATA, DEFAULT_NO_DATA));
            noInternet.setVisibility(View.VISIBLE);
        }
    }


    private void setIdToActionBarBackButton(Toolbar mActionBarToolbar) {
        for (int i = 0; i < mActionBarToolbar.getChildCount(); i++) {
            View v = mActionBarToolbar.getChildAt(i);
            if (v instanceof ImageButton) {
                ImageButton b = (ImageButton) v;
                b.setId(R.id.menu);

            } else if (v instanceof TextView) {
                TextView t = (TextView) v;
                if (t.getText().toString().equalsIgnoreCase(languagePreference.getTextofLanguage(ABOUT_US, DEFAULT_ABOUT_US)))
                    t.setId(R.id.page_title_about_us);
                else
                    t.setId(R.id.page_title_footer_menu);
            }
        }

    }
}
