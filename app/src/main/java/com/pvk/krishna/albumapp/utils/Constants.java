package com.pvk.krishna.albumapp.utils;

/**
 * Created by Krishna on 10/06/2015.
 */
public interface Constants {

    // login details.
    // http://snipsnapz.embedinfosoft.com/webservices/customer/customerlogin.php?email=test321.igex@gmail.com&cust_pswd=test123x
    int LOGIN_ID=1000;
    String LOGIN_URL="http://snipsnapz.embedinfosoft.com/webservices/customer/customerlogin.php";
    String LOGIN_TAG="login_tag";
    String LOGIN_EMAIL="email";
    String LOGIN_PWD="cust_pswd";

    // http://snipsnapz.embedinfosoft.com/webservices/category/categorylist.php?user=cats&pswd=soap123
    int CATEGORY_LIST_ID=1001;
    String CATEGORY_LIST_URL="http://snipsnapz.embedinfosoft.com/webservices/category/categorylist.php";
    String CATEGORY_LIST_TAG="category_list_tag";
    String CATEGORY_USER="user";
    String CATEGORY_PWD="pswd";


    // http://snipsnapz.embedinfosoft.com/webservices/category/categoryproducts.php?user=cats&pswd=soap123&cat_id=3
    int CATEGORY_PRODUCTS_ID=1002;
    String CATEGORY_PRODUCTS_URL="http://snipsnapz.embedinfosoft.com/webservices/category/categoryproducts.php";
    String CATEGORY_PRODUCTS_TAG="category_products_tag";
    String CATEGORY_PRODUCTS_USER="user";
    String CATEGORY_PRODUCTS_PWD="pswd";
    String CATEGORY_PRODUCTS_CAT_ID="3";


    // http://snipsnapz.embedinfosoft.com/webservices/product/productlist.php?user=cats&pswd=soap123
    int PRODUCT_LIST_ID=1003;
    String PRODUCT_LIST_URL="http://snipsnapz.embedinfosoft.com/webservices/product/productlist.php";
    String PRODUCT_LIST_TAG="product_list_tag";
    String PRODUCT_LIST_USER="user";
    String PRODUCT_PWD="pwd";


    // http://snipsnapz.embedinfosoft.com/webservices/customer/customercreate.php?user=cats&pswd=soap123&email=test321.igex@gmail.com
    // &f_name=test&l_name=soap&cust_pswd=test123x
    int CUSTOMER_CREATE_ID=1004;
    String CUSTOMER_CREATE_USER="user";
    String CUSTOMER_CREATE_EMAIL="email";
    String CUSTOMER_CREATE_FNAME="f_name";
    String CUSTOMER_CREATE_LNAME="l_name";
    String CUSTOMER_CREATE_PWD="cust_pswd";


    /**
     * 1) Category list - returns entire category tree i.e main and sub categories.
     http://snipsnapz.embedinfosoft.com/webservices/category/categorylist.php?user=cats&pswd=soap123

     2) Category-wise product list.
     http://snipsnapz.embedinfosoft.com/webservices/category/categoryproducts.php?user=cats&pswd=soap123&cat_id=3

     3) Entire product list of the catalog
     http://snipsnapz.embedinfosoft.com/webservices/product/productlist.php?user=cats&pswd=soap123

     4) Create new customer - required for registering new users
     http://snipsnapz.embedinfosoft.com/webservices/customer/customercreate.php?user=cats&pswd=soap123&email=test321.igex@gmail.com&f_name=test&l_name=soap&cust_pswd=test123x

     */


    /**
     * 1-      Category list - returns entire category tree i.e main and sub categories.

     http://snipsnapz.embedinfosoft.com/webservices/category/categorylist.php?user=cats&pswd=soap123

     2-      Category-wise product list.

     cat_id : this is the id of the category that we retrive the product list of.
     http://snipsnapz.embedinfosoft.com/webservices/category/categoryproducts.php?user=cats&pswd=soap123&cat_id=3

     3-      Login

     http://snipsnapz.embedinfosoft.com/webservices/customer/customerlogin.php?email=test321.igex@gmail.com&cust_pswd=test123x

     4-      For the sign up/ sign in using facebook

     You have to use the API to get the data from the face-book then pass it to the website using (Registration/login) web-service.

     */
}
