package com.example.sandy.androidchatapp;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    private FirebaseAuth mAuth;

    private ViewPager mViewPager;

    private SectionPagerAdapter mSectionPagerAdapter;

    private TabLayout mTablayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        mToolbar=(Toolbar)findViewById(R.id.register_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Android App");

        //tabs
        mViewPager=(ViewPager)findViewById(R.id.main_tabpager);
        mSectionPagerAdapter=new SectionPagerAdapter(getSupportFragmentManager());

        mViewPager.setAdapter(mSectionPagerAdapter);

        mTablayout=(TabLayout)findViewById(R.id.main_tabs);
        mTablayout.setupWithViewPager(mViewPager);


    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if(currentUser == null){

            sendToStart();

        }

    }

    private void sendToStart() {

        Intent startIntent=new Intent(MainActivity.this,StartActivity.class);
        startActivity(startIntent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
         super.onCreateOptionsMenu(menu);

         getMenuInflater().inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
         super.onOptionsItemSelected(item);

         if (item.getItemId()==R.id.main_logout_btn){

             FirebaseAuth.getInstance().signOut();
             sendToStart();

         }if(item.getItemId()==R.id.main_setting_btn){

             Intent settingIntent=new Intent(MainActivity.this,SettingsActivity.class);
             startActivity(settingIntent);


         }
         if (item.getItemId()==R.id.main_all_btn){

             Intent allUserIntent=new Intent(MainActivity.this,usersActivity.class);
             startActivity(allUserIntent);

         }

        return true;
    }
}
