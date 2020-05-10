package com.example.salesreport;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProfileActivity extends AppCompatActivity {
SharedPreferences sharedPreferences;
@BindView(R.id.edt_username)
    EditText edt_username;
@BindView(R.id.edt_email)
    EditText edt_email;
@BindView(R.id.edt_phonenumber)
    EditText edt_phonenumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);
        sharedPreferences= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        edt_username.setText(sharedPreferences.getString(StringUtility.NAME,""));
        edt_email.setText(sharedPreferences.getString(StringUtility.USEREMAIL,""));
        edt_phonenumber.setText(sharedPreferences.getString(StringUtility.USERMOBILE,""));

    }
    @OnClick(R.id.img_back) void back(){
        finish();
    }
}
