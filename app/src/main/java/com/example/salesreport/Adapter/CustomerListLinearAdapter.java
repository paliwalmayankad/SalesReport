package com.example.salesreport.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.salesreport.R;
import com.example.salesreport.RoomDatabaase.RoomDataBaseModel.AddCustomerDetail;

import java.io.ByteArrayOutputStream;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CustomerListLinearAdapter extends BaseAdapter {
    List<AddCustomerDetail> result;
    Context context;

    SharedPreferences sha;
    private static LayoutInflater inflater=null;
    String gamename;

    public CustomerListLinearAdapter(Context mcontext, List<AddCustomerDetail> productname) {
        this.result=productname;
        this.context=mcontext;

        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return result.size();
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

        TextView txt_username,txt_phone,txt_email,txt_debit_credit,txt_amount;
        LinearLayout linearlayout;
        CircleImageView circleimage;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        final CustomerListLinearAdapter.Holder holder=new CustomerListLinearAdapter.Holder();
        View rowView;

        rowView = inflater.inflate(R.layout.customer_linerlayout, null);

        holder.txt_username=(TextView) rowView.findViewById(R.id.txt_username);
        holder.txt_phone=(TextView) rowView.findViewById(R.id.txt_phone);
        holder.txt_email=(TextView) rowView.findViewById(R.id.txt_email);
        holder.txt_debit_credit=(TextView) rowView.findViewById(R.id.txt_debit_credit);
        holder.txt_amount=(TextView) rowView.findViewById(R.id.txt_amount);
        holder.circleimage=(CircleImageView) rowView.findViewById(R.id.circleimage);


        holder.txt_username.setText(result.get(position).getCustomer_name());
        holder.txt_phone.setText("Phone:- "+result.get(position).getCustomer_contact());
        holder.txt_email.setText("Email:-"+result.get(position).getCustomer_email());
        holder.txt_debit_credit.setText(result.get(position).getCustomer_debit_credit());
        holder.txt_amount.setText(result.get(position).getCustomer_amountpaid());

        try {

            String selectedImageUri = result.get(position).getCustomer_image();


            byte[] decodedString = Base64.decode(selectedImageUri, Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
           holder. circleimage.setImageBitmap(decodedByte);
            String as = "pp";
        } catch (Exception e) {
            e.printStackTrace();
            String s = e.toString();
        }



        return rowView;
    }

}