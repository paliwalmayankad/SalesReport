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
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.salesreport.Models.CommonModels;
import com.example.salesreport.Models.ForgotPasswordModel;
import com.example.salesreport.Models.LoginModels;
import com.example.salesreport.Models.RegisterModel;
import com.example.salesreport.api.ApiService;
import com.example.salesreport.api.RetrofitClient;

import org.json.JSONObject;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;

public class ForgotPasswordActivity extends AppCompatActivity {
@BindView(R.id.edt_email)
    EditText edt_email;
    @BindView(R.id.edt_code)
    EditText edt_code;
    @BindView(R.id.edt_password)
    EditText edt_password;
    @BindView(R.id.edt_cnfpassword)
    EditText edt_cnfpassword;


    @BindView(R.id.layout_email)

    LinearLayout layout_email;
    @BindView(R.id.layout_layout_code)
    LinearLayout layout_layout_code;
    @BindView(R.id.layout_password)
    LinearLayout layout_password;
    @BindView(R.id.layout_cnfpassword)
    LinearLayout layout_cnfpassword;

    @BindView(R.id.btn_forsubmit)
    Button btn_submit;
int button=0;
String code="123456";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_forsubmit)  void Submit(){
        if(button==0){



        if (isValidateFields()) {
            if (SystemUtility.isConnectingToInternet(ForgotPasswordActivity.this)) {


                /// CALL API FOR CODE

                _callapiforforgotpassword();


            } else {
                SystemUtility.showNetworkFailureAlert(ForgotPasswordActivity.this);
            }
        }
        }
        else if(button==1){
            if (isValidateFieldforcodes()) {
                if (SystemUtility.isConnectingToInternet(ForgotPasswordActivity.this)) {
                    /// CALL API FOR CODE
                    button=2;
                    layout_layout_code.setVisibility(View.GONE);
                    layout_password.setVisibility(View.VISIBLE);
                    layout_cnfpassword.setVisibility(View.VISIBLE);
                    btn_submit.setText("Update");
                } else {
                    SystemUtility.showNetworkFailureAlert(ForgotPasswordActivity.this);
                }
            }

        }
        else if(button==2){
            if (isValidateFieldsforforpassword()) {
                if (SystemUtility.isConnectingToInternet(ForgotPasswordActivity.this)) {
                    /// CALL API FOR CODE

                    updatepassword();
                    ;
                } else {
                    SystemUtility.showNetworkFailureAlert(ForgotPasswordActivity.this);
                }
            }

        }

    }

    private void updatepassword() {


        try {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Please wait...");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
            HashMap<String, String> param = new HashMap<>();
            param.put("email", edt_email.getText().toString());
            param.put("password", edt_password.getText().toString());

            ApiService apiService = RetrofitClient.createRetrofitService(ApiService.class);
            Call<CommonModels> call = apiService.forgotpassword(param);
            call.enqueue(new Callback<CommonModels>() {
                @Override
                public void onResponse(Call<CommonModels> call, retrofit2.Response<CommonModels> response) {
                    progressDialog.dismiss();
                    if (response.code() == 200) {
                        if (response.body().isStatus()) {
                            Toast.makeText(ForgotPasswordActivity.this,"Registeration successfull. please login again", Toast.LENGTH_SHORT).show();
                            button=3;
                            Intent intent = new Intent(ForgotPasswordActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(ForgotPasswordActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        try {
                            JSONObject jObjError = new JSONObject(response.errorBody().string());
                            String msg = jObjError.getString("message");
                            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }


                }

                @Override
                public void onFailure(Call<CommonModels> call, Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(ForgotPasswordActivity.this, "Call Failed", Toast.LENGTH_SHORT).show();

                }
            });

        }catch (Exception e){
            String ss=e.toString();
        }

    }
    @OnClick(R.id.img_back) void back(){
        finish();
    }
    private void _callapiforforgotpassword() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Please wait...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        ApiService apiService = RetrofitClient.createRetrofitService(ApiService.class);
        Call<ForgotPasswordModel> call = apiService.performforgotpassword(edt_email.getText().toString());
        call.enqueue(new Callback<ForgotPasswordModel>() {
            @Override
            public void onResponse(Call<ForgotPasswordModel> call, retrofit2.Response<ForgotPasswordModel> response) {
                progressDialog.dismiss();

                    if (response.body().isStatus()) {
                       /* button=1;
                        layout_email.setVisibility(View.GONE);
                        layout_layout_code.setVisibility(View.VISIBLE);
                        btn_submit.setText("Verify");
                        code=String.valueOf(response.body().getData());
*/
                        Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                       finish();
                }
                    else
                        {
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
            public void onFailure(Call<ForgotPasswordModel> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(ForgotPasswordActivity.this, "Call Failed", Toast.LENGTH_SHORT).show();

            }
        });
    }
    private boolean isValidateFieldsforforpassword() {
        if (edt_password.getText().toString().isEmpty()) {
            edt_password.setError("Enter Password");
            return false;
        }
        else if (edt_cnfpassword.getText().toString().isEmpty()) {
            edt_cnfpassword.setError("Enter Confirm Password");
            return false;
        }
        else if (!edt_password.getText().toString().equals(edt_cnfpassword.getText().toString())) {
            edt_cnfpassword.setError("Password not match");
            return false;
        }else
            return  true;
    }

    private boolean isValidateFieldforcodes() {
        if (edt_code.getText().toString().isEmpty()) {
            edt_code.setError("Enter Code");
            return false;
        }

        else if (!edt_code.getText().toString().equals(code)) {
            edt_code.setError("Enter Correct Code");
            return false;
        }else
            return  true;
    }

    private boolean isValidateFields() {
           if (edt_email.getText().toString().isEmpty()) {
            edt_email.setError("Enter Email");
            return false;
        }
        else if (!StringUtility.validateEmail(edt_email.getText().toString())) {
            edt_email.setError("Enter Correct Email");
            return false;
        }else
            return  true;
    }
}
