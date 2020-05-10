package com.example.salesreport.RoomDatabaase.RoomDataBaseModel;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="ProductSale")
public class SalesModel {
    @PrimaryKey(autoGenerate = true)
    private  int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPro_id() {
        return pro_id;
    }

    public void setPro_id(String pro_id) {
        this.pro_id = pro_id;
    }

    public String getSelling_date() {
        return selling_date;
    }

    public void setSelling_date(String selling_date) {
        this.selling_date = selling_date;
    }

    public String getSelling_route() {
        return selling_route;
    }

    public void setSelling_route(String selling_route) {
        this.selling_route = selling_route;
    }

    public String getSelling_distributor() {
        return selling_distributor;
    }

    public void setSelling_distributor(String selling_distributor) {
        this.selling_distributor = selling_distributor;
    }

    public String getSelling_store() {
        return selling_store;
    }

    public void setSelling_store(String selling_store) {
        this.selling_store = selling_store;
    }

    public String getSelling_qty() {
        return selling_qty;
    }

    public void setSelling_qty(String selling_qty) {
        this.selling_qty = selling_qty;
    }

    public String getSelling_pro_name() {
        return selling_pro_name;
    }

    public void setSelling_pro_name(String selling_pro_name) {
        this.selling_pro_name = selling_pro_name;
    }

    @ColumnInfo(name="pro_id")
    private String pro_id;
    @ColumnInfo(name="selling_date")
    private String selling_date;
    @ColumnInfo(name="selling_route")
    private String selling_route;
    @ColumnInfo(name="selling_distributor")
    private String selling_distributor;
    @ColumnInfo(name="selling_store")
    private String selling_store;
    @ColumnInfo(name="selling_qty")
    private String selling_qty;
    @ColumnInfo(name="selling_pro_name")
    private String selling_pro_name;
}
