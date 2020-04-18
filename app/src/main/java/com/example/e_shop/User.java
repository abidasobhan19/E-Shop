package com.example.e_shop;

import android.widget.EditText;
import android.widget.TextView;

import java.io.Serializable;

public class User  implements Serializable {

    private String uid;
    private String name;
    private String email;


    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
