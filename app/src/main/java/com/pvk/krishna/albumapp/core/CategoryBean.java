package com.pvk.krishna.albumapp.core;

/**
 * Created by Krishna on 26/05/2015.
 */
public class CategoryBean {

    private String name;
    private int path;

    public CategoryBean(String name, int path) {
        this.name = name;
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public int getPath() {
        return path;
    }
}
