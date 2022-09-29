package player.activity;

/**
 * Created by MUVI on 9/6/2017.
 */

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.home.vod.R;
import com.spotxchange.v4.SpotXAdPlayer;
import com.spotxchange.v4.SpotXAdRequest;
import com.spotxchange.v4.SpotXInterstitialAdPlayer;
import com.spotxchange.v4.datamodel.SpotXAd;
import com.spotxchange.v4.datamodel.SpotXAdGroup;

public class AdPlayerActivity extends AppCompatActivity implements SpotXAdPlayer.Observer {
    private static final String TAG = AdPlayerActivity.class.getSimpleName();

    private ProgressBar _progressBar;
    Player playerModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_player);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        playerModel = (Player) getIntent().getSerializableExtra("PlayerModel");

        // create interstitial presenter
        SpotXInterstitialAdPlayer spx_player = new SpotXInterstitialAdPlayer();
        // register SpotX observer
        spx_player.registerObserver(this);
        // load the player
        spx_player.load(this);

    }



    @Override
    public SpotXAdRequest requestForPlayer(@NonNull SpotXAdPlayer spotXAdPlayer) {
        SpotXAdRequest request = new SpotXAdRequest(null); // SpotX Test API Key
        return request;
    }

    @Override
    public void onLoadedAds(SpotXAdPlayer player, SpotXAdGroup group, Exception e) {
        if (group != null && group.ads.size() > 0) {
            player.start();
        }
    }

    @Override
    public void onGroupStart(SpotXAdGroup spotXAdGroup) {

    }

    @Override
    public void onStart(SpotXAd spotXAd) {

    }

    @Override
    public void onPlay(SpotXAd spotXAd) {

    }

    @Override
    public void onPause(SpotXAd spotXAd) {

    }

    @Override
    public void onTimeUpdate(SpotXAd spotXAd, double v) {

    }

    @Override
    public void onClick(SpotXAd spotXAd) {

    }

    @Override
    public void onComplete(SpotXAd spotXAd) {

        if (getIntent().getStringExtra("fromAd") != null) {
            Intent in = new Intent();
            setResult(RESULT_OK, in);
            finish();
        } else {
            Intent playVideoIntent = new Intent(AdPlayerActivity.this, PlayerActivity.class);
            playVideoIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            playVideoIntent.putExtra("PlayerModel", playerModel);
            startActivity(playVideoIntent);
            finish();
        }

    }

    @Override
    public void onSkip(SpotXAd spotXAd) {

        if (getIntent().getStringExtra("fromAd") != null) {
            Intent in = new Intent();
            setResult(RESULT_OK, in);
            finish();
        } else {
            Intent playVideoIntent = new Intent(AdPlayerActivity.this, PlayerActivity.class);
            playVideoIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            playVideoIntent.putExtra("PlayerModel", playerModel);
            startActivity(playVideoIntent);
            finish();
        }
    }

    @Override
    public void onUserClose(SpotXAd spotXAd) {

        if (getIntent().getStringExtra("fromAd") != null) {
            Intent in = new Intent();
            setResult(RESULT_OK, in);
            finish();
        } else {
            Intent playVideoIntent = new Intent(AdPlayerActivity.this, PlayerActivity.class);
            playVideoIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            playVideoIntent.putExtra("PlayerModel", playerModel);
            startActivity(playVideoIntent);
            finish();
        }
    }

    @Override
    public void onError(SpotXAd spotXAd, Exception e) {
        if (getIntent().getStringExtra("fromAd") != null) {
            Intent in = new Intent();
            setResult(RESULT_OK, in);
            finish();
        } else {
            Intent playVideoIntent = new Intent(AdPlayerActivity.this, PlayerActivity.class);
            playVideoIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            playVideoIntent.putExtra("PlayerModel", playerModel);
            startActivity(playVideoIntent);
            finish();
        }
    }

    @Override
    public void onGroupComplete(SpotXAdGroup spotXAdGroup) {

    }

}
