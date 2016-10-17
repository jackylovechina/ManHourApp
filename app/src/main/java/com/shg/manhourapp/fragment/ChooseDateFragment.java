package com.shg.manhourapp.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shg.manhourapp.R;

/**
 * Created by Administrator on 2016/10/17 0017.
 */

public class ChooseDateFragment extends DialogFragment implements View.OnClickListener {

    private TextView ChooseStartDate_TV;
    private TextView ChooseEndDate_TV;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_choosedate, null);
        ChooseStartDate_TV = (TextView) view.findViewById(R.id.tv_choosedate_start);
        ChooseEndDate_TV = (TextView) view.findViewById(R.id.tv_choosedate_end);
        ChooseStartDate_TV.setOnClickListener(this);
        ChooseEndDate_TV.setOnClickListener(this);

        builder.setView(view);
        builder.setTitle("时间筛选");
        builder.setPositiveButton("确定", null).setNegativeButton("取消", null);


        return builder.create();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.tv_choosedate_start:
                DatePickerFragment startDatePickerFragment = new DatePickerFragment();
                startDatePickerFragment.setTV(ChooseStartDate_TV);
                startDatePickerFragment.show(getActivity().getFragmentManager(), "");
                break;
            case R.id.tv_choosedate_end:

                DatePickerFragment endDatePickerFragment = new DatePickerFragment();
                endDatePickerFragment.setTV(ChooseEndDate_TV);
                endDatePickerFragment.show(getActivity().getFragmentManager(), "");
                break;

        }

    }
}
