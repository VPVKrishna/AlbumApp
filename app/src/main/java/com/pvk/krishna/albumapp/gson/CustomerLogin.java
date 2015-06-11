
package com.pvk.krishna.albumapp.gson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustomerLogin {

    @SerializedName("website_id")
    @Expose
    private String websiteId;
    @SerializedName("store_id")
    @Expose
    private String storeId;
    @SerializedName("entity_id")
    @Expose
    private String entityId;
    @SerializedName("entity_type_id")
    @Expose
    private String entityTypeId;
    @SerializedName("attribute_set_id")
    @Expose
    private String attributeSetId;
    @Expose
    private String email;
    @SerializedName("group_id")
    @Expose
    private String groupId;
    @SerializedName("increment_id")
    @Expose
    private Object incrementId;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("is_active")
    @Expose
    private String isActive;
    @SerializedName("disable_auto_group_change")
    @Expose
    private String disableAutoGroupChange;
    @SerializedName("created_in")
    @Expose
    private String createdIn;
    @Expose
    private String firstname;
    @Expose
    private String lastname;
    @SerializedName("password_hash")
    @Expose
    private String passwordHash;
    @SerializedName("rp_token")
    @Expose
    private String rpToken;
    @SerializedName("rp_token_created_at")
    @Expose
    private String rpTokenCreatedAt;

    /**
     * 
     * @return
     *     The websiteId
     */
    public String getWebsiteId() {
        return websiteId;
    }

    /**
     * 
     * @param websiteId
     *     The website_id
     */
    public void setWebsiteId(String websiteId) {
        this.websiteId = websiteId;
    }

    /**
     * 
     * @return
     *     The storeId
     */
    public String getStoreId() {
        return storeId;
    }

    /**
     * 
     * @param storeId
     *     The store_id
     */
    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    /**
     * 
     * @return
     *     The entityId
     */
    public String getEntityId() {
        return entityId;
    }

    /**
     * 
     * @param entityId
     *     The entity_id
     */
    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    /**
     * 
     * @return
     *     The entityTypeId
     */
    public String getEntityTypeId() {
        return entityTypeId;
    }

    /**
     * 
     * @param entityTypeId
     *     The entity_type_id
     */
    public void setEntityTypeId(String entityTypeId) {
        this.entityTypeId = entityTypeId;
    }

    /**
     * 
     * @return
     *     The attributeSetId
     */
    public String getAttributeSetId() {
        return attributeSetId;
    }

    /**
     * 
     * @param attributeSetId
     *     The attribute_set_id
     */
    public void setAttributeSetId(String attributeSetId) {
        this.attributeSetId = attributeSetId;
    }

    /**
     * 
     * @return
     *     The email
     */
    public String getEmail() {
        return email;
    }

    /**
     * 
     * @param email
     *     The email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 
     * @return
     *     The groupId
     */
    public String getGroupId() {
        return groupId;
    }

    /**
     * 
     * @param groupId
     *     The group_id
     */
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    /**
     * 
     * @return
     *     The incrementId
     */
    public Object getIncrementId() {
        return incrementId;
    }

    /**
     * 
     * @param incrementId
     *     The increment_id
     */
    public void setIncrementId(Object incrementId) {
        this.incrementId = incrementId;
    }

    /**
     * 
     * @return
     *     The createdAt
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * 
     * @param createdAt
     *     The created_at
     */
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * 
     * @return
     *     The updatedAt
     */
    public String getUpdatedAt() {
        return updatedAt;
    }

    /**
     * 
     * @param updatedAt
     *     The updated_at
     */
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
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
     *     The disableAutoGroupChange
     */
    public String getDisableAutoGroupChange() {
        return disableAutoGroupChange;
    }

    /**
     * 
     * @param disableAutoGroupChange
     *     The disable_auto_group_change
     */
    public void setDisableAutoGroupChange(String disableAutoGroupChange) {
        this.disableAutoGroupChange = disableAutoGroupChange;
    }

    /**
     * 
     * @return
     *     The createdIn
     */
    public String getCreatedIn() {
        return createdIn;
    }

    /**
     * 
     * @param createdIn
     *     The created_in
     */
    public void setCreatedIn(String createdIn) {
        this.createdIn = createdIn;
    }

    /**
     * 
     * @return
     *     The firstname
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * 
     * @param firstname
     *     The firstname
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * 
     * @return
     *     The lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * 
     * @param lastname
     *     The lastname
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * 
     * @return
     *     The passwordHash
     */
    public String getPasswordHash() {
        return passwordHash;
    }

    /**
     * 
     * @param passwordHash
     *     The password_hash
     */
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    /**
     * 
     * @return
     *     The rpToken
     */
    public String getRpToken() {
        return rpToken;
    }

    /**
     * 
     * @param rpToken
     *     The rp_token
     */
    public void setRpToken(String rpToken) {
        this.rpToken = rpToken;
    }

    /**
     * 
     * @return
     *     The rpTokenCreatedAt
     */
    public String getRpTokenCreatedAt() {
        return rpTokenCreatedAt;
    }

    /**
     * 
     * @param rpTokenCreatedAt
     *     The rp_token_created_at
     */
    public void setRpTokenCreatedAt(String rpTokenCreatedAt) {
        this.rpTokenCreatedAt = rpTokenCreatedAt;
    }

    @Override
    public String toString() {
        return "CustomerLogin{" +
                "websiteId='" + websiteId + '\'' +
                ", storeId='" + storeId + '\'' +
                ", entityId='" + entityId + '\'' +
                ", entityTypeId='" + entityTypeId + '\'' +
                ", attributeSetId='" + attributeSetId + '\'' +
                ", email='" + email + '\'' +
                ", groupId='" + groupId + '\'' +
                ", incrementId=" + incrementId +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", isActive='" + isActive + '\'' +
                ", disableAutoGroupChange='" + disableAutoGroupChange + '\'' +
                ", createdIn='" + createdIn + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                ", rpToken='" + rpToken + '\'' +
                ", rpTokenCreatedAt='" + rpTokenCreatedAt + '\'' +
                '}';
    }
}
