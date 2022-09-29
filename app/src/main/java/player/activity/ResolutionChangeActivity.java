package player.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.home.vod.R;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import player.adapter.ResolutionChangeAdapter;
import player.utils.Util;

public class ResolutionChangeActivity extends Activity {

    ListView listView;
    ArrayList<String> resolutionformst_list = new ArrayList<>();
    ResolutionChangeAdapter resolutionChangeAdapter;
    LinearLayout total_layout;

    ArrayList<String> ResolutionFormat = new ArrayList<>();
    ArrayList<String> ResolutionUrl = new ArrayList<>();
    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resolution_change);
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        try {

                            Display display = ((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
                            int orientation = display.getRotation();

                            Log.v("PINTU", "CheckAvailabilityOfChromecast called orientation=" + orientation);

                            if (orientation == 1 || orientation == 3) {
                                hideSystemUI();
                            }
                        } catch (Exception e) {
                        }
                    }
                });
            }
        }, 0, 500);

        listView = (ListView) findViewById(R.id.listView);
        total_layout = (LinearLayout) findViewById(R.id.total_layout);

        Util.call_finish_at_onUserLeaveHint = true;

        if (getIntent().getIntegerArrayListExtra("ResolutionFormat") != null) {
            ResolutionFormat = getIntent().getStringArrayListExtra("ResolutionFormat");
        } else {
            ResolutionFormat.clear();
        }

        if (getIntent().getStringArrayListExtra("ResolutionUrl") != null) {
            ResolutionUrl = getIntent().getStringArrayListExtra("ResolutionUrl");
        } else {
            ResolutionUrl.clear();
        }

        for (int i = 0; i < ResolutionFormat.size(); i++) {
            resolutionformst_list.add("" + ResolutionFormat.get(i));
        }
        resolutionChangeAdapter = new ResolutionChangeAdapter(ResolutionChangeActivity.this, resolutionformst_list);
        listView.setAdapter(resolutionChangeAdapter);

        Animation topTobottom = AnimationUtils.loadAnimation(this, R.anim.bottom_top);
        listView.startAnimation(topTobottom);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    Util.VideoResolution = ResolutionFormat.get(position).toString();
                    Intent playerIntent = new Intent();
                    playerIntent.putExtra("position", "" + position);
                    playerIntent.putExtra("type", "resolution");
                    setResult(RESULT_OK, playerIntent);
                    overridePendingTransition(0, 0);
                    finish();
                } catch (Exception e) {
                    Util.VideoResolution = "AUTO";
                    Intent playerIntent = new Intent();
                    playerIntent.putExtra("position", "nothing");
                    playerIntent.putExtra("type", "resolution");
                    setResult(RESULT_OK, playerIntent);
                    overridePendingTransition(0, 0);
                    finish();
                }
            }
        });

        total_layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Intent playerIntent = new Intent();
                playerIntent.putExtra("position", "nothing");
                playerIntent.putExtra("type", "resolution");
                setResult(RESULT_OK, playerIntent);
                overridePendingTransition(0, 0);
                finish();
                return false;
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent playerIntent = new Intent();
        playerIntent.putExtra("position", "nothing");
        playerIntent.putExtra("type", "resolution");
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
        }
    }

    private void hideSystemUI() {
        // Set the IMMERSIVE flag.
        // Set the content to appear under the system bars so that the content
        // doesn't resize when the system bars hide and show.
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
