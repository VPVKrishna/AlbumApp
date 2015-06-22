package com.pvk.krishna.albumapp.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.imageaware.ImageAware;
import com.nostra13.universalimageloader.core.imageaware.ImageViewAware;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.pvk.krishna.albumapp.R;
import com.pvk.krishna.albumapp.adapter.AlbumPagerAdapter;
import com.pvk.krishna.albumapp.adapter.FrameFooterAdapter;
import com.pvk.krishna.albumapp.adapter.FrameHeaderAdapter;
import com.pvk.krishna.albumapp.core.AlbumFrameBean;
import com.pvk.krishna.albumapp.core.FrameItemBean;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_project);

        ArrayList<String> imageList = getIntent().getStringArrayListExtra(getString(R.string.image_list));

        Log.d("ImagesList", " List:" + imageList.size() + " " + imageList.toString());
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

        footerFrameBeans = new ArrayList<>();
        albumFrameBeans = new ArrayList<>();
        headerAlbumFrameBeans = new ArrayList<>();

        int size = AlbumLoaderOptions.imageFrames.length;

        for (int i = 0; i < size; i++) {
            footerFrameBeans.add(new FrameItemBean(AlbumLoaderOptions.imageFrames[i]));

            if (i >= imageList.size()) {
                headerAlbumFrameBeans.add(new AlbumFrameBean(AlbumLoaderOptions.imageFrames[i], null));
                continue;
            }
            headerAlbumFrameBeans.add(new AlbumFrameBean(AlbumLoaderOptions.imageFrames[i], imageList.get(i)));
            albumFrameBeans.add(new AlbumFrameBean(AlbumLoaderOptions.imageFrames[i], imageList.get(i)));
        }

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

