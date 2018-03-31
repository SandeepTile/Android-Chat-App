package com.example.sandy.androidchatapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private EditText mDisplayName;
    private EditText mEmail;
    private EditText mPassword;
    private Button mCreateBtn;

    private Toolbar mToolbar;

    private FirebaseAuth mAuth;

    private ProgressDialog mRegisterProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Progressbar
        mRegisterProgress=new ProgressDialog(this);

        //Toolbar set
         mToolbar=(Toolbar)findViewById(R.id.register_toolbar);
         setSupportActionBar(mToolbar);
         getSupportActionBar().setTitle("Create Account");
         getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //Firebase Authentication
        mAuth = FirebaseAuth.getInstance();

        mDisplayName=(EditText)findViewById(R.id.reg_display_name);
        mEmail=(EditText)findViewById(R.id.reg_email);
        mPassword=(EditText)findViewById(R.id.reg_password);
        mCreateBtn=(Button)findViewById(R.id.reg_submit_btn);

        mCreateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String display_name=mDisplayName.getText().toString();
                String email=mEmail.getText().toString();
                String password=mPassword.getText().toString();

                if (!TextUtils.isEmpty(display_name)||!TextUtils.isEmpty(email)||!TextUtils.isEmpty(password)){


                    //Register user Progress Dialog
                    mRegisterProgress.setTitle("Registering User");
                    mRegisterProgress.setMessage("Please wait while we Creating your account");
                    mRegisterProgress.setCanceledOnTouchOutside(false);
                    mRegisterProgress.show();

                    register_user(display_name,email,password);

                }else {

                    Toast.makeText(RegisterActivity.this, "Fill all Fields", Toast.LENGTH_SHORT).show();
                }



            }
        });
    }

    private void register_user(String display_name, String email, String password) {

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){

                    mRegisterProgress.dismiss();
                    Intent mainIntent=new Intent(RegisterActivity.this,MainActivity.class);
                    startActivity(mainIntent);
                    finish();


                }else {

                    mRegisterProgress.hide();
                    Toast.makeText(RegisterActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
