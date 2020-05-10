package com.example.salesreport.RoomDatabaase.RoomDataBaseModel;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;


public class RouteList {
   /* @PrimaryKey
    private  int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }*/

    public String getRoute_name() {
        return route_name;
    }

    public void setRoute_name(String route_name) {
        this.route_name = route_name;
    }

    public List<StoreList> getStore_list() {
        return store_list;
    }

    public void setStore_list(List<StoreList> store_list) {
        this.store_list = store_list;
    }

    @ColumnInfo(name="route_name")
    private String route_name;
    @ColumnInfo(name="store_list")
    private List<StoreList> store_list;
    @Override
    public String toString() {
        return route_name;
    }
}
