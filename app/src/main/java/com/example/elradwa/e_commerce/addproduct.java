package com.example.elradwa.e_commerce;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class addproduct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addproduct);

      final  ecodatabase obj=new ecodatabase(this);
        final EditText productname=findViewById(R.id.editText9);
        final EditText price=findViewById(R.id.editText10);
        final EditText quantity=findViewById(R.id.editText11);

        final Spinner categories=findViewById(R.id.spinner);
       

        Button addproduct=findViewById(R.id.button6);

        addproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int category_id = 0;

                final String selectedoption=categories.getSelectedItem().toString();

                if(selectedoption.equals("mobiles")) {
                    category_id = 1;
                }
                else if(selectedoption.equals("laptops")){
                    category_id=2;
                }
                else if(selectedoption.equals("Tvs")){
                    category_id=3;
                }
                else if(selectedoption.equals("Tablets")){
                    category_id=4;
                }
                obj.addproduct(productname.getText().toString(),Integer.parseInt(price.getText().toString()),Integer.parseInt(quantity.getText().toString()),category_id);

                Toast.makeText(getApplicationContext(),"product added",Toast.LENGTH_LONG).show();

            }
        });
    }
}
