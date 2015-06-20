package com.pvk.krishna.albumapp.core;

import java.io.Serializable;

/**
 * Created by Krishna on 31/05/2015.
 */
public class AlbumFrameBean implements Serializable {
    private int frameId;
    private String imagePath;

    public AlbumFrameBean(int frameId, String imagePath) {
        this.frameId = frameId;
        this.imagePath = imagePath;
    }

    public void setFrameId(int frameId) {
        this.frameId = frameId;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getFrameId() {
        return frameId;
    }

    public String getImagePath() {
        return imagePath;
    }

    @Override
    public String toString() {
        return "AlbumFrameBean{" +
                "frameId=" + frameId +
                ", imagePath=" + imagePath +
                '}';
    }
}
