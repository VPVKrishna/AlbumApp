package com.pvk.krishna.albumapp;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pvk.krishna.albumapp.gson.CategoryList;
import com.pvk.krishna.albumapp.gson.ResponseBean;
import com.pvk.krishna.albumapp.utils.Constants;
import com.pvk.krishna.albumapp.utils.OnStringResponseListener;
import com.pvk.krishna.albumapp.utils.ResponseUtilities;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Krishna on 31/05/2015.
 */
public class CategoryFragment extends Fragment implements OnStringResponseListener {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.choose_category_list, null);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        TextView tvTitle= (TextView) getView().findViewById(R.id.tv_title);
        tvTitle.setText("Category");

        ListView lvCategory= (ListView) getView().findViewById(R.id.lv_choose_category);

        ImageButton btnBack= (ImageButton) getView().findViewById(R.id.btn_back);
        btnBack.setBackgroundResource(R.drawable.option_menu);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((SlideActivity) getActivity()).openSlidingDrawer();
            }
        });

        List<CategoryBean> list=new ArrayList<>();
        CategoryBean bean=new CategoryBean("Frames", Utils.getRandomFrame());
        list.add(bean);
        bean=new CategoryBean("Hoardings", Utils.getRandomFrame());
        list.add(bean);
        bean=new CategoryBean("Shapes", Utils.getRandomFrame());
        list.add(bean);
        bean=new CategoryBean("Interiors", Utils.getRandomFrame());
        list.add(bean);
        bean=new CategoryBean("Frames", Utils.getRandomFrame());
        list.add(bean);
        bean=new CategoryBean("Hoardings", Utils.getRandomFrame());
        list.add(bean);
        bean=new CategoryBean("Shapes", Utils.getRandomFrame());
        list.add(bean);
        bean=new CategoryBean("Interiors", Utils.getRandomFrame());
        list.add(bean);

        CategoryAdapter adapter=new CategoryAdapter(getActivity(), list);
        lvCategory.setAdapter(adapter);

        lvCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getActivity().getApplicationContext(), BookActivity.class);
                startActivity(intent);
            }
        });

        String query="user=cats&pswd=soap123";

        ResponseUtilities.getInstance().getStringResponseFromUrl(this, Constants.CATEGORY_LIST_ID, getActivity(), Constants.CATEGORY_LIST_URL+"?"+query, Constants.CATEGORY_LIST_TAG);

    }

    @Override
    public void onResponse(String response, int requestId) {
        Log.d("RESPONSE", " res :" + response);

        Gson gson = new Gson();
        Type listType = new TypeToken<ResponseBean<CategoryList>>(){}.getType();
        ResponseBean<CategoryList> categoryListResponseBean= gson.fromJson(response, listType);
        Log.d("RESPONSE", " res :" +categoryListResponseBean);
    }

    @Override
    public void onErrorResponse(VolleyError errorResponse, int requestId) {

    }

    @Override
    public void parseNetworkResponse(NetworkResponse response, int requestId) {

    }

    @Override
    public Map<String, String> getParams(int requestId) {
        return null;
    }
}
