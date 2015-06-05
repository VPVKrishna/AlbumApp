package com.pvk.krishna.albumapp;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.pvk.krishna.albumapp.core.AlbumFrameBean;
import com.pvk.krishna.albumapp.core.FrameItemBean;

import java.util.ArrayList;


public class MyProjectActivity extends FragmentActivity implements FrameItemListener{

    private RecyclerView rvHeader;
    private RecyclerView rvFooter;
    private LinearLayoutManager lmHeader, lmFooter;
    private FrameHeaderAdapter headerAdapter;
    private FrameFooterAdapter footerAdapter;
    private ViewPager vpFrame;
    private ArrayList<AlbumFrameBean> albumFrameBeans;
    private MyPagerAdapter pageAdapter;
    private AlbumPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_project);

        TextView tvTitle= (TextView) findViewById(R.id.tv_title);
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

        // specify an adapter (see also next example)
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add("Item:" + i);
        }

        ArrayList<FrameItemBean> frameItemBeans=new ArrayList<>();

        frameItemBeans.add(new FrameItemBean(R.drawable.frame_h_one));
        frameItemBeans.add(new FrameItemBean(R.drawable.frame_h_two));
        frameItemBeans.add(new FrameItemBean(R.drawable.frame_one));
        frameItemBeans.add(new FrameItemBean(R.drawable.frame_two));
        frameItemBeans.add(new FrameItemBean(R.drawable.frame_seven));

        headerAdapter=new FrameHeaderAdapter(list);
        footerAdapter=new FrameFooterAdapter(frameItemBeans, this);

        rvHeader.setAdapter(headerAdapter);
        rvFooter.setAdapter(footerAdapter);

        albumFrameBeans = new ArrayList<>();

        albumFrameBeans.add(new AlbumFrameBean(R.drawable.frame_h_one, R.drawable.hello));
        albumFrameBeans.add(new AlbumFrameBean(R.drawable.frame_seven, R.drawable.svs));
        albumFrameBeans.add(new AlbumFrameBean(R.drawable.frame_h_one, R.drawable.valentine));
        albumFrameBeans.add(new AlbumFrameBean(R.drawable.frame_h_one, R.drawable.bowl));
        albumFrameBeans.add(new AlbumFrameBean(R.drawable.frame_h_two, R.drawable.building));
        albumFrameBeans.add(new AlbumFrameBean(R.drawable.frame_two, R.drawable.cute));
        albumFrameBeans.add(new AlbumFrameBean(R.drawable.frame_seven, R.drawable.image_one));
        albumFrameBeans.add(new AlbumFrameBean(R.drawable.frame_h_one, R.drawable.image_two));

//        int[] resources={R.drawable.hello, R.drawable.svs, R.drawable.valentine,
//                R.drawable.bowl, R.drawable.building, R.drawable.clouds, R.drawable.cute,R.drawable.image_one, R.drawable.image_two};

//        pageAdapter = new MyPagerAdapter(this, albumFrameBeans);
//        vpFrame.setAdapter(pageAdapter);

        adapter = new AlbumPagerAdapter(getSupportFragmentManager(), albumFrameBeans);
        vpFrame.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, FrameItemBean bean, int position) {

        Toast.makeText(this, "Item Clicked At:"+position, Toast.LENGTH_SHORT).show();
        /*int pos=vpFrame.getCurrentItem();

        AlbumFrameBean item = albumFrameBeans.get(pos);
        AlbumFrameBean replaced=new AlbumFrameBean(bean.getFrameId(), item.getImageId());
        albumFrameBeans.remove(pos);
        albumFrameBeans.add(pos, replaced);
        View viewPagetItemView = vpFrame.getChildAt(pos);
        viewPagetItemView.findViewById(R.id.iv_my_frame_bg).setBackgroundResource(replaced.getFrameId());
//        View pagerItem= (View) pageAdapter.instantiateItem(vpFrame, position);
//        pagerItem.findViewById(R.id.iv_my_frame_bg).setBackgroundResource(replaced.getFrameId());
        pageAdapter.notifyDataSetChanged();*/

//        vpFrame

        int pos=vpFrame.getCurrentItem();
        AlbumFragment fragment=adapter.getFragmentAt(pos);

            /*AlbumFrameBean item = albumFrameBeans.get(pos);
            AlbumFrameBean replaced=new AlbumFrameBean(bean.getFrameId(), item.getImageId());
            albumFrameBeans.remove(pos);
            albumFrameBeans.add(pos, replaced);*/

            AlbumFrameBean albumFrameBean=albumFrameBeans.get(pos);
            albumFrameBean.setFrameId(bean.getFrameId());

            final View frameView=fragment.getView().findViewById(R.id.iv_my_frame_bg);
            frameView.setBackgroundResource(albumFrameBean.getFrameId());
//            String imageUri="drawable://" + bean.getFrameId();
//            ImageSize imageSize=new ImageSize(400, 400);
//
//            ImageLoader.getInstance().loadImage(this, imageUri, imageSize, new ImageLoadingListener() {
//                @Override
//                public void onLoadingStarted() {
//
//                }
//
//                @Override
//                public void onLoadingFailed(FailReason failReason) {
//
//                }
//
//                @Override
//                public void onLoadingComplete(Bitmap loadedImage) {
//                    frameView.setBackground(new BitmapDrawable(frameView.getResources(), loadedImage));
//                }
//
//                @Override
//                public void onLoadingCancelled() {
//
//                }
//            });

            adapter.notifyDataSetChanged();
        }
    }

