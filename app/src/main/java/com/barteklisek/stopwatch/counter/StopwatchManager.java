package com.barteklisek.stopwatch.counter;

import android.os.Handler;

import java.util.Objects;

/**
 * Created by bartek on 03.07.2016.
 */
public class StopwatchManager {
    private Handler stopwatchHandler;
    private Thread currentWorkingThread;

    public StopwatchManager(Handler stopwatchHandler) {
        this.stopwatchHandler = Objects.requireNonNull(stopwatchHandler);
    }

    public void start() {

    }

    public void pause() {

    }

    public void stop() {

    }
}
