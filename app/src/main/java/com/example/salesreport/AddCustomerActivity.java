package com.example.salesreport;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.room.Room;

import android.Manifest;
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
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;

import com.example.salesreport.RoomDatabaase.CUSTOMERDATABASE;
import com.example.salesreport.RoomDatabaase.RoomDataBaseModel.AddCustomerDetail;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class AddCustomerActivity extends AppCompatActivity {
@BindView(R.id.spinner)
Spinner spinner;
@BindView(R.id.img_back)
    ImageView img_back;
String gender;


@BindView(R.id.edt_custname)
    EditText edt_custname;
    @BindView(R.id.edt_custcontactno)
    EditText edt_custcontactno;
    @BindView(R.id.edt_custemail)
    EditText edt_custemail;
    @BindView(R.id.edt_mailingaddress)
    EditText edt_mailingaddress;
    @BindView(R.id.edt_amountpaid)
    EditText edt_amountpaid;
    @BindView(R.id.edt_notes)
    EditText edt_notes;
    @BindView(R.id.switchbutton)
    Switch switchbutton;
@BindView(R.id.customer_image)
    CircleImageView customer_image;
boolean advpaid=false;
    private static final int SELCT_GALLERY = 2;
    String photoImagename="",encodediamge="";
CUSTOMERDATABASE custdatabase;
Uri imageuri;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);
        ButterKnife.bind(this);
        try {
            custdatabase = Room.databaseBuilder(getApplicationContext(), CUSTOMERDATABASE.class, "SUVADB").allowMainThreadQueries().build();

            List<String> genderlist = new ArrayList<String>();
            genderlist.add("Gender");
            genderlist.add("Male");
            genderlist.add("Female");

            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, genderlist);

            // Drop down layout style - list view with radio button
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            // attaching data adapter to spinner
            spinner.setAdapter(dataAdapter);
            /*spinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    gender = genderlist.get(position);
                }
            });*/

            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    gender = genderlist.get(position);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            advpaid = switchbutton.isChecked();
        }catch (Exception e)
        {
            String ss=e.toString();
        }
    }
    @OnClick(R.id.img_back) void finishactivity(){
        finish();

    }

    @OnClick(R.id.btn_addcustomer) void AddCustomer(){

        ///// NOW ADD ALL DETAIL TO CUSTOMER DETAIL LIST AT LOCAL DATABASE
try {
    AddCustomerDetail customer = new AddCustomerDetail();
    customer.setCustomer_name(edt_custname.getText().toString());
    customer.setCustomer_contact(edt_custcontactno.getText().toString());
    customer.setCustomer_mailingaddress(edt_mailingaddress.getText().toString());
    customer.setCustomer_email(edt_custemail.getText().toString());
    customer.setCustomer_amountpaid(edt_amountpaid.getText().toString());
    customer.setCustomer_notes(edt_notes.getText().toString());
    customer.setCustomer_debit_credit("Debit");
    customer.setCustomer_image(encodediamge);

    customer.setCustomer_advpaid(advpaid);
    custdatabase.myDao().addCustomer(customer);
}catch (Exception e){
    String s=e.toString();
}
finish();
    }

    @OnClick(R.id.customer_image) void selectiamge(){
        try {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(AddCustomerActivity.this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, SELCT_GALLERY);
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
                imageuri=data.getData();
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
                customer_image.setImageBitmap(decodedByte);
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
    }}
