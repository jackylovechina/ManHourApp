package com.shg.manhourapp.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shg.manhourapp.R;
import com.shg.manhourapp.domain.DispatchListItemsViewModel;

/**
 * Created by Administrator on 2016/10/14 0014.
 */

public class DetailFragment extends DialogFragment {

    private LinearLayout uncompItemDetail_LL;
    private LinearLayout compItemDetail_LL;

    private TextView uncompItemDetailStartTime_TV;
    private TextView uncompItemDetailEndTime_TV;
    private TextView uncompItemDetailManHourActualTimes_TV;

    private TextView compItemDetailStartTime_TV;
    private TextView compItemDetailEndTime_TV;
    private TextView compItemDetailManHourActualTimes_TV;

    private DispatchListItemsViewModel dispatchListItem;
    private int isComp;

    public void getItemDetail(DispatchListItemsViewModel dispatchListItem, int isComp) {
        this.dispatchListItem = dispatchListItem;
        this.isComp = isComp;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_detail, null);
        initView(view);

        switch (isComp) {

            case 1:
                uncompItemDetail_LL.setVisibility(View.VISIBLE);
                uncompItemDetailManHourActualTimes_TV.setText(Double.toString(dispatchListItem.manHourActual) + "小时");
                break;
            case 2:
                compItemDetail_LL.setVisibility(View.VISIBLE);
                compItemDetailManHourActualTimes_TV.setText(Double.toString(dispatchListItem.manHourActual) + "小时");
                break;
        }

        builder.setTitle("实际工时:");
        builder.setIcon(R.drawable.ic_launcher_green);

        builder.setView(view).setPositiveButton("提交", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.setNegativeButton("取消", null);
        builder.setCancelable(false);

        Dialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);

        return dialog;
    }

    private void initView(View view) {

        uncompItemDetail_LL = (LinearLayout) view.findViewById(R.id.ll_uncompItemDetail);
        compItemDetail_LL = (LinearLayout) view.findViewById(R.id.ll_compItemDetail);

        uncompItemDetailStartTime_TV = (TextView) view.findViewById(R.id.tv_uncompItemDetail_startTime);
        uncompItemDetailEndTime_TV = (TextView) view.findViewById(R.id.tv_uncompItemDetail_endTime);
        uncompItemDetailManHourActualTimes_TV = (TextView) view.findViewById(R.id.tv_uncompItemDetail_manHourActualTimes);

        compItemDetailStartTime_TV = (TextView) view.findViewById(R.id.tv_compItemDetail_startTime);
        compItemDetailEndTime_TV = (TextView) view.findViewById(R.id.tv_compItemDetail_endTime);
        compItemDetailManHourActualTimes_TV = (TextView) view.findViewById(R.id.tv_compItemDetail_manHourActualTimes);

    }
}
