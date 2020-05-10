package com.example.salesreport.Models;

import java.util.ArrayList;

public class ProductModel {
    private boolean status;
    private String message;

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

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    private Data data;

    public  class Data {
        public  ArrayList<DataBean> products;
        public  ArrayList<DealerBean> dealers;

        public ArrayList<DealerBean> getDealers() {
            return dealers;
        }

        public void setDealers(ArrayList<DealerBean> routes) {
            this.dealers = routes;
        }

        public ArrayList<DataBean> getProducts() {
            return products;
        }

        public void setProducts(ArrayList<DataBean> products) {
            this.products = products;
        }
    }

  public  class DataBean{
        private String id;
        private String name;
        private String categories_ids;
        private String categories;
        private String stores;
        private String routes;
        private String distributers;

        private String qty_type;
        private String currency;
        private String sku;
      private String actual_price;
      private String sell_price;
      private String stock;
      private String barcode;
      private String image;

      public String getQty_type() {
          return qty_type;
      }

      public void setQty_type(String qty_type) {
          this.qty_type = qty_type;
      }

      public String getCurrency() {
          return currency;
      }

      public void setCurrency(String currency) {
          this.currency = currency;
      }

      public String getSku() {
          return sku;
      }

      public void setSku(String sku) {
          this.sku = sku;
      }

      public String getActual_price() {
          return actual_price;
      }

      public void setActual_price(String actual_price) {
          this.actual_price = actual_price;
      }

      public String getSell_price() {
          return sell_price;
      }

      public void setSell_price(String sell_price) {
          this.sell_price = sell_price;
      }

      public String getStock() {
          return stock;
      }

      public void setStock(String stock) {
          this.stock = stock;
      }

      public String getBarcode() {
          return barcode;
      }

      public void setBarcode(String barcode) {
          this.barcode = barcode;
      }

      public String getImage() {
          return image;
      }

      public void setImage(String image) {
          this.image = image;
      }



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

        public String getCategories_ids() {
            return categories_ids;
        }

        public void setCategories_ids(String categories_ids) {
            this.categories_ids = categories_ids;
        }

        public String getCategories() {
            return categories;
        }

        public void setCategories(String categories) {
            this.categories = categories;
        }

        public String getStores() {
            return stores;
        }

        public void setStores(String stores) {
            this.stores = stores;
        }

        public String getRoutes() {
            return routes;
        }

        public void setRoutes(String routes) {
            this.routes = routes;
        }

        public String getDistributers() {
            return distributers;
        }

        public void setDistributers(String distributers) {
            this.distributers = distributers;
        }
    }
  public  class DealerBean{
        private String id;
        private String name;
        private ArrayList<RouteBean> routes;

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

      public ArrayList<RouteBean> getRoutes() {
          return routes;
      }

      public void setRoutes(ArrayList<RouteBean> routes) {
          this.routes = routes;
      }
  }
    public  class RouteBean{
        private String id;
        private String name;
        private ArrayList<StoreBean> stores;

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

        public ArrayList<StoreBean> getStores() {
            return stores;
        }

        public void setStores(ArrayList<StoreBean> stores) {
            this.stores = stores;
        }
    }
    public  class StoreBean {
        private String id;
        private String name;

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
    }


    }

