package com.home.vod.util;

/**
 * Created by MUVI on 07-Feb-18.
 */

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.home.vod.R;

/**
 * Created by User on 10-02-2017.
 */
public class SearchProgressHandler {
    RelativeLayout bar;
    ViewGroup layout;

    public SearchProgressHandler(Context context) {
        layout = (ViewGroup) ((Activity) context).findViewById(android.R.id.content).getRootView();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        bar = (RelativeLayout) inflater.inflate(R.layout.progress_bar_layout, null);
        layout.addView(bar);
        bar.setVisibility(View.GONE);

    }

    public void show() {

        bar.setVisibility(View.VISIBLE);
        layout.bringToFront();
        bar.bringToFront();

    }
    public boolean isShowing() {
        return layout.indexOfChild(bar) != -1;
    }
    public void hide() {
        bar.setVisibility(View.GONE);
    }
}
