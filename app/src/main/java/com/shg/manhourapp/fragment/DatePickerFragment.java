package com.shg.manhourapp.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.shg.manhourapp.R;

import java.util.Calendar;

/**
 * Created by Administrator on 2016/10/17 0017.
 */

public class DatePickerFragment extends DialogFragment implements DatePicker.OnDateChangedListener, DialogInterface.OnClickListener {

    private DatePicker mDatePicker;

    private TextView mTextView;
    private int index;

    private int mYear;
    private int mMonthOfYear;
    private int mMonth;
    private int mDay;

    AlertDialog.Builder builder;


    public void setTV(TextView mTextView, int index) {
        this.mTextView = mTextView;
        this.index = index;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        builder = new AlertDialog.Builder(getActivity());

        View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_datepicker, null);
        mDatePicker = (DatePicker) view.findViewById(R.id.dp_datepicker);

        final Calendar calendar = Calendar.getInstance();
        mYear = calendar.get(Calendar.YEAR);
        mMonthOfYear = calendar.get(Calendar.MONTH);
        mMonth = mMonthOfYear + 1;
        mDay = calendar.get(Calendar.DAY_OF_MONTH);

        mDatePicker.init(mYear, mMonthOfYear, mDay, this);

        builder.setView(view);
        switch (index) {
            case 1:
                builder.setTitle("开始时间");
                break;
            case 2:
                builder.setTitle("结束时间");
                break;
        }


        builder.setPositiveButton("确定", this).setNegativeButton("取消", null);

        return builder.create();
    }


    @Override
    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        mYear = year;
        mMonth = monthOfYear + 1;
        mDay = dayOfMonth;

    }

    @Override
    public void onClick(DialogInterface dialog, int which) {

        mTextView.setText(mYear + "-" + mMonth + "-" + mDay);

    }
}
