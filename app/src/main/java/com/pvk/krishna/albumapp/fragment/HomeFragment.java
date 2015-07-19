package com.pvk.krishna.albumapp.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.pvk.krishna.albumapp.R;
import com.pvk.krishna.albumapp.activity.SlideActivity;
import com.pvk.krishna.albumapp.carousel.MyPagerAdapter;

/**
 * Created by Krishna on 31/05/2015.
 */
public class HomeFragment extends Fragment {

    private ViewPager pager;
    public final static int FIRST_PAGE = 0;
    public final static float BIG_SCALE = 1.0f;
    public final static float SMALL_SCALE = 0.8f;
    public final static float DIFF_SCALE = BIG_SCALE - SMALL_SCALE;
    private MyPagerAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_home, null);
    }

    public void initializePager() {
        pager = (ViewPager) getView().findViewById(R.id.myviewpager);
        adapter = new MyPagerAdapter(getActivity(), getChildFragmentManager());
        adapter.setPager(pager);

        pager.setAdapter(adapter);

        pager.setOnPageChangeListener(adapter);

        // Set current item to the middle page so we can fling to both
        // directions left and right
        pager.setCurrentItem(FIRST_PAGE);

        // Necessary or the pager will only have one extra page to show
        // make this at least however many pages you can see
        pager.setOffscreenPageLimit(3);


        // Set margin for pages as a negative number, so a part of next and
        // previous pages will be showed
        pager.setPageMargin(Integer.parseInt(getString(R.string.pagermargin)));

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

                ((SlideActivity) getActivity()).replaceFragment(new CategoryFragment(), true);
            }
        });

        btnMyProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((SlideActivity) getActivity()).replaceFragment(new MyProjectFragment(), true);
                Toast.makeText(getActivity().getApplicationContext(), "My Project", Toast.LENGTH_SHORT).show();
            }
        });

        initializePager();
    }
}

