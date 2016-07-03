package com.barteklisek.stopwatch.counter;

import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;

import java.util.Objects;

/**
 * Created by bartek on 02.07.2016.
 */
public class StopwatchRunnable implements Runnable {
    private final Handler uiThreadHandler;
    private volatile boolean started = true;
    private volatile long lastUptimeMillis = -1, timeElapsed;

    public StopwatchRunnable(Handler uiThreadHandler) {
        this(uiThreadHandler, -1);
    }

    public StopwatchRunnable(Handler uiThreadHandler, long lastUptimeMillis) {
        this.uiThreadHandler = Objects.requireNonNull(uiThreadHandler);
        this.lastUptimeMillis = lastUptimeMillis;
    }

    @Override
    public void run() {
        if(!started)
            return;

        if(lastUptimeMillis < 0) {
            lastUptimeMillis = SystemClock.uptimeMillis();
        }

        long tmp = SystemClock.uptimeMillis();
        timeElapsed += tmp - lastUptimeMillis;
        lastUptimeMillis = tmp;
        sendTimeUpdate();


        if(started)
            uiThreadHandler.postDelayed(this, 0);
    }


    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

    public void stopWithoutClearingLastUptime() {
        setStarted(false);
    }

    public void stopWithClearingLastUptime() {
        setStarted(false);
        lastUptimeMillis = -1;
    }

    public long getLastUptimeMillis() {
        return lastUptimeMillis;
    }

    public long getTimeElapsed() {
        return timeElapsed;
    }

    private void sendTimeUpdate() {
        int timeElapsed = (int)this.timeElapsed; // max. ~24 days w/o overflow
        Message updateMessage = uiThreadHandler.obtainMessage(
                StopwatchHandler.MESSAGE_TIME_UPDATE, timeElapsed, 0);
        updateMessage.sendToTarget();
    }
}
