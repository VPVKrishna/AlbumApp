package com.pvk.krishna.albumapp.unwanted;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.pvk.krishna.albumapp.R;


public class HomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        TextView tvTitle= (TextView) findViewById(R.id.tv_title);
        tvTitle.setText("Home");

        Button btnCreateProject= (Button) findViewById(R.id.btn_create_proj);
        Button btnMyProject = (Button) findViewById(R.id.btn_my_proj);

        btnCreateProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Create Project", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getApplicationContext(), CategoryActivity.class);
                startActivity(intent);
            }
        });

        btnMyProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "My Project", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
