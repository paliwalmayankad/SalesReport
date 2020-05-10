package com.example.salesreport;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.salesreport.Adapter.ProductListGridAdapter;
import com.example.salesreport.Adapter.ProductListLinearAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SalesActivity extends AppCompatActivity {
    @BindView(R.id.seg_product)
    TextView seg_product;
    @BindView(R.id.seg_customers)
    TextView seg_customers;
    @BindView(R.id.seg_recent)
    TextView seg_recent;
    @BindView(R.id.spinner)
    Spinner spinner;
    @BindView(R.id.img_linear)
    ImageView img_linear;
    @BindView(R.id.img_grid)
    ImageView img_grid;
    @BindView(R.id.customer_listview)
    ListView customer_listview;
    @BindView(R.id.customerGridView)
    GridView customerGridView;
    @BindView(R.id.edt_search)
    TextView edt_search;

    String filtertype;


    @BindView(R.id.txt_selecteddates)
    TextView txt_selecteddates;
    @BindView(R.id.btn_startdate)
    Button btn_startdate;
    @BindView(R.id.button_enddate)
    Button button_enddate;
    @BindView(R.id.layout_customdateoption)
    LinearLayout layout_customdateoption;
    String selectstartdate="",selectenddate="";

    @BindView(R.id.txt_nodatafound)
    TextView txt_nodatafound;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales);
        ButterKnife.bind(this);
        List<String> genderlist = new ArrayList<String>();
        genderlist.add("Today");
        genderlist.add("Yesterday");
        genderlist.add("Last 7 days");
        genderlist.add("Current Month");
        genderlist.add("Previous Month");
        genderlist.add("Custom Range");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, genderlist);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);


       ArrayList<String> listItem= new ArrayList<>();
        listItem.add("Mayank");
        listItem.add("ram");
        listItem.add("Rahul");


        ArrayAdapter<String> linearadapter=new ArrayAdapter<String>(getApplicationContext(),R.layout.sales_record_linear,R.id.txt_producttitle,listItem);
        ArrayAdapter<String> gridadapter=new ArrayAdapter<String>(getApplicationContext(),R.layout.slaes_record_grid,R.id.txt_producttitle,listItem);
        customer_listview.setAdapter(linearadapter);
        customerGridView.setAdapter(gridadapter);

        ArrayList<String> searchlsit=new ArrayList<>();



        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) view).setTextColor(Color.WHITE);
                filtertype=genderlist.get(position);
                if(filtertype.equals("Custom Range")){
                    layout_customdateoption.setVisibility(View.VISIBLE);
                }
                else{
                    layout_customdateoption.setVisibility(View.GONE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        edt_search.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //get the text in the EditText
                String searchString=edt_search.getText().toString();
                int textLength=searchString.length();

                //clear the initial data set
                searchlsit.clear();

                for(int i=0;i<listItem.size();i++)
                {
                    String playerName=listItem.get(i).toString();
                    if(textLength<=playerName.length()){
                        //compare the String in EditText with Names in the ArrayList
                        if(searchString.equalsIgnoreCase(playerName.substring(0,textLength)))
                            searchlsit.add(listItem.get(i));
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
                ArrayAdapter<String> linearadapter=new ArrayAdapter<String>(getApplicationContext(),R.layout.sales_record_linear,R.id.txt_producttitle,searchlsit);
                ArrayAdapter<String> gridadapter=new ArrayAdapter<String>(getApplicationContext(),R.layout.slaes_record_grid,R.id.txt_producttitle,searchlsit);
                customer_listview.setAdapter(linearadapter);
                customerGridView.setAdapter(gridadapter);


            }

            @Override
            public void afterTextChanged(Editable s) {

            }


        });
    }

    @OnClick({R.id.seg_product}) void seg_product(){
        seg_product.setTextColor(getResources().getColor(R.color.color_white));
        seg_product.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

        seg_customers.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        seg_customers.setBackgroundColor(getResources().getColor(R.color.color_white));
        seg_recent.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        seg_recent.setBackgroundColor(getResources().getColor(R.color.color_white));
    }
    @OnClick(R.id.seg_customers) void seg_customers(){
        seg_customers.setTextColor(getResources().getColor(R.color.color_white));
        seg_customers.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

        seg_product.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        seg_product.setBackgroundColor(getResources().getColor(R.color.color_white));
        seg_recent.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        seg_recent.setBackgroundColor(getResources().getColor(R.color.color_white));

    }
    @OnClick(R.id.seg_recent) void seg_recent(){
        seg_recent.setTextColor(getResources().getColor(R.color.color_white));
        seg_recent.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

        seg_customers.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        seg_customers.setBackgroundColor(getResources().getColor(R.color.color_white));
        seg_product.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        seg_product.setBackgroundColor(getResources().getColor(R.color.color_white));
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

    } @OnClick(R.id.add_customer) void add_customer(){
        //startActivity(new Intent(getApplicationContext(),AddCustomerActivity.class));

    } @OnClick(R.id.img_back) void finishactivity(){
        finish();

    }
    @OnClick(R.id.button_enddate) void enddate(){
        try{


            if(selectstartdate==null||selectstartdate.isEmpty()||selectstartdate.equals("")||selectstartdate.equals(" ")){
                Toast.makeText(getApplicationContext(),"Please select From date",Toast.LENGTH_SHORT).show();
            }
            else {
                int mYear, mMonth, mDay, mHour, mMinute;
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(SalesActivity.this ,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {


                                try {
                                    selectenddate=dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
                                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
                                    Date date1 = sdf.parse(selectstartdate);
                                    Date date2 = sdf.parse(selectenddate);

                                    if (date2.before(date1)){
                                        Toast.makeText(getApplicationContext(),"Please select date after "+selectstartdate,Toast.LENGTH_SHORT).show();
                                    }
                                    else{
                                        button_enddate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                                        txt_selecteddates.setVisibility(View.VISIBLE);
                                        txt_selecteddates.setText("Date:- "+selectstartdate+"-"+selectenddate);
                                        // Toast.makeText(getApplicationContext(),"aage gyo",Toast.LENGTH_SHORT).show();

                                    }


                                } catch (Exception e) {
                                    e.printStackTrace();
                                }





                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();


            }
        }
        catch (Exception e){
            String s=e.toString();
        }
    }

    @OnClick(R.id.btn_startdate) void startdate(){
        int mYear, mMonth, mDay, mHour, mMinute;
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(SalesActivity.this ,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        btn_startdate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                        selectstartdate=dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
                        //txt_selecteddates.setText("Date:- "+selectstartdate+"-"+selectenddate);
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();

    }
}
