package player.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.RecyclerView;

import com.home.vod.R;

import java.util.Timer;
import java.util.TimerTask;

import player.adapter.PlaybackSpeedAdapter;
import player.utils.Util;

/**
 * Created By Manab Boro
 */
public class PlaybackSpeedSelectActivity extends Activity {

    private PlaybackSpeedAdapter mAdapter;
    private LinearLayout total_layout;

    private Timer timer;

    /**
     * Callback interface
     */
    private PlaybackSpeedAdapter.OnPlaybackSpeedCallback onItemClickListener = (int position, String speedValue) -> {

        Intent playerIntent = new Intent();
        playerIntent.putExtra("position", "" + position);
        playerIntent.putExtra("type", "playback_speed");
        playerIntent.putExtra("code", speedValue);

        setResult(RESULT_OK, playerIntent);
        overridePendingTransition(0, 0);
        finish();
    };
    private String current_playback_speed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimary));
        }

        setContentView(R.layout.activity_playback_speed_layout);

        current_playback_speed = getIntent().getStringExtra("current_playback_speed");

        RecyclerView mRecyclerView = findViewById(R.id.mRecyclerView);
        total_layout = findViewById(R.id.total_layout);

        Util.is_running = true;
        Util.call_finish_at_onUserLeaveHint = true;
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(() -> {
                    try {

                        Display display = ((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
                        int orientation = display.getRotation();

                        if (orientation == 1 || orientation == 3) {
                            hideSystemUI();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }
        }, 0, 500);



        mRecyclerView.setHasFixedSize(true);
        mAdapter = new PlaybackSpeedAdapter(PlaybackSpeedSelectActivity.this,current_playback_speed, onItemClickListener);
        mRecyclerView.setAdapter(mAdapter);

        Animation topToBottom = AnimationUtils.loadAnimation(this, R.anim.bottom_top);
        mRecyclerView.startAnimation(topToBottom);

        total_layout.setOnTouchListener((v, event) -> {
            Intent playerIntent = new Intent();
            playerIntent.putExtra("type", "subtitle");
            playerIntent.putExtra("position", "nothing");
            playerIntent.putExtra("code", "nothing");

            setResult(RESULT_OK, playerIntent);
            overridePendingTransition(0, 0);
            finish();
            return false;
        });


    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent playerIntent = new Intent();
        playerIntent.putExtra("type", "subtitle");
        playerIntent.putExtra("position", "nothing");
        setResult(RESULT_OK, playerIntent);
        overridePendingTransition(0, 0);
        finish();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            timer.cancel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void hideSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }
}
