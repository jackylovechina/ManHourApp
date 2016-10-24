package com.shg.manhourapp;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.shg.manhourapp.fragment.CompDispListFragment;
import com.shg.manhourapp.fragment.UnCompDispListFragment;


public class DispatchListActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private RadioGroup mRadioGroup;
    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;

    private Fragment mUnCompDispListFragment;
    private Fragment mCompDispListFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispatchlist);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mRadioGroup = (RadioGroup) findViewById(R.id.rg_main_check);
        mRadioGroup.setOnCheckedChangeListener(this);
        mFragmentManager = getSupportFragmentManager();

        this.onCheckedChanged(mRadioGroup, R.id.rb_main_check_uncomp);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        if (id == R.id.action_userinfo) {

            return true;
        }
        if (id == R.id.action_logout) {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        mFragmentTransaction = mFragmentManager.beginTransaction();

        switch (checkedId) {
            case R.id.rb_main_check_uncomp:
                if (mCompDispListFragment != null) {
                    mFragmentTransaction.hide(mCompDispListFragment);
                }
                if (mUnCompDispListFragment == null) {
                    mUnCompDispListFragment = new UnCompDispListFragment();
                    mFragmentTransaction.add(R.id.fl_main_content, mUnCompDispListFragment);
                } else {
                    mFragmentTransaction.show(mUnCompDispListFragment);

                }


                break;
            case R.id.rb_main_check_comp:
                if (mUnCompDispListFragment != null) {
                    mFragmentTransaction.hide(mUnCompDispListFragment);
                }
                if (mCompDispListFragment == null) {
                    mCompDispListFragment = new CompDispListFragment();
                    mFragmentTransaction.add(R.id.fl_main_content, mCompDispListFragment);
                } else {
                    mFragmentTransaction.show(mCompDispListFragment);

                }


                break;
        }
        mFragmentTransaction.commit();

    }

    private long mExitTime;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {

                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();

            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


}
