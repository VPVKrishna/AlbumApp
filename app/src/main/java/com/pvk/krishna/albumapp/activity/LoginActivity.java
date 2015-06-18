package com.pvk.krishna.albumapp.activity;

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
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pvk.krishna.albumapp.R;
import com.pvk.krishna.albumapp.core.Login;
import com.pvk.krishna.albumapp.gson.CustomerLogin;
import com.pvk.krishna.albumapp.gson.ResponseBean;
import com.pvk.krishna.albumapp.utils.Constants;
import com.pvk.krishna.albumapp.utils.OnStringResponseListener;
import com.pvk.krishna.albumapp.utils.ResponseUtilities;
import com.pvk.krishna.albumapp.utils.Utils;
import com.sromku.simple.fb.Permission;
import com.sromku.simple.fb.SimpleFacebook;
import com.sromku.simple.fb.entities.Profile;
import com.sromku.simple.fb.listeners.OnLoginListener;
import com.sromku.simple.fb.listeners.OnLogoutListener;
import com.sromku.simple.fb.listeners.OnProfileListener;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;


public class LoginActivity extends LoadingActivity implements View.OnClickListener, OnStringResponseListener {

    private static final String TAG = LoginActivity.class.getSimpleName();
    private Button btnSignUp;
    private Button btnLogin;
    private EditText etUserName;
    private EditText etPassword;
    private ImageButton ibFacebook;
    private TextView tvForgotPwd;

    private SimpleFacebook mSimpleFacebook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mSimpleFacebook = SimpleFacebook.getInstance(this);

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

                if(Utils.haveNetworkConnection(getApplicationContext())) {
                    Login login = getLoginDetails();
                    if (isValidCredentials(login)) {
                        // TODO: Login here.

                        String query = getQuery(getLoginDetails());
                        ResponseUtilities.getInstance().getStringResponseFromUrl(this, Constants.LOGIN_ID, this, Constants.LOGIN_URL + "?" + query, Constants.LOGIN_TAG);
                        showDialog();
                    } else {
                        Toast.makeText(this, "Invalid Credientials.", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(this, "No network connection.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), SlideActivity.class);
                    startActivity(intent);
                }

                break;

            case R.id.ib_fb_login:
                if (mSimpleFacebook.isLogin()) {
                    getProfileDetails();
                } else {
                    mSimpleFacebook.login(onLoginListener);
                }
                break;

            case R.id.tv_forgot_pwd:
                Toast.makeText(this, "Forgot password", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void getProfileDetails() {
        SimpleFacebook.getInstance().getProfile(new OnProfileListener() {

            @Override
            public void onThinking() {
                showDialog();
            }

            @Override
            public void onException(Throwable throwable) {
                hideDialog();
                Log.d(TAG, "EXCEPTION RAISES:" + throwable.toString());
            }

            @Override
            public void onFail(String reason) {
                hideDialog();
                Log.d(TAG, "FAILED GET PROFILE" + reason);
            }

            @Override
            public void onComplete(Profile response) {
                hideDialog();
                String str = com.pvk.krishna.albumapp.fb.Utils.toHtml(response);
                System.out.println("Profile: " + response);
                Log.d(TAG, "FNAME:" + response.getFirstName());
                Log.d(TAG, "LNAME:" + response.getLastName());
                Log.d(TAG, "EMAIL:" + response.getEmail());
                Log.d(TAG, "DOD:" + response.getBirthday());
                Log.d(TAG, "mName:" + response.getMiddleName());
                Log.d(TAG, "Gender:" + response.getGender());
                Log.d(TAG, "Pic:" + response.getPicture());
                Log.d(TAG, "Name:" + response.getName());
                Log.d(TAG, "HTMLStr:" + str);

                getWindow().addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                Intent intent = new Intent(getApplicationContext(), SlideActivity.class);
                startActivity(intent);
            }
        });
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
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        super.onResume();
        mSimpleFacebook = SimpleFacebook.getInstance(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mSimpleFacebook.onActivityResult(this, requestCode, resultCode, data);
    }

    // Login listener
    final OnLoginListener onLoginListener = new OnLoginListener() {

        @Override
        public void onFail(String reason)
        {
            Log.w(TAG, "Failed to login");
        }

        @Override
        public void onException(Throwable throwable) {
            Log.e(TAG, "Bad thing happened", throwable);
        }

        @Override
        public void onThinking() {
            // show progress bar or something to the user while login is
            // happening
        }

        @Override
        public void onLogin() {
            // change the state of the button or do whatever you want
            Toast.makeText(getApplicationContext(), "SUCCESSFULLY LOGGED IN", Toast.LENGTH_SHORT).show();
            getProfileDetails();
        }

        @Override
        public void onNotAcceptingPermissions(Permission.Type type) {
            Toast.makeText(getApplicationContext(), String.format("You didn't accept %s permissions", type.name()), Toast.LENGTH_SHORT).show();
        }
    };

    final OnLogoutListener onLogoutListener = new OnLogoutListener() {

        @Override
        public void onFail(String reason) {
            Log.w(TAG, "Failed to login");
        }

        @Override
        public void onException(Throwable throwable) {
            Log.e(TAG, "Bad thing happened", throwable);
        }

        @Override
        public void onThinking() {
            // show progress bar or something to the user while login is
            // happening
        }

        @Override
        public void onLogout() {
            // change the state of the button or do whatever you want
            finishActivity();
        }
    };


    @Override
    public void onResponse(String response, int requestId) {
        Log.d("RESPONSE", " res :" + response);
        hideDialog();

        Gson gson = new Gson();
        Type listType = new TypeToken<ResponseBean<CustomerLogin>>() {
        }.getType();
        ResponseBean<CustomerLogin> customLogin = gson.fromJson(response, listType);
        Log.d("LOGIN BEAN:", "BEAN:" + customLogin.toString());

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Toast.makeText(this, "Successfully logged in.", Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(getApplicationContext(), SlideActivity.class);
        startActivity(intent);
    }

    @Override
    public void onErrorResponse(VolleyError errorResponse, int requestId) {

        Log.d("RESPONSE", " error res :");
        hideDialog();
    }

    @Override
    public void parseNetworkResponse(NetworkResponse response, int requestId) {

    }

    @Override
    public Map<String, String> getParams(int requestId) {
        Log.d("RESPONSE", " getParams res :" + loginDetails());
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

    public String getQuery(Login login) {
        String query = Constants.LOGIN_EMAIL + "=" + login.getEmail() + "&" + Constants.LOGIN_PWD + "=" + login.getPassword();
        return query;
    }

    @Override
    public void onBackPressed() {
        mSimpleFacebook.logout(onLogoutListener);
    }

    public void finishActivity(){
        super.onBackPressed();
    }
}
