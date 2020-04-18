package com.example.e_shop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class Message extends AppCompatActivity {
    private User user;
    EditText etemail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        if(getIntent().getExtras() != null) {
            User user = (User) getIntent().getSerializableExtra("User");
        }

        etemail = findViewById(R.id.email);

        etemail.setText(user.getName());

        }
    }

