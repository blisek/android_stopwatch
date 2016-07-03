package com.barteklisek.stopwatch.counter;

import android.os.Handler;
import android.os.Message;

import com.barteklisek.stopwatch.views.StopwatchView;

/**
 * Created by bartek on 02.07.2016.
 */
public class StopwatchHandler implements Handler.Callback {
    public static final int MESSAGE_TIME_UPDATE = 10;
    public static final int MESSAGE_STOP_STOPWATCH = 11;
    private StopwatchView stopwatchView;

    public StopwatchHandler(StopwatchView stopwatchView) {
        this.stopwatchView = stopwatchView;
    }

    @Override
    public boolean handleMessage(Message message) {
        switch (message.what) {
            case MESSAGE_TIME_UPDATE:
                stopwatchView.setTimeElapsedAndUpdateView(message.arg1);
                return true;
            default:
                return false;
        }
    }
}
