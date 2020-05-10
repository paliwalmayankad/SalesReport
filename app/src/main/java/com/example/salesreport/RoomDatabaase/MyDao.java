package com.example.salesreport.RoomDatabaase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.salesreport.RoomDatabaase.RoomDataBaseModel.AddCustomerDetail;
import com.example.salesreport.RoomDatabaase.RoomDataBaseModel.FilterModelClass;
import com.example.salesreport.RoomDatabaase.RoomDataBaseModel.ProductListModel;
import com.example.salesreport.RoomDatabaase.RoomDataBaseModel.SalesModel;

import java.util.List;
@Dao
public interface MyDao {

    @Insert
    public void addProductList(ProductListModel productdeetail);
    @Insert
    public void addCustomer(AddCustomerDetail addcustomer);

   /* @Insert
    public  void soldpproductdetail(SoldProductDetailModel solddetail);*/

    @Query("select * from Product")
    public List<ProductListModel> getProductList();

    @Query("select * from Customer")
    public List<AddCustomerDetail> getCustomerList();
    @Query("DELETE FROM Product")
    public void deletemyproducttable();
    @Query("DELETE FROM FilterTable")
    public void deletemyfiltertable();
    /*@Delete
    public void deleteaccountdetail(ProductListModel user);


    @Update
    public void updateaccountdetail(ProductListModel user);*/

    @Insert
    public void addSalesRecord(SalesModel productdeetail);
    @Query("select * from productsale")
    public List<SalesModel> getsellinglist();
    @Query("select * from FilterTable")
    public List<FilterModelClass> getfilterlsit();

    @Query("DELETE FROM productsale")
    public void deletemysaleslist();
    @Insert
    public void addfilter(FilterModelClass addcustomer);

    @Query("SELECT * FROM Product WHERE distributers LIKE :dealer" + " OR routes LIKE :route" + " OR stores like :store")
    List< ProductListModel > search(String dealer,String route,String store);

}
