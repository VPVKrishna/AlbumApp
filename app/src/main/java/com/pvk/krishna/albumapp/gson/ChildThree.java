
package com.pvk.krishna.albumapp.gson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ChildThree {

    @SerializedName("category_id")
    @Expose
    private String categoryId;
    @SerializedName("parent_id")
    @Expose
    private String parentId;
    @Expose
    private String name;
    @SerializedName("is_active")
    @Expose
    private String isActive;
    @Expose
    private String position;
    @Expose
    private String level;
    @Expose
    private List<Object> children = new ArrayList<Object>();

    /**
     * 
     * @return
     *     The categoryId
     */
    public String getCategoryId() {
        return categoryId;
    }

    /**
     * 
     * @param categoryId
     *     The category_id
     */
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * 
     * @return
     *     The parentId
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * 
     * @param parentId
     *     The parent_id
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    /**
     * 
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The isActive
     */
    public String getIsActive() {
        return isActive;
    }

    /**
     * 
     * @param isActive
     *     The is_active
     */
    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    /**
     * 
     * @return
     *     The position
     */
    public String getPosition() {
        return position;
    }

    /**
     * 
     * @param position
     *     The position
     */
    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * 
     * @return
     *     The level
     */
    public String getLevel() {
        return level;
    }

    /**
     * 
     * @param level
     *     The level
     */
    public void setLevel(String level) {
        this.level = level;
    }

    /**
     * 
     * @return
     *     The children
     */
    public List<Object> getChildren() {
        return children;
    }

    /**
     * 
     * @param children
     *     The children
     */
    public void setChildren(List<Object> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "ChildThree{" +
                "categoryId='" + categoryId + '\'' +
                ", parentId='" + parentId + '\'' +
                ", name='" + name + '\'' +
                ", isActive='" + isActive + '\'' +
                ", position='" + position + '\'' +
                ", level='" + level + '\'' +
                ", children=" + children +
                '}';
    }
}
