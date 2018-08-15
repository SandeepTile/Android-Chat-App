package com.example.sandy.androidchatapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartActivity extends AppCompatActivity {

    private Button mRegBtn;
    private Button mAlreadyAccountBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        //Register Button
        mRegBtn=(Button)findViewById(R.id.start_reg_btn);
        mRegBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent reg_Intent=new Intent(StartActivity.this,RegisterActivity.class);
                startActivity(reg_Intent);
            }
        });

        //Already Account Button
        mAlreadyAccountBtn=(Button)findViewById(R.id.start_already_acc_btn);
        mAlreadyAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent already_account_Intent=new Intent(StartActivity.this,LoginActivity.class);
                startActivity(already_account_Intent);
            }
        });
    }
}
