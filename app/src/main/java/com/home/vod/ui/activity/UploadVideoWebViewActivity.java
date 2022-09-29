package com.home.vod.ui.activity;

import static com.home.vod.preferences.LanguagePreference.ALERT;
import static com.home.vod.preferences.LanguagePreference.BUTTON_OK;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_ALERT;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_BUTTON_OK;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_OOPS_SOMETHING_WENT_WRONG;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_SUCCESS;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_UPLOAD_VIDEO;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_UPLOAD_VIDEO_COMPLETE_MESSAGE;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_UPLOAD_VIDEO_INTERUPT_MESSAGE;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_VIDEO_UPLOADING_CANCELLED;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_YES;
import static com.home.vod.preferences.LanguagePreference.NO;
import static com.home.vod.preferences.LanguagePreference.OOPS_SOMETHING_WENT_WRONG;
import static com.home.vod.preferences.LanguagePreference.SUCCESS;
import static com.home.vod.preferences.LanguagePreference.UPLOAD_VIDEO;
import static com.home.vod.preferences.LanguagePreference.UPLOAD_VIDEO_COMPLETE_MESSAGE;
import static com.home.vod.preferences.LanguagePreference.UPLOAD_VIDEO_INTERUPT_MESSAGE;
import static com.home.vod.preferences.LanguagePreference.VIDEO_UPLOADING_CANCELLED;
import static com.home.vod.preferences.LanguagePreference.YES;
import static com.home.vod.util.Constant.authTokenStr;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.home.vod.R;
import com.home.vod.handlers.ToolbarTitleHandler;
import com.home.vod.preferences.LanguagePreference;
import com.home.vod.preferences.PreferenceManager;
import com.home.vod.ui.widgets.ProgressBarHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.List;

public class UploadVideoWebViewActivity extends AppCompatActivity {


