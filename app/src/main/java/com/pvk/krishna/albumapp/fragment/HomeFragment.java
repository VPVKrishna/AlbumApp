package com.pvk.krishna.albumapp.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.pvk.krishna.albumapp.R;
import com.pvk.krishna.albumapp.activity.SlideActivity;


/**
 * Created by Krishna on 31/05/2015.
 */
public class HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_home, null);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        TextView tvTitle = (TextView) getView().findViewById(R.id.tv_title);
        tvTitle.setText("Home");
        ImageButton btnBack = (ImageButton) getView().findViewById(R.id.btn_back);
        btnBack.setBackgroundResource(R.drawable.option_menu);

        Button btnCreateProject = (Button) getView().findViewById(R.id.btn_create_proj);
        Button btnMyProject = (Button) getView().findViewById(R.id.btn_my_proj);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((SlideActivity) getActivity()).openSlidingDrawer();
            }
        });

        btnCreateProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(), "Create Project", Toast.LENGTH_SHORT).show();
                /*Intent intent = new Intent(getActivity().getApplicationContext(), CategoryActivity.class);
                startActivity(intent);*/

                ((SlideActivity) getActivity()).replaceFragment(new CategoryFragment());
            }
        });

        btnMyProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((SlideActivity) getActivity()).replaceFragment(new MyProjectFragment());
                Toast.makeText(getActivity().getApplicationContext(), "My Project", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

