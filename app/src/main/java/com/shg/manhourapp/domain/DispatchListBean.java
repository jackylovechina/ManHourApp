package com.shg.manhourapp.domain;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/10/13 0013.
 */

public class DispatchListBean implements Serializable{

    public String ID;
    public String Num;
    public String ScheduledTime;
    public String ComplateTime;
    public String CreatTime;
    public String DispatchException;
    public String GroupName;
    public String ManHourTypeName;
    public String WorkingProcedureName;
    public String DepartmentName;

    public List<DispatchListItemsViewModel> DispatchListItemsViewModel;
}
