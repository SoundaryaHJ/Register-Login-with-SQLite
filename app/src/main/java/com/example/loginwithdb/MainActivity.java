package com.example.loginwithdb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    EditText username,repassword;
    EditText password;
    Button btnsignup;
    TextView btnsignin;
    DBHelper DB;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username=(EditText) findViewById(R.id.username);
        password=(EditText) findViewById(R.id.password);
        repassword=(EditText) findViewById(R.id.repassword);
        btnsignup=(Button) findViewById(R.id.btnsignup);
        btnsignin=(TextView) findViewById(R.id.btnsignin);

        DB = new DBHelper(this);

        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();

                if(user.equals("") || pass.equals("") || repass.equals(""))

                    Toast.makeText(MainActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                    else
                    {
                        if(pass.equals(repass))
                        {
                            Boolean checkuser = DB.checkusername(user);
                            if(checkuser==false)
                            {
                                Boolean insert = DB.insertData(user, pass);
                                if(insert==true)
                                {
                                    Toast.makeText(MainActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();

                                    Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                                    startActivity(intent);

                                }
                                else
                                {
                                    Toast.makeText(MainActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                                }
                            }

                            else
                            {
                                Toast.makeText(MainActivity.this, "User already Exist! please SignIn", Toast.LENGTH_SHORT).show();
                            }

                        }
                        else
                        {
                            Toast.makeText(MainActivity.this, "Passwords not matching", Toast.LENGTH_SHORT).show();
                        }

                    }


            }
        });

        btnsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);


            }
        });
    }

}