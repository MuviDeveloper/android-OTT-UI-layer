package com.home.vod.ui.adapter;

import static com.home.vod.preferences.LanguagePreference.BUTTON_OK;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_BUTTON_OK;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_NO_DETAILS_AVAILABLE;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_SORRY;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_VIDEO_NOT_PUBLISHED;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_WANT_TO_DOWNLOAD;
import static com.home.vod.preferences.LanguagePreference.NO_DETAILS_AVAILABLE;
import static com.home.vod.preferences.LanguagePreference.SORRY;
import static com.home.vod.preferences.LanguagePreference.VIDEO_NOT_PUBLISHED;
import static com.home.vod.preferences.LanguagePreference.WANT_TO_DOWNLOAD;
import static com.home.vod.util.Util.QUEUE_ARRAY;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.home.apisdk.apiModel.ContentDetailsOutput;
import com.home.vod.R;
import com.home.vod.model.QueueModel;
import com.home.vod.preferences.LanguagePreference;
import com.home.vod.preferences.PreferenceManager;
import com.home.vod.ui.widgets.ProgressBarHandler;
import com.home.vod.util.FeatureHandler;
import com.home.vod.util.Util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import player.activity.Player;

/**
 * Created by Subhadarshani on 6/23/2017.
 */

public class SinglePartAudioAdapter extends RecyclerView.Adapter<SinglePartAudioAdapter.ItemHolder> {

    String lengh;
    private static ContentDetailsOutput itemsList;
    private static Context mContext;
    boolean itemClicked = true;
    PreferenceManager preferenceManager;
    static LanguagePreference languagePreference;
    static ItemHolder holder2;
    String emailIdStr, audioUrl;
    public static boolean downloading;
    String is_login_require;
    static int lenghtOfFile;
    static int lengthfile;
    FeatureHandler featureHandler;
    SinglePartAudioListener singlePartAudioListener;
    static Player playerModel;
    String ParentSongName = "";

    public interface SinglePartAudioListener {
        void PlaySongs(ContentDetailsOutput itemsList, boolean itemClicked);

        void downloadSinglePart(ContentDetailsOutput itemsList, boolean itemClicked);
    }

    public SinglePartAudioAdapter(Context ctx, ContentDetailsOutput list) {
        mContext = ctx;
        itemsList = list;
        this.ParentSongName = ParentSongName;
        preferenceManager = PreferenceManager.getPreferenceManager(mContext);
        featureHandler = FeatureHandler.getFeaturePreference(mContext);
        languagePreference = LanguagePreference.getLanguagePreference(mContext);
        emailIdStr = preferenceManager.getEmailIdFromPref();
        is_login_require = featureHandler.getFeatureFlag(FeatureHandler.IS_LOGIN, FeatureHandler.DEFAULT_IS_LOGIN);
    }

    public SinglePartAudioAdapter(Context context, ContentDetailsOutput itemsList, SinglePartAudioListener listner, String ParentSongName) {
        SinglePartAudioAdapter.itemsList = itemsList;
        mContext = context;
        this.ParentSongName = ParentSongName;
        preferenceManager = PreferenceManager.getPreferenceManager(mContext);
        featureHandler = FeatureHandler.getFeaturePreference(mContext);
        languagePreference = LanguagePreference.getLanguagePreference(context);
        emailIdStr = preferenceManager.getEmailIdFromPref();
        is_login_require = featureHandler.getFeatureFlag(FeatureHandler.IS_LOGIN, FeatureHandler.DEFAULT_IS_LOGIN);
        this.singlePartAudioListener = listner;

    }

    public static void singlePartDownload(String videoUrl, Player model) {
        playerModel = model;
        new SinglePartAudioAdapter(mContext, itemsList).tempDownload(videoUrl);

    }

    public void tempDownload(String videoUrl) {
        audioUrl = videoUrl;
        new DownloadFileFromURL().execute(videoUrl);
    }


