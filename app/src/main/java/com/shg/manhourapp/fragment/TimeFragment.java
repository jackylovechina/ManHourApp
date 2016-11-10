package com.shg.manhourapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TimePicker;

import com.shg.manhourapp.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Administrator on 2016/11/9 0009.
 */

public class TimeFragment extends DialogFragment implements TimePicker.OnTimeChangedListener {
    private TimePicker chooseTime_TP;
    private String timeStr;

    private int mHourOfDay;
    private int mMinuteOfHour;

    private DateFormat format = new SimpleDateFormat("HH:mm:ss", Locale.CHINA);

    public void getTimeView() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_time, container, false);
        chooseTime_TP = (TimePicker) view.findViewById(R.id.tp_choosetime);
        Bundle arguments = getArguments();
        timeStr = arguments.getString("timeStr");
        if (timeStr != null) {
            try {
                Date time = format.parse(timeStr);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(time);
                mHourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
                mMinuteOfHour = calendar.get(Calendar.MINUTE);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            Calendar calendar = Calendar.getInstance();
            mHourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
            mMinuteOfHour = calendar.get(Calendar.MINUTE);
        }
//        Log.d("MyLog", timeStr);
        chooseTime_TP.setOnTimeChangedListener(this);
        chooseTime_TP.setCurrentHour(mHourOfDay);
        chooseTime_TP.setCurrentMinute(mMinuteOfHour);
        return view;
    }


    @Override
    public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {

    }
}
