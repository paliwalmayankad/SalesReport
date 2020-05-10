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
import com.example.salesreport.RoomDatabaase.RoomDataBaseModel.ProductListModel;

import java.util.List;

public class ProductListGridAdapter extends BaseAdapter {
    List<ProductModel.DataBean> result;
    List<ProductListModel> roomdatalsit;
    Context context;

    SharedPreferences sha;
    private static LayoutInflater inflater=null;
    String gamename;

    public ProductListGridAdapter(Context mcontext, List<ProductModel.DataBean> productname, List<ProductListModel> productlistroom) {
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

        TextView txt_producttitle,txt_phone,txt_email,txt_debit_credit,txt_amount;
        LinearLayout linearlayout;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        final ProductListGridAdapter.Holder holder=new ProductListGridAdapter.Holder();
        View rowView;

        rowView = inflater.inflate(R.layout.product_gridlayout, null);

        holder.txt_producttitle=(TextView) rowView.findViewById(R.id.txt_producttitle);
      /*  holder.txt_phone=(TextView) rowView.findViewById(R.id.txt_phone);
        holder.txt_email=(TextView) rowView.findViewById(R.id.txt_email);
        holder.txt_debit_credit=(TextView) rowView.findViewById(R.id.txt_debit_credit);
        holder.txt_amount=(TextView) rowView.findViewById(R.id.txt_amount);*/

        if(result.size()>0){
            holder.txt_producttitle.setText(result.get(position).getName());
        }
        else{
            holder.txt_producttitle.setText(roomdatalsit.get(position).getPro_name());
        }

       /* holder.txt_phone.setText("Phone:- "+result.get(position).getCustomer_contact());
        holder.txt_email.setText("Email:-"+result.get(position).getCustomer_email());
        holder.txt_debit_credit.setText(result.get(position).getCustomer_debit_credit());
        holder.txt_amount.setText(result.get(position).getCustomer_amountpaid());*/





        return rowView;
    }

}