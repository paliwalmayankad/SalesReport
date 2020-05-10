package com.example.salesreport;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.util.Base64;
import android.widget.EditText;
import android.widget.Toast;

import com.example.salesreport.Models.RegisterModel;
import com.example.salesreport.api.ApiService;
import com.example.salesreport.api.RetrofitClient;

import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.WeakHashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;

public class RegisterActivity extends AppCompatActivity {
@BindView(R.id.edt_firstname)
    EditText edt_firstname;
@BindView(R.id.edt_lastname)
    EditText edt_lastname;
@BindView(R.id.edt_username)
    EditText edt_username;
@BindView(R.id.edt_password)
    EditText edt_password;
@BindView(R.id.edt_email)
    EditText edt_email;
@BindView(R.id.edt_mobile)
    EditText edt_mobile;
@BindView(R.id.userimg)
CircleImageView userimg;
    private static final int SELCT_GALLERY = 2;
    String photoImagename="",encodediamge="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_register) void register(){
        if (isValidateFields()) {
            if (SystemUtility.isConnectingToInternet(RegisterActivity.this)) {
                PerformRegister();
            } else {
                SystemUtility.showNetworkFailureAlert(RegisterActivity.this);
            }
        }



    }

    private void PerformRegister() {
        try {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Please wait...");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
            HashMap<String, String> param = new HashMap<>();
            param.put("firstname", edt_firstname.getText().toString());
            param.put("lastname", edt_lastname.getText().toString());
            param.put("username", edt_username.getText().toString());
            param.put("email", edt_email.getText().toString());
            param.put("phone", edt_mobile.getText().toString());
            param.put("password", edt_password.getText().toString());
            param.put("userphoto", encodediamge);
            param.put("userphoto_name", photoImagename);
            ApiService apiService = RetrofitClient.createRetrofitService(ApiService.class);
            Call<RegisterModel> call = apiService.performSignup(param);
            call.enqueue(new Callback<RegisterModel>() {
                @Override
                public void onResponse(Call<RegisterModel> call, retrofit2.Response<RegisterModel> response) {
                    progressDialog.dismiss();
                    if (response.code() == 200) {
                        if (response.body().isStatus()) {
                            Toast.makeText(RegisterActivity.this,"Registeration successfull. please login again", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(RegisterActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        try {
                            JSONObject jObjError = new JSONObject(response.errorBody().string());
                            String msg = jObjError.getString("message");
                            boolean status=jObjError.getBoolean("status");
                            if (status==true){
                                Toast.makeText(RegisterActivity.this,"Registeration successfull. please login again", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(intent);
                                finish();
                            }
                            else{
                                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                            }

                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }


                }

                @Override
                public void onFailure(Call<RegisterModel> call, Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(RegisterActivity.this, "Call Failed", Toast.LENGTH_SHORT).show();

                }
            });

        }catch (Exception e){
            String ss=e.toString();
        }

    }

    private boolean isValidateFields() {
        if (edt_firstname.getText().toString().isEmpty()) {
            edt_firstname.setError("Enter FirstName");
            return false;
        }
       else if (edt_lastname.getText().toString().isEmpty()) {
            edt_lastname.setError("Enter LastName");
            return false;
        } else if (edt_username.getText().toString().isEmpty()) {
            edt_username.setError("Enter UserName");
            return false;
        }
        else if (edt_password.getText().toString().isEmpty()) {
            edt_password.setError("Enter password");
            return false;
        } else if (edt_password.getText().toString().length()<6) {
            edt_password.setError("Password should be 6 digit");
            return false;
        }
        else if (edt_email.getText().toString().isEmpty()) {
            edt_email.setError("Enter Email");
            return false;
        }
        else if (!StringUtility.validateEmail(edt_email.getText().toString())) {
            edt_email.setError("Enter Correct Email");
                return false;
            }
        else if (edt_mobile.getText().toString().isEmpty()) {
            edt_email.setError("Enter Mobile");
            return false;
        }else if (edt_mobile.getText().toString().length()<10) {
            edt_mobile.setError("Enter correct Mobile");
            return false;
        }
        else
            return true;
    }
@OnClick(R.id.img_back) void back(){
        finish();
}
    @OnClick(R.id.userimg) void imgselection(){
        try {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(RegisterActivity.this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, SELCT_GALLERY);
                }
                else {
                    Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(
                            Intent.createChooser(intent, "Select Image"),
                            SELCT_GALLERY);
                }
            }
            else {
                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                startActivityForResult(
                        Intent.createChooser(intent, "Select Image"),
                        SELCT_GALLERY);
            }

                  /*  Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(
                            Intent.createChooser(intent, "Select Image"),
                            SELCT_GALLERY);
*/
        } catch (Exception e) {
            e.printStackTrace();
            String s=e.toString();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == SELCT_GALLERY && PackageManager.PERMISSION_GRANTED == grantResults[0]) {

            Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.setType("image/*");
            startActivityForResult(
                    Intent.createChooser(intent, "Select Image"),
                    SELCT_GALLERY);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SELCT_GALLERY && resultCode == RESULT_OK) {
            try {

                Uri selectedImageUri = data.getData();
                String picturePath = getPath(getApplicationContext(), selectedImageUri);


                Bitmap bitmap = MediaStore.Images.Media.getBitmap(
                        getContentResolver(), selectedImageUri);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 40, baos);
                byte[] imageBytes = baos.toByteArray();
                //checkimagesize(picturePath);
                long lengthbmp = imageBytes.length;
                long finallengthkb = imageBytes.length / 1024;
                long in_mb = finallengthkb / 1024;
                String ss = String.valueOf(in_mb);


                // encoded_membergallery = Base64.encodeToString(imageBytes, Base64.DEFAULT);
                Cursor returnCursor = getContentResolver().query(selectedImageUri, null,
                        null, null, null);

                int nameIndex = returnCursor
                        .getColumnIndex(OpenableColumns.DISPLAY_NAME);

                returnCursor.moveToFirst();
                photoImagename = returnCursor.getString(nameIndex);

                encodediamge = Base64.encodeToString(imageBytes, Base64.DEFAULT);
                Bitmap decodedByte = BitmapFactory.decodeByteArray(imageBytes,
                        0, imageBytes.length);
                userimg.setImageBitmap(decodedByte);
                String as = "pp";
            } catch (Exception e) {
                e.printStackTrace();
                String s = e.toString();
            }
        }
    }

    private String getPath(Context context, Uri selectedImageUri) {
        String result = null;
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor cursor = context.getContentResolver( ).query( selectedImageUri, proj, null, null, null );
        if(cursor != null){
            if ( cursor.moveToFirst( ) ) {
                int column_index = cursor.getColumnIndexOrThrow( proj[0] );
                result = cursor.getString( column_index );
            }
            cursor.close( );
        }
        if(result == null) {
            result = "Not found";
        }
        return result;
    }
}
