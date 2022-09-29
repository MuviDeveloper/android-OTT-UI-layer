
package com.home.vod.ui.adapter;

import static android.content.res.Configuration.SCREENLAYOUT_SIZE_LARGE;
import static android.content.res.Configuration.SCREENLAYOUT_SIZE_MASK;
import static android.content.res.Configuration.SCREENLAYOUT_SIZE_NORMAL;
import static android.content.res.Configuration.SCREENLAYOUT_SIZE_SMALL;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_NO_DATA;
import static com.home.vod.preferences.LanguagePreference.NO_DATA;
import static com.home.vod.util.Util.POTRAIT_TYPE;

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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.home.vod.R;
import com.home.vod.model.GridItem;
import com.home.vod.preferences.LanguagePreference;
import com.home.vod.util.FeatureHandler;
import com.home.vod.util.Util;

import java.util.ArrayList;

public class VideoFilterAdapter extends RecyclerView.Adapter<VideoFilterAdapter.MyViewHolder> {
    private Context context;
    private int layoutResourceId;
    private ArrayList<GridItem> data = new ArrayList<GridItem>();
    LayoutInflater inflater;
    FeatureHandler featureHandler;

    String imageType;

    public VideoFilterAdapter(Context context, int layoutResourceId, ArrayList<GridItem> data, String image_Type) {
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
        this.inflater = LayoutInflater.from(context);
        featureHandler = new FeatureHandler(context);
        this.imageType = image_Type;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView videoImageview, movieImageOverlay;

        public MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.movieTitle);
            videoImageview = view.findViewById(R.id.movieImageView);
            movieImageOverlay = view.findViewById(R.id.movieImageOverlay);


        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = inflater.inflate(layoutResourceId, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoFilterAdapter.MyViewHolder holder, int position) {

        if ((context.getResources().getConfiguration().screenLayout & SCREENLAYOUT_SIZE_MASK) == SCREENLAYOUT_SIZE_LARGE) {
            try {
                holder.videoImageview.setImageBitmap(decodeSampledBitmapFromResource(context.getResources(), R.id.movieImageView, holder.videoImageview.getDrawable().getIntrinsicWidth(), holder.videoImageview.getDrawable().getIntrinsicHeight()));
            } catch (Exception e) {
                e.printStackTrace();
                holder.videoImageview.setBackgroundColor(context.getResources().getColor(R.color.no_image_bg_color));
            }
        } else if ((context.getResources().getConfiguration().screenLayout & SCREENLAYOUT_SIZE_MASK) == SCREENLAYOUT_SIZE_NORMAL) {
            try {
                holder.videoImageview.setImageBitmap(decodeSampledBitmapFromResource(context.getResources(), R.id.movieImageView, holder.videoImageview.getDrawable().getIntrinsicWidth(), holder.videoImageview.getDrawable().getIntrinsicHeight()));
            } catch (Exception e) {
                e.printStackTrace();
                holder.videoImageview.setBackgroundColor(context.getResources().getColor(R.color.no_image_bg_color));
            }

        } else if ((context.getResources().getConfiguration().screenLayout & SCREENLAYOUT_SIZE_MASK) == SCREENLAYOUT_SIZE_SMALL) {
            try {
                holder.videoImageview.setImageBitmap(decodeSampledBitmapFromResource(context.getResources(), R.id.movieImageView, holder.videoImageview.getDrawable().getIntrinsicWidth(), holder.videoImageview.getDrawable().getIntrinsicHeight()));
            } catch (Exception e) {
                e.printStackTrace();
                holder.videoImageview.setBackgroundColor(context.getResources().getColor(R.color.no_image_bg_color));
            }
        } else {
            try {
                holder.videoImageview.setImageBitmap(decodeSampledBitmapFromResource(context.getResources(), R.id.movieImageView, holder.videoImageview.getDrawable().getIntrinsicWidth(), holder.videoImageview.getDrawable().getIntrinsicHeight()));
            } catch (Exception e) {
                e.printStackTrace();
                holder.videoImageview.setBackgroundColor(context.getResources().getColor(R.color.no_image_bg_color));
            }

        }
        try {
            GridItem item = data.get(position);

            /*
             * desc- need to show video duration of video thumbnails for nike customer
             * */

            {
                holder.title.setText(item.getTitle());
            }
            String imageId = item.getImage();

              /*
              @description : To check Visibility status of  of title & overlay*/

            if (featureHandler.getFeatureFlag(FeatureHandler.IS_TITLE_ENABLED, FeatureHandler.DEFAULT_IS_TITLE_ENABLED).equals("1")) {
                holder.title.setVisibility(View.VISIBLE);
            } else {

                /*
                 * desc-need to show video duration of video thumbnails for nike customer
                 * */

                holder.title.setVisibility(View.GONE);

            }
            if (featureHandler.getFeatureFlag(FeatureHandler.IS_OVERLAY_DISPLAY, FeatureHandler.DEFAULT_IS_OVERLAY_DISPLAY).equals("1")) {
                holder.movieImageOverlay.setVisibility(View.VISIBLE);
            } else {
                holder.movieImageOverlay.setVisibility(View.GONE);
            }


            if (imageId == null || imageId.matches("") || imageId.matches(LanguagePreference.getLanguagePreference(context).getTextofLanguage(NO_DATA, DEFAULT_NO_DATA))) {
                holder.title.setVisibility(View.VISIBLE);
                holder.videoImageview.setBackgroundColor(context.getResources().getColor(R.color.no_image_bg_color));

                if (imageType.equals(POTRAIT_TYPE)) {
                    holder.videoImageview.setImageDrawable(context.getResources().getDrawable(R.drawable.potrait_noimage));
                } else {
                    holder.videoImageview.setImageDrawable(context.getResources().getDrawable(R.drawable.landscape_noimage));
                }

            } else {

                if (Util.is_contain_noimage(item.getImage())) {
                    holder.title.setVisibility(View.VISIBLE);
                    holder.videoImageview.setBackgroundColor(context.getResources().getColor(R.color.no_image_bg_color));

                    if (imageType.equals(POTRAIT_TYPE)) {
                        holder.videoImageview.setImageDrawable(context.getResources().getDrawable(R.drawable.potrait_noimage));
                    } else {
                        holder.videoImageview.setImageDrawable(context.getResources().getDrawable(R.drawable.landscape_noimage));
                    }


                } else {

                    Glide.with(context)
                            .load(item.getImage())
                            .thumbnail(0.1f)
                            .listener(new RequestListener<Drawable>() {
                                @Override
                                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                    holder.videoImageview.setBackgroundColor(context.getResources().getColor(R.color.no_image_bg_color));
                                    holder.title.setVisibility(View.VISIBLE);
                                    return true;
                                }

                                @Override
                                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                    holder.videoImageview.setBackgroundColor(context.getResources().getColor(R.color.transparent));
                                    return false;
                                }
                            })
                            .into(holder.videoImageview);

                }

            }
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }

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