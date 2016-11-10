package com.shg.manhourapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import com.shg.manhourapp.R;

/**
 * Created by Administrator on 2016/11/7 0007.
 */

public class DateFragment extends DialogFragment implements DatePicker.OnDateChangedListener {
    private DatePicker chooseDate_DP;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_date, container, false);
        chooseDate_DP = (DatePicker) view.findViewById(R.id.dp_choosedate);

        chooseDate_DP.init(1990,11,5,this);

        return view;
    }

    @Override
    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

    }
}
