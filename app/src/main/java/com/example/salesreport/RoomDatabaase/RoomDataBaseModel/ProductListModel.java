package com.example.salesreport.RoomDatabaase.RoomDataBaseModel;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="Product")
public class ProductListModel {
    @PrimaryKey(autoGenerate = true)
    private  int id;

    @ColumnInfo(name="pro_id")
    private String pro_id;

    @ColumnInfo(name="pro_name")
    private String pro_name;
    @ColumnInfo(name="categories_ids")
    private String categories_ids;
    @ColumnInfo(name="pro_qty_type")
    private String pro_qty_type;

    @ColumnInfo(name="pro_currency")
    private String pro_currency;

    @ColumnInfo(name="pro_sku")
    private String pro_sku;

    @ColumnInfo(name="pro_actual_price")
    private String pro_actual_price;

    @ColumnInfo(name="pro_sell_price")
    private String pro_sell_price;

    @ColumnInfo(name="pro_stock")
    private String pro_stock;


    @ColumnInfo(name="pro_barcode")
    private String pro_barcode;

    @ColumnInfo(name="pro_categories")
    private String pro_categories;

    @ColumnInfo(name="pro_image")
    private String pro_image;

    @ColumnInfo(name="categories")
    private String categories;
    @ColumnInfo(name="stores")
    private String stores;

    public String getCategories_ids() {
        return categories_ids;
    }

    public void setCategories_ids(String categories_ids) {
        this.categories_ids = categories_ids;
    }

    public String getStores() {
        return stores;
    }

    public void setStores(String stores) {
        this.stores = stores;
    }

    public String getRoutes() {
        return routes;
    }

    public void setRoutes(String routes) {
        this.routes = routes;
    }

    public String getDistributers() {
        return distributers;
    }

    public void setDistributers(String distributers) {
        this.distributers = distributers;
    }

    @ColumnInfo(name="routes")
    private String routes;
    @ColumnInfo(name="distributers")
    private String distributers;

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

    public String getPro_name() {
        return pro_name;
    }

    public void setPro_name(String pro_name) {
        this.pro_name = pro_name;
    }

    public String getPro_qty_type() {
        return pro_qty_type;
    }

    public void setPro_qty_type(String pro_qty_type) {
        this.pro_qty_type = pro_qty_type;
    }

    public String getPro_currency() {
        return pro_currency;
    }

    public void setPro_currency(String pro_currency) {
        this.pro_currency = pro_currency;
    }

    public String getPro_sku() {
        return pro_sku;
    }

    public void setPro_sku(String pro_sku) {
        this.pro_sku = pro_sku;
    }

    public String getPro_actual_price() {
        return pro_actual_price;
    }

    public void setPro_actual_price(String pro_actual_price) {
        this.pro_actual_price = pro_actual_price;
    }

    public String getPro_sell_price() {
        return pro_sell_price;
    }

    public void setPro_sell_price(String pro_sell_price) {
        this.pro_sell_price = pro_sell_price;
    }

    public String getPro_stock() {
        return pro_stock;
    }

    public void setPro_stock(String pro_stock) {
        this.pro_stock = pro_stock;
    }

    public String getPro_barcode() {
        return pro_barcode;
    }

    public void setPro_barcode(String pro_barcode) {
        this.pro_barcode = pro_barcode;
    }

    public String getPro_categories() {
        return pro_categories;
    }

    public void setPro_categories(String pro_categories) {
        this.pro_categories = pro_categories;
    }

    public String getPro_image() {
        return pro_image;
    }

    public void setPro_image(String pro_image) {
        this.pro_image = pro_image;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }
}
