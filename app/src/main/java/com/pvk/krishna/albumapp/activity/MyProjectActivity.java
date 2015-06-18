package com.pvk.krishna.albumapp.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.pvk.krishna.albumapp.R;
import com.pvk.krishna.albumapp.adapter.AlbumPagerAdapter;
import com.pvk.krishna.albumapp.adapter.FrameFooterAdapter;
import com.pvk.krishna.albumapp.adapter.FrameHeaderAdapter;
import com.pvk.krishna.albumapp.core.AlbumFrameBean;
import com.pvk.krishna.albumapp.core.FrameItemBean;
import com.pvk.krishna.albumapp.fragment.AlbumFragment;
import com.pvk.krishna.albumapp.listener.FrameItemListener;

import java.util.ArrayList;


public class MyProjectActivity extends FragmentActivity implements FrameItemListener {

    private RecyclerView rvHeader;
    private RecyclerView rvFooter;
    private LinearLayoutManager lmHeader, lmFooter;
    private FrameHeaderAdapter headerAdapter;
    private FrameFooterAdapter footerAdapter;
    private ViewPager vpFrame;
    private ArrayList<AlbumFrameBean> albumFrameBeans;
    private AlbumPagerAdapter adapter;
    private ArrayList<FrameItemBean> frameItemBeans;
    private ArrayList<AlbumFrameBean> headerAlbumFrameBeans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_project);

        ArrayList<String> imageList=getIntent().getStringArrayListExtra(getString(R.string.image_list));
        Log.d("ImagesList"," List:"+imageList.size()+" "+imageList.toString());
        TextView tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitle.setText("MyProject");

        rvHeader = (RecyclerView) findViewById(R.id.my_recycler_view_header);
        rvFooter = (RecyclerView) findViewById(R.id.my_recycler_view_footer);

        vpFrame = (ViewPager) findViewById(R.id.vp_frames);

        rvHeader.setHasFixedSize(true);
        rvFooter.setHasFixedSize(true);

        lmHeader = new GridLayoutManager(this, 1, LinearLayoutManager.HORIZONTAL, false);
        lmFooter = new GridLayoutManager(this, 1, LinearLayoutManager.HORIZONTAL, false);

        rvHeader.setLayoutManager(lmHeader);
        rvFooter.setLayoutManager(lmFooter);

        frameItemBeans = new ArrayList<>();

        frameItemBeans.add(new FrameItemBean(R.drawable.frame_h_one));
        frameItemBeans.add(new FrameItemBean(R.drawable.frame_h_two));
        frameItemBeans.add(new FrameItemBean(R.drawable.frame_one));
        frameItemBeans.add(new FrameItemBean(R.drawable.frame_two));
        frameItemBeans.add(new FrameItemBean(R.drawable.frame_seven));

        albumFrameBeans = new ArrayList<>();
        albumFrameBeans.add(new AlbumFrameBean(R.drawable.frame_h_one, R.drawable.hello));
        albumFrameBeans.add(new AlbumFrameBean(R.drawable.frame_seven, R.drawable.svs));
        albumFrameBeans.add(new AlbumFrameBean(R.drawable.frame_h_one, R.drawable.valentine));
        albumFrameBeans.add(new AlbumFrameBean(R.drawable.frame_h_one, R.drawable.bowl));
        albumFrameBeans.add(new AlbumFrameBean(R.drawable.frame_h_two, R.drawable.building));
        albumFrameBeans.add(new AlbumFrameBean(R.drawable.frame_two, R.drawable.cute));
        albumFrameBeans.add(new AlbumFrameBean(R.drawable.frame_seven, R.drawable.image_one));
        albumFrameBeans.add(new AlbumFrameBean(R.drawable.frame_h_one, R.drawable.image_two));

        adapter = new AlbumPagerAdapter(getSupportFragmentManager(), albumFrameBeans);
        vpFrame.setAdapter(adapter);

        headerAlbumFrameBeans = new ArrayList<>();
        headerAlbumFrameBeans.addAll(albumFrameBeans);

        footerAdapter = new FrameFooterAdapter(frameItemBeans, MyProjectActivity.this);
        rvFooter.setAdapter(footerAdapter);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                headerAdapter = new FrameHeaderAdapter(headerAlbumFrameBeans);
                rvHeader.setAdapter(headerAdapter);
            }
        }, 500);
    }

    @Override
    public void onItemClick(View view, FrameItemBean bean, int position) {

        Toast.makeText(this, "Item Clicked At:" + position, Toast.LENGTH_SHORT).show();

        int pos = vpFrame.getCurrentItem();
        AlbumFragment fragment = adapter.getFragmentAt(pos);

        AlbumFrameBean albumFrameBean = albumFrameBeans.get(pos);
        albumFrameBean.setFrameId(bean.getFrameId());

        final View frameView = fragment.getView().findViewById(R.id.iv_my_frame_bg);
        frameView.setBackgroundResource(albumFrameBean.getFrameId());

        adapter.notifyDataSetChanged();

        View headerItemView = lmHeader.getChildAt(fragment.getPage() - lmHeader.findFirstVisibleItemPosition());

        int headerItemPosition = 0;
        if (headerItemView != null) {
            ImageView ivFrame = (ImageView) headerItemView.findViewById(R.id.iv_frame);
            headerItemPosition = (int) ivFrame.getTag();
            ivFrame.setBackgroundResource(albumFrameBean.getFrameId());
        } else {
            Log.e("Error Message: ", "View is null");
        }
        Log.e("Message: Posion", "pgPos: " + fragment.getPage() + "   " + headerItemPosition);

        if (frameItemBeans.size() > headerItemPosition) {
            AlbumFrameBean aBean = headerAlbumFrameBeans.get(fragment.getPage());
            aBean.setFrameId(albumFrameBean.getFrameId());
        } else {
            Log.e("Error Message: ", "position exceeds");
        }
    }
}

