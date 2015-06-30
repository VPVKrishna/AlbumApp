package com.pvk.krishna.albumapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pvk.krishna.albumapp.R;
import com.pvk.krishna.albumapp.core.AlbumBean;

import java.util.ArrayList;

/**
 * Created by Krishna on 29/06/2015.
 */
public class MyProjectAdapter extends BaseAdapter {

    private final LayoutInflater inflater;
    private ArrayList<AlbumBean> albumBeans;

    public MyProjectAdapter(Context context, ArrayList<AlbumBean> albumBeans) {
        this.albumBeans = albumBeans;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return albumBeans.size();
    }

    @Override
    public AlbumBean getItem(int position) {
        return albumBeans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.my_project_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.updateView(getItem(position));
        return convertView;
    }

    private static class ViewHolder {
        private TextView tvProject;

        ViewHolder(View view) {
            tvProject = (TextView) view.findViewById(R.id.tv_project_name);
        }

        void updateView(AlbumBean bean) {
            tvProject.setText(bean.getAlbumName());
        }
    }
}
