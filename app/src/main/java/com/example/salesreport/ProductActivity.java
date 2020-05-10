package com.example.salesreport;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.salesreport.Adapter.CustomerListGridAdapter;
import com.example.salesreport.Adapter.CustomerListLinearAdapter;
import com.example.salesreport.Adapter.ProductListGridAdapter;
import com.example.salesreport.Adapter.ProductListLinearAdapter;
import com.example.salesreport.Models.LoginModels;
import com.example.salesreport.Models.ProductModel;
import com.example.salesreport.RoomDatabaase.CUSTOMERDATABASE;
import com.example.salesreport.RoomDatabaase.RoomDataBaseModel.AddCustomerDetail;
import com.example.salesreport.RoomDatabaase.RoomDataBaseModel.DelerList;
import com.example.salesreport.RoomDatabaase.RoomDataBaseModel.FilterModelClass;
import com.example.salesreport.RoomDatabaase.RoomDataBaseModel.ProductListModel;
import com.example.salesreport.RoomDatabaase.RoomDataBaseModel.RouteList;
import com.example.salesreport.RoomDatabaase.RoomDataBaseModel.SalesModel;
import com.example.salesreport.RoomDatabaase.RoomDataBaseModel.StoreList;
import com.example.salesreport.api.ApiService;
import com.example.salesreport.api.RetrofitClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;
import retrofit2.Call;
import retrofit2.Callback;

public class ProductActivity extends AppCompatActivity {
    @BindView(R.id.seg_atoz)
    TextView seg_atoz;
    @BindView(R.id.seg_ztoa)
    TextView seg_ztoa;
    @BindView(R.id.seg_sku)
    TextView seg_sku;
    @BindView(R.id.seg_Stock)
    TextView seg_Stock;
    @BindView(R.id.edt_search)
    TextView edt_search;


    @BindView(R.id.img_linear)
    ImageView img_linear;
    @BindView(R.id.img_grid)
    ImageView img_grid;
    @BindView(R.id.img_back)
    ImageView img_back;
    @BindView(R.id.customer_listview)
    ListView customer_listview;
    @BindView(R.id.customerGridView)
    GridView customerGridView;
    String[] listItem={"Mayank","ram","Rahul"};
    SharedPreferences sharedprefrences;
    ArrayList<ProductModel.DataBean> productlist;
    ArrayList<ProductModel.DealerBean> filterlsit;
    List<ProductListModel> productlistroom;
    List<FilterModelClass> filterlistroom;
    List<ProductListModel> searchlist;
    CUSTOMERDATABASE custdatabase;
    String sellingdate;
    ProductListLinearAdapter linadapter;
    ProductListGridAdapter gridadapter;
    String date,distributor,route,store;
    int dealerposition,routeposition,storeposition;

