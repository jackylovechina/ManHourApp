package com.shg.manhourapp.fragment;

import android.app.AlertDialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.shg.manhourapp.R;
import com.shg.manhourapp.domain.DispatchListItemsViewModel;

/**
 * Created by Administrator on 2016/10/14 0014.
 */

public class DetailFragment extends DialogFragment {

    private EditText uncompItemDetailManHourActual_ET;
    private TextView compItemDetailManHourActual_TV;

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
        uncompItemDetailManHourActual_ET = (EditText) view.findViewById(R.id.et_uncompItemDetail_ManHourActual);
        compItemDetailManHourActual_TV = (TextView) view.findViewById(R.id.tv_compItemDetail_ManHourActual);

        switch (isComp) {

            case 1:
                uncompItemDetailManHourActual_ET.setVisibility(View.VISIBLE);
                uncompItemDetailManHourActual_ET.setText(Double.toString(dispatchListItem.ManHourActual));
                break;
            case 2:
                compItemDetailManHourActual_TV.setVisibility(View.VISIBLE);
                compItemDetailManHourActual_TV.setText(Double.toString(dispatchListItem.ManHourActual)+"小时");
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
}
