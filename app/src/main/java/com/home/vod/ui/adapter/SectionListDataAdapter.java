package com.home.vod.ui.adapter;

import static com.home.vod.preferences.LanguagePreference.BUTTON_OK;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_BUTTON_OK;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_NO_DETAILS_AVAILABLE;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_SORRY;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_VIDEO_NOT_PUBLISHED;
import static com.home.vod.preferences.LanguagePreference.NO_DETAILS_AVAILABLE;
import static com.home.vod.preferences.LanguagePreference.SORRY;
import static com.home.vod.preferences.LanguagePreference.VIDEO_NOT_PUBLISHED;
import static com.home.vod.util.Constant.PERMALINK_INTENT_KEY;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
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
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.home.vod.R;
import com.home.vod.model.SingleItemModel;
import com.home.vod.preferences.LanguagePreference;
import com.home.vod.preferences.PreferenceManager;
import com.home.vod.ui.activity.AudioContentDetailsActivity;
import com.home.vod.ui.activity.MovieDetailsActivity;
import com.home.vod.ui.activity.SeasonDetailsActivity;
import com.home.vod.ui.activity.ShowWithEpisodesActivity;
import com.home.vod.ui.fragment.HomeFragment;
import com.home.vod.util.FeatureHandler;
import com.home.vod.util.FontUtls;
import com.home.vod.util.Util;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class SectionListDataAdapter extends RecyclerView.Adapter<SectionListDataAdapter.SingleItemRowHolder> {


    HomeFragment mFragment;

    private onSectionItemClickListener onSectionItemClick;

    public interface onSectionItemClickListener {
        void onItemClick(SingleItemModel model);
    }


    int layoutname = 0;


    static PreferenceManager preferenceManager;
    FeatureHandler featureHandler;
    int corePoolSize = 60;
    int maximumPoolSize = 80;
    int keepAliveTime = 10;
    BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>(maximumPoolSize);

    LanguagePreference languagePreference;


    private ArrayList<SingleItemModel> itemsList;
    private static Context mContext;
    public static int sampleSize = 100;

    public SectionListDataAdapter(Context context, ArrayList<SingleItemModel> itemsList, int layoutname, HomeFragment fragment, onSectionItemClickListener listener) {
        this.itemsList = itemsList;
        mContext = context;
        this.mFragment = fragment;
        this.layoutname = layoutname;
        preferenceManager = PreferenceManager.getPreferenceManager(mContext);
        featureHandler = FeatureHandler.getFeaturePreference(context);
        this.onSectionItemClick = listener;

        languagePreference = LanguagePreference.getLanguagePreference(mContext);
    }


    @Override
    public SingleItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(layoutname, null);
        SingleItemRowHolder mh = new SingleItemRowHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(SingleItemRowHolder holder, int i) {

        SingleItemModel singleItem = itemsList.get(i);
        FontUtls.loadFont(mContext, mContext.getResources().getString(R.string.regular_fonts), holder.itemTitle);

         // desc-need to show video duration of video thumbnails for nike customer
        holder.itemTitle.setText(singleItem.getTitle());
        holder.position = i;
        try {

             /*
              @description : To check Visibility status of  of title & overlay*/
            if (featureHandler.getFeatureFlag(FeatureHandler.IS_TITLE_ENABLED, FeatureHandler.DEFAULT_IS_TITLE_ENABLED).equals("1")) {
                holder.itemTitle.setVisibility(View.VISIBLE);
            } else {
                /*
                 * desc-need to show video duration of video thumbnails for nike customer
                 * */
                holder.itemTitle.setVisibility(View.GONE);

            }
            if (featureHandler.getFeatureFlag(FeatureHandler.IS_OVERLAY_DISPLAY, FeatureHandler.DEFAULT_IS_OVERLAY_DISPLAY).equals("1")) {
                holder.movieImageOverlay.setVisibility(View.VISIBLE);
            } else {
                holder.movieImageOverlay.setVisibility(View.GONE);
            }


            if (singleItem.getImage() != null) {
                if (Util.is_contain_noimage(singleItem.getImage())) {
                    holder.itemTitle.setVisibility(View.VISIBLE);
                } else {
                    holder.itemImage.setBackgroundColor(mContext.getResources().getColor(R.color.transparent));
                    Glide.with(mContext)
                            .load(singleItem.getImage())
                            .thumbnail(0.1f)
                            .listener(new RequestListener<Drawable>() {
                                @Override
                                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                    holder.itemTitle.setVisibility(View.VISIBLE);
                                    return true;
                                }

                                @Override
                                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                    return false;
                                }
                            })
                            .into(holder.itemImage);
                }
            } else {
                holder.itemTitle.setVisibility(View.VISIBLE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {

        // to retuen only 5 values in Feature section in homePage
        if (itemsList.size() > Integer.parseInt(mContext.getResources().getString(R.string.Feature_section_limit)))
            return Integer.parseInt(mContext.getResources().getString(R.string.Feature_section_limit));
        else
            return itemsList.size();
    }

    public class SingleItemRowHolder extends RecyclerView.ViewHolder {

        protected TextView itemTitle;

        protected ImageView itemImage, movieImageOverlay;
        protected View temPV;
        protected int position;

        public SingleItemRowHolder(View view) {
            super(view);

            this.itemTitle = view.findViewById(R.id.itemTitle);
            this.itemImage = view.findViewById(R.id.itemImage);
            this.movieImageOverlay = view.findViewById(R.id.movieImageOverlay);


            try {
                this.itemImage.setImageBitmap(decodeSampledBitmapFromResource(mContext.getResources(), R.id.movieImageView, this.itemImage.getDrawable().getIntrinsicWidth(), this.itemImage.getDrawable().getIntrinsicHeight()));
            } catch (Exception e) {
                e.printStackTrace();
            }


            view.setOnClickListener(v -> {
                String moviePermalink = itemsList.get(position).getPermalink();
                String is_epsiode = itemsList.get(position).getIsEpisode();
                String movieTypeId = itemsList.get(position).getVideoTypeId();
                String is_play_list = itemsList.get(position).getIs_play_list();
                String play_list_type = itemsList.get(position).getPlay_list_type();
                String list_id = itemsList.get(position).getList_id();
                int is_converted = itemsList.get(position).getIsConverted();
                int is_media_publish = itemsList.get(position).getIs_media_published();
                String season_permalink = itemsList.get(position).getSeason_permalink();


                /*
                 * @desc : The below condition will check whether the content is a play list or normal content*/
                if (is_play_list.equals("0")) {

                    if ((movieTypeId.trim().equalsIgnoreCase("1")) || (movieTypeId.trim().equalsIgnoreCase("2")) || (movieTypeId.trim().equalsIgnoreCase("4"))) {
                        final Intent detailsIntent = new Intent(mContext, MovieDetailsActivity.class);
                        detailsIntent.putExtra(PERMALINK_INTENT_KEY, moviePermalink);
                        detailsIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        mContext.startActivity(detailsIntent);

                    } else if ((movieTypeId.trim().equalsIgnoreCase("3")) && is_epsiode.equals("2")) {
                        final Intent detailsIntent = new Intent(mContext, SeasonDetailsActivity.class);
                        detailsIntent.putExtra(PERMALINK_INTENT_KEY, moviePermalink);
                        detailsIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        detailsIntent.putExtra("season_permalink", season_permalink);
                        detailsIntent.putExtra("parent_permalink", moviePermalink);
                        mContext.startActivity(detailsIntent);

                    } else if ((movieTypeId.trim().equalsIgnoreCase("3")) && is_epsiode.equals("1")) {

                        if (is_media_publish == 0) {
                            AlertDialog.Builder dlgAlert = new AlertDialog.Builder(mContext, R.style.MyAlertDialogStyle);
                            dlgAlert.setMessage(languagePreference.getTextofLanguage(VIDEO_NOT_PUBLISHED, DEFAULT_VIDEO_NOT_PUBLISHED));
                            dlgAlert.setTitle(languagePreference.getTextofLanguage(SORRY, DEFAULT_SORRY));
                            dlgAlert.setPositiveButton(languagePreference.getTextofLanguage(BUTTON_OK, DEFAULT_BUTTON_OK), null);
                            dlgAlert.setCancelable(false);
                            dlgAlert.setPositiveButton(languagePreference.getTextofLanguage(BUTTON_OK, DEFAULT_BUTTON_OK),
                                    (dialog, id) -> dialog.cancel());
                            AlertDialog alertDialog = dlgAlert.create();
                            alertDialog.show();
                            TextView tv = (TextView) alertDialog.findViewById(android.R.id.message);
                            Configuration configuration = mContext.getResources().getConfiguration();
                            if (configuration.getLayoutDirection() == View.LAYOUT_DIRECTION_RTL) {
                                if (tv != null) {
                                    tv.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);

                                }
                            }
                            return;
                        }

                        if (is_converted == 0) {
                            AlertDialog.Builder dlgAlert = new AlertDialog.Builder(mContext, R.style.MyAlertDialogStyle);
                            dlgAlert.setMessage(languagePreference.getTextofLanguage(NO_DETAILS_AVAILABLE, DEFAULT_NO_DETAILS_AVAILABLE));
                            dlgAlert.setTitle(languagePreference.getTextofLanguage(SORRY, DEFAULT_SORRY));
                            dlgAlert.setPositiveButton(languagePreference.getTextofLanguage(BUTTON_OK, DEFAULT_BUTTON_OK), null);
                            dlgAlert.setCancelable(false);
                            dlgAlert.setPositiveButton(languagePreference.getTextofLanguage(BUTTON_OK, DEFAULT_BUTTON_OK),
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            dialog.cancel();

                                        }
                                    });
                            AlertDialog alertDialog = dlgAlert.create();
                            alertDialog.show();
                            TextView tv = (TextView) alertDialog.findViewById(android.R.id.message);
                            Configuration configuration = mContext.getResources().getConfiguration();
                            if (configuration.getLayoutDirection() == View.LAYOUT_DIRECTION_RTL) {
                                if (tv != null) {
                                    tv.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);

                                }
                            }
                            return;
                        }

                        onSectionItemClick.onItemClick(itemsList.get(position));

                    } else if ((movieTypeId.trim().equalsIgnoreCase("3"))) {
                        final Intent detailsIntent = new Intent(mContext, ShowWithEpisodesActivity.class);
                        detailsIntent.putExtra(PERMALINK_INTENT_KEY, moviePermalink);
                        detailsIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        mContext.startActivity(detailsIntent);

                    } else if (movieTypeId.trim().equals("6") || (movieTypeId.trim().equals("5"))) {
                        //Audio
                        final Intent detailsIntent = new Intent(mContext, AudioContentDetailsActivity.class);
                        detailsIntent.putExtra("PERMALINK", moviePermalink);
                        detailsIntent.putExtra("CONTENT_TYPE", movieTypeId);
                        detailsIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        mContext.startActivity(detailsIntent);
                    }
                }
            });
        }

    }


    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId, int reqWidth, int reqHeight) {
        final BitmapFactory.Options opt = new BitmapFactory.Options();
        try {
            opt.inJustDecodeBounds = true;
            opt.inSampleSize = sampleSize;
            BitmapFactory.decodeResource(res, resId, opt);
            opt.inSampleSize = calculateInSampleSize(opt, reqWidth, reqHeight);
            opt.inJustDecodeBounds = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return BitmapFactory.decodeResource(res, resId, opt);

    }

    public static int calculateInSampleSize(BitmapFactory.Options opt, int reqWidth, int reqHeight) {
        final int height = opt.outHeight * sampleSize;
        final int width = opt.outWidth * sampleSize;
        int sampleSize = 1;
        try {
            if (height > reqHeight || width > reqWidth) {
                final int halfWidth = width / 2;
                final int halfHeight = height / 2;
                while ((halfHeight / sampleSize) > reqHeight && (halfWidth / sampleSize) > reqWidth) {
                    sampleSize *= 2;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sampleSize;
    }

}