package com.barteklisek.stopwatch;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.barteklisek.stopwatch.counter.StopwatchHandler;
import com.barteklisek.stopwatch.counter.StopwatchRunnable;
import com.barteklisek.stopwatch.views.StopwatchView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private StopwatchView stopwatchView;
    private StopwatchHandler stopwatchHandlerCallback;
    private Handler stopwatchHandler;
    private StopwatchRunnable stopwatchRunnable;
    private Thread stopwatchThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stopwatchView = Objects.requireNonNull((StopwatchView)findViewById(R.id.main_stopwatch_view));
        stopwatchHandlerCallback = new StopwatchHandler(stopwatchView);
        stopwatchHandler = new Handler(Looper.getMainLooper(), stopwatchHandlerCallback);
        stopwatchRunnable = new StopwatchRunnable(stopwatchHandler);

        stopwatchView.setStartButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(stopwatchRunnable.isStarted()) {
                    pauseStopwatch();
                    ((Button)view).setText(getResources().getText(
                            R.string.stopwatch_view_counter_button_start, "Start"));
                } else {
                    startStopwatch();
                    ((Button) view).setText(getResources().getText(
                            R.string.stopwatch_view_counter_button_pause, "Pause"));
                }
            }
        });

        stopwatchView.setResetButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pauseStopwatch();
                resetStopwatch();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void startStopwatch() {
        stopwatchThread = new Thread(stopwatchRunnable);
        stopwatchRunnable.setStarted(true);
        stopwatchThread.start();
    }

    private void pauseStopwatch() {
        stopwatchRunnable.stopWithClearingLastUptime();
    }

    private void resetStopwatch() {
        stopwatchView.setTimeElapsedAndUpdateView(0);
    }
}
