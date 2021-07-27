package com.example.loginwithdb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity
{
    EditText username1,password1;
    Button btnsignin1;
    TextView register;
    DBHelper DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username1=(EditText) findViewById(R.id.username1);
        password1=(EditText) findViewById(R.id.password1);
        btnsignin1=(Button) findViewById(R.id.btnsignin1);
        register = (TextView) findViewById(R.id.register);

        DB = new DBHelper(this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });

        btnsignin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
               String user = username1.getText().toString();
               String pass = password1.getText().toString();

               if(user.equals("")||pass.equals(""))

                   Toast.makeText(LoginActivity.this, "please enter all the fields", Toast.LENGTH_SHORT).show();
                else
               {
                   Boolean checkuserpass = DB.checkusernamepassword(user, pass);
                   if (checkuserpass == true)
                   {
                       Toast.makeText(LoginActivity.this, "SignIn is Successful", Toast.LENGTH_SHORT).show();

                       Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                       startActivity(intent);
                   }
                   else
                   {
                       Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                   }

               }
            }
        });
    }
}