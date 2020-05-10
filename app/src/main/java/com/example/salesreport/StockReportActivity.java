package com.example.salesreport;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StockReportActivity extends AppCompatActivity {
    String filtertype;
    String title;
    @BindView(R.id.txt_title)
    TextView txt_title;
    @BindView(R.id.txt_selecteddates)
    TextView txt_selecteddates;
    @BindView(R.id.btn_startdate)
    Button btn_startdate;
    @BindView(R.id.button_enddate)
    Button button_enddate;
    @BindView(R.id.layout_customdateoption)
    LinearLayout layout_customdateoption;
    String selectstartdate="",selectenddate="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_report);
        ButterKnife.bind(this);

        filtertype=getIntent().getStringExtra("filter");

        if(filtertype.equals("today")){
            title="Today";
        }
        if(filtertype.equals("yesterday")){
            title="Yesterday";
        }
        if(filtertype.equals("sevenday")){
            title="Last 7 Days";
        }
        if(filtertype.equals("thirtydays")){
            title="Last 30 Days";
        }
        if(filtertype.equals("previousmonth")){
            title="Previous Month";
        }
        if(filtertype.equals("currentmonth")){
            title="Current Month";
        }
        if(filtertype.equals("grandreport")){
            title="Grand Report";
        }
        if(filtertype.equals("customdates")){
            title="Custom Dates";
            layout_customdateoption.setVisibility(View.VISIBLE);
        }
        txt_title.setText(title);
    }
    @OnClick(R.id.img_back) void killfinish(){
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


                DatePickerDialog datePickerDialog = new DatePickerDialog(StockReportActivity.this ,
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


        DatePickerDialog datePickerDialog = new DatePickerDialog(StockReportActivity.this ,
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
