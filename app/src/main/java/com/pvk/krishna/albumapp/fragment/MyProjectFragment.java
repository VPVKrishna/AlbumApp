package com.pvk.krishna.albumapp.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupMenu;

import com.pvk.krishna.albumapp.R;
import com.pvk.krishna.albumapp.activity.MyProjectActivity;
import com.pvk.krishna.albumapp.adapter.MyProjectAdapter;
import com.pvk.krishna.albumapp.core.AlbumBean;
import com.pvk.krishna.albumapp.database.AlbumDatabase;

import java.util.ArrayList;

/**
 * Created by Krishna on 29/06/2015.
 */
public class MyProjectFragment extends Fragment {

    private ArrayList<AlbumBean> albumList;
    private AlbumDatabase database;
    private MyProjectAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.my_project_fragment, null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        database = new AlbumDatabase(getActivity());
        albumList = database.getAlbumList();
        adapter = new MyProjectAdapter(getActivity(), albumList);

        ListView lvMyProjects = (ListView) getView().findViewById(R.id.lv_my_projects);
        lvMyProjects.setAdapter(adapter);

        lvMyProjects.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showPopupMenu(view, position);
            }
        });
    }

    public void showPopupMenu(final View view, final int itemPosition) {
        //Creating the instance of PopupMenu
        PopupMenu popup = new PopupMenu(getActivity(), view);
        //Inflating the Popup using xml file
        popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());

        //registering popup with OnMenuItemClickListener
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                selectedOption(item.getItemId(), itemPosition);
                return true;
            }
        });

        popup.show();//showing popup menu
    }

    public void selectedOption(int option, int itemPosition) {

        AlbumBean bean = albumList.get(itemPosition);

        switch (option) {
            case R.id.menu_open://open
                Intent intent = new Intent(getActivity(), MyProjectActivity.class);
                intent.putExtra(MyProjectActivity.MY_PROJECT_KEY, bean.getAlbumId());
                startActivity(intent);
                break;
            case R.id.menu_duplicate://duplicate
                database.duplicateAlbum(bean.getAlbumId(), bean.getAlbumName());
                AlbumBean duplicateBean = new AlbumBean(bean.getAlbumId(), bean.getAlbumName());
                albumList.add(duplicateBean);
                adapter.notifyDataSetChanged();
                break;
            case R.id.menu_remove://remove
                database.deleteAlbum(bean.getAlbumId());
                albumList.remove(itemPosition);
                adapter.notifyDataSetChanged();
                break;
        }
    }
}
