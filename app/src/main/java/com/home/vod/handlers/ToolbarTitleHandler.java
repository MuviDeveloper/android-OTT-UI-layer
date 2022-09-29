package com.home.vod.handlers;


import android.app.Activity;
import android.view.View;

import com.home.vod.R;
import com.home.vod.ui.activity.MainActivity;

/**
 * Created by Android on 1/10/2018.
 */

public class ToolbarTitleHandler {
    Activity activity;


    public ToolbarTitleHandler(Activity activity){
        this.activity=activity;
        try {
            if (activity instanceof MainActivity){
                ((MainActivity)activity).getSupportActionBar().setTitle(R.string.app_name);
                ((MainActivity)activity).toolbarimage.setVisibility(View.GONE);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
