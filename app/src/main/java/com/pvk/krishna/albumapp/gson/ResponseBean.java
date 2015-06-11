
package com.pvk.krishna.albumapp.gson;

import com.google.gson.annotations.Expose;

public class ResponseBean<T> {

    @Expose
    private Long status;
    @Expose
    private String message;
    @Expose
    private T data;

    /**
     * 
     * @return
     *     The status
     */
    public Long getStatus() {
        return status;
    }

    /**
     * 
     * @param status
     *     The status
     */
    public void setStatus(Long status) {
        this.status = status;
    }

    /**
     * 
     * @return
     *     The message
     */
    public String getMessage() {
        return message;
    }

    /**
     * 
     * @param message
     *     The message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 
     * @return
     *     The data
     */
    public T getData() {
        return data;
    }

    /**
     * 
     * @param data
     *     The data
     */
    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseBean{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
