package com.example.salesreport;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddAProductActivity extends AppCompatActivity {
    @BindView(R.id.spinner_qntytype)
    Spinner spinner_qntytype;
    @BindView(R.id.spinner_curremcy)
    Spinner spinner_curremcy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_a_product);

        ButterKnife.bind(this);

        //// FOR QNTY TYPE
            List<String> TYPELIST = new ArrayList<String>();
        TYPELIST.add("Quantity Type");
        TYPELIST.add("Box");
        TYPELIST.add("Case");
        TYPELIST.add("Centimeter");
        TYPELIST.add("Feet");
        TYPELIST.add("Gallon");
        TYPELIST.add("Gram");
        TYPELIST.add("Inch");
        TYPELIST.add("Kilogram");
        TYPELIST.add("Kilometer");
        TYPELIST.add("Litre");
        TYPELIST.add("Meter");
        TYPELIST.add("Mile");
        TYPELIST.add("Miligram");
        TYPELIST.add("MiliLitre");
        TYPELIST.add("Milimeter");
        TYPELIST.add("Pack");
        TYPELIST.add("Piece");
        TYPELIST.add("Pound");
        TYPELIST.add("Set");
        TYPELIST.add("Ton");
        TYPELIST.add("Yard");
        TYPELIST.add("Unit");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, TYPELIST);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_qntytype.setAdapter(dataAdapter);
        //// FOR CURRENCY
        List<String> curnctlist = new ArrayList<String>();
        curnctlist.add("Currency");
        curnctlist.add("AED");
        curnctlist.add("AFN");
        curnctlist.add("ALL");
        curnctlist.add("AMD");
        curnctlist.add("ANG");
        curnctlist.add("AOA");
        curnctlist.add("ARS");
        curnctlist.add("AUD");
        curnctlist.add("AWG");
        curnctlist.add("AZN");
        curnctlist.add("BAM");
        curnctlist.add("BBD");
        curnctlist.add("BDT");
        curnctlist.add("BGN");
        curnctlist.add("BHD");
        curnctlist.add("BIF");
        curnctlist.add("BMD");
        curnctlist.add("BND");
        curnctlist.add("BOB");
        curnctlist.add("BRL");
        curnctlist.add("BSD");
        curnctlist.add("BTN");
        curnctlist.add("BWP");
        curnctlist.add("BYR");
        curnctlist.add("BZD");
        curnctlist.add("CAD");
        curnctlist.add("CDF");
        curnctlist.add("CHF");
        curnctlist.add("CLP");
        curnctlist.add("CNY");
        curnctlist.add("COP");
        curnctlist.add("CRC");
        curnctlist.add("CUC");
        curnctlist.add("CUP");
        curnctlist.add("CVE");
        curnctlist.add("CZK");
        curnctlist.add("DJF");
        curnctlist.add("DKK");
        curnctlist.add("DOP");
        curnctlist.add("DZD");
        curnctlist.add("EGP");
        curnctlist.add("ERN");
        curnctlist.add("ETB");
        curnctlist.add("EUR");
        curnctlist.add("FJD");
        curnctlist.add("FKP");
        curnctlist.add("GBP");
        curnctlist.add("GEL");
        curnctlist.add("GGP");
        curnctlist.add("GHS");
        curnctlist.add("GIP");
        curnctlist.add("GMD");
        curnctlist.add("GNF");
        curnctlist.add("GTQ");
        curnctlist.add("GYD");
        curnctlist.add("HKD");
        curnctlist.add("HNL");
        curnctlist.add("HRK");
        curnctlist.add("HTG");
        curnctlist.add("HUF");
        curnctlist.add("IDR");
        curnctlist.add("ILS");
        curnctlist.add("IMP");
        curnctlist.add("INR");
        curnctlist.add("IQT");
        curnctlist.add("IRR");
        curnctlist.add("ISK");
        curnctlist.add("JEP");
        curnctlist.add("JMD");
        curnctlist.add("JOD");
        curnctlist.add("JPY");
        curnctlist.add("KES");
        curnctlist.add("KGS");
        curnctlist.add("KHR");
        curnctlist.add("KMF");
        curnctlist.add("KPW");
        curnctlist.add("KRW");
        curnctlist.add("KWD");
        curnctlist.add("KYD");
        curnctlist.add("KZT");
        curnctlist.add("LAK");
        curnctlist.add("LBP");
        curnctlist.add("LKR");
        curnctlist.add("LRD");
        curnctlist.add("LSL");
        curnctlist.add("LYD");
        curnctlist.add("MAD");
        curnctlist.add("MDL");
        curnctlist.add("MGA");
        curnctlist.add("MKD");
        curnctlist.add("MMK");
        curnctlist.add("MNT");
        curnctlist.add("MOP");
        curnctlist.add("MRO");
        curnctlist.add("MUR");
        curnctlist.add("MVR");
        curnctlist.add("MWK");
        curnctlist.add("MXN");
        curnctlist.add("MYR");
        curnctlist.add("MZN");
        curnctlist.add("NAD");
        curnctlist.add("NGN");
        curnctlist.add("NIO");
        curnctlist.add("NOK");
        curnctlist.add("NPR");
        curnctlist.add("NZD");
        curnctlist.add("OMR");
        curnctlist.add("PAB");
        curnctlist.add("PEN");
        curnctlist.add("PGK");
        curnctlist.add("PHP");
        curnctlist.add("PKR");
        curnctlist.add("PLN");
        curnctlist.add("PYG");
        curnctlist.add("QAR");
        curnctlist.add("RON");
        curnctlist.add("RSD");
        curnctlist.add("RUB");
        curnctlist.add("RWF");
        curnctlist.add("SAR");
        curnctlist.add("SBD");
        curnctlist.add("SCR");
        curnctlist.add("SDG");
        curnctlist.add("SEK");
        curnctlist.add("SGD");
        curnctlist.add("SHP");
        curnctlist.add("SLL");
        curnctlist.add("SOS");
        curnctlist.add("SPL");
        curnctlist.add("SRD");
        curnctlist.add("STD");
        curnctlist.add("SVC");
        curnctlist.add("SYP");
        curnctlist.add("SZL");
        curnctlist.add("THB");
        curnctlist.add("TJS");
        curnctlist.add("TMT");
        curnctlist.add("TND");
        curnctlist.add("TOP");
        curnctlist.add("TRY");
        curnctlist.add("TTD");
        curnctlist.add("PVD");
        curnctlist.add("PWD");
        curnctlist.add("TZS");
        curnctlist.add("UAH");
        curnctlist.add("UGX");
        curnctlist.add("USD");
        curnctlist.add("UYU");
        curnctlist.add("UZS");
        curnctlist.add("VEF");
        curnctlist.add("VND");
        curnctlist.add("VUV");
        curnctlist.add("WST");
        curnctlist.add("XAF");
        curnctlist.add("XCD");
        curnctlist.add("XDR");
        curnctlist.add("XOF");
        curnctlist.add("XPF");
        curnctlist.add("YER");
        curnctlist.add("ZAR");
        curnctlist.add("ZMW");
        curnctlist.add("ZWD");







        ArrayAdapter<String> curncyadapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, curnctlist);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_curremcy.setAdapter(curncyadapter);


    }


    @OnClick(R.id.img_back) void finishactivity(){
        finish();

    }
}
