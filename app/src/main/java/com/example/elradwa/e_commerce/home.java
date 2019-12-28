package com.example.elradwa.e_commerce;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


public class home extends AppCompatActivity {
   Cursor productss;
   ArrayAdapter <String> productsname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
      final ecodatabase obj1=new ecodatabase(getApplicationContext());

        final Button addproduct=findViewById(R.id.button4);
        final Button mycart=findViewById(R.id.button5);
        final Button searchproduct=findViewById(R.id.button7);
        final EditText input=findViewById(R.id.editText6);
       final ListView products=findViewById(R.id.list1);
        productsname=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1);
        products.setAdapter(productsname);

        productsname.clear();
       productss=obj1.getproducts();


       if(productss!=null){
           while(!productss.isAfterLast()){
               productsname.add(productss.getString(0));
               productss.moveToNext();
           }
       }
       else{
           Toast.makeText(getApplicationContext(),"no products",Toast.LENGTH_LONG).show();
       }


     searchproduct.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             String productName = input.getText().toString();
             productss = obj1.searchproducts(productName);
             productsname.clear();
             if (productss != null) {
                 while (!productss.isAfterLast()) {
                     productsname.add(productss.getString(0));
                     productss.moveToNext();
                 }
             }
         }
     });

        addproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(home.this,addproduct.class);
                startActivity(i);
            }
        });

        mycart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(home.this,mycart.class);
                startActivity(i);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.categories,menu);
        return super.onCreateOptionsMenu(menu);
    }


}
