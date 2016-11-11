package com.shg.manhourapp.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.shg.manhourapp.R;
import com.shg.manhourapp.domain.DispatchListItemsViewModel;
import com.shg.manhourapp.utils.DateTimeUtils;
import com.shg.manhourapp.utils.ServerApi;

import org.xutils.HttpManager;
import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.zip.DeflaterInputStream;

/**
 * Created by Administrator on 2016/10/14 0014.
 */

public class DetailFragment extends DialogFragment implements View.OnClickListener {

    private LinearLayout uncompItemDetail_LL;
    private LinearLayout compItemDetail_LL;

    private TextView uncompItemDetailStartTime_TV;
    private TextView uncompItemDetailEndTime_TV;
    private TextView uncompItemDetailManHourActualTimes_TV;
    private EditText uncompItemDetailRemark_ET;

    private TextView compItemDetailStartTime_TV;
    private TextView compItemDetailEndTime_TV;
    private TextView compItemDetailManHourActualTimes_TV;
    private TextView compItemDetailRemark_TV;

    private DispatchListItemsViewModel dispatchListItem;
    private int isComp;

    private DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
    private String startTime;
    private String endTime;

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
                uncompItemDetailManHourActualTimes_TV.setText("实动工时:" + Double.toString(dispatchListItem.manHourActual) + "小时");


                if (dispatchListItem.completeDatetime != null && dispatchListItem.completeDatetime.length() != 0) {

                    uncompItemDetailStartTime_TV.setText(getStartTime(DateTimeUtils.getDateTime(dispatchListItem.completeDatetime), 5.5));
                    uncompItemDetailEndTime_TV.setText(DateTimeUtils.getDateTime(dispatchListItem.completeDatetime));

                }

                break;
            case 2:
                compItemDetail_LL.setVisibility(View.VISIBLE);
                compItemDetailManHourActualTimes_TV.setText("实动工时:" + Double.toString(dispatchListItem.manHourActual) + "小时");

                if (dispatchListItem.completeDatetime != null && dispatchListItem.completeDatetime.length() != 0) {
                    compItemDetailEndTime_TV.setText("" + DateTimeUtils.getDateTime(dispatchListItem.completeDatetime));
                }

