package com.example.e_shop.Firebase;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MyDatabaseRef {

    private static final String USERS_REF="Users";


    private static MyDatabaseRef myDatabaseRef;

    private FirebaseDatabase database;

    private MyDatabaseRef() {
        this.database  = FirebaseDatabase.getInstance();
    }

    public static MyDatabaseRef getInstance() {
        if (myDatabaseRef == null) {
            myDatabaseRef = new MyDatabaseRef();
        }
        return myDatabaseRef;
    }


    public DatabaseReference getUserRef(){
        return database.getReference().child(USERS_REF);
    }



}