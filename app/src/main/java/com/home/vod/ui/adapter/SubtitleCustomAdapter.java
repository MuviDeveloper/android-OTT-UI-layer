package com.home.vod.ui.adapter;

import static android.content.Context.MODE_PRIVATE;
import static com.home.vod.util.Util.SELECTED_SUBTITLE;
import static com.home.vod.util.Util.SELECTED_SUBTITLE_POSITION;
import static com.home.vod.util.Util.SUBTITLE_PREF;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.home.vod.R;

import java.util.ArrayList;

public class SubtitleCustomAdapter extends RecyclerView.Adapter<SubtitleCustomAdapter.ViewHolder> {
    public Context mContext;
    public static ArrayList<String> cast_play_subtitle_url = new ArrayList<>();
    public static ArrayList<String> cast_play_subtitle_name = new ArrayList<>();


    public SubtitleCustomAdapter(Context mContext, ArrayList<String> cast_play_subtitle_url , ArrayList<String> cast_play_subtitle_name ) {
        this.mContext = mContext;
        SubtitleCustomAdapter.cast_play_subtitle_url = cast_play_subtitle_url;
        SubtitleCustomAdapter.cast_play_subtitle_name = cast_play_subtitle_name;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.subtitle_recycler_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        
        holder.language.setText(cast_play_subtitle_name.get(position));


        if(SELECTED_SUBTITLE_POSITION == -1){
            SELECTED_SUBTITLE_POSITION = 1;
            final SharedPreferences.Editor editor = mContext.getSharedPreferences(SUBTITLE_PREF, MODE_PRIVATE).edit();
            editor.putInt(SELECTED_SUBTITLE, SELECTED_SUBTITLE_POSITION);
            editor.apply();

        }

        if(SELECTED_SUBTITLE_POSITION == position){
            holder.imageView.setImageResource(R.drawable.selected);
        }else{
            holder.imageView.setImageResource(R.drawable.unselected);
        }


        holder.main_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SELECTED_SUBTITLE_POSITION  = position;
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return cast_play_subtitle_url.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView language;
        public ImageView imageView;
        public RelativeLayout main_ll;

        public ViewHolder(View v) {
            super(v);
            imageView = v.findViewById(R.id.image);
            language = v.findViewById(R.id.language);
            main_ll = v.findViewById(R.id.main_ll);

            imageView.setClickable(false);

        }
    }


}

