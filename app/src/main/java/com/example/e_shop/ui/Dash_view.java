package com.example.e_shop.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.media.MediaRouter;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.e_shop.Firebase.MyDatabaseRef;
import com.example.e_shop.R;
import com.example.e_shop.User;
import com.example.e_shop.UserAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import static android.system.Os.remove;

public class Dash_view extends AppCompatActivity {
    private UserAdapter adapter;
    private  List<User> userList;

    ChildEventListener childEventListener = new ChildEventListener() {
        @Override
        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            Log.d("UUUUUU",dataSnapshot.toString());
            User user = dataSnapshot.getValue(User.class);
            adapter.addUser(user);

        }

        @Override
        public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            Log.d("UUUUUU",dataSnapshot.toString());
            User user = dataSnapshot.getValue(User.class);
            adapter.updateUser(user);

        }

        @Override
        public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
            Log.d("UUUUUU",dataSnapshot.toString());
            Log.d("UUUUUU","CHILD REMOVED CALLED");
            User user = dataSnapshot.getValue(User.class);
            adapter.removeUser(user);

        }

        @Override
        public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_view);

        adapter = new UserAdapter(this);



        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(adapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);


//        MyDatabaseRef.getInstance().getUserRef().addListenerForSingleValueEvent(myListener);

        MyDatabaseRef.getInstance().getUserRef().addChildEventListener(childEventListener);
    }


    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT| ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

            int position = viewHolder.getAdapterPosition();
            switch (direction){
                case ItemTouchHelper.LEFT:
                    userList.remove(adapter);
                    adapter.notifyItemRemoved(position);
                    break;

                case ItemTouchHelper.RIGHT:

                    break;

            }
        }
    };

    }

