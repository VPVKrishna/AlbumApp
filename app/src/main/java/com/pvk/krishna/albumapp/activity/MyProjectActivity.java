package com.pvk.krishna.albumapp.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.pvk.krishna.albumapp.R;
import com.pvk.krishna.albumapp.adapter.AlbumPagerAdapter;
import com.pvk.krishna.albumapp.adapter.FrameFooterAdapter;
import com.pvk.krishna.albumapp.adapter.FrameHeaderAdapter;
import com.pvk.krishna.albumapp.core.AlbumFrameBean;
import com.pvk.krishna.albumapp.core.FrameItemBean;
import com.pvk.krishna.albumapp.database.AlbumDatabase;
import com.pvk.krishna.albumapp.fragment.AlbumFragment;
import com.pvk.krishna.albumapp.listener.FrameItemListener;
import com.pvk.krishna.albumapp.listener.ImageChangeListener;
import com.pvk.krishna.albumapp.utils.AlbumLoaderOptions;
import com.pvk.krishna.albumapp.utils.Constants;
import com.pvk.krishna.albumapp.utils.Utils;

import java.util.ArrayList;


public class MyProjectActivity extends FragmentActivity implements FrameItemListener, ImageChangeListener, View.OnClickListener {

    private static final int GALLERY_REQ_ID = 1212;
    public static final String IMAGE_FILE_TYPE = "image/*";
    private RecyclerView rvHeader;
    private RecyclerView rvFooter;
    private LinearLayoutManager lmHeader, lmFooter;
    private FrameHeaderAdapter headerAdapter;
    private FrameFooterAdapter footerAdapter;
    private ViewPager vpFrame;
    private ArrayList<AlbumFrameBean> albumFrameBeans;
    private AlbumPagerAdapter adapter;
    private ArrayList<FrameItemBean> footerFrameBeans;
    private ArrayList<AlbumFrameBean> headerAlbumFrameBeans;
    private EditText etTitle;
    public static String MY_PROJECT_KEY = "is_my_project";
    private long myProjectId;
    private AlbumDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_project);

        database = new AlbumDatabase(getApplicationContext());
        database.printDb();

        footerFrameBeans = new ArrayList<>();
        albumFrameBeans = new ArrayList<>();
        headerAlbumFrameBeans = new ArrayList<>();

        myProjectId = getIntent().getLongExtra(MY_PROJECT_KEY, -1);
        if (myProjectId == -1) {
            ArrayList<String> imageList = getIntent().getStringArrayListExtra(getString(R.string.image_list));

            int size = AlbumLoaderOptions.imageFrameNames.length;

            for (int i = 0; i < size; i++) {
                footerFrameBeans.add(new FrameItemBean(AlbumLoaderOptions.imageFrameNames[i]));

                if (i >= imageList.size()) {
                    headerAlbumFrameBeans.add(new AlbumFrameBean(AlbumLoaderOptions.imageFrameNames[i], null));
                    continue;
                }
                headerAlbumFrameBeans.add(new AlbumFrameBean(AlbumLoaderOptions.imageFrameNames[i], imageList.get(i)));
                albumFrameBeans.add(new AlbumFrameBean(AlbumLoaderOptions.imageFrameNames[i], imageList.get(i)));
            }
            Log.d("ImagesList", " List:" + imageList.size() + " " + imageList.toString());
        } else {
            int size = AlbumLoaderOptions.imageFrameNames.length;

            for (int i = 0; i < size; i++) {
                footerFrameBeans.add(new FrameItemBean(AlbumLoaderOptions.imageFrameNames[i]));
            }

            ArrayList<AlbumFrameBean> beans = database.getPhotoList(myProjectId);
            albumFrameBeans.addAll(beans);
            headerAlbumFrameBeans.addAll(beans);

            for (int i = albumFrameBeans.size(); i < footerFrameBeans.size(); i++) {
                headerAlbumFrameBeans.add(new AlbumFrameBean(AlbumLoaderOptions.imageFrameNames[i], null));
            }
        }

        etTitle = (EditText) findViewById(R.id.tv_title);
        etTitle.setText("MyProject");
        etTitle.setEnabled(false);

        rvHeader = (RecyclerView) findViewById(R.id.my_recycler_view_header);
        rvFooter = (RecyclerView) findViewById(R.id.my_recycler_view_footer);

        vpFrame = (ViewPager) findViewById(R.id.vp_frames);

        rvHeader.setHasFixedSize(true);
        rvFooter.setHasFixedSize(true);

        lmHeader = new GridLayoutManager(this, 1, LinearLayoutManager.HORIZONTAL, false);
        lmFooter = new GridLayoutManager(this, 1, LinearLayoutManager.HORIZONTAL, false);

        rvHeader.setLayoutManager(lmHeader);
        rvFooter.setLayoutManager(lmFooter);

        adapter = new AlbumPagerAdapter(getSupportFragmentManager(), albumFrameBeans);
        vpFrame.setAdapter(adapter);

        footerAdapter = new FrameFooterAdapter(this, footerFrameBeans, MyProjectActivity.this);
        rvFooter.setAdapter(footerAdapter);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                headerAdapter = new FrameHeaderAdapter(MyProjectActivity.this, headerAlbumFrameBeans, MyProjectActivity.this);
                rvHeader.setAdapter(headerAdapter);
            }
        }, Constants.HEADER_FRAME_DELAY);

        findViewById(R.id.iv_top).setOnClickListener(this);
        findViewById(R.id.iv_bottom).setOnClickListener(this);
        findViewById(R.id.iv_left).setOnClickListener(this);
        findViewById(R.id.iv_right).setOnClickListener(this);
        findViewById(R.id.iv_edit).setOnClickListener(this);

        etTitle.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    etTitle.setSelection(0, 0);
                    etTitle.setEnabled(false);
                    Log.d("Editable done", "clicked done button & enabled");
                }
                return true;
            }
        });

        Button btnSave = (Button) findViewById(R.id.btn_save);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Saving started.", Toast.LENGTH_SHORT).show();
                changeViewPagerItem();
            }
        });
    }

    private Handler handler = new Handler();
    private int cItem;
    private int timeDelay = 4000;
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (cItem < albumFrameBeans.size()) {

                Log.d("MyProjectActivity", "run (Line:163) :" + cItem);
                AlbumFragment fragment = adapter.getFragmentAt(cItem);
                fragment.setIsVeryBigImage(true);
                View saveView = fragment.getView().findViewById(R.id.rl_pager_item);
                saveView.setDrawingCacheEnabled(true);
                saveView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
                Bitmap bitmap = saveView.getDrawingCache();
                Utils.saveBitmapIntoFile(bitmap, Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/sample" + cItem + ".png");

                handler.postDelayed(runnable, timeDelay);
                cItem++;
                vpFrame.setCurrentItem(cItem);
            } else {
                Log.d("MyProjectActivity", "run (Line:164) :" + "Saving completed");

                if (myProjectId == -1) {
                    long albumId = database.insertAlbum(etTitle.getText().toString(), albumFrameBeans);
                    database.getPhotoList(albumId);
                } else {
                    Log.d("MyProjectActivity", "run (Line:190) :" + "Update" + albumFrameBeans);
                    database.updateAlbum(myProjectId, etTitle.getText().toString(), albumFrameBeans);
                    database.getPhotoList(myProjectId);
                }
                database.printDb();
                Toast.makeText(getApplicationContext(), "Saving Completed", Toast.LENGTH_SHORT).show();
            }
        }
    };

    public void changeViewPagerItem() {
        cItem = 0;
        vpFrame.setCurrentItem(cItem);
        handler.postDelayed(runnable, timeDelay);
    }

    @Override
    public void onItemClick(View view, FrameItemBean bean, int position) {

        Toast.makeText(this, "Item Clicked At:" + position, Toast.LENGTH_SHORT).show();

        int pos = vpFrame.getCurrentItem();
        Log.d("MyProjectActivity", "onItemClick (Line:216) :Pos:" + pos + " Bean:" + bean.toString());
        changeImageFrame(pos, bean.getFrame(), albumFrameBeans.get(pos).getImagePath());
    }

    private void changeImageFrame(int pos, String frameName, final String imagePath) {
        AlbumFragment fragment = adapter.getFragmentAt(pos);

        final AlbumFrameBean albumFrameBean = albumFrameBeans.get(pos);
        albumFrameBean.setFrameName(frameName);
        albumFrameBean.setImagePath(imagePath);

        if (fragment != null && fragment.getView() != null) {
            final ImageView frameView = (ImageView) fragment.getView().findViewById(R.id.iv_my_frame_bg);
            final ImageView frameImgView = (ImageView) fragment.getView().findViewById(R.id.iv_my_frame_image);

            if (imagePath != null) {
                Utils.updateImage(frameImgView, imagePath, albumFrameBean.getRotation(), Utils.BIG_IMG_WIDTH, Utils.BIG_IMG_HEIGHT);
            }

            Utils.updateFrame(frameView, frameName);
        }
        adapter.notifyDataSetChanged();

        View headerItemView = lmHeader.getChildAt(pos - lmHeader.findFirstVisibleItemPosition());

        int headerItemPosition = 0;
        if (headerItemView != null) {
            final ImageView ivFrame = (ImageView) headerItemView.findViewById(R.id.iv_frame);
            final ImageView ivImage = (ImageView) headerItemView.findViewById(R.id.iv_image);
            headerItemPosition = (int) ivFrame.getTag();
            Utils.updateFrame(ivFrame, albumFrameBean.getFrameName());
            if (imagePath != null) {
                Utils.updateImage(ivImage, imagePath, albumFrameBean.getRotation(), Utils.SMALL_IMG_WIDTH, Utils.SMALL_IMG_HEIGHT);
            }
        } else {
            Log.e("Error Message: ", "View is null");
        }
        headerAdapter.notifyDataSetChanged();

        Log.e("Message: Posion", "pgPos: " + pos + "   " + headerItemPosition);

        if (footerFrameBeans.size() > headerItemPosition) {
            AlbumFrameBean aBean = headerAlbumFrameBeans.get(pos);
            aBean.setFrameName(albumFrameBean.getFrameName());
            if (imagePath != null) {
                aBean.setImagePath(imagePath);
            }
        } else {
            Log.e("Error Message: ", "position exceeds");
        }
    }

    private AlbumFrameBean mBean;
    private int mPosition;

    @Override
    public void onImageItemClick(View view, AlbumFrameBean bean, int position) {
        Log.d("ImageChangeItem", "Item At:" + bean + "  Pos:" + position);

        mBean = bean;
        mPosition = position;
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType(IMAGE_FILE_TYPE);
        startActivityForResult(photoPickerIntent, GALLERY_REQ_ID);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == GALLERY_REQ_ID) {
                String imgUri = data.getDataString();
                Log.d("ACTIVITY_RESULT:", "IMAGE_URI:" + imgUri);

                Uri selectedImageUri = data.getData();
                // String filePath = Utils.getRealPathFromURI(getApplicationContext(), selectedImageUri);
                Log.d("Image Uri", selectedImageUri.toString() + "  --" + selectedImageUri.getUserInfo());
                if (mPosition >= albumFrameBeans.size()) {
                    AlbumFrameBean bean = headerAlbumFrameBeans.get(mPosition);
                    albumFrameBeans.add(new AlbumFrameBean(bean.getFrameName(), null));
                    adapter.notifyDataSetChanged();
                }

                int pos = mPosition < albumFrameBeans.size() ? mPosition : albumFrameBeans.size() - 1;

                changeImageFrame(pos, mBean.getFrameName(), selectedImageUri.toString());

//                if(filePath==null) {
//                    Toast.makeText(this, "Error in filePath retrieving....!", Toast.LENGTH_SHORT).show();
//                }else {
//                    changeImageFrame(pos, mBean.getFrameName(), filePath);
//                }
            }
        }
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.iv_edit) {
            etTitle.setEnabled(!etTitle.isEnabled());
            if (etTitle.isEnabled()) {
                etTitle.selectAll();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
            } else {
                etTitle.setSelection(0, 0);
            }
            Log.d("iv edit", "clicked...........");
            return;
        }

        AlbumFragment fragment = adapter.getFragmentAt(vpFrame.getCurrentItem());
        final ImageView frameImgView = (ImageView) fragment.getView().findViewById(R.id.iv_my_frame_image);

        final AlbumFrameBean albumFrameBean = albumFrameBeans.get(vpFrame.getCurrentItem());
        final String imagePath = albumFrameBean.getImagePath();

        if (imagePath != null) {
            switch (v.getId()) {
                case R.id.iv_top:
                    albumFrameBean.setRotation(180);
                    break;
                case R.id.iv_bottom:
                    albumFrameBean.setRotation(0);
                    break;
                case R.id.iv_left:
                    albumFrameBean.setRotation(90);
                    break;
                case R.id.iv_right:
                    albumFrameBean.setRotation(270);
                    break;
            }

            Utils.updateImage(frameImgView, imagePath, albumFrameBean.getRotation(), Utils.BIG_IMG_WIDTH, Utils.BIG_IMG_HEIGHT);

            View headerItemView = lmHeader.getChildAt(fragment.getPage() - lmHeader.findFirstVisibleItemPosition());
            final AlbumFrameBean headerImageBean = headerAlbumFrameBeans.get(vpFrame.getCurrentItem());
            headerImageBean.setRotation(albumFrameBean.getRotation());

            if (headerItemView != null) {
                final ImageView ivImage = (ImageView) headerItemView.findViewById(R.id.iv_image);

                if (headerImageBean.getImagePath() != null) {
                    Utils.updateImage(ivImage, headerImageBean.getImagePath(), headerImageBean.getRotation(), Utils.SMALL_IMG_WIDTH, Utils.SMALL_IMG_HEIGHT);
                }
            } else {
                Log.e("Error Message: ", "View is null");
            }
        } else {
            Log.e("MyProjectActivity", "error in image path");
        }
    }
}

