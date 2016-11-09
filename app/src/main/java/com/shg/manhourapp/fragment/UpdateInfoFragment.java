package com.shg.manhourapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.shg.manhourapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/18 0018.
 */

public class UpdateInfoFragment extends DialogFragment {

    private ViewPager updateInfo_VP;
    private List<Fragment> allFragments;


    Fragment mDateFragment;
    Fragment mTimeFragment;
    Fragment mRemarkFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.fragment_updateinfo,container,false);

        updateInfo_VP = (ViewPager) view.findViewById(R.id.vp_updateInfo);

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


        return view;
    }


    private List initFragments() {

        List<Fragment> fragments = new ArrayList<>();
        mDateFragment = new DateFragment();
        mTimeFragment=new TimeFragment();
        mRemarkFragment = new RemarkFragment();

        fragments.add(mDateFragment);
        fragments.add(mTimeFragment);
        fragments.add(mRemarkFragment);

        return fragments;

    }




}
