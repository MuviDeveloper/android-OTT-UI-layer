package com.home.vod.network;

import static com.home.vod.preferences.LanguagePreference.CONNECTED_TO_MOBILE_NETWORK;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_CONNECTED_TO_MOBILE_NETWORK;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.home.vod.preferences.LanguagePreference;

/**
 * Created by Debashish
 */

public class NetworkChangeReceiver extends BroadcastReceiver {

    LanguagePreference languagePreference;
    String msg="";
    @Override
    public void onReceive(Context context, Intent intent) {


        try{
            languagePreference = LanguagePreference.getLanguagePreference(context);
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            if (activeNetwork != null) { // connected to the internet
                if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                    // connected to the mobile provider's data plan
                    msg=languagePreference.getTextofLanguage(CONNECTED_TO_MOBILE_NETWORK, DEFAULT_CONNECTED_TO_MOBILE_NETWORK);
                    Toast.makeText(context,  msg, Toast.LENGTH_LONG).show();
                }
            }
        }catch (Exception e){e.printStackTrace();}

    }
}
