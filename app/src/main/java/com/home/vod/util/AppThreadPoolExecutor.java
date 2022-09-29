package com.home.vod.util;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by muvi on 31/7/17.
 */

public class AppThreadPoolExecutor {

    static AppThreadPoolExecutor instance = null;
    private final ThreadPoolExecutor executor;
    int corePoolSize = 60;
    int maximumPoolSize = 80;
    int keepAliveTime = 10;
    private BlockingQueue<Runnable> workQueue;

    public AppThreadPoolExecutor() {
        workQueue = new LinkedBlockingQueue<Runnable>(maximumPoolSize);
        executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS, workQueue);
    }

    public static AppThreadPoolExecutor getInstance() {
        if (instance == null) {
            instance = new AppThreadPoolExecutor();
        }
        return instance;

    }

    public Executor getThreadPoolExecutor() {
        return executor;
    }

}
