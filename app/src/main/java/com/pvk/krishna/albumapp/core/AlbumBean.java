package com.pvk.krishna.albumapp.core;

/**
 * Created by Krishna on 29/06/2015.
 */
public class AlbumBean {

    private long albumId;
    private String albumName;

    public AlbumBean(long albumId, String albumName) {
        this.albumId = albumId;
        this.albumName = albumName;
    }

    public long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(long albumId) {
        this.albumId = albumId;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    @Override
    public String toString() {
        return "AlbumBean{" +
                "albumId=" + albumId +
                ", albumName='" + albumName + '\'' +
                '}';
    }
}
