package com.home.vod.ui.adapter;

import static com.home.vod.preferences.LanguagePreference.BUTTON_OK;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_NO_DATA;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_VIDEO_NOT_PUBLISHED;
import static com.home.vod.preferences.LanguagePreference.NO_DATA;
import static com.home.vod.preferences.LanguagePreference.VIDEO_NOT_PUBLISHED;
import static player.utils.Util.DEFAULT_BUTTON_OK;
import static player.utils.Util.DEFAULT_SORRY;
import static player.utils.Util.SORRY;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.home.vod.R;
import com.home.vod.model.EpisodesListModel;
import com.home.vod.preferences.LanguagePreference;
import com.home.vod.util.FontUtls;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class EpisodesListViewMoreAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private Context context;
    private @LayoutRes int layoutResourceId;
    private LanguagePreference languagePreference;

    private  OnItemClickListener listener;
    private ArrayList<EpisodesListModel> data = new ArrayList<EpisodesListModel>();



    public interface OnItemClickListener {
        void onItemClick(EpisodesListModel item);

    }


    public EpisodesListViewMoreAdapter(Context context, @LayoutRes int layoutResourceId,
                                       ArrayList<EpisodesListModel> data, OnItemClickListener listener) {
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
        this.listener = listener;
        languagePreference = LanguagePreference.getLanguagePreference(context);

    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView episodeTitleTextView;
        public TextView episodeNameTextView;
        public TextView episodeDateTextView;
        public ImageView playButton;

        public ImageView episodeImageView;
        public ViewHolder(View view) {
            super(view);
            episodeTitleTextView = view.findViewById(R.id.episodeTitleTextView);
            episodeNameTextView = view.findViewById(R.id.episodeNameTextView);
            playButton = view.findViewById(R.id.playButton);
            episodeNameTextView.setVisibility(View.GONE);
            episodeDateTextView = view.findViewById(R.id.episodeDateTextView);
            FontUtls.loadFont(context,context.getResources().getString(R.string.regular_fonts),episodeTitleTextView);
            FontUtls.loadFont(context,context.getResources().getString(R.string.regular_fonts),episodeNameTextView);
            FontUtls.loadFont(context,context.getResources().getString(R.string.regular_fonts),episodeDateTextView);

            episodeImageView = view.findViewById(R.id.episodeImageView);
        }

        public void bind(final EpisodesListModel item, final OnItemClickListener listener) {
            episodeTitleTextView.setText(item.getEpisodeTitle());
            episodeNameTextView.setText(item.getEpisodeNumber());
            episodeDateTextView.setText("");

            /*
            * Author:Chitra
            * desc: Visibility of play button depending upon publish status*/

            if(item.getIs_media_published().equals("1")){
                    playButton.setVisibility(View.VISIBLE);
            } else {
                playButton.setVisibility(View.GONE);
            }

                if(episodeDateTextView.getText().toString().matches("") || episodeDateTextView.getText().toString().matches(languagePreference
                        .getTextofLanguage(NO_DATA,DEFAULT_NO_DATA))) {


                    episodeDateTextView.setVisibility(View.GONE);
                }
                String imageId = item.getEpisodeThumbnailImageView();

            if(imageId.matches("") || imageId.matches(languagePreference.getTextofLanguage(NO_DATA,DEFAULT_NO_DATA))){
                episodeImageView.setImageResource(R.drawable.logo);

            }else {
                Picasso.with(context)
                        .load(item.getEpisodeThumbnailImageView()).error(R.drawable.logo).placeholder(R.drawable.logo)
                        .into(episodeImageView);
            }
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(item.getIs_media_published().equals("1")){
                        listener.onItemClick(item);
                    } else {
                        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(context, R.style.MyAlertDialogStyle);
                        dlgAlert.setMessage(languagePreference.getTextofLanguage(VIDEO_NOT_PUBLISHED, DEFAULT_VIDEO_NOT_PUBLISHED));
                        dlgAlert.setTitle(languagePreference.getTextofLanguage(SORRY, DEFAULT_SORRY));
                        dlgAlert.setPositiveButton(languagePreference.getTextofLanguage(BUTTON_OK, DEFAULT_BUTTON_OK), null);
                        dlgAlert.setCancelable(false);
                        dlgAlert.setPositiveButton(languagePreference.getTextofLanguage(BUTTON_OK, DEFAULT_BUTTON_OK),
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();

                                    }
                                });
                        dlgAlert.create().show();

                    }
                }
            });
        }
    }

    public boolean isHeader(int position) {
        return position == 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(layoutResourceId, parent, false));
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ViewHolder groupViewHolder = (ViewHolder) holder;
        // groupViewHolder.mImage.setText(labels.get(position - 1));
        groupViewHolder.bind(data.get(position), listener);
    }


    @Override
    public int getItemCount() {
        return data.size();
    }
    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId, int reqWidth, int reqHeight){
        final BitmapFactory.Options opt =new BitmapFactory.Options();
        opt.inJustDecodeBounds=true;
        BitmapFactory.decodeResource(res, resId, opt);
        opt.inSampleSize = calculateInSampleSize(opt,reqWidth,reqHeight);
        opt.inJustDecodeBounds=false;
        return BitmapFactory.decodeResource(res, resId, opt);
    }

    public void setLayoutResourceId(@LayoutRes int layoutResourceId) {
        int checkExistence = 0;
        try {
            String resourceName = String.valueOf(layoutResourceId);
            checkExistence = context.getResources().getIdentifier(resourceName , "layout", context.getPackageName());
        } catch (Exception e) {
            e.printStackTrace();
            checkExistence = 0;
        }
        if ( checkExistence != 0 ) {  // the resouce exists...
            this.layoutResourceId = layoutResourceId;
        }
        notifyDataSetChanged();
    }

    public static int calculateInSampleSize(BitmapFactory.Options opt, int reqWidth, int reqHeight){
        final int height = opt.outHeight;
        final int width = opt.outWidth;
        int sampleSize=1;
        if (height > reqHeight || width > reqWidth){
            final int halfWidth = width/2;
            final int halfHeight = height/2;
            while ((halfHeight/sampleSize) > reqHeight && (halfWidth/sampleSize) > reqWidth){
                sampleSize *=2;
            }

        }
        return sampleSize;
    }
}



