package com.shg.manhourapp.listadapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.shg.manhourapp.R;
import com.shg.manhourapp.domain.DispatchListBean;
import com.shg.manhourapp.utils.DateTimeUtils;

import org.w3c.dom.Text;

import java.sql.Date;
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
            viewHolder.DispatchException_TV = (TextView) convertView.findViewById(R.id.tv_dispatchList_dispatchException);

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
        viewHolder.DispatchListNum_TV.setText("派工单编号:" + mDispatchListItems.get(position).num);
        viewHolder.ScheduledTime_TV.setText("计划时间:" + DateTimeUtils.getDateTime(mDispatchListItems.get(position).scheduledTime));
        viewHolder.CreatTime_TV.setText("创建时间:" + DateTimeUtils.getDateTime(mDispatchListItems.get(position).creatTime));
        viewHolder.DepartmentName_TV.setText("部门:" + mDispatchListItems.get(position).departmentName);
        viewHolder.GroupName_TV.setText("班组:" + mDispatchListItems.get(position).groupName);
        viewHolder.WorkingProcedureName_TV.setText("工序:" + mDispatchListItems.get(position).workingProcedureName);
        viewHolder.ManHourTypeName_TV.setText("类型:" + mDispatchListItems.get(position).manHourTypeName);
        if (mDispatchListItems.get(position).dispatchException != null)
            viewHolder.DispatchException_TV.setText("派工单异常:"+mDispatchListItems.get(position).dispatchException);


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
        TextView DispatchException_TV;

    }


}
