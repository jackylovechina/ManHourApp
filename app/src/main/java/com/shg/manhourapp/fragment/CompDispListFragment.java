package com.shg.manhourapp.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shg.manhourapp.DispatchItemsActivity;
import com.shg.manhourapp.R;
import com.shg.manhourapp.domain.DispatchListBean;
import com.shg.manhourapp.listadapter.DispatchListAdapter;
import com.shg.manhourapp.utils.ServerApi;

import org.xutils.HttpManager;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.Serializable;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CompDispListFragment extends Fragment implements AdapterView.OnItemClickListener{
    private ListView mCompDispatchListView;
    private List<DispatchListBean> mCompDispatchLists;

    public CompDispListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_compdisplist, container, false);
        mCompDispatchListView = (ListView) view.findViewById(R.id.lv_comp_dispatchlistview);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        HttpManager httpManager = x.http();
        String url = ServerApi.Address;
        String order = ServerApi.GET_COMPLETE;


        RequestParams params = new RequestParams(url + order);
        params.addParameter("employeeID", "E23DA3DF-A7E5-48EA-BF3D-7FDF76DA511E");

        httpManager.get(params, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {


                Gson gson = new Gson();
                mCompDispatchLists = gson.fromJson(result, new TypeToken<List<DispatchListBean>>() {
                }.getType());

                mCompDispatchListView.setAdapter(new DispatchListAdapter(mCompDispatchLists,2));
                mCompDispatchListView.setOnItemClickListener(CompDispListFragment.this);



            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent = new Intent();
        intent.putExtra("mDispatchListItems", (Serializable) mCompDispatchLists.get(position));
        intent.putExtra("isComp",2);
        intent.setClass(getActivity(), DispatchItemsActivity.class);
        startActivity(intent);

    }
}
