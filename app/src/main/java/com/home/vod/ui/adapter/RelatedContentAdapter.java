package com.home.vod.ui.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.home.vod.R;
import com.home.vod.model.RelatedContentModel;
import com.home.vod.util.FeatureHandler;
import com.home.vod.util.FontUtls;
import com.home.vod.util.Util;

import java.util.ArrayList;

public class RelatedContentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private String movieUniqueId;
    FeatureHandler featureHandler;

    private Context context;
    private int layoutResourceId;


    private OnItemClickListener listener;
    private ArrayList<RelatedContentModel> data = new ArrayList();


    public interface OnItemClickListener {
        void onItemClick(RelatedContentModel item);
    }


    public RelatedContentAdapter(Context context, int layoutResourceId,
                                 ArrayList<RelatedContentModel> data, OnItemClickListener listener) {
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
        this.listener = listener;
        this.movieUniqueId = movieUniqueId;
        featureHandler = FeatureHandler.getFeaturePreference(context);

    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView episodeTitleTextView;
        public TextView episodeNameTextView;
        public TextView episodeDateTextView;

        public ImageView episodeImageView,movieImageOverlay;

        public ViewHolder(View view) {
            super(view);
            episodeTitleTextView = view.findViewById(R.id.itemTitle);
            movieImageOverlay = view.findViewById(R.id.movieImageOverlay);
            FontUtls.loadFont(context, context.getResources().getString(R.string.light_fonts), episodeTitleTextView);
            episodeImageView = view.findViewById(R.id.itemImage);
        }

        public void bind(final RelatedContentModel item, final OnItemClickListener listener) {
            episodeTitleTextView.setText(item.getEpisodeTitle());

            String poster = item.getEpisodeThumbnailImageView();

             /*  @author : Chitra
              @description : To check Visibility status of  of title & overlay*/
            if (featureHandler.getFeatureFlag(FeatureHandler.IS_TITLE_ENABLED, FeatureHandler.DEFAULT_IS_TITLE_ENABLED).equals("1")) {
                episodeTitleTextView.setVisibility(View.VISIBLE);
            } else {
                episodeTitleTextView.setVisibility(View.GONE);
            }
            if (featureHandler.getFeatureFlag(FeatureHandler.IS_OVERLAY_DISPLAY, FeatureHandler.DEFAULT_IS_OVERLAY_DISPLAY).equals("1")) {
                movieImageOverlay.setVisibility(View.VISIBLE);
            } else {
                movieImageOverlay.setVisibility(View.GONE);
            }

            // for no-image
            if (poster != null) {
                if (Util.is_contain_noimage(poster)) {
                    episodeImageView.setBackgroundColor(context.getResources().getColor(R.color.no_image_bg_color));
                    episodeTitleTextView.setVisibility(View.VISIBLE);
                } else {
                    Glide.with(context)
                            .load(poster)
                            .thumbnail(0.1f)
                            .listener(new RequestListener<Drawable>() {
                                @Override
                                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                    episodeImageView.setBackgroundColor(context.getResources().getColor(R.color.no_image_bg_color));
                                    episodeTitleTextView.setVisibility(View.VISIBLE);
                                    return true;
                                }

                                @Override
                                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                    episodeImageView.setBackgroundColor(context.getResources().getColor(R.color.transparent));
                                    return false;
                                }
                            })
                            .into(episodeImageView);
                }
            } else {
                episodeTitleTextView.setVisibility(View.VISIBLE);
            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
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

    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId, int reqWidth, int reqHeight) {
        final BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, opt);
        opt.inSampleSize = calculateInSampleSize(opt, reqWidth, reqHeight);
        opt.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, opt);
    }

    public static int calculateInSampleSize(BitmapFactory.Options opt, int reqWidth, int reqHeight) {
        final int height = opt.outHeight;
        final int width = opt.outWidth;
        int sampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            final int halfWidth = width / 2;
            final int halfHeight = height / 2;
            while ((halfHeight / sampleSize) > reqHeight && (halfWidth / sampleSize) > reqWidth) {
                sampleSize *= 2;
            }

        }
        return sampleSize;
    }
}



