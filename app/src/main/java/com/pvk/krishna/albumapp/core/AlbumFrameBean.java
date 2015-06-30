package com.pvk.krishna.albumapp.core;

import java.io.Serializable;

/**
 * Created by Krishna on 31/05/2015.
 */
public class AlbumFrameBean implements Serializable {

    private long albumId;
    private long photoId;

    private String frameName;
    private String imagePath;
    private int rotation;

    public AlbumFrameBean(String frameName, String imagePath) {
        this.frameName = frameName;
        this.imagePath = imagePath;
    }

    public void setFrameName(String frameName) {
        this.frameName = frameName;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getFrameName() {
        return frameName;
    }

    public String getImagePath() {
        return imagePath;
    }

    public int getRotation() {
        return rotation;
    }

    public void setRotation(int rotation) {
        this.rotation = rotation;
    }

    public long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(long albumId) {
        this.albumId = albumId;
    }

    public long getPhotoId() {
        return photoId;
    }

    public void setPhotoId(long photoId) {
        this.photoId = photoId;
    }


    @Override
    public String toString() {
        return "AlbumFrameBean{" +
                "albumId=" + albumId +
                ", photoId=" + photoId +
                ", frameName=" + frameName +
                ", imagePath='" + imagePath + '\'' +
                ", rotation=" + rotation +
                '}';
    }
}
