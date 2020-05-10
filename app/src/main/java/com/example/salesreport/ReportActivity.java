package com.example.salesreport;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ReportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        ButterKnife.bind(this);
    }
    @OnClick(R.id.txt_business) void onBusiness(){
        Intent i=new Intent(getApplicationContext(),FilterActivity.class);
        i.putExtra("title","Business Report");
        i.putExtra("type","business");
        startActivity(i);
    }  @OnClick(R.id.txt_sales) void onSales(){
        Intent i=new Intent(getApplicationContext(),FilterActivity.class);
        i.putExtra("title","Stock Report");
        i.putExtra("type","stock");
        startActivity(i);
    }

    @OnClick(R.id.img_back) void imgback(){
        finish();
    }

}
