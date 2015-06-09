package com.pvk.krishna.albumapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.pvk.krishna.albumapp.core.Login;
import com.pvk.krishna.albumapp.fb.MainActivity;


public class LoginActivity extends Activity implements View.OnClickListener {

    private Button btnSignUp;
    private Button btnLogin;
    private EditText etUserName;
    private EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnSignUp = (Button) findViewById(R.id.btn_signup);
        btnLogin = (Button) findViewById(R.id.btn_login);

        etUserName = (EditText) findViewById(R.id.et_username);
        etPassword = (EditText) findViewById(R.id.et_password);

        btnLogin.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_signup:
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                Intent signupIntent = new Intent(this, SignUpActivity.class);
                startActivity(signupIntent);
                break;

            case R.id.btn_login:
                String email=etUserName.getText().toString().trim();
                String pwd=etPassword.getText().toString().trim();
                Login login=new Login(email, pwd);
                if(isValidCredentials(login)) {
                    // TODO: Login here.
                    getWindow().addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                    Toast.makeText(this, "Successfully logged in.", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getApplicationContext(), SlideActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(this, "Invalid Credientials.", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(this, MainActivity.class);
                    startActivity(intent);
                }
                break;
        }
    }

    private boolean isValidCredentials(Login login){
        // TODO: validation logic here.
        int emailLen=login.getEmail().trim().length();
        if(emailLen<10){
            return false;
        }
        return true;
    }
}
