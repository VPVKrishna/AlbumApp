package com.pvk.krishna.albumapp.core;

/**
 * Created by Krishna on 31/05/2015.
 */
public class BookBean {
    private String name;
    private int imageId;

    public BookBean(String name) {
        this.name = name;
    }

    public BookBean(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
