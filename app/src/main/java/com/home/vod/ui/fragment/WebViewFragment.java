package com.home.vod.ui.fragment;

import static com.home.vod.preferences.LanguagePreference.DEFAULT_NO_INTERNET_CONNECTION;
import static com.home.vod.preferences.LanguagePreference.NO_INTERNET_CONNECTION;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.home.vod.R;
import com.home.vod.network.NetworkStatus;
import com.home.vod.preferences.LanguagePreference;
import com.home.vod.preferences.PreferenceManager;
import com.home.vod.ui.activity.MainActivity;
import com.home.vod.ui.widgets.ProgressBarHandler;


public class WebViewFragment extends Fragment {


    private WebView webView;
    private TextView noInternetConnectionTextView;

    private String urlStr;
    private boolean isLastGoback=false;

    private  Resources res;
    private Context context;
    private Activity activity;
    private int mRedirectedCount=0;

    private LanguagePreference languagePreference;
    private PreferenceManager preferenceManager;
    private ProgressBarHandler progressDialog;

    public WebViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context = getActivity();
        setHasOptionsMenu(true);
        res = context.getResources();
        languagePreference = LanguagePreference.getLanguagePreference(context);
        View rootView = inflater.inflate(R.layout.fragment_web_view, container, false);
        preferenceManager = PreferenceManager.getPreferenceManager(getActivity());

        noInternetConnectionTextView = (TextView)rootView.findViewById(R.id.noInternetConnection);
        noInternetConnectionTextView.setText(languagePreference.getTextofLanguage(NO_INTERNET_CONNECTION,DEFAULT_NO_INTERNET_CONNECTION));
        noInternetConnectionTextView.setVisibility(View.GONE);

        urlStr =  getArguments().getString("item");

        ((MainActivity)getActivity()).getSupportActionBar().setTitle(getArguments().getString("title"));

        webView = (WebView) rootView.findViewById(R.id.webView);
        webView.clearFormData();
        webView.clearCache(true);
        webView.clearHistory();
        webView.canGoBack();
        webView.setOnKeyListener(new View.OnKeyListener() {

            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK
                        && event.getAction() == MotionEvent.ACTION_UP
                        && webView.canGoBack()) {
                    webView.goBack();
                    return true;
                } else if (!webView.canGoBack() && !isLastGoback) {
                        isLastGoback=true;
                        final Intent startIntent = new Intent(context, MainActivity.class);
                        preferenceManager.setFRAGMENTS_CHANGED("home");
                        getActivity().runOnUiThread(new Runnable() {
                            public void run() {
                                if (progressDialog != null && progressDialog.isShowing()) {
                                    progressDialog.hide();
                                    progressDialog = null;
                                }
                                startIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                startActivity(startIntent);
                                getActivity().finish();

                            }
                        });
                    }


                return false;
            }
        });
        if (NetworkStatus.getInstance().isConnected(getActivity())){
            startWebView(urlStr);
        }else{
            noInternetConnectionTextView.setVisibility(View.VISIBLE);
        }
        rootView.setFocusableInTouchMode(true);
        rootView.requestFocus();

        rootView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        final Intent startIntent = new Intent(context, MainActivity.class);
                        preferenceManager.setFRAGMENTS_CHANGED("home");
                        getActivity().runOnUiThread(new Runnable() {
                            public void run() {
                                if (progressDialog != null && progressDialog.isShowing()) {
                                    progressDialog.hide();
                                    progressDialog = null;
                                }
                                startIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                startActivity(startIntent);
                                getActivity().finish();

                            }
                        });

                        return true;
                    }
                }
                return false;
            }
        });
        return rootView;
    }



    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        MenuItem item = menu.findItem(R.id.search);
        item.setVisible(false);

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
    }

    private void startWebView(String url) {

        webView.setWebViewClient(new WebViewClient() {

            boolean mIsPageFinished = true;

            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                if (mIsPageFinished) {
                    mRedirectedCount = 0; //clear count
                }

                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                mIsPageFinished = false;
                if (progressDialog != null && progressDialog.isShowing()) {
                    progressDialog.hide();
                    progressDialog = null;
                }else {
                    progressDialog = new ProgressBarHandler(context);
                    progressDialog.show();
                }

            }

            //Show loader on url load
            public void onLoadResource(WebView view, String url) {

            }

            public void onPageFinished(WebView view, String url) {
                mIsPageFinished = true;
                try {
                    if (progressDialog != null && progressDialog.isShowing()) {
                        progressDialog.hide();
                        progressDialog = null;
                    }
                } catch (Exception exception) {
                    noInternetConnectionTextView.setVisibility(View.VISIBLE);
                    exception.printStackTrace();
                }
            }

            @Override
            public void doUpdateVisitedHistory(WebView view, String url, boolean isReload) {
                super.doUpdateVisitedHistory(view, url, isReload);

                if(!mIsPageFinished){
                    mRedirectedCount++;
                }
            }
        });


        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setSupportMultipleWindows(true);

        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setSaveFormData(true);
        webView.getSettings().setAllowContentAccess(true);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setAllowFileAccessFromFileURLs(true);
        webView.getSettings().setAllowUniversalAccessFromFileURLs(true);
        webView.getSettings().setSupportZoom(true);

        webView.setWebViewClient(new WebViewClient());
        webView.setClickable(true);
        webView.setWebChromeClient(new WebChromeClient());

        webView.loadUrl(url);

    }
    public void onBackPressed1() {
        if(mRedirectedCount>0){

            while(mRedirectedCount>0){

                webView.goBack();
                mRedirectedCount--;
            }
            mRedirectedCount = 0; //clear
        } else {
            getActivity().finish();
            System.exit(0);

        }


    }
    @Override
    public void onResume() {
        super.onResume();
        if (!NetworkStatus.getInstance().isConnected(activity)) {
            Toast.makeText(activity, languagePreference.getTextofLanguage(NO_INTERNET_CONNECTION,
                    DEFAULT_NO_INTERNET_CONNECTION), Toast.LENGTH_LONG).show();

        }

    }
}
