package com.example.salesreport;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;

public class SplashActivity extends AppCompatActivity {
SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        sharedPreferences=  PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
    }

    @Override
    protected void onStart() {
        super.onStart();
new Handler().postDelayed(new Runnable() {
    @Override
    public void run() {

          //// REDIRECT TO DASHBOARD
        if(sharedPreferences.contains(StringUtility.LOGIN)){
       if( sharedPreferences.getBoolean(StringUtility.LOGIN,false)==true){
           startActivity(new Intent(getApplicationContext(),DashboardActivty.class));
           finish();
       }
       else{
           startActivity(new Intent(getApplicationContext(),LoginActivity.class));
           finish();
       }}else {
            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            finish();
        }



    }
},3000);
    }
}
