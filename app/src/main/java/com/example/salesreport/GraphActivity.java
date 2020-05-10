package com.example.salesreport;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GraphActivity extends AppCompatActivity {
@BindView(R.id.txt_title)
    TextView txt_title;

@BindView(R.id.txt_barchart)
    TextView txt_barchart;
    @BindView(R.id.txt_linescahrt)
    TextView txt_linescahrt;
    @BindView(R.id.txt_piechart)
    TextView txt_piechart;
@BindView(R.id.barchart)
BarChart barchart;
@BindView(R.id.piechart)
PieChart piechart;
@BindView(R.id.linechart)
LineChart linechart;

@BindView(R.id.layout_barchart)
LinearLayout layout_barchart;

    @BindView(R.id.layout_piechart)
    LinearLayout layout_piechart;

    @BindView(R.id.layout_linechart)
    LinearLayout layout_linechart;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        ButterKnife.bind(this);
        txt_title.setText(getIntent().getStringExtra("title"));
        _createbarchart();

    }
@OnClick(R.id.img_back) void killactivity(){
        finish();
}
  @OnClick(R.id.txt_barchart) void barchart(){
      txt_barchart.setTextColor(getResources().getColor(R.color.color_white));
      txt_barchart.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

      txt_linescahrt.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
      txt_linescahrt.setBackgroundColor(getResources().getColor(R.color.color_white));

      txt_piechart.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
      txt_piechart.setBackgroundColor(getResources().getColor(R.color.color_white));
      layout_barchart.setVisibility(View.VISIBLE);
      layout_linechart.setVisibility(View.GONE);
      layout_piechart.setVisibility(View.GONE);
      _createbarchart();

  }

    private void _createbarchart() {
        //// BAR CHART
        ArrayList<BarEntry> NoOfEmp = new ArrayList();

        NoOfEmp.add(new BarEntry(2008, 0));
        NoOfEmp.add(new BarEntry(2009, 1));
        NoOfEmp.add(new BarEntry(2010, 2));
        NoOfEmp.add(new BarEntry(2011, 3));
        NoOfEmp.add(new BarEntry(2012, 4));
        NoOfEmp.add(new BarEntry(2013, 5));
        NoOfEmp.add(new BarEntry(2014, 6));
        NoOfEmp.add(new BarEntry(2015, 7));
        NoOfEmp.add(new BarEntry(2016, 8));
        NoOfEmp.add(new BarEntry(2017, 9));

        ArrayList<String> year = new ArrayList<String>();

        year.add("2008");
        year.add("2009");
        year.add("2010");
        year.add("2011");
        year.add("2012");
        year.add("2013");
        year.add("2014");
        year.add("2015");
        year.add("2016");
        year.add("2017");

        BarDataSet bardataset = new BarDataSet(NoOfEmp, "No Of Employee");
        barchart.animateY(5000);
        BarData data = new BarData( bardataset);
        bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
        barchart.setData(data);

    }

    @OnClick(R.id.txt_linescahrt) void linechart(){
        txt_linescahrt.setTextColor(getResources().getColor(R.color.color_white));
        txt_linescahrt.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

        txt_barchart.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        txt_barchart.setBackgroundColor(getResources().getColor(R.color.color_white));

        txt_piechart.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        txt_piechart.setBackgroundColor(getResources().getColor(R.color.color_white));
        layout_barchart.setVisibility(View.GONE);
        layout_linechart.setVisibility(View.VISIBLE);
        layout_piechart.setVisibility(View.GONE);
        _creataealiechart();

    }

    private void _creataealiechart() {



        ArrayList<String> xVals = new ArrayList<>();
        xVals.add("10");
        xVals.add("20");
        xVals.add("30");
        xVals.add("30.5");
        xVals.add("40");


        ArrayList<Entry> yVals = new ArrayList<>();
        yVals.add(new Entry(60, 0));
        yVals.add(new Entry(48, 1));
        yVals.add(new Entry(70.5f, 2));
        yVals.add(new Entry(100, 3));
        yVals.add(new Entry(180.9f, 4));

        LineDataSet set1;

        // create a dataset and give it a type
        set1 = new LineDataSet(yVals, "DataSet 1");

        set1.setFillAlpha(110);
        // set1.setFillColor(Color.RED);

        // set the line to be drawn like this "- - - - - -"
        //   set1.enableDashedLine(10f, 5f, 0f);
        // set1.enableDashedHighlightLine(10f, 5f, 0f);
        set1.setColor(Color.BLACK);
        set1.setCircleColor(Color.BLACK);
        set1.setLineWidth(1f);
        set1.setCircleRadius(3f);
        set1.setDrawCircleHole(false);
        set1.setValueTextSize(9f);
        set1.setDrawFilled(true);

        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        dataSets.add(set1); // add the datasets

        // create a data object with the datasets
        LineData data = new LineData( dataSets);

        // set data
        linechart.setData(data);






        Legend l = linechart.getLegend();

        // modify the legend ...
        // l.setPosition(LegendPosition.LEFT_OF_CHART);
        l.setForm(Legend.LegendForm.LINE);

        // no description text


        // enable touch gestures
        linechart.setTouchEnabled(true);

        // enable scaling and dragging
        linechart.setDragEnabled(true);
        linechart.setScaleEnabled(true);
        // linechart.setScaleXEnabled(true);
        // linechart.setScaleYEnabled(true);

        LimitLine upper_limit = new LimitLine(130f, "Upper Limit");
        upper_limit.setLineWidth(4f);
        upper_limit.enableDashedLine(10f, 10f, 0f);
        upper_limit.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);
        upper_limit.setTextSize(10f);

        LimitLine lower_limit = new LimitLine(-30f, "Lower Limit");
        lower_limit.setLineWidth(4f);
        lower_limit.enableDashedLine(10f, 10f, 0f);
        lower_limit.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
        lower_limit.setTextSize(10f);

        YAxis leftAxis = linechart.getAxisLeft();
        leftAxis.removeAllLimitLines(); // reset all limit lines to avoid overlapping lines
        leftAxis.addLimitLine(upper_limit);
        leftAxis.addLimitLine(lower_limit);
        leftAxis.setAxisMaxValue(220f);
        leftAxis.setAxisMinValue(-50f);
        //leftAxis.setYOffset(20f);
        leftAxis.enableGridDashedLine(10f, 10f, 0f);
        leftAxis.setDrawZeroLine(false);

        // limit lines are drawn behind data (and not on top)
        leftAxis.setDrawLimitLinesBehindData(true);

        linechart.getAxisRight().setEnabled(false);

        //linechart.getViewPortHandler().setMaximumScaleY(2f);
        //linechart.getViewPortHandler().setMaximumScaleX(2f);

        linechart.animateX(2500, Easing.EaseInOutQuart);

        //  dont forget to refresh the drawing
        linechart.invalidate();

    }

    @OnClick(R.id.txt_piechart) void piechart(){
        txt_piechart.setTextColor(getResources().getColor(R.color.color_white));
        txt_piechart.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

        txt_linescahrt.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        txt_linescahrt.setBackgroundColor(getResources().getColor(R.color.color_white));

        txt_barchart.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        txt_barchart.setBackgroundColor(getResources().getColor(R.color.color_white));
        layout_barchart.setVisibility(View.GONE);
        layout_linechart.setVisibility(View.GONE);
        layout_piechart.setVisibility(View.VISIBLE);
        _createpiechart();

    }

    private void _createpiechart() {
try {
    ArrayList<PieEntry> NoOfEmp = new ArrayList<PieEntry>();

    NoOfEmp.add(new PieEntry(2008, 0));
    NoOfEmp.add(new PieEntry(2009, 1));
    NoOfEmp.add(new PieEntry(2010, 2));
    NoOfEmp.add(new PieEntry(2011, 3));
    NoOfEmp.add(new PieEntry(2012, 4));
    NoOfEmp.add(new PieEntry(2013, 5));
    NoOfEmp.add(new PieEntry(2014, 6));
    NoOfEmp.add(new PieEntry(2015, 7));
    NoOfEmp.add(new PieEntry(2016, 8));
    NoOfEmp.add(new PieEntry(2017, 9));
    PieDataSet dataSet = new PieDataSet(NoOfEmp, "Number Of Employees");

    ArrayList year = new ArrayList();

    year.add("2008");
    year.add("2009");
    year.add("2010");
    year.add("2011");
    year.add("2012");
    year.add("2013");
    year.add("2014");
    year.add("2015");
    year.add("2016");
    year.add("2017");
    PieData data = new PieData(dataSet);
    piechart.setData(data);
    dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
    piechart.animateXY(5000, 5000);
}
catch (Exception e){
    String ss=e.toString();

}
    }
}
