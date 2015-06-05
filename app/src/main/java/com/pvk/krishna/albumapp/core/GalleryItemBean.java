package com.pvk.krishna.albumapp.core;

/**
 * Created by Krishna on 31/05/2015.
 */
public class GalleryItemBean {
    private String filePath;
    private boolean isChecked;

    public GalleryItemBean(String filePath) {
        this.filePath = filePath;
    }

    public GalleryItemBean(String filePath, boolean isChecked) {
        this.filePath = filePath;
        this.isChecked = isChecked;
    }

    public void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

    public String getFilePath() {
        return filePath;
    }

    public boolean isChecked() {
        return isChecked;
    }

    @Override
    public String toString() {
        return "GalleryItemBean{" +
                "filePath='" + filePath + '\'' +
                ", isChecked=" + isChecked +
                '}';
    }
}
