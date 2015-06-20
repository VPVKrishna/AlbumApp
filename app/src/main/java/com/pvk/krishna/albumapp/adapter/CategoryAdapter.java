package com.pvk.krishna.albumapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.imageaware.ImageAware;
import com.nostra13.universalimageloader.core.imageaware.ImageViewAware;
import com.pvk.krishna.albumapp.utils.AlbumLoaderOptions;
import com.pvk.krishna.albumapp.core.CategoryBean;
import com.pvk.krishna.albumapp.R;

import java.util.List;

/**
 * Created by Krishna on 26/05/2015.
 */
public class CategoryAdapter extends BaseAdapter {

    private List<CategoryBean> list;
    private final LayoutInflater inflater;

    public CategoryAdapter(Context context, List<CategoryBean> list) {

        inflater = LayoutInflater.from(context);
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CategoryBean getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.choose_category_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.updateView(getItem(position));
        return convertView;
    }

    private static final class ViewHolder {
        ImageView ivCat;
        TextView tvName;

        ViewHolder(View view) {
            ivCat = (ImageView) view.findViewById(R.id.iv_choose_item_frame);
            tvName = (TextView) view.findViewById(R.id.tv_choose_item_name);
        }

        void updateView(CategoryBean bean) {
            tvName.setText(bean.getName());
//            ivCat.setImageResource(bean.getPath());

            String imageUri = "drawable://" + bean.getPath();
            ImageAware imageAware = new ImageViewAware(ivCat, false);
            ImageLoader.getInstance().displayImage(imageUri, imageAware, AlbumLoaderOptions.OPTIONS_EMPTY);
        }
    }
}
