package com.example.salesreport;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FilterActivity extends AppCompatActivity {
@BindView(R.id.txt_title)
TextView txt_title;
String type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        ButterKnife.bind(this);
        txt_title.setText(getIntent().getStringExtra("title"));
   type=   getIntent().getStringExtra("type");
    }
    @OnClick(R.id.img_back) void imgback(){
        finish();
    }

@OnClick(R.id.txt_today) void today(){
    Intent i=new Intent();
    if(type.equals("stock")){
        i.setClass(getApplicationContext(),StockReportActivity.class);
    }
    else{
        i.setClass(getApplicationContext(),BusinessReportActivity.class);
    }
    i.putExtra("filter","today");
    startActivity(i);

}
    @OnClick(R.id.txt_yesterday) void yesterday()
    {
        Intent i=new Intent();
        if(type.equals("stock")){
            i.setClass(getApplicationContext(),StockReportActivity.class);
        }
        else{
            i.setClass(getApplicationContext(),BusinessReportActivity.class);
        }
        i.putExtra("filter","yesterday");
        startActivity(i);
    }
    @OnClick(R.id.txt_last_seven_days) void last_sevendays()
    {
        Intent i=new Intent();
        if(type.equals("stock")){
            i.setClass(getApplicationContext(),StockReportActivity.class);
        }
        else{
            i.setClass(getApplicationContext(),BusinessReportActivity.class);
        }
        i.putExtra("filter","sevenday");
        startActivity(i);
    }
    @OnClick(R.id.txt_lastthirtydays) void lastthirtydays()
    {
        Intent i=new Intent();
        if(type.equals("stock")){
            i.setClass(getApplicationContext(),StockReportActivity.class);
        }
        else{
            i.setClass(getApplicationContext(),BusinessReportActivity.class);
        }
        i.putExtra("filter","thirtydays");
        startActivity(i);
    }
    @OnClick(R.id.txt_previousmonth) void previousmonth()
    {
        Intent i=new Intent();
        if(type.equals("stock")){
            i.setClass(getApplicationContext(),StockReportActivity.class);
        }
        else{
            i.setClass(getApplicationContext(),BusinessReportActivity.class);
        }
        i.putExtra("filter","previousmonth");
        startActivity(i);
    }
    @OnClick(R.id.txt_currentmonth) void currentmonth()
    {
        Intent i=new Intent();
        if(type.equals("stock")){
            i.setClass(getApplicationContext(),StockReportActivity.class);
        }
        else{
            i.setClass(getApplicationContext(),BusinessReportActivity.class);
        }
        i.putExtra("filter","currentmonth");
        startActivity(i);
    }
    @OnClick(R.id.txt_grandreport) void grandreport()
    {
        Intent i=new Intent();
        if(type.equals("stock")){
            i.setClass(getApplicationContext(),StockReportActivity.class);
        }
        else{
            i.setClass(getApplicationContext(),BusinessReportActivity.class);
        }
        i.putExtra("filter","grandreport");
        startActivity(i);
}
@OnClick(R.id.txt_custdates) void custdates(){
    Intent i=new Intent();
    if(type.equals("stock")){
        i.setClass(getApplicationContext(),StockReportActivity.class);
    }
    else{
        i.setClass(getApplicationContext(),BusinessReportActivity.class);
    }
    i.putExtra("filter","customdates");
    startActivity(i);
    }

}
