package com.shg.manhourapp.listadapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.shg.manhourapp.R;
import com.shg.manhourapp.domain.DispatchListItemsViewModel;

import java.util.List;

/**
 * Created by Administrator on 2016/10/14 0014.
 */

public class DispatchItemsAdapter extends BaseAdapter {

    private List<DispatchListItemsViewModel> dispatchListItemsViewModels;

    public DispatchItemsAdapter(List<DispatchListItemsViewModel> dispatchListItemsViewModels) {
        this.dispatchListItemsViewModels = dispatchListItemsViewModels;

    }

    @Override
    public int getCount() {
        return dispatchListItemsViewModels.size();
    }

    @Override
    public Object getItem(int position) {
        return dispatchListItemsViewModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        TextView ItemListConstructionSiteName_TV;
//        TextView ItemListEquipmentName_TV;
//        TextView ItemListMaterialName_TV;
//        TextView ItemListVolume_TV;
//        TextView ItemListShiftName_TV;

        ViewHolder viewHolder = null;

        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.dispatchitem_listview_item, parent, false);
            viewHolder = new ViewHolder();

            viewHolder.ItemListConstructionSiteName_TV = (TextView) convertView.findViewById(R.id.tv_listitem_ConstructionSiteName);
            viewHolder.ItemListEquipmentName_TV = (TextView) convertView.findViewById(R.id.tv_listitem_EquipmentName);
            viewHolder.ItemListMaterialName_TV = (TextView) convertView.findViewById(R.id.tv_listitem_MaterialName);
            viewHolder.ItemListVolume_TV = (TextView) convertView.findViewById(R.id.tv_listitem_Volume);
            viewHolder.ItemListShiftName_TV = (TextView) convertView.findViewById(R.id.tv_listitem_ShiftName);

            convertView.setTag(viewHolder);
        } else
            viewHolder = (ViewHolder) convertView.getTag();

        viewHolder.ItemListConstructionSiteName_TV.setText("场地:" + dispatchListItemsViewModels.get(position).constructionSiteName);
        viewHolder.ItemListEquipmentName_TV.setText("设备:" + dispatchListItemsViewModels.get(position).equipmentName);
        viewHolder.ItemListMaterialName_TV.setText("物料:" + dispatchListItemsViewModels.get(position).materialName);
        viewHolder.ItemListVolume_TV.setText("物量:" + dispatchListItemsViewModels.get(position).volume);
        viewHolder.ItemListShiftName_TV.setText("班次:" + dispatchListItemsViewModels.get(position).shiftName);

        return convertView;
    }

    private static class ViewHolder {
        TextView ItemListConstructionSiteName_TV;
        TextView ItemListEquipmentName_TV;
        TextView ItemListMaterialName_TV;
        TextView ItemListVolume_TV;
        TextView ItemListShiftName_TV;
    }
}
