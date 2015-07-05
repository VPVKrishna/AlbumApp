package com.pvk.krishna.albumapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pvk.krishna.albumapp.R;
import com.pvk.krishna.albumapp.core.Registration;
import com.pvk.krishna.albumapp.gson.CustomerReg;
import com.pvk.krishna.albumapp.gson.ResponseBean;
import com.pvk.krishna.albumapp.utils.Constants;
import com.pvk.krishna.albumapp.utils.OnStringResponseListener;
import com.pvk.krishna.albumapp.utils.ResponseUtilities;

import java.lang.reflect.Type;
import java.util.Map;

public class SignUpActivity extends LoadingActivity implements OnStringResponseListener {

    private EditText etFName, etMName, etLName, etEmail, etPwd, etConfirmPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        etFName = (EditText) findViewById(R.id.et_sign_up_fname);
        etMName = (EditText) findViewById(R.id.et_sign_up_mname);
        etLName = (EditText) findViewById(R.id.et_sign_up_lname);
        etEmail = (EditText) findViewById(R.id.et_sign_up_email);
        etPwd = (EditText) findViewById(R.id.et_sign_up_pwd);
        etConfirmPwd = (EditText) findViewById(R.id.et_sign_up_cfm_pwd);

        TextView tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitle.setText("Registration");

        Button btnSignUp = (Button) findViewById(R.id.btn_signup);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (com.pvk.krishna.albumapp.utils.Utils.haveNetworkConnection(getApplicationContext())) {
                    if (isValidRegistration()) {

                        String userName = "cats";
                        String password = "soap123";
                        String mail = "test321.igex@gmaila4.com";
                        String f_name = "test4";
                        String l_name = "soap4";
                        String cust_pwd = "test123x4";

                        String query = Constants.CUSTOMER_CREATE_USER + "=" + userName +
                                "&" + Constants.CUSTOMER_CREATE_PWD + "=" + password +
                                "&" + Constants.CUSTOMER_CREATE_EMAIL + "=" + mail +
                                "&" + Constants.CUSTOMER_CREATE_FNAME + "=" + f_name +
                                "&" + Constants.CUSTOMER_CREATE_LNAME + "=" + l_name +
                                "&" + Constants.CUSTOMER_CREATE_CUST_PWD + "=" + cust_pwd;

                        Log.d("REGISTRATION:", "Query:" + query);
                        ResponseUtilities.getInstance().getStringResponseFromUrl(SignUpActivity.this, Constants.CUSTOMER_CREATE_ID,
                                SignUpActivity.this, Constants.CUSTOMER_CREATE_URL + "?" + query, Constants.CUSTOMER_CREATE_TAG);
                        showDialog();
                        Toast.makeText(getApplicationContext(), "SignUp", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(getApplicationContext(), "Invalid Registration.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "No network connection.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), SlideActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    public boolean isValidRegistration() {
        String fName = etFName.getText().toString().trim();
        String mName = etMName.getText().toString().trim();
        String lName = etLName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String pwd = etPwd.getText().toString().trim();
        String confirmPwd = etConfirmPwd.getText().toString().trim();

        if (!pwd.equals(confirmPwd)) {
            return false;
        }
        // TODO: other validation here

        Registration registration = new Registration(fName, mName, lName, email, pwd);

        Log.d("REGISTRATION:", "REG:" + registration);

        return true;
    }

    @Override
    public void onResponse(String response, int requestId) {
        Log.d("RESPONSE:", "RES:" + response);

        Gson gson = new Gson();
        Type listType = new TypeToken<ResponseBean<CustomerReg>>() {
        }.getType();
        ResponseBean<CustomerReg> customReg = gson.fromJson(response, listType);
        Log.d("REG BEAN:", "BEAN:" + customReg.toString());
        hideDialog();

        Intent intent = new Intent(getApplicationContext(), SlideActivity.class);
        startActivity(intent);
    }

    @Override
    public void onErrorResponse(VolleyError errorResponse, int requestId) {
        Log.d("RESPONSE:", "ERROR RES:");
        hideDialog();
    }

    @Override
    public void parseNetworkResponse(NetworkResponse response, int requestId) {

    }

    @Override
    public Map<String, String> getParams(int requestId) {
        return null;
    }

}
