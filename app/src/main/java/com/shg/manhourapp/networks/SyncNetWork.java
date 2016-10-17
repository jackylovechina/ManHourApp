package com.shg.manhourapp.networks;

import android.os.AsyncTask;

import com.shg.manhourapp.utils.ServerApi;

import org.xutils.HttpManager;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Administrator on 2016/10/17 0017.
 */

public class SyncNetWork extends AsyncTask{


    String result;
    @Override
    protected Object doInBackground(Object[] params) {
        HttpManager httpManager = x.http();

        RequestParams requestParams=new RequestParams(ServerApi.Address+ServerApi.GET_COMPLETE);
        requestParams.addParameter("employeeID", "E23DA3DF-A7E5-48EA-BF3D-7FDF76DA511E");
        try {
            result = httpManager.getSync(requestParams, String.class);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }
}
