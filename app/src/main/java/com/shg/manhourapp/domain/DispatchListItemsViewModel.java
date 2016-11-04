package com.shg.manhourapp.domain;

import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/10/13 0013.
 */

public class DispatchListItemsViewModel implements Serializable{

    public double manHourActual;
    public String materialName;
    public double volume;
    public String shiftName;
    public String constructionSiteName;
    public String equipmentName;
    public String employeeNum;
    public String employeeName;
    public String employeeID;
    public String manHourActualID;
    public String completeDatetime;
}
