package com.example.salesreport.Models;

public class LoginModels {



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
    private boolean status;
    private String message;
    private DataBean data;
    public static class DataBean {


        private String id;
        private String name;
        private String email;
        private String mobile;
        private String picture;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public String getLogged_in() {
            return logged_in;
        }

        public void setLogged_in(String logged_in) {
            this.logged_in = logged_in;
        }

        private String logged_in;

    }
}
