package player.activity;

/**
 * Created by MUVI on 9/6/2017.
 */

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.ads.interactivemedia.v3.api.AdDisplayContainer;
import com.google.ads.interactivemedia.v3.api.AdErrorEvent;
import com.google.ads.interactivemedia.v3.api.AdErrorEvent.AdErrorListener;
import com.google.ads.interactivemedia.v3.api.AdEvent;
import com.google.ads.interactivemedia.v3.api.AdEvent.AdEventListener;
import com.google.ads.interactivemedia.v3.api.AdsLoader;
import com.google.ads.interactivemedia.v3.api.AdsLoader.AdsLoadedListener;
import com.google.ads.interactivemedia.v3.api.AdsManager;
import com.google.ads.interactivemedia.v3.api.AdsManagerLoadedEvent;
import com.google.ads.interactivemedia.v3.api.AdsRequest;
import com.google.ads.interactivemedia.v3.api.ImaSdkFactory;
import com.google.ads.interactivemedia.v3.api.ImaSdkSettings;
import com.google.ads.interactivemedia.v3.api.player.ContentProgressProvider;
import com.google.ads.interactivemedia.v3.api.player.VideoProgressUpdate;
import com.home.vod.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Main Activity.
 */
public class MyActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        //  orientVideoDescriptionFragment(getResources().getConfiguration().orientation);
    }


    @Override
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    /**
     * The main fragment for displaying video content.
     */
    public static class VideoFragment extends Fragment implements AdEventListener, AdErrorListener {

        private static String LOGTAG = "ImaExample";

        // The video player.
        private SampleVideoPlayer mVideoPlayer;
        private TextView playerDescription;
        private Button skipButton;
        Player playerModel;

        // The container for the ad's UI.
        private ViewGroup mAdUiContainer;

        // Factory class for creating SDK objects.
        private ImaSdkFactory mSdkFactory;

        // The AdsLoader instance exposes the requestAds method.
        private AdsLoader mAdsLoader;

        // AdsManager exposes methods to control ad playback and listen to ad events.
        private AdsManager mAdsManager;

        // Whether an ad is displayed.
        private boolean mIsAdDisplayed;

        // The play button to trigger the ad request.
        // private View mPlayButton;

        @Override
        public void onActivityCreated(Bundle bundle) {
            super.onActivityCreated(bundle);
            ImaSdkSettings imaSdkSettings = ImaSdkFactory.getInstance().createImaSdkSettings();
            AdDisplayContainer adDisplayContainer = mSdkFactory.createAdDisplayContainer();
            adDisplayContainer.setAdContainer(mAdUiContainer);
            // Create an AdsLoader.
            mSdkFactory = ImaSdkFactory.getInstance();
            mAdsLoader = mSdkFactory.createAdsLoader(this.getContext(),imaSdkSettings,adDisplayContainer);
            // Add listeners for when ads are loaded and for errors.
            mAdsLoader.addAdErrorListener(this);
            mAdsLoader.addAdsLoadedListener(new AdsLoadedListener() {
                @Override
                public void onAdsManagerLoaded(AdsManagerLoadedEvent adsManagerLoadedEvent) {
                    // Ads were successfully loaded, so get the AdsManager instance. AdsManager has
                    // events for ad playback and errors.
                    mAdsManager = adsManagerLoadedEvent.getAdsManager();
                    // Attach event and error event listeners.
                    mAdsManager.addAdErrorListener(VideoFragment.this);
                    mAdsManager.addAdEventListener(VideoFragment.this);
                    mAdsManager.init();
                }
            });

            // Add listener for when the content video finishes.
            mVideoPlayer.addVideoCompletedListener(new SampleVideoPlayer.OnVideoCompletedListener() {
                @Override
                public void onVideoCompleted() {
                    // Handle completed event for playing post-rolls.
                    if (mAdsLoader != null) {
                        mAdsLoader.contentComplete();


                    }
                }
            });
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_ad, container, false);

            mVideoPlayer = (SampleVideoPlayer) rootView.findViewById(R.id.sampleVideoPlayer);
            skipButton = (Button) rootView.findViewById(R.id.skipButton);
            skipButton.setVisibility(View.GONE);
            mAdUiContainer = (ViewGroup) rootView.findViewById(R.id.videoPlayerWithAdPlayback);
            // mPlayButton = rootView.findViewById(R.id.playButton);

            return rootView;
        }
        public void startTimer(){
            Timer t = new Timer();
            TimerTask task = new TimerTask() {

                @Override
                public void run() {
                    getActivity().runOnUiThread(new Runnable() {

                        @Override
                        public void run() {

                        }
                    });
                }
            };
            t.scheduleAtFixedRate(task, 0, 1000);
        }
//        /**
//         * Request video ads from the given VAST ad tag.
//         * @param adTagUrl URL of the ad's VAST XML
//         */
        private void requestAds(String adTagUrl) {


            // Create the ads request.
            AdsRequest request = mSdkFactory.createAdsRequest();
            request.setAdTagUrl(adTagUrl);
            request.setContentProgressProvider(new ContentProgressProvider() {
                @Override
                public VideoProgressUpdate getContentProgress() {
                    Log.i(LOGTAG, "getContentProgress: " + mVideoPlayer.getDuration());

                    if (mIsAdDisplayed || mVideoPlayer == null || mVideoPlayer.getDuration() <= 0) {
                        return VideoProgressUpdate.VIDEO_TIME_NOT_READY;
                    }
                    return new VideoProgressUpdate(mVideoPlayer.getCurrentPosition(),
                            mVideoPlayer.getDuration());
                }
            });

            // Request the ad. After the ad is loaded, onAdsManagerLoaded() will be called.
            mAdsLoader.requestAds(request);
        }

        @Override
        public void onAdEvent(AdEvent adEvent) {

        }

        @Override
        public void onAdError(AdErrorEvent adErrorEvent) {
            if (mAdsManager != null) {
                mAdsManager.destroy();
                mAdsManager = null;
            }
                getActivity().finish();
        }

        @Override
        public void onResume() {
            if (mAdsManager != null && mIsAdDisplayed) {
                mAdsManager.resume();
            } else {
                mVideoPlayer.play();
            }
            super.onResume();
        }
        @Override
        public void onPause() {
            if (mAdsManager != null && mIsAdDisplayed) {
                mAdsManager.pause();
            } else {
                mVideoPlayer.pause();
            }
            super.onPause();
        }

    }


    @Override
    public void onBackPressed() {
    }

}
