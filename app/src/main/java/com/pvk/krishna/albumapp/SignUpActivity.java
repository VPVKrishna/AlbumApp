package com.pvk.krishna.albumapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.pvk.krishna.albumapp.core.Registration;


public class SignUpActivity extends Activity {

    private EditText etFName, etMName, etLName, etEmail, etPwd, etConfirmPwd, etDOB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);

        etFName= (EditText) findViewById(R.id.et_sign_up_fname);
        etMName= (EditText) findViewById(R.id.et_sign_up_mname);
        etLName= (EditText) findViewById(R.id.et_sign_up_lname);
        etEmail= (EditText) findViewById(R.id.et_sign_up_email);
        etPwd= (EditText) findViewById(R.id.et_sign_up_pwd);
        etConfirmPwd= (EditText) findViewById(R.id.et_sign_up_cfm_pwd);
        etDOB= (EditText) findViewById(R.id.et_sign_up_dob);

        TextView tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitle.setText("Registration");

        Button btnSignUp = (Button) findViewById(R.id.btn_signup);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isValidRegistration()) {
                    Toast.makeText(getApplicationContext(), "SignUp", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), SlideActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(), "Invalid Registration.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public boolean isValidRegistration(){
        String fName=etFName.getText().toString().trim();
        String mName=etMName.getText().toString().trim();
        String lName=etLName.getText().toString().trim();
        String email=etEmail.getText().toString().trim();
        String pwd=etPwd.getText().toString().trim();
        String confirmPwd=etConfirmPwd.getText().toString().trim();
        String dob=etDOB.getText().toString().trim();

        if(!pwd.equals(confirmPwd)){
            return false;
        }
        // TODO: other validation here

        Registration registration=new Registration(fName, mName, lName, email, pwd, dob);

        return true;
    }
}
