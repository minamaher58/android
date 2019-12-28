package com.example.elradwa.e_commerce;

import android.content.ContentValues;
import android.content.Context;
//import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.EditText;

public class ecodatabase extends SQLiteOpenHelper{

  private static String databaseName="ecommerce";
  private   SQLiteDatabase ecommerce ;

  private String customers = "create table customers(custid integer primary key autoincrement, custname text not null, username text not null,password text not null,gender text not null,birthdate text not null,job text not null) ";
  private String orders = "create table orders(ordid integer primary key autoincrement,orddate text not null, address text not null,cust_id integer,FOREIGN KEY(cust_id)REFERENCES customers(custid))";
  private     String orderDetails = "create table orderdetails(quantity integer, ord_id integer,pro_id integer, Foreign Key (ord_id) references orders(ordid) ,FOREIGN KEY(pro_id) REFERENCES products(proid),PRIMARY KEY ( ord_id,pro_id) )";

  private  String Products="create table products(proid integer primary key autoincrement , proname text not null,price integer,quantity integer,cat_id integer,Foreign Key(cat_id)references categories(catid))";
  private   String categories = "create table categories(catid integer primary key autoincrement,catname text not null)";



    public ecodatabase(Context context) {
        super(context, databaseName,null,2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(customers);
       db.execSQL(orders);
     db.execSQL(orderDetails);
        db.execSQL(Products);
      db.execSQL(categories);


     //   db.execSQL("insert into customers (custname,username,password,gender,birthdate,job)values('minamaher','mina','1234','male','28/08/1997','student')");
    //    db.execSQL("insert into orders (orddate,address,cust_id) values('5/6/2018','abasya',1)");
     //   db.execSQL("insert into orderdetails(quantity,ord_id,proid)values(2,1,2)");
    //   db.execSQL("insert into products (proname,price,quantity,cat_id)values('laptop',10000,1,3)");
    //    db.execSQL("insert into categories(catid,catname)values(3,'electronics')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
          db.execSQL("drop table if exists customers");
          db.execSQL("drop table if exists orders");
          db.execSQL("drop table if exists orderdetails");
          db.execSQL("drop table if exists products");
          db.execSQL("drop table if exists categories");
          onCreate(db);
    }

    public void register(String a,String b,String c,String d,String e,String f){
        ContentValues row=new ContentValues();
        row.put("custname",a);
        row.put("username",b);
        row.put("password",c);
        row.put("gender",d);
        row.put("birthdate",e);
        row.put("job",f);

        ecommerce = getWritableDatabase();
        ecommerce.insert("customers",null,row);
        ecommerce.close();


    }
   public boolean login(String name,String password){
        ecommerce=getReadableDatabase();
        Cursor cursor=ecommerce.rawQuery("select * from customers where username="+"'name'"+"and password="+"'password'",null);
       if(cursor.getCount()!=0){
           return  true;
       }
       else{
           return false;
       }
   }

    public void addproduct(String productName, int price,int quantity, int category_id){
       ContentValues row=new ContentValues();
       row.put("proname",productName);
       row.put("price",price);
       row.put("quantity",quantity);
       row.put("cat_id",category_id);

       ecommerce=getWritableDatabase();
       ecommerce.insert("products",null,row);
       ecommerce.close();
    }

    public Cursor getproducts(){

       ecommerce=getReadableDatabase();
        Cursor products=ecommerce.rawQuery("select proname from products",null);
        if(products.getCount()!=0){
            products.moveToFirst();
            ecommerce.close();
            return products;
        }
        ecommerce.close();
        return null;


    }

    public Cursor searchproducts(String product){
        ecommerce=getReadableDatabase();
        Cursor products=ecommerce.rawQuery("select proname from products where proname like?",new String[]{"%"+product+"%"});

       if(products.getCount()!=0){
           products.moveToFirst();
           ecommerce.close();
           return products;
       }
       ecommerce.close();
       return null;
    }


}
