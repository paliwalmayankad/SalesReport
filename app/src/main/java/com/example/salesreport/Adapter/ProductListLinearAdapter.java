package com.example.salesreport.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.salesreport.Models.ProductModel;
import com.example.salesreport.R;
import com.example.salesreport.RoomDatabaase.RoomDataBaseModel.AddCustomerDetail;
import com.example.salesreport.RoomDatabaase.RoomDataBaseModel.ProductListModel;

import java.util.List;

public class ProductListLinearAdapter extends BaseAdapter {
    List<ProductModel.DataBean> result;
    List<ProductListModel> roomdatalsit;
    Context context;

    SharedPreferences sha;
    private static LayoutInflater inflater=null;
    String gamename;

    public ProductListLinearAdapter(Context mcontext, List<ProductModel.DataBean> productname, List<ProductListModel> productlistroom) {
        this.result=productname;
        this.context=mcontext;
        this.roomdatalsit=productlistroom;

        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        if(result.size()>0){
            return result.size();
        }
        else
            return roomdatalsit.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder
    {

        TextView txt_producttitle,txt_cat,txt_stock,txt_offerprice,txt_actualprice;
        LinearLayout linearlayout;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        final ProductListLinearAdapter.Holder holder=new ProductListLinearAdapter.Holder();
        View rowView;

        rowView = inflater.inflate(R.layout.product_linearlayout, null);

        holder.txt_producttitle=(TextView) rowView.findViewById(R.id.txt_producttitle);
        holder.txt_cat=(TextView) rowView.findViewById(R.id.txt_cat);
        holder.txt_stock=(TextView) rowView.findViewById(R.id.txt_stock);
        holder.txt_offerprice=(TextView) rowView.findViewById(R.id.txt_offerprice);
        holder.txt_actualprice=(TextView) rowView.findViewById(R.id.txt_actualprice);
      /*  holder.txt_phone=(TextView) rowView.findViewById(R.id.txt_phone);
        holder.txt_email=(TextView) rowView.findViewById(R.id.txt_email);
        holder.txt_debit_credit=(TextView) rowView.findViewById(R.id.txt_debit_credit);
        holder.txt_amount=(TextView) rowView.findViewById(R.id.txt_amount);*/

if(result.size()>0){
    holder.txt_producttitle.setText(result.get(position).getName());
    holder.txt_cat.setText(result.get(position).getCategories());
    holder.txt_stock.setText(result.get(position).getStock());
    holder.txt_offerprice.setText(result.get(position).getSell_price());
    holder.txt_actualprice.setText(result.get(position).getActual_price());
}
else{
    holder.txt_producttitle.setText(roomdatalsit.get(position).getPro_name());
    holder.txt_cat.setText(roomdatalsit.get(position).getCategories());
    holder.txt_stock.setText(roomdatalsit.get(position).getPro_stock());
    holder.txt_offerprice.setText(roomdatalsit.get(position).getPro_sell_price());
    holder.txt_actualprice.setText(roomdatalsit.get(position).getPro_actual_price());
}

       /* holder.txt_phone.setText("Phone:- "+result.get(position).getCustomer_contact());
        holder.txt_email.setText("Email:-"+result.get(position).getCustomer_email());
        holder.txt_debit_credit.setText(result.get(position).getCustomer_debit_credit());
        holder.txt_amount.setText(result.get(position).getCustomer_amountpaid());*/





        return rowView;
    }

}