package com.example.sandy.androidchatapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class usersActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private RecyclerView mUsersList;

    private DatabaseReference mUserDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        mUserDatabase= FirebaseDatabase.getInstance().getReference().child("Users");

        mToolbar=(Toolbar)findViewById(R.id.user_appbar);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setTitle("All Users");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mUsersList=(RecyclerView)findViewById(R.id.users_list);
        mUsersList.setHasFixedSize(true);
        mUsersList.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onStart() {
        super.onStart();

     FirebaseRecyclerAdapter<Users,userviewHolder> firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<Users, userviewHolder>(

             Users.class,
             R.layout.user_single_layout,
             userviewHolder.class,
             mUserDatabase

     ) {
         @Override
         protected void populateViewHolder(userviewHolder viewHolder, Users model, int position) {

             viewHolder.setName(model.getName());
             viewHolder.setUserStatus(model.getStatus());
             viewHolder.setUserImage(model.getThumb_image());

             final String user_id=getRef(position).getKey();


             viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {


                     Intent profileIntent=new Intent(usersActivity.this,ProfileActivity.class);
                     profileIntent.putExtra("user_id",user_id);
                     startActivity(profileIntent);

                 }
             });

         }
     };

     mUsersList.setAdapter(firebaseRecyclerAdapter);

    }

    public static class userviewHolder extends RecyclerView.ViewHolder{

        View mView;

        public userviewHolder(View itemView) {
            super(itemView);

            mView=itemView;

        }

        public void setName(String name){

            TextView userNameView=mView.findViewById(R.id.user_single_name);
            userNameView.setText(name);


        }

        public void setUserStatus(String status){

            TextView userStatusView=mView.findViewById(R.id.user_single_status);
            userStatusView.setText(status);
        }

        public void setUserImage(String thumb_image){

            CircleImageView userImage=(CircleImageView)mView.findViewById(R.id.user_single_image);

            Picasso.get().load(thumb_image).placeholder(R.mipmap.defaultuser).into(userImage);



        }
    }
}
