
package com.pvk.krishna.albumapp.gson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class CustomerProduct {

    @SerializedName("product_id")
    @Expose
    private String productId;
    @Expose
    private String sku;
    @Expose
    private String name;
    @Expose
    private String set;
    @Expose
    private String type;
    @SerializedName("category_ids")
    @Expose
    private List<Object> categoryIds = new ArrayList<Object>();
    @SerializedName("website_ids")
    @Expose
    private List<String> websiteIds = new ArrayList<String>();

    /**
     * 
     * @return
     *     The productId
     */
    public String getProductId() {
        return productId;
    }

    /**
     * 
     * @param productId
     *     The product_id
     */
    public void setProductId(String productId) {
        this.productId = productId;
    }

    /**
     * 
     * @return
     *     The sku
     */
    public String getSku() {
        return sku;
    }

    /**
     * 
     * @param sku
     *     The sku
     */
    public void setSku(String sku) {
        this.sku = sku;
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
     *     The set
     */
    public String getSet() {
        return set;
    }

    /**
     * 
     * @param set
     *     The set
     */
    public void setSet(String set) {
        this.set = set;
    }

    /**
     * 
     * @return
     *     The type
     */
    public String getType() {
        return type;
    }

    /**
     * 
     * @param type
     *     The type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 
     * @return
     *     The categoryIds
     */
    public List<Object> getCategoryIds() {
        return categoryIds;
    }

    /**
     * 
     * @param categoryIds
     *     The category_ids
     */
    public void setCategoryIds(List<Object> categoryIds) {
        this.categoryIds = categoryIds;
    }

    /**
     * 
     * @return
     *     The websiteIds
     */
    public List<String> getWebsiteIds() {
        return websiteIds;
    }

    /**
     * 
     * @param websiteIds
     *     The website_ids
     */
    public void setWebsiteIds(List<String> websiteIds) {
        this.websiteIds = websiteIds;
    }

    @Override
    public String toString() {
        return "CustomerProduct{" +
                "productId='" + productId + '\'' +
                ", sku='" + sku + '\'' +
                ", name='" + name + '\'' +
                ", set='" + set + '\'' +
                ", type='" + type + '\'' +
                ", categoryIds=" + categoryIds +
                ", websiteIds=" + websiteIds +
                '}';
    }
}
