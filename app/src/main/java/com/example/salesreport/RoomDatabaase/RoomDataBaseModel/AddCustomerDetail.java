package com.example.salesreport.RoomDatabaase.RoomDataBaseModel;

import android.net.Uri;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="Customer")
public class AddCustomerDetail {
    @PrimaryKey(autoGenerate = true)
    private  int id;


    @ColumnInfo(name="customer_name")
    private String customer_name;
    @ColumnInfo(name="customer_iamge")
    private String customer_image;

    public String getCustomer_image() {
        return customer_image;
    }

    public void setCustomer_image(String customer_image) {
        this.customer_image = customer_image;
    }

    @ColumnInfo(name="customer_contact")
    private String customer_contact;
    @ColumnInfo(name="customer_gender")
    private String customer_gender;

    @ColumnInfo(name="customer_email")
    private String customer_email;

    @ColumnInfo(name="customer_mailingaddress")
    private String customer_mailingaddress;
    @ColumnInfo(name="customer_advpaid")
    private boolean customer_advpaid;

    @ColumnInfo(name="customer_amountpaid")
    private String customer_amountpaid;

    @ColumnInfo(name="customer_notes")
    private String customer_notes;
    @ColumnInfo(name="customer_debit_credit")
    private String customer_debit_credit;

    public String getCustomer_debit_credit() {
        return customer_debit_credit;
    }

    public void setCustomer_debit_credit(String customer_debit_credit) {
        this.customer_debit_credit = customer_debit_credit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_contact() {
        return customer_contact;
    }

    public void setCustomer_contact(String customer_contact) {
        this.customer_contact = customer_contact;
    }

    public String getCustomer_gender() {
        return customer_gender;
    }

    public void setCustomer_gender(String customer_gender) {
        this.customer_gender = customer_gender;
    }

    public String getCustomer_email() {
        return customer_email;
    }

    public void setCustomer_email(String customer_email) {
        this.customer_email = customer_email;
    }

    public String getCustomer_mailingaddress() {
        return customer_mailingaddress;
    }

    public void setCustomer_mailingaddress(String customer_mailingaddress) {
        this.customer_mailingaddress = customer_mailingaddress;
    }

    public boolean isCustomer_advpaid() {
        return customer_advpaid;
    }

    public void setCustomer_advpaid(boolean customer_advpaid) {
        this.customer_advpaid = customer_advpaid;
    }

    public String getCustomer_amountpaid() {
        return customer_amountpaid;
    }

    public void setCustomer_amountpaid(String customer_amountpaid) {
        this.customer_amountpaid = customer_amountpaid;
    }

    public String getCustomer_notes() {
        return customer_notes;
    }

    public void setCustomer_notes(String customer_notes) {
        this.customer_notes = customer_notes;
    }
}
