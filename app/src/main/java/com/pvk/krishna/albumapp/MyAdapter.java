//package com.pvk.krishna.albumapp;
//
//import android.content.Context;
//import android.database.Cursor;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.net.Uri;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.animation.Animation;
//import android.view.animation.AnimationUtils;
//import android.widget.ImageView;
//import android.widget.SimpleCursorAdapter;
//import android.widget.TextView;
//
//public class MyAdapter extends SimpleCursorAdapter {
//
//        Cursor myCursor;
//        Context myContext;
//
//        public MyAdapter(Context context, int layout, Cursor c, String[] from,
//                int[] to) {
//            super(context, layout, c, from, to);
//
//            myCursor = c;
//            myContext = context;
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            View row = convertView;
//            if(row==null){
//                LayoutInflater inflater=getLayoutInflater();
//                row=inflater.inflate(R.layout.itemtexview, parent, false);
//            }
//
//            ImageView thumbV = (ImageView)row.findViewById(R.id.thumb);
//            TextView textV = (TextView)row.findViewById(R.id.text);
//
//            myCursor.moveToPosition(position);
//
//            int myID = myCursor.getInt(myCursor.getColumnIndex(android.provider.MediaStore.Audio.Albums._ID));
//            String myData = myCursor.getString(myCursor.getColumnIndex(android.provider.MediaStore.Audio.Albums.ALBUM));
//            textV.setText(myData);
//
//            String[] thumbColumns = {thumb_DATA, thumb_IMAGE_ID};
//            CursorLoader thumbCursorLoader = new CursorLoader(
//                    myContext,
//                    sourceUri,
//                    null,
//                    null,
//                    null,
//                    null);
//            Cursor thumbCursor = thumbCursorLoader.loadInBackground();
//
//            Bitmap myBitmap = null;
//            if(thumbCursor.moveToFirst()){
//                int thCulumnIndex = thumbCursor.getColumnIndex(thumb_DATA);
//                //String thumbPath = thumbCursor.getString(thCulumnIndex);
//                String thumbPath = android.provider.MediaStore.Audio.Albums.ALBUM_ART;
//                myBitmap = BitmapFactory.decodeFile(thumbPath);
//                thumbV.setImageBitmap(myBitmap);
//            }
//            Animation animation = null;
//            animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.wave);
//            animation.setDuration(200);
//            row.startAnimation(animation);
//            animation = null;
//            return row;
//        }
//
//
//    public View getView(int position, View convertView, ViewGroup parent) {
//        View row = convertView;
//        if(row==null){
//            LayoutInflater inflater=getLayoutInflater();
//            row=inflater.inflate(R.layout.itemtexview, parent, false);
//        }
//
//        ImageView thumbV = (ImageView)row.findViewById(R.id.thumb);
//        TextView textV = (TextView)row.findViewById(R.id.text);
//
//        myCursor.moveToPosition(position);
//
//        String myID = myCursor.getString(myCursor.getColumnIndex(android.provider.MediaStore.Audio.Albums._ID));
//        String myData = myCursor.getString(myCursor.getColumnIndex(android.provider.MediaStore.Audio.Albums.ALBUM));
//        textV.setText(myData);
//
//        // gets the artWorkUri.
//        Uri sArtworkUri = Uri.parse("content://media/external/audio/albumart");
//
//// gets the albumArtUri from artWorkUri and albumId.
//        Uri albumArtUri = Uri.withAppendedPath(sArtworkUri, myID);
//        thumbV.setImageURI(albumArtUri);
//
//        Animation animation = null;
//        animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.wave);
//        animation.setDuration(200);
//        row.startAnimation(animation);
//        animation = null;
//        return row;
//    }
//
//    }
