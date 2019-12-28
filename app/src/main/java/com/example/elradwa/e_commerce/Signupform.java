package com.example.elradwa.e_commerce;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Signupform extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signupform);


        final   EditText CustomerName=findViewById(R.id.editText3);
        final   EditText UserName=findViewById(R.id.editText4);
        final  EditText Password=findViewById(R.id.editText5);
       final Spinner Gender=findViewById(R.id.spinner2);
        final  EditText BirthDate=findViewById(R.id.editText7);
        final  EditText Job=findViewById(R.id.editText8);
          Button register=findViewById(R.id.button3);
        final ecodatabase obj = new ecodatabase(this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if((!(CustomerName.getText().toString().equals(""))) && (!(UserName.getText().toString().equals(""))) && (!(Password.getText().toString().equals("")))  && (!(BirthDate.getText().toString().equals(""))) && (!(Job.getText().toString().equals(""))) ) {
                   obj.register(CustomerName.getText().toString(), UserName.getText().toString(), Password.getText().toString(), Gender.getSelectedItem().toString(), BirthDate.getText().toString(), Job.getText().toString());

                   Intent i=new Intent(Signupform.this,home.class);
                   startActivity(i);
              }
             else{
                  Toast.makeText(getApplicationContext(),"please fill all fields",Toast.LENGTH_LONG).show();
               }

            }
        });
    }


}