                break;
        }
        uncompItemDetailStartTime_TV.setOnClickListener(this);
        uncompItemDetailEndTime_TV.setOnClickListener(this);

        builder.setTitle("实际工时:");
        builder.setIcon(R.drawable.ic_launcher_green);

        builder.setView(view);
        builder.setPositiveButton("提交", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                startTime = uncompItemDetailStartTime_TV.getText().toString();
                endTime = uncompItemDetailEndTime_TV.getText().toString();
                if (startTime.equals("开始时间") || endTime.equals("结束时间")) {

                    Toast.makeText(getActivity(), "请填写正确的时间格式,提交失败", Toast.LENGTH_SHORT).show();

                    try {
                        Field field = dialog.getClass().getSuperclass().getDeclaredField("mShowing");
                        field.setAccessible(true);
                        field.set(dialog, false);
                    } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }

                } else {

                    HttpManager update_manager = x.http();
                    String url = ServerApi.Address;
                    String order = ServerApi.UPDATE;
                    RequestParams putParams = new RequestParams(url + order);

//                    Map<String, Object> map = new HashMap<String, Object>();
//                    map.put("ManHourActualID", dispatchListItem.manHourActualID);
//                    map.put("ManHourActual", compute_manHourActual(startTime, endTime));
//                    map.put("CompleteDatetime", endTime);
//                    map.put("Remark", "");
//
//                    map.put("MaterialName", "");
//                    map.put("Volume", "");
//                    map.put("ShiftName", "");
//                    map.put("ConstructionSiteName", "");
//                    map.put("EquipmentName", "");
//                    map.put("EmployeeNum", "");
//                    map.put("EmployeeName", "");
//                    map.put("EmployeeID", "");
                    DispatchListItemsViewModel d = new DispatchListItemsViewModel();
                    d.manHourActualID = dispatchListItem.manHourActualID;
                    d.manHourActual = compute_manHourActual(startTime, endTime);
                    d.completeDatetime = endTime;
                    d.remark = "";

                    Gson gson = new Gson();

                    String viewModel = gson.toJson(d);

                    Log.d("MyLog", viewModel);


//                    putParams.addBodyParameter("manHourActualID", dispatchListItem.manHourActualID);
//                    putParams.addBodyParameter("manHourActual", Double.toString(compute_manHourActual(startTime, endTime)));
//                    putParams.addBodyParameter("completeDatetime", endTime);
//                    putParams.addBodyParameter("remark", "");

//                    putParams.addBodyParameter("viewModel", viewModel);

                    putParams.setAsJsonContent(true);
                    putParams.setBodyContent(viewModel);

                    update_manager.request(HttpMethod.PUT, putParams, new Callback.CommonCallback<String>() {
                        @Override
                        public void onSuccess(String result) {

                            Log.d("MyLog", result);

                        }

                        @Override
                        public void onError(Throwable ex, boolean isOnCallback) {

                            Log.d("MyLog", isOnCallback + "|" + ex.toString());

                        }

                        @Override
                        public void onCancelled(CancelledException cex) {

                        }

                        @Override
                        public void onFinished() {

                        }
                    });

                    try {
                        Field field = dialog.getClass().getSuperclass().getDeclaredField("mShowing");
                        field.setAccessible(true);
                        field.set(dialog, true);
                    } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }

                }

            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    Field field = dialog.getClass().getSuperclass().getDeclaredField("mShowing");
                    field.setAccessible(true);
                    field.set(dialog, true);
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });
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
        uncompItemDetailRemark_ET = (EditText) view.findViewById(R.id.et_uncompItemDetail_remark);

        compItemDetailStartTime_TV = (TextView) view.findViewById(R.id.tv_compItemDetail_startTime);
        compItemDetailEndTime_TV = (TextView) view.findViewById(R.id.tv_compItemDetail_endTime);
        compItemDetailManHourActualTimes_TV = (TextView) view.findViewById(R.id.tv_compItemDetail_manHourActualTimes);
        compItemDetailRemark_TV = (TextView) view.findViewById(R.id.tv_compItemDetail_remark);
    }

    private String getStartTime(String endTime, double actualHours) {

        String startTime = null;

        if (endTime != null && endTime.length() != 0) {

            format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);

            try {
                Date endTime_date = format.parse(endTime);
                long endTime_long = endTime_date.getTime();
                long startTime_long = endTime_long - (long) (actualHours * 3600 * 1000);
//                Log.d("MyLog", endTime_long + "|||" + startTime_long);
                Date startTime_date = new Date(startTime_long);
//                Log.d("MyLog", endTime_date + "|||" + startTime_date);
                startTime = format.format(startTime_date);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            return startTime;
        }

        return null;
    }

    private double compute_manHourActual(String startTime, String endTime) {

        double manHours = 0.00;
        DecimalFormat df = new DecimalFormat("######0.00");

        try {
            long s_Time = format.parse(startTime).getTime();
            long e_Time = format.parse(endTime).getTime();

            manHours = (double) (e_Time - s_Time) / (3600 * 1000);
            manHours = Double.parseDouble(df.format(manHours));

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return manHours;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_uncompItemDetail_startTime:
                UpdateInfoFragment startTimePicker = new UpdateInfoFragment();
                startTimePicker.getTextView(uncompItemDetailStartTime_TV);
                startTimePicker.show(getFragmentManager(), "startTime");
                break;
            case R.id.tv_uncompItemDetail_endTime:
                UpdateInfoFragment endTimePicker = new UpdateInfoFragment();
                endTimePicker.getTextView(uncompItemDetailEndTime_TV);
                endTimePicker.show(getFragmentManager(), "endTime");
                break;
        }
    }
}
