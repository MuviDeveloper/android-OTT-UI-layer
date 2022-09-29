package com.home.vod.handlers;

import android.content.Intent;

import com.home.vod.ui.activity.LoginActivity;


/**
 * Created by Android on 9/21/2017.
 */

public class LoginHandler {
    private LoginActivity context;

    public LoginHandler(LoginActivity context) {
        this.context = context;
    }


    public void sendBroadCast() {
        Intent Sintent = new Intent("LOGIN_SUCCESS");
        context.sendBroadcast(Sintent);
    }

}
