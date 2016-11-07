package com.shg.manhourapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.shg.manhourapp.domain.DispatchListBean;
import com.shg.manhourapp.domain.DispatchListItemsViewModel;
import com.shg.manhourapp.fragment.DetailFragment;
import com.shg.manhourapp.listadapter.DispatchItemsAdapter;
import com.shg.manhourapp.utils.DateTimeUtils;

public class DispatchItemsActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ImageView ItemDispatchImageview_IV;
    private TextView ItemDispatchListNum_TV;
    private TextView ItemDispatchScheduledTime_TV;
    private TextView ItemDispatchCreatTime_TV;

    private TextView ItemDispatchDepartmentName_TV;
    private TextView ItemDispatchGroupName_TV;
    private TextView ItemDispatchworkingProcedureName_TV;
    private TextView ItemDispatchmanHourTypeName_TV;

    private ListView mItemList_LV;

    private DispatchListBean mDispatchListItems;
    private int isComp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispatchitems);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        initItemView();

        Intent intent = getIntent();
        mDispatchListItems = (DispatchListBean) intent.getSerializableExtra("mDispatchListItems");
        isComp = intent.getIntExtra("isComp", 0);

        switch (isComp) {
            case 1:
                toolbar.setTitle("未完成派工单");
                break;
            case 2:
                toolbar.setTitle("已完成派工单");

                break;
        }
        setSupportActionBar(toolbar);

        initItemTitle();


        mItemList_LV.setAdapter(new DispatchItemsAdapter(mDispatchListItems.dispatchListItemsViewModel));
        mItemList_LV.setOnItemClickListener(this);

    }

    private void initItemView() {

        ItemDispatchImageview_IV = (ImageView) findViewById(R.id.iv_dispatchItem_imageView);
        ItemDispatchListNum_TV = (TextView) findViewById(R.id.tv_dispatchItem_dispatchListNum);
        ItemDispatchScheduledTime_TV = (TextView) findViewById(R.id.tv_dispatchItem_scheduledTime);
        ItemDispatchCreatTime_TV = (TextView) findViewById(R.id.tv_dispatchItem_creatTime);

        ItemDispatchDepartmentName_TV = (TextView) findViewById(R.id.tv_dispatchItem_departmentName);
        ItemDispatchGroupName_TV = (TextView) findViewById(R.id.tv_dispatchItem_groupName);
        ItemDispatchworkingProcedureName_TV = (TextView) findViewById(R.id.tv_dispatchItem_workingProcedureName);
        ItemDispatchmanHourTypeName_TV = (TextView) findViewById(R.id.tv_dispatchItem_manHourTypeName);

        mItemList_LV = (ListView) findViewById(R.id.lv_dispatchItem);
    }

    private void initItemTitle() {

        switch (isComp) {
            case 1:
                ItemDispatchImageview_IV.setImageResource(R.drawable.uncomp_72px);
                break;
            case 2:
                ItemDispatchImageview_IV.setImageResource(R.drawable.comp_72px);
                break;
        }

        ItemDispatchListNum_TV.setText("派工单编号:" + mDispatchListItems.num);
        ItemDispatchScheduledTime_TV.setText("计划时间:" + DateTimeUtils.getDateTime(mDispatchListItems.scheduledTime));
        ItemDispatchCreatTime_TV.setText("创建时间:" + DateTimeUtils.getDateTime(mDispatchListItems.creatTime));

        ItemDispatchDepartmentName_TV.setText("部门:" + mDispatchListItems.departmentName);
        ItemDispatchGroupName_TV.setText("班组:" + mDispatchListItems.groupName);
        ItemDispatchworkingProcedureName_TV.setText("工序:" + mDispatchListItems.workingProcedureName);
        ItemDispatchmanHourTypeName_TV.setText("类型:" + mDispatchListItems.manHourTypeName);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        DispatchListItemsViewModel dispatchListItem = mDispatchListItems.dispatchListItemsViewModel.get(position);

        DetailFragment detailFragment = new DetailFragment();
        detailFragment.getItemDetail(dispatchListItem,isComp);
        detailFragment.show(getSupportFragmentManager(), "");

    }
}
