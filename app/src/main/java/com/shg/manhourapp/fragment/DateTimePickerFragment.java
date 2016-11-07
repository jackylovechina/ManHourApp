package com.shg.manhourapp.fragment;

import android.app.AlertDialog;
import android.app.Dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.shg.manhourapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/18 0018.
 */

public class DateTimePickerFragment extends DialogFragment implements ViewPager.OnPageChangeListener{

    private ViewPager DatePickerPager_VP;
    private FragmentPagerAdapter pagerAdapter;
    private List<Fragment> fragments = new ArrayList<Fragment>();
    private Context context;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        initFragments();

        View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_datetimepicker, null);
        DatePickerPager_VP = (ViewPager) view.findViewById(R.id.vp_dataTimePickerPager);

        pagerAdapter = new FragmentPagerAdapter(getFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        };
        DatePickerPager_VP.setAdapter(pagerAdapter);
        DatePickerPager_VP.addOnPageChangeListener(this);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);


        return builder.create();
    }
    private void initFragments(){
        Fragment mDateFragment=new DateFragment();
        Fragment mTimeFragment=new TimeFragment();

        fragments.add(mDateFragment);
        fragments.add(mTimeFragment);

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
