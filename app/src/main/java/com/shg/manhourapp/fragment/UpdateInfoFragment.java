package com.shg.manhourapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.shg.manhourapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/18 0018.
 */

public class UpdateInfoFragment extends DialogFragment implements View.OnClickListener, ViewPager.OnPageChangeListener {

    private View view;
    private ViewPager updateInfo_VP;
    private Button updateInfoNext_BT;
    private Button updateInfoBefore_BT;

    private TextView textView;
    private String datetimeStr;
    private String[] datetime;
    private String dateStr;
    private String timeStr;

    private List<Fragment> allFragments;
    private int index = 0;

    Fragment mDateFragment;
    Fragment mTimeFragment;

    public void getTextView(TextView tv) {

        this.textView = tv;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        view = inflater.inflate(R.layout.fragment_updateinfo, container, false);

        updateInfo_VP = (ViewPager) view.findViewById(R.id.vp_updateInfo);
        updateInfoNext_BT = (Button) view.findViewById(R.id.bt_updateinfo_next);
        updateInfoBefore_BT = (Button) view.findViewById(R.id.bt_updateinfo_before);

        datetimeStr = textView.getText().toString();
        if (datetimeStr.equals("开始时间") || datetimeStr.equals("结束时间")) {
//            Log.d("MyLog", "ERROR");
        } else {
            datetime = datetimeStr.split(" ");
            dateStr = datetime[0];
            timeStr = datetime[1];
            Log.d("MyLog", dateStr + "|" + timeStr);
        }

        allFragments = initFragments();

        updateInfo_VP.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return allFragments.get(position);
            }

            @Override
            public int getCount() {
                return allFragments.size();
            }
        });
        updateInfo_VP.addOnPageChangeListener(this);
        updateInfoBefore_BT.setOnClickListener(this);
        updateInfoNext_BT.setOnClickListener(this);

        ((ImageView) view.findViewById(R.id.iv_updateinfo_date)).setImageResource(R.drawable.black_pot);

        return view;
    }


    private List<Fragment> initFragments() {

        List<Fragment> fragments = new ArrayList<Fragment>();
        Bundle bundle = new Bundle();

        mDateFragment = new DateFragment();
        bundle.putString("dateStr", dateStr);
        mDateFragment.setArguments(bundle);


        mTimeFragment = new TimeFragment();
        bundle.putString("timeStr", timeStr);
        mTimeFragment.setArguments(bundle);


        fragments.add(mDateFragment);
        fragments.add(mTimeFragment);


        return fragments;

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_updateinfo_next:
                if (index < allFragments.size() - 1) {

                    updateInfo_VP.setCurrentItem(index + 1);

                } else {
                    Log.d("MyLog", "end");



                }
                break;
            case R.id.bt_updateinfo_before:
                if (index > 0) {

                    updateInfo_VP.setCurrentItem(index - 1);

                } else {
                    Log.d("MyLog", "start");
                }


                break;
        }

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        index = position;
        resetPageIndex();

        switch (position) {
            case 0:
                ((ImageView) view.findViewById(R.id.iv_updateinfo_date)).setImageResource(R.drawable.black_pot);

                break;
            case 1:
                ((ImageView) view.findViewById(R.id.iv_updateinfo_time)).setImageResource(R.drawable.black_pot);
                break;

        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void resetPageIndex() {

        ((ImageView) view.findViewById(R.id.iv_updateinfo_date)).setImageResource(R.drawable.green_pot);

        ((ImageView) view.findViewById(R.id.iv_updateinfo_time)).setImageResource(R.drawable.green_pot);

    }
}
