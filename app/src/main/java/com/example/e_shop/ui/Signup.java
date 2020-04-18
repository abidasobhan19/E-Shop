package com.example.e_shop.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.e_shop.Firebase.MyDatabaseRef;
import com.example.e_shop.R;
import com.example.e_shop.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

public class Signup extends AppCompatActivity implements View.OnClickListener {

    EditText etname, etemail,etpassword;
    private Button btnsign_up;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        initView();
    }

    private void initView() {
        etname =findViewById(R.id.name);
        etemail = findViewById(R.id.Email);
        etpassword = findViewById(R.id.password);
        btnsign_up =findViewById(R.id.sign_up);
        btnsign_up.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        final String name = etname.getText().toString().trim();
        String email = etemail.getText().toString().trim();
        String password= etpassword.getText().toString().trim();

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){


                        FirebaseUser firebaseUser = task.getResult().getUser();

                        User user = new User();
                        user.setUid(firebaseUser.getUid());
                        user.setName(name);
                        user.setEmail(firebaseUser.getEmail());
                        MyDatabaseRef.getInstance().getUserRef().child(user.getUid())
                                .setValue(user, new DatabaseReference.CompletionListener() {
                                    @Override
                                    public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                    }
                                });

                    }
            }
        });
//                       DatabaseReference userRef= MyDatabaseRef.getInstance().getUserRef();
//
//                       String key = userRef.push().getKey();
//                       user.setUid(key);
//                       userRef.child(key).setValue(user);





                    }
            }



