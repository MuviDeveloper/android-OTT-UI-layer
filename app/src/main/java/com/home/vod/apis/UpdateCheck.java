package com.home.vod.apis;

import static com.home.vod.util.Util.playstore_version;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class UpdateCheck extends AsyncTask<String, Void, String> {
    String packageName;

    @Override
    protected String doInBackground(String... params) {
        packageName = params[0];
        String playStoreVersion = getPlayStoreAppVersion(packageName);
        return playStoreVersion;
    }

    @Override
    protected void onPostExecute(String playstoreversion) {
        super.onPostExecute(playstoreversion);
        playstore_version = playstoreversion;
    }

    private String getPlayStoreAppVersion(String appPackageName) {
        String appUrlString = "https://play.google.com/store/apps/details?id=" + appPackageName;
        final String currentVersion_PatternSeq = "<div[^>]*?>Current\\sVersion</div><span[^>]*?>(.*?)><div[^>]*?>(.*?)><span[^>]*?>(.*?)</span>";
        final String appVersion_PatternSeq = "htlgb\">([^<]*)</s";
        String playStoreAppVersion = null;

        BufferedReader inReader = null;
        URLConnection uc = null;
        StringBuilder urlData = new StringBuilder();

        try {
            final URL url = new URL(appUrlString);
            uc = url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (uc == null) {
            return null;
        }
        uc.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6");
        try {
            inReader = new BufferedReader(new InputStreamReader(uc.getInputStream()));
            if (null != inReader) {
                String str = "";
                while ((str = inReader.readLine()) != null) {
                    urlData.append(str);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Get the current version pattern sequence
        String versionString = getAppVersion(currentVersion_PatternSeq, urlData.toString());
        if (null == versionString) {
            return null;
        } else {
            // get version from "htlgb">X.X.X</span>
            playStoreAppVersion = getAppVersion(appVersion_PatternSeq, versionString);
        }

        return playStoreAppVersion;
    }

    private String getAppVersion(String patternString, String inputString) {
        try {
            //Create a pattern
            Pattern pattern = Pattern.compile(patternString);
            if (null == pattern) {
                return null;
            }

            //Match the pattern string in provided string
            Matcher matcher = pattern.matcher(inputString);
            if (null != matcher && matcher.find()) {
                return matcher.group(1);
            }

        } catch (PatternSyntaxException ex) {

            ex.printStackTrace();
        }

        return null;
    }

}