   /* @Override
    protected void onResume() {
        super.onResume();
        productlist.clear();
        productlistroom.clear();
        productlistroom=custdatabase.myDao().getProductList();
        _checkandcompleteloaddata();

    }*/
@BindView(R.id.txt_nodatafound)
TextView txt_nodatafound;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        try{
            sharedprefrences=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            custdatabase = Room.databaseBuilder(getApplicationContext(), CUSTOMERDATABASE.class, "SUVADB").allowMainThreadQueries().build();

            ButterKnife.bind(this);
            productlist= new ArrayList();


            productlistroom=custdatabase.myDao().getProductList();
            filterlistroom=custdatabase.myDao().getfilterlsit();

            _checkandcompleteloaddata();


            customer_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    _createdialogboxandadditemtolocaldatabase(position);
                }
            });
        }catch (Exception e){
            String s=e.toString();
        }
        searchlist=new ArrayList<>();
        edt_search.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //get the text in the EditText
                String searchString=edt_search.getText().toString();
                int textLength=searchString.length();

                //clear the initial data set
                searchlist.clear();

                for(int i=0;i<productlistroom.size();i++)
                {
                    String playerName=productlistroom.get(i).getPro_name().toString();
                    if(textLength<=playerName.length()){
                        //compare the String in EditText with Names in the ArrayList
                        if(searchString.equalsIgnoreCase(playerName.substring(0,textLength)))
                            searchlist.add(productlistroom.get(i));
                    }
                }
                if(searchlist.size()>0){
                    txt_nodatafound.setVisibility(View.GONE);
                }
                else{
                    txt_nodatafound.setVisibility(View.VISIBLE);
                }

                linadapter=new ProductListLinearAdapter(getApplicationContext(),productlist,searchlist);
                customer_listview.setAdapter(linadapter);
                gridadapter=new ProductListGridAdapter(getApplicationContext(),productlist,searchlist);
                // customer_listview.setAdapter(adapter);
                customerGridView.setAdapter(gridadapter);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }


        });




    }

    private void _createdialogboxandadditemtolocaldatabase(int position) {
        final AlertDialog.Builder alert = new AlertDialog.Builder(ProductActivity.this,R.style.Theme_AppCompat_DayNight_Dialog_Alert);
        View  view = getLayoutInflater().inflate(R.layout.product_update_layout,null);

        alert.setCancelable(false);
        alert.setView(view);
        final AlertDialog dialog = alert.create();

        EditText edt_productqty=view.findViewById(R.id.edt_productqty);
        Button btn_login=view.findViewById(R.id.btn_login);
        Button btn_back=view.findViewById(R.id.btn_back);
        TextView txt_error=view.findViewById(R.id.txt_error);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if(edt_productqty.length()<0&&edt_productqty.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Enter qty",Toast.LENGTH_SHORT).show();
                }
                else {
                    int entqty = Integer.parseInt((edt_productqty.getText().toString()));

                    int prodqty= Integer.parseInt(productlistroom.get(position).getPro_stock());
                    if(entqty<=prodqty){
                        _updatatodatabase(position,edt_productqty.getText().toString());
                        dialog.dismiss();
                    }
                    else {
                        txt_error.setText("Sorry, we have only "+prodqty+" in stock");
                    }


                }
            }
        });

        dialog.show();


    }

    private void _updatatodatabase(int position, String qty) {
        try {
            SalesModel smodel = new SalesModel();
            smodel.setPro_id(productlistroom.get(position).getPro_id());
            smodel.setSelling_date(sellingdate);
            smodel.setSelling_route(productlistroom.get(position).getRoutes());
            smodel.setSelling_distributor(productlistroom.get(position).getDistributers());
            smodel.setSelling_store(productlistroom.get(position).getStores());
            smodel.setSelling_qty(qty);
            smodel.setSelling_pro_name(productlistroom.get(position).getPro_name());

            custdatabase.myDao().addSalesRecord(smodel);
        }catch (Exception e)
        {
            String s=e.toString();
        }


    }

    private void _checkandcompleteloaddata() {
        if (SystemUtility.isConnectingToInternet(ProductActivity.this))
        {
            _getPRoductListandStoreinDatBase();
        }
        else
        {
            if(productlistroom.size()>0){
                linadapter=new ProductListLinearAdapter(getApplicationContext(),productlist,productlistroom);
                customer_listview.setAdapter(linadapter);
                gridadapter=new ProductListGridAdapter(getApplicationContext(),productlist,productlistroom);
                // customer_listview.setAdapter(adapter);
                customerGridView.setAdapter(gridadapter);
                _opencustomDialogforfilters();
            }
            else{
                SystemUtility.showNetworkFailureAlert(ProductActivity.this);
            }


        }
    }

    private void _getPRoductListandStoreinDatBase() {
        try
        {

            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Please wait...");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            ApiService apiService = RetrofitClient.createRetrofitService(ApiService.class);
            Call<ProductModel> call = apiService.getProductList(sharedprefrences.getString(StringUtility.USERID,"1"));
            call.enqueue(new Callback<ProductModel>() {
                @Override
                public void onResponse(Call<ProductModel> call, retrofit2.Response<ProductModel> response) {
                    progressDialog.dismiss();

                    if (response.body().isStatus()) {
                        productlist=response.body().getData().getProducts();
                        filterlsit=response.body().getData().getDealers();
                        custdatabase.myDao().deletemyproducttable();
                        custdatabase.myDao().deletemyfiltertable();
                        _updatemydatabase();
                        Log.d("response",response.body().getData().toString());
                    }
                    else {
                        Toast.makeText(ProductActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<ProductModel> call, Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(ProductActivity.this, "Call Failed", Toast.LENGTH_SHORT).show();

                }
            });


        }
        catch (Exception e)
        {
            Log.d("error",e.toString());
        }


    }

    private void _updatemydatabase() {
        try
        {for(int i=0;i<productlist.size();i++){
            ProductListModel productlistmodel= new ProductListModel();
            productlistmodel.setPro_id(productlist.get(i).getId());
            productlistmodel.setPro_name(productlist.get(i).getName());
            productlistmodel.setCategories_ids(productlist.get(i).getCategories_ids());
            productlistmodel.setCategories(productlist.get(i).getCategories());
            productlistmodel.setStores(productlist.get(i).getStores());
            productlistmodel.setRoutes(productlist.get(i).getRoutes());
            productlistmodel.setDistributers(productlist.get(i).getDistributers());

            productlistmodel.setPro_qty_type(productlist.get(i).getQty_type());
            productlistmodel.setPro_currency(productlist.get(i).getCurrency());
            productlistmodel.setPro_sku(productlist.get(i).getSku());
            productlistmodel.setPro_actual_price(productlist.get(i).getActual_price());
            productlistmodel.setPro_sell_price(productlist.get(i).getSell_price());
            productlistmodel.setPro_stock(productlist.get(i).getStock());
            productlistmodel.setPro_barcode(productlist.get(i).getBarcode());
            productlistmodel.setPro_image(productlist.get(i).getImage());




            custdatabase.myDao().addProductList(productlistmodel);
        }
            try{


                for(int j=0;j<filterlsit.size();j++){
                    FilterModelClass flm=new FilterModelClass();
                    List<DelerList> delaerlist=new ArrayList<>();
                    DelerList dls=new DelerList();
                    flm.setDealer_name(filterlsit.get(j).getName());
                    List<RouteList> oarentroutelist=new ArrayList<>();


                    for(int k=0;k<filterlsit.get(j).getRoutes().size();k++){
                        RouteList routeList=new RouteList();
                        routeList.setRoute_name(filterlsit.get(j).getRoutes().get(k).getName());
                        List<StoreList> sl=new ArrayList<>();
                        for(int l=0;l<filterlsit.get(j).getRoutes().get(k).getStores().size();l++)
                        {

                            StoreList storeList=new StoreList();
                            storeList.setStore_name(filterlsit.get(j).getRoutes().get(k).getStores().get(l).getName());
                            sl.add(storeList);
                        }

                        routeList.setStore_list(sl);
                        oarentroutelist.add(routeList);
                        dls.setRoute_list(oarentroutelist);
                        delaerlist.add(dls);
                    }

                    flm.setDealer_list(delaerlist);

                    custdatabase.myDao().addfilter(flm);

                }
                filterlistroom=custdatabase.myDao().getfilterlsit();


            }
            catch (Exception e)
            {
                String s=e.toString();

            }






            linadapter=new ProductListLinearAdapter(getApplicationContext(),productlist,productlistroom);
            customer_listview.setAdapter(linadapter);
            gridadapter=new ProductListGridAdapter(getApplicationContext(),productlist,productlistroom);
            // customer_listview.setAdapter(adapter);
            customerGridView.setAdapter(gridadapter);
            productlistroom=custdatabase.myDao().getProductList();
            _opencustomDialogforfilters();





        }
        catch (Exception e)
        {
            String ss=e.toString();
        }


    }

    private void _opencustomDialogforfilters() {



        final AlertDialog.Builder alert = new AlertDialog.Builder(ProductActivity.this,R.style.Theme_AppCompat_DayNight_Dialog_Alert);
        View  view = getLayoutInflater().inflate(R.layout.product_filter_dialog,null);

        alert.setCancelable(false);
        alert.setView(view);
        final AlertDialog dialog = alert.create();

        EditText edt_date=view.findViewById(R.id.edt_date);

/// SPINNER FOR DEALER
        Spinner spinner_dealer=view.findViewById(R.id.spinner_dealer);


        Button btn_apply=view.findViewById(R.id.btn_apply);
        Button btn_back=view.findViewById(R.id.btn_back);
        TextView txt_error=view.findViewById(R.id.txt_error);

        spinner_dealer.setAdapter(new ArrayAdapter<FilterModelClass>(getApplicationContext(),  android.R.layout.simple_spinner_dropdown_item, filterlistroom));
        spinner_dealer.setPrompt("Select Distributor");





/// SPINNER FOR ROUTES
        Spinner spinner_routes=view.findViewById(R.id.spinner_routes);
        spinner_routes.setAdapter(new ArrayAdapter<RouteList>(getApplicationContext(),  android.R.layout.simple_spinner_dropdown_item, filterlistroom.get(0).getDealer_list().get(0).getRoute_list()));
        spinner_routes.setPrompt("Select Route");

        Spinner spinner_stores=view.findViewById(R.id.spinner_stores);
        spinner_stores.setAdapter(new ArrayAdapter<StoreList>(getApplicationContext(),  android.R.layout.simple_spinner_dropdown_item, filterlistroom.get(0).getDealer_list().get(0).getRoute_list().get(0).getStore_list()));
        spinner_stores.setPrompt("Select Route");

        distributor=filterlistroom.get(0).getDealer_name();
        route=filterlistroom.get(0).getDealer_list().get(0).getRoute_list().get(0).getRoute_name();
        store=filterlistroom.get(0).getDealer_list().get(0).getRoute_list().get(0).getStore_list().get(0).getStore_name();


        spinner_dealer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                dealerposition=position;
                distributor=filterlistroom.get(position).getDealer_name();
                spinner_routes.setAdapter(new ArrayAdapter<RouteList>(getApplicationContext(),  android.R.layout.simple_spinner_dropdown_item, filterlistroom.get(position).getDealer_list().get(0).getRoute_list()));
                spinner_stores.setAdapter(new ArrayAdapter<StoreList>(getApplicationContext(),  android.R.layout.simple_spinner_dropdown_item, filterlistroom.get(position).getDealer_list().get(0).getRoute_list().get(0).getStore_list()));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner_routes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                routeposition=position;
                route=filterlistroom.get(dealerposition).getDealer_list().get(routeposition).getRoute_list().get(routeposition).getRoute_name();
                spinner_stores.setAdapter(new ArrayAdapter<StoreList>(getApplicationContext(),  android.R.layout.simple_spinner_dropdown_item, filterlistroom.get(dealerposition).getDealer_list().get(routeposition).getRoute_list().get(routeposition).getStore_list()));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner_routes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                storeposition=position;
                store=filterlistroom.get(dealerposition).getDealer_list().get(routeposition).getRoute_list().get(routeposition).getStore_list().get(storeposition).getStore_name();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        edt_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Toast.makeText(getApplicationContext(),"Date Picker Open",Toast.LENGTH_SHORT).show();
                Log.d("printedt","printing");*/
                int mYear, mMonth, mDay, mHour, mMinute;
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(ProductActivity.this ,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                edt_date.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                                sellingdate=edt_date.getText().toString();
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        dialog.show();
        btn_apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{


                    if(sellingdate==null||sellingdate.isEmpty()||sellingdate.length()==0){
                        txt_error.setText("Select date");

                    }
                    else if(distributor.isEmpty()||distributor.length()==0) {
                        txt_error.setText("Select Distributor");
                    }
                    else if(route.isEmpty()||route.length()==0) {
                        txt_error.setText("Select Route");
                    }
                    else if(store.isEmpty()||store.length()==0) {
                        txt_error.setText("Select Store");
                    }
                    else{
                        // productlistroom=custdatabase.myDao().search(distributor,route,store);

                        productlistroom=custdatabase.myDao().search(null,"BHilara to chowk,Nawab Shop to Nawada,Picker Point to drop One","Shop #5");

                        customer_listview.setAdapter(null);
                        customerGridView.setAdapter(null);
                        productlist.clear();
                        linadapter=new ProductListLinearAdapter(getApplicationContext(),productlist,productlistroom);
                        customer_listview.setAdapter(linadapter);
                        gridadapter=new ProductListGridAdapter(getApplicationContext(),productlist,productlistroom);
                        // customer_listview.setAdapter(adapter);
                        customerGridView.setAdapter(gridadapter);





                        dialog.dismiss();
                    }
                }catch (Exception e)
                {
                    String s=e.toString();
                }
            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                finishactivity();
            }
        });

    }
    /* @Optional
  @OnClick(R2.id.edt_date) void OpendatePicker(){
      Toast.makeText(getApplicationContext(),"Date Picker Open",Toast.LENGTH_SHORT).show();
         Log.d("printedt","printing");
  }*/
    @OnClick({R.id.seg_atoz}) void Seg_atoz(){
        seg_atoz.setTextColor(getResources().getColor(R.color.color_white));
        seg_atoz.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

        seg_ztoa.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        seg_ztoa.setBackgroundColor(getResources().getColor(R.color.color_white));

        seg_sku.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        seg_sku.setBackgroundColor(getResources().getColor(R.color.color_white));

        seg_Stock.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        seg_Stock.setBackgroundColor(getResources().getColor(R.color.color_white));
    }
    @OnClick({R.id.seg_ztoa}) void Seg_ztoa(){
        seg_ztoa.setTextColor(getResources().getColor(R.color.color_white));
        seg_ztoa.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

        seg_atoz.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        seg_atoz.setBackgroundColor(getResources().getColor(R.color.color_white));

        seg_sku.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        seg_sku.setBackgroundColor(getResources().getColor(R.color.color_white));

        seg_Stock.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        seg_Stock.setBackgroundColor(getResources().getColor(R.color.color_white));
    }
    @OnClick({R.id.seg_sku}) void Seg_sku(){
        seg_sku.setTextColor(getResources().getColor(R.color.color_white));
        seg_sku.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

        seg_atoz.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        seg_atoz.setBackgroundColor(getResources().getColor(R.color.color_white));

        seg_ztoa.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        seg_ztoa.setBackgroundColor(getResources().getColor(R.color.color_white));

        seg_Stock.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        seg_Stock.setBackgroundColor(getResources().getColor(R.color.color_white));
    }
    @OnClick({R.id.seg_Stock}) void seg_Stock(){
        seg_Stock.setTextColor(getResources().getColor(R.color.color_white));
        seg_Stock.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

        seg_atoz.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        seg_atoz.setBackgroundColor(getResources().getColor(R.color.color_white));

        seg_ztoa.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        seg_ztoa.setBackgroundColor(getResources().getColor(R.color.color_white));

        seg_sku.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        seg_sku.setBackgroundColor(getResources().getColor(R.color.color_white));
    }
    @OnClick(R.id.img_linear) void img_linear(){
        img_grid.setBackgroundColor(getResources().getColor(R.color.color_white));
        img_grid.setImageResource(R.drawable.grid_blue);
        img_linear.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        img_linear.setImageResource(R.drawable.linear_white);
        customer_listview.setVisibility(View.VISIBLE);
        customerGridView.setVisibility(View.GONE);

    }
    @OnClick(R.id.img_grid) void img_grid(){
        img_grid.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        img_grid.setImageResource(R.drawable.grid_white);
        img_linear.setBackgroundColor(getResources().getColor(R.color.color_white));
        img_linear.setImageResource(R.drawable.linear_blue);

        customer_listview.setVisibility(View.GONE);
        customerGridView.setVisibility(View.VISIBLE);

    } @OnClick(R.id.add_customer) void add_customer(){
        startActivity(new Intent(getApplicationContext(),AddAProductActivity.class));

    } @OnClick(R.id.img_back) void finishactivity(){
        finish();

    }
    @OnClick(R.id.sync_sales) void sync_data(){

        List<SalesModel> sellinglist=custdatabase.myDao().getsellinglist();

        if(sellinglist.size()>0)
        {
            if (SystemUtility.isConnectingToInternet(ProductActivity.this))
            {
                _parselistofsels(sellinglist);
            }
            else
            {

                SystemUtility.showNetworkFailureAlert(ProductActivity.this);

            }


        }
        else{
            /// call only productlist
            _checkandcompleteloaddata();
        }


    }

    private void _parselistofsels(List<SalesModel> sellinglist)

    {
        try
        {

            try
            {
                sellinglist=custdatabase.myDao().getsellinglist();

                JSONArray jarray=new JSONArray();
                for(int i=0;i<sellinglist.size();i++){
                    JSONObject jobj=new JSONObject();
                    jobj.put("user_id","");
                    jobj.put("sale_date","");
                    jobj.put("client","");
                    jobj.put("route","");
                    jobj.put("store","");
                    jobj.put("products","");
                    jobj.put("","");

                }



                final ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("Please wait...");
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();

                ApiService apiService = RetrofitClient.createRetrofitService(ApiService.class);
                Call<ProductModel> call = apiService.getProductList(sharedprefrences.getString(StringUtility.USERID,"1"));
                call.enqueue(new Callback<ProductModel>() {
                    @Override
                    public void onResponse(Call<ProductModel> call, retrofit2.Response<ProductModel> response) {
                        progressDialog.dismiss();

                        if (response.body().isStatus()) {



                            custdatabase.myDao().deletemysaleslist();
                            _checkandcompleteloaddata();
                        }
                        else {
                            Toast.makeText(ProductActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<ProductModel> call, Throwable t) {
                        progressDialog.dismiss();
                        Toast.makeText(ProductActivity.this, "Call Failed", Toast.LENGTH_SHORT).show();

                    }
                });


            }
            catch (Exception e)
            {
                Log.d("error",e.toString());
            }




        }
        catch (Exception e)
        {

            String s=e.toString();
        }

    }


}
