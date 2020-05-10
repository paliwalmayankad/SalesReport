package com.example.salesreport.RoomDatabaase.RoomDataBaseModel;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


public class StoreList {
    /*@PrimaryKey
    private  int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }*/


    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    @ColumnInfo(name="name")
    private String store_name;
    @Override
    public String toString() {
        return store_name;
    }
}
