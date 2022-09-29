package com.home.vod.ui.fragment;


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
import android.os.Bundle;
import android.text.Html;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.home.apisdk.apiController.AboutUsAsync;
import com.home.apisdk.apiModel.AboutUsInput;
import com.home.vod.R;
import com.home.vod.preferences.LanguagePreference;
import com.home.vod.preferences.PreferenceManager;
import com.home.vod.ui.activity.MainActivity;
import com.home.vod.ui.widgets.ProgressBarHandler;

/**
 * A simple {@link Fragment} subclass.
 */
public class AboutUsFragment extends Fragment implements AboutUsAsync.AboutUsListener {
    private String about;
    private Context context;
    private ProgressBar progresBar;
    private WebView webView;
    private ProgressBarHandler pDialog;
    private AboutUsAsync asyncAboutUS;
    private LanguagePreference languagePreference;
    private PreferenceManager preferenceManager;
    private boolean returnValue = false;
    private TextView noInternetTextView;
    private RelativeLayout noInternet;
    private boolean isNetwork;
    private ImageView imageView;
    private LinearLayout aboutuslayout;
    private String aboutUSTextColor;

    public AboutUsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_about_us, container, false);
        context = getActivity();
        languagePreference = LanguagePreference.getLanguagePreference(context);
        preferenceManager = PreferenceManager.getPreferenceManager(context);
        isNetwork = player.utils.Util.checkNetwork(context);

        noInternet = (RelativeLayout) view.findViewById(R.id.noInternet);
        progresBar = (ProgressBar) view.findViewById(R.id.progress_bar);
        noInternetTextView = (TextView) view.findViewById(R.id.noInternetTextView);
        imageView = (ImageView) view.findViewById(R.id.imageView);
        aboutuslayout = (LinearLayout) view.findViewById(R.id.aboutuslayout);
        noInternetTextView.setText(languagePreference.getTextofLanguage(NO_INTERNET_CONNECTION, DEFAULT_NO_INTERNET_CONNECTION));
        noInternet.setVisibility(View.GONE);


        ((MainActivity) getActivity()).getSupportActionBar().setTitle(Html.fromHtml(getArguments().getString("title")));
        //  set Id to back button and text in Toolabr
        Toolbar toolbar = ((MainActivity) getActivity()).mToolbar;
        setIdToActionBarBackButton(toolbar);
        ((MainActivity) getActivity()).toolbarimage.setVisibility(View.GONE);
        ((MainActivity) getActivity()).mToolbar.setTitleTextColor(getResources().getColor(R.color.toolbarTitleColor));
        webView = (WebView) view.findViewById(R.id.aboutUsWebView);


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


        if (isNetwork) {

            AboutUsInput aboutUsInput = new AboutUsInput();
            aboutUsInput.setAuthToken(authTokenStr);
            aboutUsInput.setLang_code(languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, DEFAULT_SELECTED_LANGUAGE_CODE));
            String strtext = getArguments().getString("item");
            aboutUsInput.setPermalink(strtext);

            aboutUsInput.setCountryCode(preferenceManager.getCountryCodeFromPref());
            asyncAboutUS = new AboutUsAsync(aboutUsInput, this, context);
            asyncAboutUS.execute();
        } else {
            noInternet.setVisibility(View.VISIBLE);
        }

        view.setFocusableInTouchMode(true);
        view.requestFocus();

        webView.setFocusableInTouchMode(true);
        webView.requestFocus();

        webView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {

                        if (webView.canGoBack()) {
                            webView.goBack();
                            returnValue = true;
                        } else {
                            PreferenceManager preferenceManager;
                            preferenceManager = PreferenceManager.getPreferenceManager(getActivity());
                            preferenceManager.setFRAGMENTS_CHANGED("home");
                            final Intent startIntent = new Intent(getActivity(), MainActivity.class);


                            getActivity().runOnUiThread(new Runnable() {
                                public void run() {
                                    startIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                    getActivity().startActivity(startIntent);
                                    getActivity().finish();

                                }
                            });
                        }
                    }
                }
                return returnValue;
            }
        });

        setHasOptionsMenu(true);
        return view;

    }

    private ActionBar getActionBar() {
        return ((AppCompatActivity) getActivity()).getSupportActionBar();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        MenuItem item1, item2, item3;
        item1 = menu.findItem(R.id.option);
        item2 = menu.findItem(R.id.search);
        item1.setVisible(false);
        item2.setVisible(false);
    }

    @Override
    public void onAboutUsPreExecuteStarted() {
        pDialog = new ProgressBarHandler(context);
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
        String bodyData = about;
        if (status == 200) {
            try {
                int color = getActivity().getResources().getColor(R.color.aboutustextcolor);
                aboutUSTextColor = "#" + Integer.toHexString(color & 0x00FFFFFF);
                String text = "<html><head>"
                        + "<style type=\"text/css\" >body{color:" + aboutUSTextColor + ";}"
                        + "</style></head>"
                        + "<body style >"
                        + about
                        + "</body></html>";

                String base64 = android.util.Base64.encodeToString(text.getBytes("UTF-8"), android.util.Base64.DEFAULT);
                webView.loadData(base64, "text/html; charset=utf-8", "base64");

                if (getActivity().getPackageName().equals("com.release.kinoatv")) {
                    webView.loadUrl("https://kinoa.tv/es/penas");
                }

                webView.setBackgroundColor(getResources().getColor(R.color.aboutustestcolor));
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

