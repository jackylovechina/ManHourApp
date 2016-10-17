package com.shg.manhourapp.listadapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.shg.manhourapp.R;
import com.shg.manhourapp.domain.DispatchListBean;

import java.util.List;

/**
 * Created by Administrator on 2016/10/9 0009.
 */

public class DispatchListAdapter extends BaseAdapter {

    private List<DispatchListBean> mDispatchListItems;
    private int mIndex;

    public DispatchListAdapter(List<DispatchListBean> mDispatchListItems) {
        this.mDispatchListItems = mDispatchListItems;
    }

    public DispatchListAdapter(List<DispatchListBean> mDispatchListItems, int mIndex) {
        this.mDispatchListItems = mDispatchListItems;
        this.mIndex = mIndex;
    }

    @Override
    public int getCount() {
        return mDispatchListItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mDispatchListItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView DispatchListImage_IV;
        TextView DispatchListNum_TV;
        TextView ScheduledTime_TV;
        TextView CreatTime_TV;
        TextView DepartmentName_TV;
        TextView GroupName_TV;
        TextView WorkingProcedureName_TV;
        TextView ManHourTypeName_TV;

        if (convertView == null)

            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.dispatch_listview_item, parent, false);

        DispatchListImage_IV= (ImageView) convertView.findViewById(R.id.iv_dispatchList_imageView);
        DispatchListNum_TV = (TextView) convertView.findViewById(R.id.tv_dispatchList_dispatchListNum);
        ScheduledTime_TV = (TextView) convertView.findViewById(R.id.tv_dispatchList_scheduledTime);
        CreatTime_TV = (TextView) convertView.findViewById(R.id.tv_dispatchList_creatTime);
        DepartmentName_TV = (TextView) convertView.findViewById(R.id.tv_dispatchList_departmentName);
        GroupName_TV = (TextView) convertView.findViewById(R.id.tv_dispatchList_groupName);
        WorkingProcedureName_TV = (TextView) convertView.findViewById(R.id.tv_dispatchList_workingProcedureName);
        ManHourTypeName_TV = (TextView) convertView.findViewById(R.id.tv_dispatchList_manHourTypeName);


        switch (mIndex){
            case 1:
                DispatchListImage_IV.setImageResource(R.drawable.uncomp_72px);
                break;
            case 2:
                DispatchListImage_IV.setImageResource(R.drawable.comp_72px);
                break;
            default:
                DispatchListImage_IV.setImageResource(R.drawable.comp_72px);
                break;
        }
        DispatchListNum_TV.setText("派工单编号:" + mDispatchListItems.get(position).Num);
        ScheduledTime_TV.setText("计划时间:" + mDispatchListItems.get(position).ScheduledTime.substring(0, 19));
        CreatTime_TV.setText("创建时间:" + mDispatchListItems.get(position).CreatTime.substring(0, 19));
        DepartmentName_TV.setText("部门:"+mDispatchListItems.get(position).DepartmentName);
        GroupName_TV.setText("班组:"+mDispatchListItems.get(position).GroupName);
        WorkingProcedureName_TV.setText("工序:"+mDispatchListItems.get(position).WorkingProcedureName);
        ManHourTypeName_TV.setText("类型:"+mDispatchListItems.get(position).ManHourTypeName);


        return convertView;
    }
}
