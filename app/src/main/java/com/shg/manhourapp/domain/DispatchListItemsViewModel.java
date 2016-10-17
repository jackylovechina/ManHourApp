package com.shg.manhourapp.domain;

import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/10/13 0013.
 */

public class DispatchListItemsViewModel implements Serializable{

    public int ManHourActual;
    public String MaterialName;
    public int Volume;
    public String ShiftName;
    public String ConstructionSiteName;
    public String EquipmentName;
    public String EmployeeNum;
    public String EmployeeName;
    public String EmployeeID;
    public String ManHourActualID;
}
