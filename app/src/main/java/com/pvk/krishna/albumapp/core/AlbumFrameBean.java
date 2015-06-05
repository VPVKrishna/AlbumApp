package com.pvk.krishna.albumapp.core;

import java.io.Serializable;

/**
 * Created by Krishna on 31/05/2015.
 */
public class AlbumFrameBean implements Serializable{
    int frameId;
    int imageId;

    public AlbumFrameBean(int frameId, int imageId) {
        this.frameId = frameId;
        this.imageId = imageId;
    }

    public void setFrameId(int frameId) {
        this.frameId = frameId;
    }

    public int getFrameId() {
        return frameId;
    }

    public int getImageId() {
        return imageId;
    }

    @Override
    public String toString() {
        return "AlbumFrameBean{" +
                "frameId=" + frameId +
                ", imageId=" + imageId +
                '}';
    }
}
