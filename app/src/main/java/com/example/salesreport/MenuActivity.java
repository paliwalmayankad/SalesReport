package com.example.salesreport;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MenuActivity extends AppCompatActivity {
SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        sharedPreferences= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        ButterKnife.bind(this);
    }
    @OnClick(R.id.layout_profile) void profile(){
        if(sharedPreferences.getBoolean(StringUtility.LOGIN,false)==true){
            startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
            finish();
        }
        else{
            Toast.makeText(getApplicationContext(),"You are not login, please login for profile",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            finish();
        }
    }
    @OnClick(R.id.layout_signout) void signout(){


        SystemUtility.showAlert(MenuActivity.this, getResources().getString(R.string.app_name), "Are you sure want to logout ?", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                SharedPreferences.Editor edit=sharedPreferences.edit();
                edit.putBoolean(StringUtility.LOGIN,false);
                edit.commit();
                startActivity(new Intent(getApplicationContext(),DashboardActivty.class));
                finish();
                    }
        });
    }
    @OnClick(R.id.layout_support) void support(){


        startActivity(new Intent(getApplicationContext(),SupportActivity.class));
    }
    @OnClick(R.id.layout_theme) void Theme(){


       Toast.makeText(getApplicationContext(),"Coming Soon",Toast.LENGTH_SHORT).show();
    }
    @OnClick(R.id.subscription) void Subscription(){


       Toast.makeText(getApplicationContext(),"Coming Soon",Toast.LENGTH_SHORT).show();
    }
    @OnClick(R.id.layout_notification) void notification(){


        startActivity(new Intent(getApplicationContext(),NotificationActivity.class));
    }@OnClick(R.id.img_back) void killactivity(){


     finish();
    }
}
