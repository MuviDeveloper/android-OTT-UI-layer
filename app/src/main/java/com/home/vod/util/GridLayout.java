package com.home.vod.util;

import android.content.Context;

import com.home.vod.R;

public class GridLayout {

    /**
     * Get the layout to be inflated in recycler grid view according to video height and width
     * @param videoWidth
     * @param videoHeight
     * @param density
     * @return layout to be inflated
     */

    // IN CASE IF FOUND NOT WORKING FOR WATCH HISTORY, PASS ACTIVITY AND CHECK INSTANCE AND RETURN WATCH HISTORY RELATED LAYOUTS (ADD <watch_history_> before all layouts below)
    public static int getGridLayout(int videoWidth, int videoHeight, float density){
        int layout;
        if (videoWidth > videoHeight) {
            if (density >= 3.5 && density <= 4.0) {
                layout = R.layout.nexus_videos_grid_layout_land;
            } else {
                layout = R.layout.videos_280_grid_layout;
            }
        } else {
            if (density >= 3.5 && density <= 4.0) {
                layout = R.layout.nexus_videos_grid_layout;
            } else {
                layout = R.layout.videos_grid_layout;

            }
        }
        return layout;
    }
    public static int getLayoutForMyUpload(int videoWidth, int videoHeight, float density){
        int layout;
        if (videoWidth > videoHeight) {
            if (density >= 3.5 && density <= 4.0) {
                layout = R.layout.nexus_myupload_list_videos_grid_layout_land;
            } else {
                layout = R.layout.videos_280_myupload_list_grid_layout;
            }
        } else {
            if (density >= 3.5 && density <= 4.0) {
                layout = R.layout.nexus_videos_myupload_list_grid_layout;
            } else {
                layout = R.layout.videos_grid_myupload_list_layout;

            }
        }
        return layout;
    }


    /*
      Season layout selection.
     */
    public static int getSeasonGridLayout(Context mContext, int videoWidth, int videoHeight) {
        int layout;
        if (videoWidth > videoHeight) {
            if (!Util.isTablet(mContext)){
                layout = R.layout.season_land_layout;
            }else{
                layout = R.layout.season_tab_land_layout;
            }

        } else {

            if (!Util.isTablet(mContext)){
                layout = R.layout.season_vertical_layout;
            }else{
                layout = R.layout.season_vertical_tab_layout;
            }
        }
        return layout;
    }
}
