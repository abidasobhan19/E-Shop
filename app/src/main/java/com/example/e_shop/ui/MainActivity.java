package com.example.e_shop.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.e_shop.R;
import com.example.e_shop.ui.Login_Activity;
import com.example.e_shop.ui.Signup;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnlogin;
    Button btnsignup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnlogin = findViewById(R.id.login);
        btnsignup = findViewById(R.id.sign_up);
        btnlogin.setOnClickListener(this);
        btnsignup.setOnClickListener(this);




        }

    @Override
    public void onClick(View v) {
        if (v== btnsignup){
            startActivity(new Intent(getApplicationContext(), Signup.class));
        }else if (v==btnlogin){
            startActivity(new Intent(getApplicationContext(), Login_Activity.class));

        }
    }
}




