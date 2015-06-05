package com.pvk.krishna.albumapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pvk.krishna.albumapp.core.BookBean;

import java.util.ArrayList;

/**
 * Created by Krishna on 26/05/2015.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
    private ArrayList<BookBean> allObjects;
    private boolean isList=true;
    private ArrayList<BookBean> visibleObjects=new ArrayList<>();
    private BookItemListener listener;

    public void setList(boolean isList) {
        this.isList=isList;
    }

    public void setListener(BookItemListener listener){
        this.listener = listener;
    }
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        // each data item is just a string in this case
        public TextView tvFrame;
        public ImageView ivFrame;

        public ViewHolder(View v) {
            super(v);
            tvFrame = (TextView) v.findViewById(R.id.tv_frame);
            ivFrame= (ImageView) v.findViewById(R.id.iv_frame);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onItemClick(v, visibleObjects.get(getPosition()), getPosition());
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(ArrayList<BookBean> myDataset) {
        allObjects = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v;
        if(isList) {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_book_item, parent, false);
        }else{
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_book_item, parent, false);
        }
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        BookBean bean=visibleObjects.get(position);
        holder.tvFrame.setText(bean.getName());
        if(isList) {
            holder.ivFrame.setImageResource(bean.getImageId());
        }else{
            holder.ivFrame.setImageResource(bean.getImageId());
        }
        holder.tvFrame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //remove(name);
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return visibleObjects.size();
    }

    public void flushFilter(){
        visibleObjects=new ArrayList<>();
        visibleObjects.addAll(allObjects);
        notifyDataSetChanged();
    }

    public void setFilter(String queryText) {

        visibleObjects = new ArrayList<>();
        //constraint = constraint.toString().toLowerCase();
        for (BookBean item: allObjects) {
            if (item.getName().toLowerCase().contains(queryText))
                visibleObjects.add(item);
        }
        notifyDataSetChanged();
    }
}