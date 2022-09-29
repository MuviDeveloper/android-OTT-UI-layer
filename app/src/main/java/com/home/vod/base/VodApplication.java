
package com.home.vod.base;

import static com.facebook.stetho.Stetho.initializeWithDefaults;
import static com.home.apisdk.APIUrlConstant.BASE_URl;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.upstream.FileDataSourceFactory;
import com.google.android.exoplayer2.upstream.HttpDataSource;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.upstream.cache.Cache;
import com.google.android.exoplayer2.upstream.cache.CacheDataSource;
import com.google.android.exoplayer2.upstream.cache.CacheDataSourceFactory;
import com.google.android.exoplayer2.upstream.cache.NoOpCacheEvictor;
import com.google.android.exoplayer2.upstream.cache.SimpleCache;
import com.google.android.exoplayer2.util.Util;
import com.home.vod.BuildConfig;
import com.home.vod.helper.LocaleLanguageHelper;
import com.home.vod.util.FeatureHandler;

import java.io.File;

/**
 * Application class
 */

public class VodApplication extends MultiDexApplication {

    private String CLICKED_TO_BANNER_LINK = "0";
    private String ISSUBSCRIBED = "0";
    private static final String DOWNLOAD_CONTENT_DIRECTORY = "downloads";

    protected String userAgent;
    private File downloadDirectory;
    private Cache downloadCache;

    public static final String DEMO_PREF = "demo_pref";
    FeatureHandler featureHandler;
    private boolean isMute;

    @Override
    public void onCreate() {
        super.onCreate();

        registerActivityLifecycleCallbacks(new AdjustLifecycleCallbacks());
        initializeWithDefaults(this);

        featureHandler = FeatureHandler.getFeaturePreference(VodApplication.this);

        BASE_URl = BuildConfig.SERVICE_BASE_PATH;
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        userAgent = Util.getUserAgent(this, "ExoPlayerDemo");
    }

    /**
     * Returns a {@link DataSource.Factory}.
     */
    public DataSource.Factory buildDataSourceFactory(TransferListener listener) {
        DefaultDataSourceFactory upstreamFactory =
                new DefaultDataSourceFactory(this, listener, buildHttpDataSourceFactory(listener));
        return buildReadOnlyCacheDataSource(upstreamFactory, getDownloadCache());
    }

    /**
     * Returns a {@link HttpDataSource.Factory}.
     */
    public HttpDataSource.Factory buildHttpDataSourceFactory(
            TransferListener listener) {
        return new DefaultHttpDataSourceFactory(userAgent, listener);
    }


    private synchronized Cache getDownloadCache() {
        if (downloadCache == null) {
            File downloadContentDirectory = new File(getDownloadDirectory(), DOWNLOAD_CONTENT_DIRECTORY);
            downloadCache = new SimpleCache(downloadContentDirectory, new NoOpCacheEvictor());
        }
        return downloadCache;
    }

    private File getDownloadDirectory() {
        if (downloadDirectory == null) {
            downloadDirectory = getExternalFilesDir(null);
            if (downloadDirectory == null) {
                downloadDirectory = getFilesDir();
            }
        }
        return downloadDirectory;
    }

    private static CacheDataSourceFactory buildReadOnlyCacheDataSource(
            DefaultDataSourceFactory upstreamFactory, Cache cache) {
        return new CacheDataSourceFactory(
                cache,
                upstreamFactory,
                new FileDataSourceFactory(),
                /* cacheWriteDataSinkFactory= */ null,
                CacheDataSource.FLAG_IGNORE_CACHE_ON_ERROR,
                /* eventListener= */ null);
    }

    public SharedPreferences getSharedPreferences() {
        return getSharedPreferences(DEMO_PREF, MODE_PRIVATE);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
        LocaleLanguageHelper.onAttach(base);
    }

    @Override
    public void onTrimMemory(int i) {
        super.onTrimMemory(i);
    }

    public String getCLICKED_TO_BANNER_LINK() {
        return CLICKED_TO_BANNER_LINK;
    }

    public void setCLICKED_TO_BANNER_LINK(String CLICKED_TO_BANNER_LINK) {
        this.CLICKED_TO_BANNER_LINK = CLICKED_TO_BANNER_LINK;
    }

    public String getISSUBSCRIBED() {
        return ISSUBSCRIBED;
    }

    public void setISSUBSCRIBED(String ISSUBSCRIBED) {
        this.ISSUBSCRIBED = ISSUBSCRIBED;
    }


    public boolean isMute() {
        return isMute;
    }

    public void setMute(boolean mute) {
        isMute = mute;
    }

    private static final class AdjustLifecycleCallbacks implements ActivityLifecycleCallbacks {
        @Override
        public void onActivityResumed(Activity activity) {
        }

        @Override
        public void onActivityPaused(Activity activity) {
        }

        @Override
        public void onActivityStopped(Activity activity) {
        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        }

        @Override
        public void onActivityDestroyed(Activity activity) {
        }

        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        }

        @Override
        public void onActivityStarted(Activity activity) {
        }
    }


}
