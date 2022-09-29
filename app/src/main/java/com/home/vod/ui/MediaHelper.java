package com.home.vod.ui;

import android.content.Context;

import com.home.vod.model.QueueModel;

/**
 * Created by Muvi on 9/1/2017.
 */

public interface MediaHelper {

    void Transporter (QueueModel QueueModel,int position);
    void BottomOptionMenu(Context context ,String SongName, String ArtistName,int Position );

}