    public Toolbar mActionBarToolbar;
    public ToolbarTitleHandler toolbarTitleHandler;
    private WebView webView;
    PreferenceManager preferenceManager;
    LanguagePreference languagePreference;
    private Uri mCapturedImageURI;
    public final int REQUEST_SELECT_FILE = 5;
    public final int FILECHOOSER_RESULTCODE = 51;
    private ValueCallback<Uri> mUploadMessage;
    private ValueCallback<Uri[]> uploadMessage;
    String videoPath;
    private boolean uploadVideoInteruptionStatus = false;
    ProgressBarHandler pDialog;
    AlertDialog.Builder mDialogAlert;
    private boolean firstTime = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_video_web_view);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 11);
        languagePreference = LanguagePreference.getLanguagePreference(this);
        preferenceManager = PreferenceManager.getPreferenceManager(this);
        mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar);
        /*Set Toolbar*/
        toolbarTitleHandler = new ToolbarTitleHandler(this);
        mActionBarToolbar.setTitle(languagePreference.getTextofLanguage(UPLOAD_VIDEO, DEFAULT_UPLOAD_VIDEO));
        mActionBarToolbar.setTitleTextColor(getResources().getColor(R.color.toolbarTitleColor));
        setSupportActionBar(mActionBarToolbar);
        mActionBarToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
        mActionBarToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        setIdToActionBarBackButton(mActionBarToolbar);
        webView = findViewById(R.id.webview);
        if (getIntent().hasExtra("video_type")) {
            videoPath = getIntent().getStringExtra("upload_video_path");
        } else {
            videoPath = getIntent().getStringExtra("upload_video_path") + authTokenStr;
        }

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setAllowFileAccessFromFileURLs(true);
        webView.setWebChromeClient(new MyWebChromeClient());


        webView.evaluateJavascript("javascript:myTestFunction();", new ValueCallback<String>() {
            @Override
            public void onReceiveValue(String s) {
                // Do what you want with the return value
            }
        });
        webView.addJavascriptInterface(new WebAppInterface(UploadVideoWebViewActivity.this), "Android");
        webView.setWebViewClient(new CustomWebViewClient());
        webView.loadUrl(videoPath);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SELECT_FILE) {
            if (uploadMessage == null) return;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                uploadMessage.onReceiveValue(WebChromeClient.FileChooserParams.parseResult(resultCode, data));
            }
            uploadMessage = null;
        } else if (requestCode == FILECHOOSER_RESULTCODE) {
            if (null == mUploadMessage)
                return;
            // Use MainActivity.RESULT_OK if you're implementing WebView inside Fragment
            // Use RESULT_OK only if you're implementing WebView inside an Activity
            Uri result = data == null || resultCode != MainActivity.RESULT_OK ? null : data.getData();
            mUploadMessage.onReceiveValue(result);
            mUploadMessage = null;
        }
    }


    public class WebAppInterface {
        Context mContext;

        /**
         * Instantiate the interface and set the context
         */
        WebAppInterface(Context c) {
            mContext = c;
        }

        /**
         * Show a toast from the web page
         */
        @JavascriptInterface
        @SuppressWarnings("unused")
        public void showUploadStatus(String response) {

            if (response != null) {
                try {

                    if (response != null) {
                        JSONObject object = new JSONObject(response);
                        int code = Integer.parseInt(object.optString("code"));
                        if (code == 200) {
                            //if code == 200 video uploading is completed
                            showWarningMessage(languagePreference.getTextofLanguage(SUCCESS, DEFAULT_SUCCESS), languagePreference.getTextofLanguage(UPLOAD_VIDEO_COMPLETE_MESSAGE, DEFAULT_UPLOAD_VIDEO_COMPLETE_MESSAGE));
                        } else if (code == 201) {
                            //if code == 201 video uploading is started
                            uploadVideoInteruptionStatus = true;
                        } else if (code == 202) {
                            //if code == 202 video uploading is cancelled
                            uploadVideoInteruptionStatus = false;
                            showCancelWarningMessage(languagePreference.getTextofLanguage(ALERT, DEFAULT_ALERT), languagePreference.getTextofLanguage(VIDEO_UPLOADING_CANCELLED, DEFAULT_VIDEO_UPLOADING_CANCELLED));
                        }
                    }


                } catch (JSONException e) {
                    Log.v("Exception", e.getMessage());
                    e.printStackTrace();
                }

            }
        }
    }


    private class CustomWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            webView.loadUrl(url);
            return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            if(firstTime){
                pDialog = new ProgressBarHandler(UploadVideoWebViewActivity.this);
                pDialog.show();
            }

        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            //   int color = UploadVideoWebViewActivity.this.getResources().getColor(R.color.button_background);
            //  String buttonColor = "#" + Integer.toHexString(color & 0x00FFFFFF);
            injectCSS();
            if (pDialog != null && pDialog.isShowing()) {
                pDialog.hide();
                firstTime = false;
            }

        }
    }

    private class MyWebChromeClient extends WebChromeClient {
        // For Lollipop 5.0+ Devices
        public boolean onShowFileChooser(WebView mWebView, ValueCallback<Uri[]> filePathCallback, WebChromeClient.FileChooserParams fileChooserParams) {
            if (uploadMessage != null) {
                uploadMessage.onReceiveValue(null);
                uploadMessage = null;
            }

            uploadMessage = filePathCallback;
            Intent intent = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                intent = fileChooserParams.createIntent();
            }
            try {
                startActivityForResult(intent, REQUEST_SELECT_FILE);
            } catch (ActivityNotFoundException e) {
                uploadMessage = null;
                Toast.makeText(getApplicationContext(), languagePreference.getTextofLanguage(OOPS_SOMETHING_WENT_WRONG, DEFAULT_OOPS_SOMETHING_WENT_WRONG), Toast.LENGTH_LONG).show();
                return false;
            }
            return true;
        }

        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            result.confirm();
            return true;
        }
    }

    @Override
    public void onBackPressed() {
        // super.onBackPressed();
        //Show warning pop-up if there is any interuption occours while uploading the video.
        if (uploadVideoInteruptionStatus) {
            showInteruptMessage(languagePreference.getTextofLanguage(UPLOAD_VIDEO_INTERUPT_MESSAGE, DEFAULT_UPLOAD_VIDEO_INTERUPT_MESSAGE));
        } else {
            Intent intent = new Intent();
            //intent.setFlags(Intent.FLAG_ACTIVITY_FORWARD_RESULT);
            if (!getIntent().hasExtra("video_type")) {
                intent.putExtra("video_upload_status", true);
            }
            setResult(RESULT_OK, intent);
            finish();
        }

    }

    /*
    * @author Subhadarshani
    * Description: This method helps to show pop-up after successfully uploading a video or if the video uploading got cancelled.
    * **/
    public void showWarningMessage(String title, final String message) {
        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(UploadVideoWebViewActivity.this, R.style.MyAlertDialogStyle);
        dlgAlert.setMessage(message);
        dlgAlert.setTitle(title);
        dlgAlert.setPositiveButton(languagePreference.getTextofLanguage(BUTTON_OK, DEFAULT_BUTTON_OK), new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // Do nothing but close the dialog
                dialog.cancel();
                if (message.equalsIgnoreCase(languagePreference.getTextofLanguage(UPLOAD_VIDEO_COMPLETE_MESSAGE, DEFAULT_UPLOAD_VIDEO_COMPLETE_MESSAGE))) {
                    Intent intent = new Intent();
                    intent.putExtra("video_upload_status", true);
                    setResult(RESULT_OK, intent);
                    finish();
                }

            }
        });

        dlgAlert.setCancelable(false);

        dlgAlert.create().show();
    }


    /*
           Kushal- To set id to back button in Action Bar
       */
    private void setIdToActionBarBackButton(Toolbar mActionBarToolbar) {
        for (int i = 0; i < mActionBarToolbar.getChildCount(); i++) {
            View v = mActionBarToolbar.getChildAt(i);
            if (v instanceof ImageButton) {
                ImageButton b = (ImageButton) v;
                b.setId(R.id.back);
                /*try {
                    if (b.getContentDescription().equals("Open")) {
                        b.setId(R.id.drawer_menu);
                    } else {
                        b.setId(R.id.back_btn);
                    }
                }catch (Exception e){
                    b.setId(R.id.back_btn);
                }*/
            } else if (v instanceof TextView) {
                TextView t = (TextView) v;
                if (t != null) {
                    t.setId(R.id.page_title_webview);
                }

            }
        }
    }

    /*
    * DESC;  this method helps to inject our local  css file to remote webview
    * */
    private void injectCSS() {
        try {
            InputStream inputStream = getResources().openRawResource(
                    getResources().getIdentifier("custom",
                            "raw", getPackageName()));
            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
            inputStream.close();
            String encoded = Base64.encodeToString(buffer, Base64.NO_WRAP);
            webView.loadUrl("javascript:(function() {" +
                    "var parent = document.getElementsByTagName('head').item(0);" +
                    "var style = document.createElement('style');" +
                    "style.type = 'text/css';" +
                    // Tell the browser to BASE64-decode the string into your script !!!
                    "style.innerHTML = window.atob('" + encoded + "');" +
                    "parent.appendChild(style)" +
                    "})()");
        } catch (Exception e) {
            e.printStackTrace();


        }

    }

    /*
    * @author Subhadarshani
    * Description: This method helps to show pop-up, if there is any interuption while uploading the video.
    * **/
    private void showInteruptMessage(String message) {

        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(UploadVideoWebViewActivity.this, R.style.MyAlertDialogStyle);
        dlgAlert.setMessage(message);
        dlgAlert.setTitle(languagePreference.getTextofLanguage(ALERT, DEFAULT_ALERT));
        dlgAlert.setPositiveButton(languagePreference.getTextofLanguage(YES, DEFAULT_YES), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        dlgAlert.setNegativeButton(languagePreference.getTextofLanguage(NO, LanguagePreference.DEFAULT_NO), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        // dlgAlert.setPositiveButton(getResources().getString(R.string.yes_str), null);
        dlgAlert.setCancelable(false);
        dlgAlert.create().show();


    }

    public static boolean hasOpenedDialogs(FragmentActivity activity) {
        List<Fragment> fragments = activity.getSupportFragmentManager().getFragments();
        if (fragments != null) {
            for (Fragment fragment : fragments) {
                if (fragment instanceof DialogFragment) {
                    return true;
                }
            }
        }

        return false;
    }
    public void showCancelWarningMessage(String title, final String message) {
        try{
            if(mDialogAlert == null){
                mDialogAlert = new AlertDialog.Builder(UploadVideoWebViewActivity.this, R.style.MyAlertDialogStyle);
                mDialogAlert.setMessage(message);
                mDialogAlert.setTitle(title);
                mDialogAlert.setPositiveButton(languagePreference.getTextofLanguage(BUTTON_OK, DEFAULT_BUTTON_OK), new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing but close the dialog
                        // isAlertDialogShowing = false;
                        dialog.cancel();
                        mDialogAlert = null;
                        if(pDialog!=null && pDialog.isShowing()){
                            pDialog.hide();
                        }

                    }
                });

                mDialogAlert.setCancelable(false);
                mDialogAlert.create().show();
            }
        }catch(Exception e){

        }



        }



}
