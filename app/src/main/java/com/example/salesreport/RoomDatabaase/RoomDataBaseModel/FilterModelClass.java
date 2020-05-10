package com.example.salesreport.RoomDatabaase.RoomDataBaseModel;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.List;

@Entity(tableName = "FilterTable")
public class FilterModelClass {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<DelerList> getDealer_list() {
        return dealer_list;
    }

    public void setDealer_list(List<DelerList> dealer_list) {
        this.dealer_list = dealer_list;
    }

    @PrimaryKey(autoGenerate = true)
    private  int id;


    public String getDealer_name() {
        return dealer_name;
    }

    public void setDealer_name(String dealer_name) {
        this.dealer_name = dealer_name;
    }

    @TypeConverters(Converters.class)
    private List<DelerList> dealer_list;
    @ColumnInfo(name="dealer_name")
    private String dealer_name;
    @Override
    public String toString() {
        return dealer_name;
    }


}

