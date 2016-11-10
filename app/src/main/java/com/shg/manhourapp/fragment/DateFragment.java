package com.shg.manhourapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import com.shg.manhourapp.R;
import com.shg.manhourapp.utils.GlobalVar;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Administrator on 2016/11/7 0007.
 */

public class DateFragment extends Fragment implements DatePicker.OnDateChangedListener {
    private DatePicker chooseDate_DP;
    private TextView textView;
    private String dateStr;

    private int mYear;
    private int mMonthOfYear;
    private int mDayOfMonth;

    private DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_date, container, false);
        chooseDate_DP = (DatePicker) view.findViewById(R.id.dp_choosedate);

        Bundle arguments = getArguments();
        dateStr = arguments.getString("dateStr");
//        Log.d("MyLog", dateStr + "**");
        if (dateStr != null) {
            try {
                Date date = format.parse(dateStr);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                mYear = calendar.get(Calendar.YEAR);
                mMonthOfYear = calendar.get(Calendar.MONTH);
                mDayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                int mMonth = mMonthOfYear + 1;
                GlobalVar.DATE = mYear + "-" + mMonth + "-" + mDayOfMonth;
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            Calendar calendar = Calendar.getInstance();
            mYear = calendar.get(Calendar.YEAR);
            mMonthOfYear = calendar.get(Calendar.MONTH);
            mDayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
            int mMonth = mMonthOfYear + 1;
            GlobalVar.DATE = mYear + "-" + mMonth + "-" + mDayOfMonth;
        }

        chooseDate_DP.init(mYear, mMonthOfYear, mDayOfMonth, this);

        return view;
    }

    @Override
    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        int mMonth = monthOfYear + 1;
        GlobalVar.DATE = year + "-" + mMonth + "-" + dayOfMonth;

    }


}