//        vpFrame.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
//                photoPickerIntent.setType(IMAGE_FILE_TYPE);
//                startActivityForResult(photoPickerIntent, GALLERY_REQ_ID);
//                return true;
//            }
//        });
    }

    @Override
    public void onItemClick(View view, FrameItemBean bean, int position) {

        Toast.makeText(this, "Item Clicked At:" + position, Toast.LENGTH_SHORT).show();

        int pos = vpFrame.getCurrentItem();
        changeImageFrame(pos, bean.getFrameId(), null);
    }

    private void changeImageFrame(int pos, int frameId, final String imagePath) {
        AlbumFragment fragment = adapter.getFragmentAt(pos);

        final AlbumFrameBean albumFrameBean = albumFrameBeans.get(pos);
        albumFrameBean.setFrameId(frameId);

        final View frameView = fragment.getView().findViewById(R.id.iv_my_frame_bg);
        final ImageView frameImgView = (ImageView) fragment.getView().findViewById(R.id.iv_my_frame_image);
//        frameView.setBackgroundResource(albumFrameBean.getFrameId());

        if (imagePath != null) {
            String imageUri = "file://" + imagePath;
            ImageAware imageAware = new ImageViewAware(frameImgView, false);
            ImageLoader.getInstance().displayImage(imageUri, imageAware, AlbumLoaderOptions.OPTIONS_EMPTY);
            albumFrameBean.setImagePath(imagePath);
        }

        setBigFrame(frameView, albumFrameBean.getFrameId());

        adapter.notifyDataSetChanged();

        View headerItemView = lmHeader.getChildAt(fragment.getPage() - lmHeader.findFirstVisibleItemPosition());

        int headerItemPosition = 0;
        if (headerItemView != null) {
            final ImageView ivFrame = (ImageView) headerItemView.findViewById(R.id.iv_frame);
            final ImageView ivImage = (ImageView) headerItemView.findViewById(R.id.iv_image);
            headerItemPosition = (int) ivFrame.getTag();
//            ivFrame.setBackgroundResource(albumFrameBean.getFrameId());
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    setSmallFrame(ivFrame, albumFrameBean.getFrameId());
                    if (imagePath != null) {
                        String imageUri = "file://" + imagePath;
                        ImageAware imageAware = new ImageViewAware(ivImage, false);
                        ImageLoader.getInstance().displayImage(imageUri, imageAware, AlbumLoaderOptions.options);
                    }
                }
            }, Constants.HEADER_FRAME_ITEM_DELAY);
        } else {
            Log.e("Error Message: ", "View is null");
        }

        Log.e("Message: Posion", "pgPos: " + fragment.getPage() + "   " + headerItemPosition);

        if (footerFrameBeans.size() > headerItemPosition) {
            AlbumFrameBean aBean = headerAlbumFrameBeans.get(fragment.getPage());
            aBean.setFrameId(albumFrameBean.getFrameId());
            if (imagePath != null) {
                aBean.setImagePath(imagePath);
            }
        } else {
            Log.e("Error Message: ", "position exceeds");
        }
    }

    public void setBigFrame(final View ivFrameBg, int frameId) {
        String frameUri = "drawable://" + frameId;
        ImageSize frameSize = new ImageSize(250, 250);

//        MyViewAware myViewAware=new MyViewAware(ivFrameBg, false);
//        imageLoader.displayImage(frameUri, myViewAware, AlbumLoaderOptions.options);

        ImageLoader.getInstance().loadImage(frameUri, frameSize, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {
                // Toast.makeText(getActivity(), "ImageStarted", Toast.LENGTH_SHORT).show();
                Log.d("lodd", "lstarted");
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                // Toast.makeText(getActivity(), "ImageFailed", Toast.LENGTH_SHORT).show();
                Log.d("lodd", "lfinished");
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                //Toast.makeText(getActivity(), "ImageCompleted", Toast.LENGTH_SHORT).show();
                Log.d("lodd", "lcompleted");
                ivFrameBg.setBackground(new BitmapDrawable(ivFrameBg.getResources(), loadedImage));
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {
                Log.d("lodd", "lcancelled");
                //Toast.makeText(getActivity(), "ImageCancelled", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setSmallFrame(final ImageView ivFrameBg, int frameId) {
        /******************************Bg**********************************************/
        String frameUri = "drawable://" + frameId;
        ImageSize frameSize = new ImageSize(150, 150);

//        MyViewAware myViewAware=new MyViewAware(ivFrameBg, false);
//        imageLoader.displayImage(frameUri, myViewAware, AlbumLoaderOptions.options);

        ImageLoader.getInstance().loadImage(frameUri, frameSize, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {
                // Toast.makeText(getActivity(), "ImageStarted", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                // Toast.makeText(getActivity(), "ImageFailed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                //Toast.makeText(getActivity(), "ImageCompleted", Toast.LENGTH_SHORT).show();
                ivFrameBg.setBackground(new BitmapDrawable(ivFrameBg.getResources(), loadedImage));
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {
                //Toast.makeText(getActivity(), "ImageCancelled", Toast.LENGTH_SHORT).show();
            }
        });
        /******************************************************************************************/
    }

    private View mView;
    private AlbumFrameBean mBean;
    private int mPosition;

    @Override
    public void onImageItemClick(View view, AlbumFrameBean bean, int position) {
        Log.d("ImageChangeItem", "Item At:" + bean + "  Pos:" + position);

        mView = view;
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
                String filePath = Utils.getRealPathFromURI(getApplicationContext(), selectedImageUri);
                Log.d("Image Path", filePath + "  " + selectedImageUri.toString() + "  --" + selectedImageUri.getUserInfo());
                if (mPosition >= albumFrameBeans.size()) {
                    AlbumFrameBean bean = headerAlbumFrameBeans.get(mPosition);
                    albumFrameBeans.add(new AlbumFrameBean(bean.getFrameId(), null));
                    adapter.notifyDataSetChanged();
                }

                int pos = mPosition < albumFrameBeans.size() ? mPosition : albumFrameBeans.size() - 1;
                changeImageFrame(pos, mBean.getFrameId(), filePath);
            }
        }
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.iv_edit) {
            etTitle.setEnabled(!etTitle.isEnabled());
            if(etTitle.isEnabled()){
                etTitle.selectAll();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
            }else {
                etTitle.setSelection(0,0);
            }
            Log.d("iv edit", "clicked...........");
            return;
        }

        AlbumFragment fragment = adapter.getFragmentAt(vpFrame.getCurrentItem());
        final ImageView frameImgView = (ImageView) fragment.getView().findViewById(R.id.iv_my_frame_image);

        final AlbumFrameBean albumFrameBean = albumFrameBeans.get(vpFrame.getCurrentItem());
        String imagePath = albumFrameBean.getImagePath();

        if (imagePath != null) {
            ImageAware imageAware = new ImageViewAware(frameImgView, false);
            switch (v.getId()) {
                case R.id.iv_top:
                    imageAware.setImageBitmap(Utils.rotatedBitmap(imagePath, 180));
                    break;
                case R.id.iv_bottom:
                    imageAware.setImageBitmap(Utils.rotatedBitmap(imagePath, 0));
                    break;
                case R.id.iv_left:
                    imageAware.setImageBitmap(Utils.rotatedBitmap(imagePath, 90));
                    break;
                case R.id.iv_right:
                    imageAware.setImageBitmap(Utils.rotatedBitmap(imagePath, 270));
                    break;
            }
        } else {
            Log.e("MyProjectActivity", "error in image path");
        }
    }
}

