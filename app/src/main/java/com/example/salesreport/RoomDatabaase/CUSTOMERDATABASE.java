package com.example.salesreport.RoomDatabaase;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.salesreport.RoomDatabaase.RoomDataBaseModel.AddCustomerDetail;

import com.example.salesreport.RoomDatabaase.RoomDataBaseModel.Converters;
import com.example.salesreport.RoomDatabaase.RoomDataBaseModel.DelerList;
import com.example.salesreport.RoomDatabaase.RoomDataBaseModel.FilterModelClass;
import com.example.salesreport.RoomDatabaase.RoomDataBaseModel.ProductListModel;
import com.example.salesreport.RoomDatabaase.RoomDataBaseModel.RouteList;
import com.example.salesreport.RoomDatabaase.RoomDataBaseModel.SalesModel;
import com.example.salesreport.RoomDatabaase.RoomDataBaseModel.StoreList;

@Database(entities = {AddCustomerDetail.class, ProductListModel.class, SalesModel.class, FilterModelClass.class}, version = 8/*,exportSchema = false*/)
@TypeConverters({Converters.class})
public abstract class CUSTOMERDATABASE extends RoomDatabase {

    public  abstract MyDao myDao();

}
