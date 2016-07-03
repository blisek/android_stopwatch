package com.barteklisek.stopwatch.views;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.barteklisek.stopwatch.R;

import java.util.Objects;

/**
 * Created by bartek on 02.07.2016.
 */
public class StopwatchView extends LinearLayout {
    public static final String DEFAULT_TIME_FORMAT = "%02d:%02d:%02d.%03d";
    private static final String TAG = StopwatchView.class.getCanonicalName();

    private TextView counterTextView;
    private Button startButton, resetButton;
    private long timeElapsed;
    private String timeElapsedFormat;
    private OnClickListener startButtonClickListener, resetButtonClickListener;

    public StopwatchView(Context context) {
        super(context);
        init(context);
    }

    public StopwatchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public StopwatchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public StopwatchView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    public void updateCounterTextView() {
        long tmp = timeElapsed;
        int millis = (int)(tmp % 1000l);
        tmp /= 1000l;
        int seconds = (int)(tmp % 60l);
        tmp /= 60l;
        int minutes = (int)(tmp % 60l);
        tmp /= 60l;
        int hours = (int)tmp;

        counterTextView.setText(String.format(timeElapsedFormat, hours, minutes, seconds, millis));
    }

    public void setTimeElapsedAndUpdateView(long milliseconds) {
        setTimeElapsed(milliseconds);
        updateCounterTextView();
    }

    public void setTimeElapsed(long milliseconds) {
        timeElapsed = milliseconds;
    }

    public long getTimeElapsed() {
        return timeElapsed;
    }


    public OnClickListener getStartButtonClickListener() {
        return startButtonClickListener;
    }

    public void setStartButtonClickListener(OnClickListener startButtonClickListener) {
        this.startButtonClickListener = Objects.requireNonNull(startButtonClickListener);
    }

    public OnClickListener getResetButtonClickListener() {
        return resetButtonClickListener;
    }

    public void setResetButtonClickListener(OnClickListener resetButtonClickListener) {
        this.resetButtonClickListener = Objects.requireNonNull(resetButtonClickListener);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        counterTextView = Objects.requireNonNull((TextView)findViewById(R.id.counter_text));
        startButton = Objects.requireNonNull((Button)findViewById(R.id.button_start));
        resetButton = Objects.requireNonNull((Button)findViewById(R.id.reset_button));

        try {
            timeElapsedFormat = getResources()
                    .getString(R.string.stopwatch_view_time_elapsed_template);
            Log.i(TAG, "Stopwatch time format found: " + timeElapsedFormat);
        } catch(Resources.NotFoundException ex) {
            Log.w(TAG, "No stopwatch time format found. Using default: " + DEFAULT_TIME_FORMAT, ex);
            timeElapsedFormat = DEFAULT_TIME_FORMAT;

        }

        startButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(startButtonClickListener != null)
                    startButtonClickListener.onClick(view);
            }
        });

        resetButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(resetButtonClickListener != null)
                    resetButtonClickListener.onClick(view);
            }
        });

        updateCounterTextView();
    }

    private void init(Context context) {
        LayoutInflater inflater =
                (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.stopwatch_view, this);
    }
}
