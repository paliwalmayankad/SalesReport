package com.example.salesreport;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DashboardActivty extends AppCompatActivity {

/*@BindView(R.id.btn_setting) ImageView btn_setting;
@BindView(R.id.img_refresh) ImageView img_refresh;*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_activty);
        ButterKnife.bind(this);
        /*img_refresh=findViewById(R.id.img_refresh);
        img_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            }
        });*/


    }
    @OnClick(R.id.btn_setting)
    void setting(){
        startActivity(new Intent(getApplicationContext(),MenuActivity.class));
    }
    @OnClick(R.id.layout_customers)
    void customers(){
        startActivity(new Intent(getApplicationContext(),CustomerActivity.class));
    }
 @OnClick(R.id.layout_products)
    void Products(){
        startActivity(new Intent(getApplicationContext(),ProductActivity.class));
    }
    @OnClick(R.id.layout_representatice)
    void represent(){
        startActivity(new Intent(getApplicationContext(),RepresentativeActivity.class));
    } @OnClick(R.id.layout_reports)
    void Report(){
        startActivity(new Intent(getApplicationContext(),ReportActivity.class));
    }
    @OnClick(R.id.layout_sales)
    void Sales(){
        startActivity(new Intent(getApplicationContext(),SalesActivity.class));
    }
    @OnClick(R.id.img_refresh)
    void loginandrefresh(){
        startActivity(new Intent(getApplicationContext(),LoginActivity.class));
    }



}
