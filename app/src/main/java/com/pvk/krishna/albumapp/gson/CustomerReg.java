
package com.pvk.krishna.albumapp.gson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustomerReg {

    @SerializedName("cust_id")
    @Expose
    private String custId;

    /**
     * 
     * @return
     *     The custId
     */
    public String getCustId() {
        return custId;
    }

    /**
     * 
     * @param custId
     *     The cust_id
     */
    public void setCustId(String custId) {
        this.custId = custId;
    }

    @Override
    public String toString() {
        return "CustomerReg{" +
                "custId='" + custId + '\'' +
                '}';
    }
}