    @Override
    public ItemHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listdata_item, null);
        ItemHolder mh = new ItemHolder(v);

        return mh;
    }

    @Override
    public void onBindViewHolder(final ItemHolder holder, int i) {
        holder2 = holder;
        if (preferenceManager.getFreeUserPref().equals("1") || Integer.parseInt(itemsList.getIsFreeContent()) == 1) {
            holder.listsong_layout.setEnabled(true);
        } else if (itemsList.getIs_preorder() == 1) {
            holder.listsong_layout.setEnabled(false);
        } else {
            holder.listsong_layout.setEnabled(true);
        }


        try {
            holder.list_songName.setText(itemsList.getName());
            holder.list_artistName.setText(itemsList.getCast_detail());
            holder.listsong_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (itemsList.getIsConverted() == 0) {
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
                        dlgAlert.create().show();
                        return;
                    }

                    if (itemsList.getIs_media_published().equals("0")) {
                        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(mContext, R.style.MyAlertDialogStyle);
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
                        return;
                    }

                    if (itemsList.getIsConverted() == 1) {
                        QUEUE_ARRAY.clear();
                        QUEUE_ARRAY.add(new QueueModel(itemsList.getName(), itemsList.getPoster(), itemsList.getMovieUrl(), itemsList.getCast_detail(), "episode", itemsList.getMuviUniqId(),
                                itemsList.getMovieStreamUniqId(), itemsList.getIsConverted(), ParentSongName));
                        singlePartAudioListener.PlaySongs(itemsList, itemClicked);
                    }
                }
            });
            String link = itemsList.getPoster();



            // for no-image
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

        } catch (Exception e) {

        }

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class ItemHolder extends RecyclerView.ViewHolder {
        private TextView list_songName;
        private TextView list_artistName;
        private ImageView list_albumart;
        private RelativeLayout listsong_layout;


        public ItemHolder(View view) {
            super(view);
            this.listsong_layout = view.findViewById(R.id.listsong_layout);
            this.list_songName = view.findViewById(R.id.list_songName);
            Typeface list_songName_tf = Typeface.createFromAsset(mContext.getAssets(), mContext.getString(R.string.regular_fonts));
            list_songName.setTypeface(list_songName_tf);

            this.list_artistName = view.findViewById(R.id.list_artistName);
            Typeface list_artistName_tf = Typeface.createFromAsset(mContext.getAssets(), mContext.getString(R.string.light_fonts));
            list_artistName.setTypeface(list_artistName_tf);
            this.list_albumart = view.findViewById(R.id.list_albumart);
        }
    }


    class DownloadFileFromURL extends AsyncTask<String, String, String> {

        ProgressBarHandler pDialog;

        @Override
        protected void onPreExecute() {
            pDialog = new ProgressBarHandler(mContext);
            pDialog.show();

        }

        @Override
        protected String doInBackground(String... f_url) {

            try {
                URL url = new URL(f_url[0]);
                URLConnection conection = url.openConnection();
                conection.connect();
                lenghtOfFile = conection.getContentLength();
                lengthfile = lenghtOfFile / 1024 / 1024;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String file_url) {
            if (pDialog != null && pDialog.isShowing()) {
                pDialog.hide();
            }

            lengh = String.valueOf(lengthfile);

            LayoutInflater li = LayoutInflater.from(mContext);
            View promptsView = li.inflate(R.layout.custom_download_dialog, null);
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mContext);
            alertDialogBuilder.setView(promptsView);

            Button saveButton = promptsView.findViewById(R.id.saveButton);
            Button cancelButton = promptsView.findViewById(R.id.cancelButton);
            TextView dialog_text = promptsView.findViewById(R.id.dialog_text);
            Typeface dialog_text_tf = Typeface.createFromAsset(mContext.getAssets(), mContext.getString(R.string.regular_fonts));
            dialog_text.setTypeface(dialog_text_tf);
            dialog_text.setText(languagePreference.getTextofLanguage(WANT_TO_DOWNLOAD, DEFAULT_WANT_TO_DOWNLOAD));
            final TextView userInput = promptsView.findViewById(R.id.editTextDialogUserInput);
            Typeface dialoguserInput = Typeface.createFromAsset(mContext.getAssets(), mContext.getString(R.string.regular_fonts));
            userInput.setTypeface(dialoguserInput);
            userInput.setText(itemsList.getName() + " " + "(" + lengh + "MB)");
            final AlertDialog alertDialogadd = alertDialogBuilder.create();
            alertDialogadd.getWindow().setBackgroundDrawableResource(R.color.transparent);
            alertDialogadd.setCancelable(false);
            saveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // alertDialog.cancel;
                    alertDialogadd.dismiss();
                    downloading = true;
                }
            });
            cancelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Do some thing
                    alertDialogadd.dismiss();


                }
            });
            alertDialogadd.show();

        }
    }


}

