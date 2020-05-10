package com.example.salesreport.RoomDatabaase.RoomDataBaseModel;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;


 public class DelerList {
   /* @PrimaryKey
    private  int id;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }*/

    public String getDealer_name() {
        return dealer_name;
    }

    public void setDealer_name(String dealer_name) {
        this.dealer_name = dealer_name;
    }

    public List<RouteList> getRoute_list() {
        return route_list;
    }

    public void setRoute_list(List<RouteList> route_list) {
        this.route_list = route_list;
    }

    @ColumnInfo(name="dealer_name")
    private String dealer_name;
    @ColumnInfo(name="route_list")
    private List<RouteList> route_list;

}
