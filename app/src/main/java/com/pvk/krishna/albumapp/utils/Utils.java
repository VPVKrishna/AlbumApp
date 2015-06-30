package com.pvk.krishna.albumapp.utils;

import android.content.Context;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.pvk.krishna.albumapp.R;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

/**
 * Created by Krishna on 01/06/2015.
 */
public class Utils {

    public static int getRandomFrame() {
        return AlbumLoaderOptions.imageFrames[new Random().nextInt(AlbumLoaderOptions.imageFrames.length)];
    }

    public static boolean haveNetworkConnection(Context context) {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }

    public static String getRealPathFromURI(Context context, Uri contentUri) {

        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                res = cursor.getString(column_index);
            }
            cursor.close();
        } else {
            Log.d("Utils", "Cursor is null");
            return contentUri.getPath();
        }
        return res;
    }

    public static Bitmap rotatedBitmap(String imagePath, int degrees) {
        String imageUri = "file://" + imagePath;
        Bitmap loadedBitmap = ImageLoader.getInstance().loadImageSync(imageUri, AlbumLoaderOptions.OPTIONS_EMPTY);
        return rotate(loadedBitmap, degrees);
    }

    public static Bitmap rotate(Bitmap b, int degrees) {
        if (degrees != 0 && b != null) {
            Matrix m = new Matrix();

            m.setRotate(degrees, (float) b.getWidth() / 2, (float) b.getHeight() / 2);
            try {
                Bitmap b2 = Bitmap.createBitmap(b, 0, 0, b.getWidth(), b.getHeight(), m, true);
                if (b != b2) {
                    b.recycle();
                    b = b2;
                }
            } catch (OutOfMemoryError ex) {
                throw ex;
            }
        }
        return b;
    }

    // BIG_IMG_WIDTH 400        VERY_BIG_IMG_WIDTH  600
    public static int BIG_IMG_WIDTH = 600, BIG_IMG_HEIGHT = 600, SMALL_IMG_WIDTH = 150, SMALL_IMG_HEIGHT = 150;
    public static int VERY_BIG_IMG_WIDTH = 600, VERY_BIG_IMG_HEIGHT = 600;


    public static void updateImage(ImageView ivImage, String path, int rotation, int width, int height) {

        Picasso.with(ivImage.getContext())
                .load("file://" + path)
                .rotate(rotation)
                .placeholder(R.drawable.iv_default_image)
                .centerInside()
                .resize(width, height)
                .into(ivImage);
    }

    public static void updateFrame(ImageView ivFrame, String resourceName) {

        Picasso.with(ivFrame.getContext())
                .load(Constants.DRAWABLE_PREFIX + resourceName)
                .fit()
                .into(ivFrame);
    }

    public static void saveBitmapIntoFile(Bitmap bitmap, String fileName) {

        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100 /*ignored for PNG*/, bos);
            byte[] bitmapdata = bos.toByteArray();

            //write the bytes in file
            FileOutputStream fos = new FileOutputStream(fileName);
            fos.write(bitmapdata);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void setEmptyRecycleView(final BaseAdapter adapter, final View emptyView) {

        adapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                if (adapter.getCount() == 0) {
                    emptyView.setVisibility(View.VISIBLE);
                } else {
                    emptyView.setVisibility(View.GONE);
                }
            }
        });
    }

    public static void setEmptyRecycleView(final RecyclerView.Adapter adapter, final View emptyView) {

        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                if (adapter.getItemCount() == 0) {
                    emptyView.setVisibility(View.VISIBLE);
                } else {
                    emptyView.setVisibility(View.GONE);
                }
            }
        });
    }

}
