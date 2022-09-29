package com.home.vod.ui;

import static com.home.vod.util.Util.QUEUE_ARRAY;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.home.vod.R;
import com.home.vod.model.QueueModel;
import com.home.vod.preferences.PreferenceManager;
import com.home.vod.ui.activity.AudioContentDetailsActivity;
import com.home.vod.util.Util;

import java.util.ArrayList;


/**
 * Created by Muvi on 9/1/2017.
 */
public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.ViewHolder> {
    Context mContext;
    static int prevPosition = -1;
    static int adaptorPosition = -1;
    MediaHelper mediaHelper = new AudioContentDetailsActivity();
    MediaHelper mediaHelperListner;

    PreferenceManager preferenceManager;

    public PlayerAdapter(Context context) {
        this.mContext = context;
        mediaHelperListner = (MediaHelper) context;
        preferenceManager = PreferenceManager.getPreferenceManager(context);
    }

    @Override
    public PlayerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.player_list_data_item, parent, false);
        return new PlayerAdapter.ViewHolder(view);
    }

    public ArrayList<QueueModel> getQueueArray() {
        return QUEUE_ARRAY;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.list_songName.setText(QUEUE_ARRAY.get(position).getSongName());
        holder.list_artistName.setText(QUEUE_ARRAY.get(position).getArtist_Name());

       if(preferenceManager.getCurrentAudioPosition()==position) {
           holder.list_songName.setTextColor(mContext.getResources().getColor(R.color.button_background));
           holder.list_artistName.setTextColor(mContext.getResources().getColor(R.color.button_background));
       }

        String link = QUEUE_ARRAY.get(position).getAlbumArt();

        if (Util.is_contain_noimage(link)) {
            holder.list_albumart.setBackgroundColor(mContext.getResources().getColor(R.color.no_image_bg_color));
        } else {
            Glide.with(mContext)
                    .load(link)
                    .thumbnail(0.1f)
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            holder.list_albumart.setBackgroundColor(mContext.getResources().getColor(R.color.no_image_bg_color));
                            return true;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            holder.list_albumart.setBackgroundColor(mContext.getResources().getColor(R.color.transparent));
                            return false;
                        }
                    })
                    .into(holder.list_albumart);
        }

        holder.listsong_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // used to store the current track position
                preferenceManager.setCurrentAudioPosition(position);
                mediaHelperListner.Transporter(QUEUE_ARRAY.get(position), position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return QUEUE_ARRAY.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView list_songName;
        private TextView list_artistName;
        private ImageView list_albumart, list_option_menu;
        private RelativeLayout list_view, downloadLayout;
        private RelativeLayout listsong_layout;

        public ViewHolder(View view) {
            super(view);

            this.listsong_layout = view.findViewById(R.id.list_view);
            this.list_songName = view.findViewById(R.id.list_songName);
            Typeface list_songName_tf = Typeface.createFromAsset(mContext.getAssets(), mContext.getString(R.string.regular_fonts));
            list_songName.setTypeface(list_songName_tf);


            this.list_artistName = view.findViewById(R.id.list_artistName);
            Typeface list_artistName_tf = Typeface.createFromAsset(mContext.getAssets(), mContext.getString(R.string.regular_fonts));
            list_artistName.setTypeface(list_artistName_tf);

            this.list_albumart = view.findViewById(R.id.list_albumart);


            this.list_option_menu = view.findViewById(R.id.list_option_menu);
            list_option_menu.setVisibility(View.GONE);

        }
    }


    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
}
