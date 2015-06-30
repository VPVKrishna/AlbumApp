package com.pvk.krishna.albumapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.pvk.krishna.albumapp.core.AlbumBean;
import com.pvk.krishna.albumapp.core.AlbumFrameBean;

import java.util.ArrayList;

/**
 * Created by Krishna on 28/06/2015.
 */
public class AlbumDatabase extends SQLiteOpenHelper {

    private static final String DB_NAME = "album.sqlite";
    private static final String TABLE_ALBUM = "album_table";
    private static final String TABLE_PHOTO = "photo_table";

    private static final String ALBUM_ID = "album_id";
    private static final String ALBUM_NAME = "album_name";

    private static final String PHOTO_ID = "photo_id";
    private static final String PHOTO_ALBUM_ID = "album_id";
    private static final String PHOTO_FRAME = "frame_name";
    private static final String PHOTO_IMAGE = "image_path";
    private static final String PHOTO_ROTATION = "rotation";

    public AlbumDatabase(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String albumTableQuery = "create table " + TABLE_ALBUM + " ( " + ALBUM_ID + " integer primary key autoincrement, " +
                ALBUM_NAME + " text )";
        String photoTableQuery = "create table " + TABLE_PHOTO + " ( " + PHOTO_ID + " integer primary key autoincrement, " +
                PHOTO_ALBUM_ID + " integer, " + PHOTO_FRAME + " text, " + PHOTO_IMAGE + " text, " + PHOTO_ROTATION + " integer)";
        db.execSQL(albumTableQuery);
        db.execSQL(photoTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long insertAlbum(String albumName, ArrayList<AlbumFrameBean> beans) {
        SQLiteDatabase database = getWritableDatabase();

        //inserting new album.
        long albumId = addAlbum(database, albumName);

        // inserting new photos.
        addPhotos(database, albumId, beans);

        // closing database.
        database.close();
        return albumId;
    }

    public void updateAlbum(long albumId, String albumName, ArrayList<AlbumFrameBean> beans) {
        SQLiteDatabase database = getWritableDatabase();

        // updating new album.
        long updatedRows = updateAlbum(database, albumId, albumName);

        // updating new photos.
        updatePhotos(database, albumId, beans);

        // closing database.
        database.close();
    }

    private long addAlbum(SQLiteDatabase database, String albumName) {
        ContentValues values = new ContentValues(1);// for adding one value.
        values.put(ALBUM_NAME, albumName);
        long rowId = database.insert(TABLE_ALBUM, null, values);
        Log.d("AlbumDatabase", "addAlbum (Line:55) albumRowId:" + rowId + " albumName:" + albumName);
        return rowId;
    }

    private long updateAlbum(SQLiteDatabase database, long albumId, String albumName) {
        ContentValues values = new ContentValues(1);// for adding one value.
        values.put(ALBUM_NAME, albumName);
        long rowId = database.update(TABLE_ALBUM, values, PHOTO_ALBUM_ID + " = " + albumId, null);
        Log.d("AlbumDatabase", "addAlbum (Line:55) albumRowId:" + rowId + " albumName:" + albumName);
        return rowId;
    }
//
//    public void duplicateAlbum(long albumId){
//
//        String dupRow="insert into "+TABLE_PHOTO+" ("+PHOTO_ALBUM_ID+" , "+PHOTO_FRAME+" , "+PHOTO_IMAGE+" , "+PHOTO_ROTATION+" ) "+
//                " select "+PHOTO_ALBUM_ID+" , "+PHOTO_FRAME+" , "+PHOTO_IMAGE+" , "+PHOTO_ROTATION+" from "+TABLE_PHOTO+" where "+PHOTO_ID+" = "+albumId;
//        insert into Table (column1, column2, column3)
//        select column1, column2, column3
//        from OtherTable
//        where rowId = 5
//    }

    private void addPhotos(SQLiteDatabase database, long albumId, ArrayList<AlbumFrameBean> beans) {

        for (AlbumFrameBean bean : beans) {
            ContentValues values = new ContentValues(4);
            values.put(PHOTO_ALBUM_ID, albumId);
            values.put(PHOTO_FRAME, bean.getFrameName());
            values.put(PHOTO_IMAGE, bean.getImagePath());
            values.put(PHOTO_ROTATION, bean.getRotation());
            long photoId = database.insert(TABLE_PHOTO, null, values);

            bean.setAlbumId(albumId);
            bean.setPhotoId(photoId);
            Log.d("AlbumDatabase", "addPhotos (Line:81) PhotoId:" + photoId + " bean:" + bean);
        }
        Log.d("AlbumDatabase", "addPhotos (Line:83) : BeanList:" + beans);
    }

    private void updatePhotos(SQLiteDatabase database, long albumId, ArrayList<AlbumFrameBean> beans) {

        Log.d("AlbumDatabase", "updatePhotos (Line:124) :" + albumId + "  " + beans);
        for (AlbumFrameBean bean : beans) {
            ContentValues values = new ContentValues(4);
            values.put(PHOTO_ALBUM_ID, albumId);
            values.put(PHOTO_FRAME, bean.getFrameName());
            values.put(PHOTO_IMAGE, bean.getImagePath());
            values.put(PHOTO_ROTATION, bean.getRotation());
            long photoId = database.update(TABLE_PHOTO, values, PHOTO_ID + " = " + bean.getPhotoId(), null);
            Log.d("AlbumDatabase", "updatePhotos (Line:181) PhotoId:" + photoId + " bean:" + bean);
        }
        Log.d("AlbumDatabase", "updatePhotos (Line:183) : BeanList:" + beans);
    }

    public ArrayList<AlbumBean> getAlbumList() {

        ArrayList<AlbumBean> albumList = new ArrayList<>();

        SQLiteDatabase database = getReadableDatabase();

        Cursor cursor = database.query(TABLE_ALBUM, null, null, null, null, null, null);
        Log.d("AlbumDatabase", "getAlbumList (Line:143) :" + cursor.getCount());
        while (cursor.moveToNext()) {

            long albumId = cursor.getLong(cursor.getColumnIndex(ALBUM_ID));
            String albumName = cursor.getString(cursor.getColumnIndex(ALBUM_NAME));
            Log.d("AlbumDatabase", "getAlbumList (Line:94) :" + albumId + " " + albumName);
            albumList.add(new AlbumBean(albumId, albumName));
        }
        cursor.close();
        database.close();
        return albumList;
    }

    public ArrayList<AlbumFrameBean> getPhotoList(long albumId) {

        Log.d("AlbumDatabase", "getPhotoList (Line:158) :" + albumId);
        ArrayList<AlbumFrameBean> frameBeans = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();

        Cursor cursor = database.query(TABLE_PHOTO, null, PHOTO_ALBUM_ID + " = " + albumId, null, null, null, null);
        Log.d("AlbumDatabase", "getPhotoList (Line:163) :" + cursor.getCount());
        while (cursor.moveToNext()) {
            long photoId = cursor.getLong(cursor.getColumnIndex(PHOTO_ID));
            long albId = cursor.getLong(cursor.getColumnIndex(PHOTO_ALBUM_ID));
            String frameName = cursor.getString(cursor.getColumnIndex(PHOTO_FRAME));
            String imagePath = cursor.getString(cursor.getColumnIndex(PHOTO_IMAGE));
            int rotation = cursor.getInt(cursor.getColumnIndex(PHOTO_ROTATION));
            Log.d("AlbumDatabase", "getPhotoList (Line:110) :" + photoId + " " + albId + " " + frameName + " " + imagePath + " " + rotation);

            AlbumFrameBean bean = new AlbumFrameBean(frameName, imagePath);
            bean.setRotation(rotation);
            bean.setPhotoId(photoId);
            bean.setAlbumId(albId);

            frameBeans.add(bean);
            Log.d("AlbumDatabase", "getPhotoList (Line:159) :" + bean);
        }
        cursor.close();
        database.close();
        return frameBeans;
    }

    public void printDb() {
        ArrayList<AlbumBean> albumBeans = getAlbumList();
        Log.d("AlbumDatabase", "printDb (Line:187) :" + albumBeans.toString());

        for (AlbumBean bean : albumBeans) {
            ArrayList<AlbumFrameBean> photoList = getPhotoList(bean.getAlbumId());
            Log.d("AlbumDatabase", "printDb (Line:191) :" + bean.getAlbumId() + " " + bean.getAlbumName() + " " + photoList);
        }
        Log.d("AlbumDatabase", "printDb (Line:193) :");
    }

    public void deleteAlbum(long albumId) {
        SQLiteDatabase database = getWritableDatabase();
        database.delete(TABLE_PHOTO, PHOTO_ALBUM_ID + " = " + albumId, null);
        database.delete(TABLE_ALBUM, ALBUM_ID + " = " + albumId, null);
        database.close();
    }

    public ArrayList<AlbumFrameBean> duplicateAlbum(long albumId, String albumName) {
        ArrayList<AlbumFrameBean> photoList = getPhotoList(albumId);

        insertAlbum(albumName, photoList);
        return photoList;
    }
}
