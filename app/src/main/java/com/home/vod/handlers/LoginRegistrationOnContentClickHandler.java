package com.home.vod.handlers;

import android.app.Activity;
import android.content.Intent;

import com.home.vod.ui.activity.LoginActivity;

/**
 * Created by BISHAL on 09-10-2017.
 */
// the purpose to create this handler is that,in other application from intent it goes to register activitybut in Matas flavour it goes to Loginactivity
public class LoginRegistrationOnContentClickHandler {
    Activity activity;
    public LoginRegistrationOnContentClickHandler(Activity activity){
            this.activity = activity;
    }

    public Intent handleClickOnContent(){
        return new Intent(activity, LoginActivity.class).putExtra("is_a_guest_user","is_a_guest_user");
    }

    public Intent handleClickOnFollowButton(){

        return new Intent(activity, LoginActivity.class);

    }

}
