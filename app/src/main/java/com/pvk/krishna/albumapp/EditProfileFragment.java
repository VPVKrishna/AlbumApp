package com.pvk.krishna.albumapp;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.pvk.krishna.albumapp.core.Profile;

/**
 * Created by Krishna on 01/06/2015.
 */
public class EditProfileFragment extends Fragment {

    private EditText etFName, etMName, etLName, etEmail, etDOB, etTelephone, etStreet, etCountry, etState, etCity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.edit_profile, null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        View view=getView();

        TextView tvTitle= (TextView) view.findViewById(R.id.tv_title);
        tvTitle.setText("Edit Profile");
        ImageButton btnBack= (ImageButton) view.findViewById(R.id.btn_back);
        btnBack.setBackgroundResource(R.drawable.option_menu);

        btnBack.setBackgroundResource(R.drawable.option_menu);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((SlideActivity) getActivity()).openSlidingDrawer();
            }
        });

        etFName= (EditText) view.findViewById(R.id.et_sign_up_fname);
        etMName= (EditText) view.findViewById(R.id.et_sign_up_mname);
        etLName= (EditText) view.findViewById(R.id.et_sign_up_lname);
        etEmail= (EditText) view.findViewById(R.id.et_sign_up_email);
        etDOB= (EditText) view.findViewById(R.id.et_sign_up_dob);
        etTelephone= (EditText) view.findViewById(R.id.et_sign_up_telephone);
        etStreet= (EditText) view.findViewById(R.id.et_sign_up_street);
        etCountry= (EditText) view.findViewById(R.id.et_sign_up_country);
        etState= (EditText) view.findViewById(R.id.et_sign_up_state);
        etCity= (EditText) view.findViewById(R.id.et_sign_up_city);

        view.findViewById(R.id.btn_edit_profile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fName=etFName.getText().toString().trim();
                String mName=etMName.getText().toString().trim();
                String lName=etLName.getText().toString().trim();
                String email=etEmail.getText().toString().trim();
                String dob=etDOB.getText().toString().trim();
                String telephone=etTelephone.getText().toString().trim();
                String street=etStreet.getText().toString().trim();
                String country=etCountry.getText().toString().trim();
                String state=etState.getText().toString().trim();
                String city=etCity.getText().toString().trim();

                Profile profile=new Profile(fName, mName, lName, email, dob, telephone, street, country, state, city);

                Toast.makeText(getActivity(), "Profile updation...", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
