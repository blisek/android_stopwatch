package com.barteklisek.stopwatch.counter;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.barteklisek.stopwatch.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CounterFragment extends Fragment {
    public static final String COUNTER_FRAGMENT_TIME_ELAPSED =
            CounterFragment.class.getCanonicalName() + ".TIME_ELAPSED";
    public static final String COUNTER_FRAGMENT_STOP_TIME =
            CounterFragment.class.getCanonicalName() + ".STOP_TIME";

    private StopwatchRunnable counterRunnable;
    private Thread workingThread;

    public CounterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_counter, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putLong(COUNTER_FRAGMENT_TIME_ELAPSED, counterRunnable.getTimeElapsed());
        outState.putLong(COUNTER_FRAGMENT_STOP_TIME, counterRunnable.getLastUptimeMillis());
        super.onSaveInstanceState(outState);
    }
}
