package player.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.home.vod.R;
import com.home.vod.util.FontUtls;

import java.util.ArrayList;

/**
 * Created by Manab Boro on 03,June,2020
 */
public class PlaybackSpeedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private LayoutInflater mInflater;
    private ArrayList<Object> mData;
    private OnPlaybackSpeedCallback callback;
    private String[] resolutionName;
    private String currentPlaybackSpeed;

    /**
     * Default constructor
     *
     * @param context                context
     * @param current_playback_speed
     * @param callback               callback interface
     */
    public PlaybackSpeedAdapter(Context context, String current_playback_speed, OnPlaybackSpeedCallback callback) {
        mInflater = LayoutInflater.from(context);
        mData = new ArrayList<>();
        this.callback = callback;
        this.currentPlaybackSpeed = current_playback_speed == null ? "Normal" : current_playback_speed;

        resolutionName = context.getResources().getStringArray(R.array.playback_speed_array);
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PlaybackViewHolder(mInflater.inflate(R.layout.resolution_list_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        PlaybackViewHolder viewHolder = (PlaybackViewHolder) holder;
        viewHolder.bind(resolutionName[(position)], currentPlaybackSpeed);
        viewHolder.itemView.setOnClickListener(v -> callback.onSelectedPlaybackSpeed(position, resolutionName[position]));
    }

    @Override
    public int getItemCount() {
        return resolutionName.length;
    }


    /**
     * View holder class for a playback speed change adapter for a single item
     */
    private static class PlaybackViewHolder extends RecyclerView.ViewHolder {
        private TextView mTitle;
        private ImageView selectedSpeedIcon;

        PlaybackViewHolder(@NonNull View itemView) {
            super(itemView);
            mTitle = itemView.findViewById(R.id.title);
            selectedSpeedIcon = itemView.findViewById(R.id.selected_resolution);
            FontUtls.loadFont(itemView.getContext(), itemView.getContext().getResources().getString(R.string.regular_fonts), mTitle);

        }

        public void bind(String data, String currentPlaybackSpeed) {
            if (data.equals("Normal")) {
                mTitle.setText(data);
            } else {
                mTitle.setText(data + "x");
            }

            if (data.equals(currentPlaybackSpeed)){
                selectedSpeedIcon.setVisibility(View.VISIBLE);
            }
            else {
                selectedSpeedIcon.setVisibility(View.INVISIBLE);
            }

        }
    }

    /**
     * Callback interface
     */
    public interface OnPlaybackSpeedCallback {
        void onSelectedPlaybackSpeed(int position, String speedValue);
    }
}
