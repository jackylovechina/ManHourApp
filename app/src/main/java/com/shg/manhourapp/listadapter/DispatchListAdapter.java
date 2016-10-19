package com.shg.manhourapp.listadapter;

import android.support.v7.widget.RecyclerView;
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
//        ImageView DispatchListImage_IV;
//        TextView DispatchListNum_TV;
//        TextView ScheduledTime_TV;
//        TextView CreatTime_TV;
//        TextView DepartmentName_TV;
//        TextView GroupName_TV;
//        TextView WorkingProcedureName_TV;
//        TextView ManHourTypeName_TV;

        ViewHolder viewHolder = null;
        if (convertView == null) {

            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.dispatch_listview_item, parent, false);
            viewHolder = new ViewHolder();


            viewHolder.DispatchListImage_IV = (ImageView) convertView.findViewById(R.id.iv_dispatchList_imageView);
            viewHolder.DispatchListNum_TV = (TextView) convertView.findViewById(R.id.tv_dispatchList_dispatchListNum);
            viewHolder.ScheduledTime_TV = (TextView) convertView.findViewById(R.id.tv_dispatchList_scheduledTime);
            viewHolder.CreatTime_TV = (TextView) convertView.findViewById(R.id.tv_dispatchList_creatTime);
            viewHolder.DepartmentName_TV = (TextView) convertView.findViewById(R.id.tv_dispatchList_departmentName);
            viewHolder.GroupName_TV = (TextView) convertView.findViewById(R.id.tv_dispatchList_groupName);
            viewHolder.WorkingProcedureName_TV = (TextView) convertView.findViewById(R.id.tv_dispatchList_workingProcedureName);
            viewHolder.ManHourTypeName_TV = (TextView) convertView.findViewById(R.id.tv_dispatchList_manHourTypeName);

            convertView.setTag(viewHolder);
        } else

            viewHolder = (ViewHolder) convertView.getTag();


        switch (mIndex) {
            case 1:
                viewHolder.DispatchListImage_IV.setImageResource(R.drawable.uncomp_72px);
                break;
            case 2:
                viewHolder.DispatchListImage_IV.setImageResource(R.drawable.comp_72px);
                break;
            default:
                viewHolder.DispatchListImage_IV.setImageResource(R.drawable.comp_72px);
                break;
        }
        viewHolder.DispatchListNum_TV.setText("派工单编号:" + mDispatchListItems.get(position).Num);
        viewHolder.ScheduledTime_TV.setText("计划时间:" + mDispatchListItems.get(position).ScheduledTime.substring(0, 19));
        viewHolder.CreatTime_TV.setText("创建时间:" + mDispatchListItems.get(position).CreatTime.substring(0, 19));
        viewHolder.DepartmentName_TV.setText("部门:" + mDispatchListItems.get(position).DepartmentName);
        viewHolder.GroupName_TV.setText("班组:" + mDispatchListItems.get(position).GroupName);
        viewHolder.WorkingProcedureName_TV.setText("工序:" + mDispatchListItems.get(position).WorkingProcedureName);
        viewHolder.ManHourTypeName_TV.setText("类型:" + mDispatchListItems.get(position).ManHourTypeName);


        return convertView;
    }

    private static class ViewHolder {

        ImageView DispatchListImage_IV;
        TextView DispatchListNum_TV;
        TextView ScheduledTime_TV;
        TextView CreatTime_TV;
        TextView DepartmentName_TV;
        TextView GroupName_TV;
        TextView WorkingProcedureName_TV;
        TextView ManHourTypeName_TV;

    }


}
