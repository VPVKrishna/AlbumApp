package com.pvk.krishna.albumapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.pvk.krishna.albumapp.core.Login;
import com.pvk.krishna.albumapp.fb.MainActivity;
import com.pvk.krishna.albumapp.utils.Constants;
import com.pvk.krishna.albumapp.utils.OnStringResponseListener;
import com.pvk.krishna.albumapp.utils.ResponseUtilities;

import java.util.HashMap;
import java.util.Map;


public class LoginActivity extends Activity implements View.OnClickListener, OnStringResponseListener {

    private Button btnSignUp;
    private Button btnLogin;
    private EditText etUserName;
    private EditText etPassword;
    private ImageButton ibFacebook;
    private TextView tvForgotPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnSignUp = (Button) findViewById(R.id.btn_signup);
        btnLogin = (Button) findViewById(R.id.btn_login);
        ibFacebook = (ImageButton) findViewById(R.id.ib_fb_login);
        tvForgotPwd = (TextView) findViewById(R.id.tv_forgot_pwd);

        etUserName = (EditText) findViewById(R.id.et_username);
        etPassword = (EditText) findViewById(R.id.et_password);

        etUserName.setText("test321.igex@gmail.com");
        etPassword.setText("test123x");
        btnLogin.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);
        ibFacebook.setOnClickListener(this);
        tvForgotPwd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_signup:
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                Intent signupIntent = new Intent(this, SignUpActivity.class);
                startActivity(signupIntent);
                break;

            case R.id.btn_login:
                Login login = getLoginDetails();
                if (isValidCredentials(login)) {
                    // TODO: Login here.
                    getWindow().addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
//                    Toast.makeText(this, "Successfully logged in.", Toast.LENGTH_SHORT).show();
//                    Intent intent=new Intent(getApplicationContext(), SlideActivity.class);
//                    startActivity(intent);
                    ResponseUtilities.getInstance().getStringResponseFromUrl(this, Constants.LOGIN_ID, this, Constants.LOGIN_URL, Constants.LOGIN_TAG);
                } else {
                    Toast.makeText(this, "Invalid Credientials.", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.ib_fb_login:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;

            case R.id.tv_forgot_pwd:
                Toast.makeText(this, "Forgot password", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private boolean isValidCredentials(Login login) {
        // TODO: validation logic here.
        int emailLen = login.getEmail().trim().length();
        if (emailLen < 10) {
            return false;
        }
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
    }

    @Override
    public void onResponse(String response, int requestId) {
        Log.d("RESPONSE", " res :" + response);
    }

    @Override
    public void onErrorResponse(VolleyError errorResponse, int requestId) {

        Log.d("RESPONSE", " error res :");
    }

    @Override
    public void parseNetworkResponse(NetworkResponse response, int requestId) {

    }

    @Override
    public Map<String, String> getParams(int requestId) {
        Log.d("RESPONSE", " getParams res :"+loginDetails());
        return loginDetails();
    }

    public Login getLoginDetails() {
        String email = etUserName.getText().toString().trim();
        String pwd = etPassword.getText().toString().trim();
        return new Login(email, pwd);
    }

    public HashMap<String, String> loginDetails() {
        Login login = getLoginDetails();
        HashMap<String, String> loginMap = new HashMap<>();
        loginMap.put(Constants.LOGIN_EMAIL, login.getEmail());
        loginMap.put(Constants.LOGIN_PWD, login.getPassword());
        return loginMap;
    }
}
