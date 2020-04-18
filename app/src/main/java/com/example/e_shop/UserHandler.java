package com.example.e_shop;

public class UserHandler {

    private UserAdapter adapter;

    public UserHandler(UserAdapter adapter){
        this.adapter=adapter;
    }

    public void addUser(User user){
        this.adapter.getUserList().add(user);
        int position = this.adapter.getUserList().indexOf(user);
        adapter.notifyItemInserted(position);

    }
    public void updateUser(User user){

    }
    public void removeUser(User user){

    }
}
