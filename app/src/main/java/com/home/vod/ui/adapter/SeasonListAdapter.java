
package com.home.vod.ui.adapter;

import static android.content.res.Configuration.SCREENLAYOUT_SIZE_LARGE;
import static android.content.res.Configuration.SCREENLAYOUT_SIZE_MASK;
import static android.content.res.Configuration.SCREENLAYOUT_SIZE_NORMAL;
import static android.content.res.Configuration.SCREENLAYOUT_SIZE_SMALL;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_NO_DATA;
import static com.home.vod.preferences.LanguagePreference.NO_DATA;

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
import com.home.apisdk.apiModel.SeasonListOutputModel;
import com.home.vod.R;
import com.home.vod.preferences.LanguagePreference;
import com.home.vod.util.FeatureHandler;
import com.home.vod.util.Util;

import java.util.ArrayList;

public class SeasonListAdapter extends RecyclerView.Adapter<SeasonListAdapter.MyViewHolder> {
    private Context context;
    private int layoutResourceId;
    private ArrayList<SeasonListOutputModel> data = new ArrayList<>();
    LayoutInflater inflater;
    FeatureHandler featureHandler;

    public SeasonListAdapter(Context context, int layoutResourceId,
                             ArrayList<SeasonListOutputModel> data) {
        //super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
        this.inflater = LayoutInflater.from(context);
        featureHandler = new FeatureHandler(context);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView videoImageview,movieImageOverlay;

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
    public void onBindViewHolder(@NonNull SeasonListAdapter.MyViewHolder holder, int position) {

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


            holder.videoImageview.setScaleType(ImageView.ScaleType.CENTER_CROP);
            SeasonListOutputModel item = data.get(position);
            holder.title.setText(item.getTitle());
            String imageId = item.getMobile_poster();

            holder.title.setVisibility(View.VISIBLE);
            holder.movieImageOverlay.setVisibility(View.VISIBLE);

            if (imageId == null || imageId.matches("") || imageId.matches(LanguagePreference.getLanguagePreference(context).getTextofLanguage(NO_DATA, DEFAULT_NO_DATA))) {
                holder.title.setVisibility(View.VISIBLE);
                holder.videoImageview.setBackgroundColor(context.getResources().getColor(R.color.no_image_bg_color));


            } else {

                if (Util.is_contain_noimage(item.getMobile_poster())){
                    holder.title.setVisibility(View.VISIBLE);
                    holder.videoImageview.setBackgroundColor(context.getResources().getColor(R.color.no_image_bg_color));

                } else {

                    Glide.with(context)
                            .load(item.getMobile_poster())
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