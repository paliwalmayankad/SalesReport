package com.example.salesreport.Models;

public class RegisterModel {
    private boolean status;
    private String message;
    private DataBean data;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {


        private String adm_id;
        private String adm_username;
        private String adm_password;
        private String adm_email;
        private String adm_firstname;

        public String getAdm_id() {
            return adm_id;
        }

        public void setAdm_id(String adm_id) {
            this.adm_id = adm_id;
        }

        public String getAdm_username() {
            return adm_username;
        }

        public void setAdm_username(String adm_username) {
            this.adm_username = adm_username;
        }

        public String getAdm_password() {
            return adm_password;
        }

        public void setAdm_password(String adm_password) {
            this.adm_password = adm_password;
        }

        public String getAdm_email() {
            return adm_email;
        }

        public void setAdm_email(String adm_email) {
            this.adm_email = adm_email;
        }

        public String getAdm_firstname() {
            return adm_firstname;
        }

        public void setAdm_firstname(String adm_firstname) {
            this.adm_firstname = adm_firstname;
        }
    }
}
