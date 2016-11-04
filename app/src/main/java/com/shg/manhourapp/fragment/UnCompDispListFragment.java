package com.shg.manhourapp.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
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
import com.shg.manhourapp.domain.DispatchListItemsViewModel;
import com.shg.manhourapp.listadapter.DispatchListAdapter;
import com.shg.manhourapp.utils.ServerApi;

import org.xutils.HttpManager;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class UnCompDispListFragment extends Fragment implements AdapterView.OnItemClickListener, SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private ListView mUnCompDispatchListView;

    private List<DispatchListBean> mUnCompDispatchLists;

    private HttpManager httpManager;
    private RequestParams params;


    public UnCompDispListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_uncompdisplist, container, false);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.srl_uncomp_dropRefresh);
        mUnCompDispatchListView = (ListView) view.findViewById(R.id.lv_uncomp_dispatchlistview);

        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        httpManager = x.http();
        String url = ServerApi.Address;
        String order = ServerApi.GET_NOCOMPLETE;

        params = new RequestParams(url + order);
        params.addParameter("employeeID", "9195a2f0-97a3-4e52-982d-6e92e9544841");

        onRefresh();

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        List<DispatchListItemsViewModel> dispatchListItemsViewModels = new ArrayList<>();
        dispatchListItemsViewModels = mUnCompDispatchLists.get(position).DispatchListItemsViewModel;

        Intent intent = new Intent();
        intent.putExtra("mDispatchListItems", (Serializable) mUnCompDispatchLists.get(position));
        intent.putExtra("isComp", 1);
        intent.setClass(getActivity(), DispatchItemsActivity.class);
        startActivity(intent);


    }

    @Override
    public void onRefresh() {


        httpManager.get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                Gson gson = new Gson();
                mUnCompDispatchLists = gson.fromJson(result, new TypeToken<List<DispatchListBean>>() {
                }.getType());

                mUnCompDispatchListView.setAdapter(new DispatchListAdapter(mUnCompDispatchLists, 1));
                mUnCompDispatchListView.setOnItemClickListener(UnCompDispListFragment.this);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

                mSwipeRefreshLayout.setRefreshing(false);

            }
        });

    }
}
