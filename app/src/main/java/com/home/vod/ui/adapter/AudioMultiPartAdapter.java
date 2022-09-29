package com.home.vod.ui.adapter;

import static android.content.Context.DOWNLOAD_SERVICE;
import static com.home.vod.preferences.LanguagePreference.BUTTON_OK;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_BUTTON_OK;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_NO_DETAILS_AVAILABLE;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_SORRY;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_VIDEO_NOT_PUBLISHED;
import static com.home.vod.preferences.LanguagePreference.NO_DETAILS_AVAILABLE;
import static com.home.vod.preferences.LanguagePreference.SORRY;
import static com.home.vod.preferences.LanguagePreference.VIDEO_NOT_PUBLISHED;
import static com.home.vod.util.Util.QUEUE_ARRAY;

import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.home.apisdk.apiModel.Episode_Details_output;
import com.home.vod.R;
import com.home.vod.model.QueueModel;
import com.home.vod.preferences.LanguagePreference;
import com.home.vod.preferences.PreferenceManager;
import com.home.vod.util.FeatureHandler;
import com.home.vod.util.Util;

import java.util.ArrayList;

import player.model.ContactModel1;
import player.utils.DBHelper;


/**
 * Created by Muvi on 6/23/2017.
 */

public class AudioMultiPartAdapter extends RecyclerView.Adapter<AudioMultiPartAdapter.ItemHolder> {

    public static Episode_Details_output itemsList;
    public static Context mContext;
    private boolean itemClicked = true;
    private ArrayList<Episode_Details_output> episode_details_output;
    private int adaptorPosition = -1;

    private String artist_multi_data;
    private PreferenceManager preferenceManager;
    private LanguagePreference languagePreference;
    public DownloadManager downloadManager;
    public Handler downloadHandler;
    public DBHelper dbHelper;
    public static ContactModel1  audio;
    public static String emailIdStr, userId, audioUrl,  option_value;
    private FeatureHandler featureHandler;

    private MultiPartAudioListener multiPartAudioListener;

    private String ParentSongName = "";
    private  ArrayList<String> all_stream_unique_id;


    public interface MultiPartAudioListener {
        void PlaySongsmulti(Episode_Details_output episode_Details_output, boolean itemClicked, int position);

        void downloadMultipart(Episode_Details_output episode_Details_output, boolean itemClicked, int index);

    }

    public AudioMultiPartAdapter(Context context, MultiPartAudioListener listner,
                                 ArrayList<Episode_Details_output> episode_details_output,
                                 String artist_multi, String option_value,
                                 String posterimage, String ParentSongName) {
        this.episode_details_output = episode_details_output;
        mContext = context;
        this.ParentSongName = ParentSongName;
        this.artist_multi_data = artist_multi;
        AudioMultiPartAdapter.option_value = option_value;
        this.multiPartAudioListener = listner;

        preferenceManager = PreferenceManager.getPreferenceManager(context);
        languagePreference = LanguagePreference.getLanguagePreference(context);
        featureHandler = FeatureHandler.getFeaturePreference(mContext);
        emailIdStr = preferenceManager.getEmailIdFromPref();
        downloadManager = (DownloadManager) mContext.getSystemService(DOWNLOAD_SERVICE);
        downloadHandler = new Handler();
        dbHelper = new DBHelper(mContext);
        dbHelper.getWritableDatabase();
    }


    @Override
    public ItemHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listdata_item, null);
        ItemHolder mh = new ItemHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(final ItemHolder holder, final int position) {

        final Episode_Details_output multi_song_list_adaptor = episode_details_output.get(position);
        multi_song_list_adaptor.setGenre(artist_multi_data);


        holder.list_songName.setText(multi_song_list_adaptor.getEpisode_title());
        holder.artist_multi.setText(artist_multi_data);
        if (adaptorPosition != -1) {
            adaptorPosition = -1;
        } else {
            {
                //revert back to regular color
                holder.list_songName.setTextColor(mContext.getResources().getColor(R.color.editTextColor));
                holder.artist_multi.setTextColor(mContext.getResources().getColor(R.color.editTextColor));
            }
        }
        Util.ArtistName = artist_multi_data;



        holder.listsong_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (multi_song_list_adaptor.getIs_converted() == 0) {
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

                if (multi_song_list_adaptor.getIs_media_published().equals("0")) {
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

                if (multi_song_list_adaptor.getIs_converted() == 1 && multi_song_list_adaptor.getIs_media_published().equals("1")) {
                    QUEUE_ARRAY.clear();


                    QUEUE_ARRAY.add(new QueueModel(multi_song_list_adaptor.getEpisode_title(), multi_song_list_adaptor.getPoster_url(), multi_song_list_adaptor.getVideo_url(), artist_multi_data, "episode", multi_song_list_adaptor.getMuvi_uniq_id(),
                            multi_song_list_adaptor.getMovie_stream_uniq_id(), multi_song_list_adaptor.getIs_converted(), ParentSongName));

                    all_stream_unique_id = new ArrayList<>();
                    for (int i = 0; i < episode_details_output.size(); i++) {
                        if (episode_details_output.get(i).getIs_converted() == 1 && episode_details_output.get(i).getIs_media_published().equals("1"))
                            all_stream_unique_id.add(episode_details_output.get(i).getMovie_stream_uniq_id());

                    }

                    multiPartAudioListener.PlaySongsmulti(multi_song_list_adaptor, itemClicked, all_stream_unique_id.indexOf(multi_song_list_adaptor.getMovie_stream_uniq_id()));
                }
            }
        });





        String link = multi_song_list_adaptor.getPoster_url();

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


    }

    @Override
    public int getItemCount() {
        return episode_details_output.size();
    }


    public class ItemHolder extends RecyclerView.ViewHolder {
        private TextView list_songName, listDummy;
        private TextView artist_multi;
        private ImageView list_albumart;
        private RelativeLayout listsong_layout;
        private View dividerView;

        public ItemHolder(View view) {
            super(view);
            this.listsong_layout = view.findViewById(R.id.listsong_layout);
            this.list_songName = view.findViewById(R.id.list_songName);
            this.listDummy = view.findViewById(R.id.listDummy);
            Typeface list_songName_tf = Typeface.createFromAsset(mContext.getAssets(), mContext.getString(R.string.regular_fonts));
            list_songName.setTypeface(list_songName_tf);
            this.artist_multi = view.findViewById(R.id.list_artistName);
            Typeface artist_multi_tf = Typeface.createFromAsset(mContext.getAssets(), mContext.getString(R.string.regular_fonts));
            artist_multi.setTypeface(artist_multi_tf);
            this.list_albumart = view.findViewById(R.id.list_albumart);
            this.dividerView = view.findViewById(R.id.divider);


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
