package com.example.e_shop;

import android.content.Context;
import android.content.Intent;
import android.icu.text.Transliterator;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_shop.Firebase.MyDatabaseRef;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserHolder> {

    private Context context;
    private LayoutInflater inflater;
    private List<User> userList;

    private UserHandler handler;

    public UserAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.userList = new ArrayList<>();
        this.handler = new UserHandler(this);
    }

    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_user,parent,false);
        return new UserHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder holder, int position) {
        User user = userList.get(position);

        holder.bind(user);


    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public List<User> getUserList(){
        return this.userList;
    }

    public void addUser(User user){
        handler.addUser(user);



    }

    public void updateUser(User user){
        int position = getPosition(user);
        if(position!=-1){
            userList.set(position,user);
            notifyItemChanged(position);
        }

    }

    public void removeUser(User user){
        int position = getPosition(user);
        userList.remove(position);
        notifyItemRemoved(position);

    }

    private int getPosition(User user){
        for (User x: userList){
            if(x.getUid().equals(user.getUid())){
                return userList.indexOf(x);
            }
        }
        return -1;
    }


    class UserHolder extends RecyclerView.ViewHolder  implements Serializable {

        TextView tvName,tvEmail;


        public UserHolder(@NonNull View itemView) {
            super(itemView);
    itemView.setOnClickListener(new View.OnClickListener() {

    @Override
    public void onClick(View v) {
        User user = userList.get(getAdapterPosition());
        Intent intent = new Intent(context, Message.class);
        intent.putExtra("User", (Serializable) user);
        context.startActivity(intent);
    }
});
            tvName = itemView.findViewById(R.id.name);
            tvEmail = itemView.findViewById(R.id.email);



        }


        public void bind(User user){
            tvName.setText(user.getName());
            tvEmail.setText(user.getEmail());

        }



    }
    }

