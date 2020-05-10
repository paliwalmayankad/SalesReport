package com.example.salesreport;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.salesreport.Models.LoginModels;
import com.example.salesreport.api.ApiService;
import com.example.salesreport.api.RetrofitClient;

import org.json.JSONObject;

import java.util.WeakHashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;

public class LoginActivity extends AppCompatActivity {
@BindView(R.id.edt_password)
    EditText edt_password;

@BindView(R.id.edt_username) EditText edt_username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        /* btn_register=findViewById(R.id.btn_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),RegisterActivity.class));
            }
        });*/
    }
    @OnClick(R.id.btn_register)
    void register() {
        startActivity(new Intent(getApplicationContext(),RegisterActivity.class));
    }@OnClick(R.id.layout_forgotpassword)
    void forgotpassword() {
        startActivity(new Intent(getApplicationContext(),ForgotPasswordActivity.class));
    }
    @OnClick(R.id.btn_login)
    void login() {
        if (isValidateFields()) {
            if (SystemUtility.isConnectingToInternet(LoginActivity.this)) {
                performLogin();
            } else {
                SystemUtility.showNetworkFailureAlert(LoginActivity.this);
            }
        }
    }

    private void performLogin() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Logging in...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        ApiService apiService = RetrofitClient.createRetrofitService(ApiService.class);
        Call<LoginModels> call = apiService.performlogin(edt_username.getText().toString(),edt_password.getText().toString());
        call.enqueue(new Callback<LoginModels>() {
            @Override
            public void onResponse(Call<LoginModels> call, retrofit2.Response<LoginModels> response) {
                progressDialog.dismiss();
                if (response.code() == 200) {
                    if (response.body().isStatus()) {
                        SharedPreferences prefs= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                        SharedPreferences.Editor myEdit
                                = prefs.edit();
                        myEdit.putBoolean(StringUtility.LOGIN,true);
                        myEdit.putString(StringUtility.NAME,response.body().getData().getName());
                        myEdit.putString(StringUtility.USERID,response.body().getData().getId());
                        myEdit.putString(StringUtility.USEREMAIL,response.body().getData().getEmail());
                        myEdit.putString(StringUtility.USERMOBILE,response.body().getData().getMobile());
                        myEdit.putString(StringUtility.USERPICTURE,response.body().getData().getPicture());
                        myEdit.commit();

                        Intent intent = new Intent(LoginActivity.this, DashboardActivty.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else{
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
 String msg=jObjError.getString("message");
                        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
                    //Toast.makeText(LoginActivity.this, "Something went wrong..", Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onFailure(Call<LoginModels> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(LoginActivity.this, "Call Failed", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private boolean isValidateFields() {

            if (edt_username.getText().toString().isEmpty()) {
                edt_username.setError("Enter Usernmae");
                return false;
            } /*else if (!StringUtility.validateEmail(edt_username.getText().toString())) {
                edt_username.setError("Enter Correct Email");
                return false;
            }*/ else if (edt_password.getText().toString().isEmpty()) {
                edt_password.setError("Enter password");
                return false;
            } else
                return true;

    }
}
