package com.example.salesreport;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.salesreport.Adapter.CustomerListGridAdapter;
import com.example.salesreport.Adapter.CustomerListLinearAdapter;
import com.example.salesreport.RoomDatabaase.CUSTOMERDATABASE;
import com.example.salesreport.RoomDatabaase.RoomDataBaseModel.AddCustomerDetail;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CustomerActivity extends AppCompatActivity {
@BindView(R.id.seg_name)
    TextView seg_name;
@BindView(R.id.seg_debit)
    TextView seg_debit;
@BindView(R.id.seg_credit)
    TextView seg_credit;
@BindView(R.id.img_linear)
    ImageView img_linear;
@BindView(R.id.img_grid)
ImageView img_grid;
@BindView(R.id.img_back)
ImageView img_back;
@BindView(R.id.customer_listview)
ListView customer_listview;
@BindView(R.id.customerGridView)
GridView customerGridView;
    String[] listItem={"Mayank","ram","Rahul"};
    List<AddCustomerDetail> customerlist;
    CUSTOMERDATABASE custdatabase;

@BindView(R.id.edt_search)
    EditText edt_search;
    @BindView(R.id.txt_nodatafound)
    TextView txt_nodatafound;
    CustomerListLinearAdapter linearadapter;
    CustomerListGridAdapter adaptera;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_customer);
       ButterKnife.bind(this);
       custdatabase= Room.databaseBuilder(getApplicationContext(),CUSTOMERDATABASE.class,"SUVADB").allowMainThreadQueries().build();
       customerlist=custdatabase.myDao().getCustomerList();

        linearadapter=new CustomerListLinearAdapter(getApplicationContext(),customerlist);
       customer_listview.setAdapter(linearadapter);
        adaptera= new CustomerListGridAdapter(getApplicationContext(),customerlist);
       customerGridView.setAdapter(adaptera);
        List<AddCustomerDetail> searchlsit=new ArrayList<>();
        edt_search.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //get the text in the EditText
          /*      searchlist.clear();
                String searchString=edt_search.getText().toString();
                int textLength=searchString.length();

                //clear the initial data set


                for(int i=0;i<customerlist.size();i++)
                {
                    String playerName=customerlist.get(i).toString();
                    if(textLength<=playerName.length()){
                        //compare the String in EditText with Names in the ArrayList
                        if(searchString.equalsIgnoreCase(playerName.substring(0,textLength)))
                            searchlist.add(customerlist.get(i));
                    }
                }
              *//*  linadapter=new ProductListLinearAdapter(getApplicationContext(),productlist,searchlist);
                customer_listview.setAdapter(linadapter);
                gridadapter=new ProductListGridAdapter(getApplicationContext(),productlist,searchlist);
                // customer_listview.setAdapter(adapter);
                customerGridView.setAdapter(gridadapter);*//*

                if(searchlist.size()>0){
                    txt_nodatafound.setVisibility(View.GONE);
                }
                else{
                    txt_nodatafound.setVisibility(View.VISIBLE);
                }
                CustomerListLinearAdapter linearadapter=new CustomerListLinearAdapter(getApplicationContext(),searchlist);
                customer_listview.setAdapter(linearadapter);
                CustomerListGridAdapter adaptera= new CustomerListGridAdapter(getApplicationContext(),searchlist);
                customerGridView.setAdapter(adaptera);*/

                String searchString=edt_search.getText().toString();
                int textLength=searchString.length();

                //clear the initial data set
                searchlsit.clear();

                for(int i=0;i<customerlist.size();i++)
                {
                    String playerName=customerlist.get(i).getCustomer_name().toString();
                    if(textLength<=playerName.length()){
                        //compare the String in EditText with Names in the ArrayList
                        if(searchString.equalsIgnoreCase(playerName.substring(0,textLength)))
                            searchlsit.add(customerlist.get(i));
                    }
                }
              /*  linadapter=new ProductListLinearAdapter(getApplicationContext(),productlist,searchlist);
                customer_listview.setAdapter(linadapter);
                gridadapter=new ProductListGridAdapter(getApplicationContext(),productlist,searchlist);
                // customer_listview.setAdapter(adapter);
                customerGridView.setAdapter(gridadapter);*/

                if(searchlsit.size()>0){
                    txt_nodatafound.setVisibility(View.GONE);
                }
                else{
                    txt_nodatafound.setVisibility(View.VISIBLE);
                }

                 linearadapter=new CustomerListLinearAdapter(getApplicationContext(),searchlsit);
                customer_listview.setAdapter(linearadapter);
                 adaptera= new CustomerListGridAdapter(getApplicationContext(),searchlsit);
                customerGridView.setAdapter(adaptera);





            }

            @Override
            public void afterTextChanged(Editable s) {

            }


        });

    }
    @OnClick({R.id.seg_name}) void Seg_one(){
        seg_name.setTextColor(getResources().getColor(R.color.color_white));
        seg_name.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

        seg_debit.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        seg_debit.setBackgroundColor(getResources().getColor(R.color.color_white));
        seg_credit.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        seg_credit.setBackgroundColor(getResources().getColor(R.color.color_white));
    }
    @OnClick(R.id.seg_debit) void seg_debit(){
        seg_debit.setTextColor(getResources().getColor(R.color.color_white));
        seg_debit.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

        seg_name.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        seg_name.setBackgroundColor(getResources().getColor(R.color.color_white));
        seg_credit.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        seg_credit.setBackgroundColor(getResources().getColor(R.color.color_white));

    }
    @OnClick(R.id.seg_credit) void seg_credit(){
        seg_credit.setTextColor(getResources().getColor(R.color.color_white));
        seg_credit.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

        seg_name.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        seg_name.setBackgroundColor(getResources().getColor(R.color.color_white));
        seg_debit.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        seg_debit.setBackgroundColor(getResources().getColor(R.color.color_white));
    }
    @OnClick(R.id.img_linear) void img_linear(){
        img_grid.setBackgroundColor(getResources().getColor(R.color.color_white));
        img_grid.setImageResource(R.drawable.grid_blue);
        img_linear.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        img_linear.setImageResource(R.drawable.linear_white);
        customer_listview.setVisibility(View.VISIBLE);
        customerGridView.setVisibility(View.GONE);

    }
    @OnClick(R.id.img_grid) void img_grid(){
        img_grid.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        img_grid.setImageResource(R.drawable.grid_white);
        img_linear.setBackgroundColor(getResources().getColor(R.color.color_white));
        img_linear.setImageResource(R.drawable.linear_blue);

        customer_listview.setVisibility(View.GONE);
        customerGridView.setVisibility(View.VISIBLE);

    }
    @OnClick(R.id.add_customer) void add_customer(){
     startActivity(new Intent(getApplicationContext(),AddCustomerActivity.class));

    }
    @OnClick(R.id.img_back) void finishactivity(){
     finish();

    }

    @Override
    protected void onResume() {
        super.onResume();
        customerlist=custdatabase.myDao().getCustomerList();

        CustomerListLinearAdapter linearadapter=new CustomerListLinearAdapter(getApplicationContext(),customerlist);
        customer_listview.setAdapter(linearadapter);

    }
}
