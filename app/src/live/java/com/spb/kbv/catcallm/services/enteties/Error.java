package com.spb.kbv.catcallm.services.enteties;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Error {
    @SerializedName("err_msg")
    @Expose
    private String errMsg;
    @SerializedName("err_code")
    @Expose
    private Integer errCode;

    /**
     *
     * @return
     * The errMsg
     */
    public String getErrMsg() {
        return errMsg;
    }

    /**
     *
     * @param errMsg
     * The err_msg
     */
    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    /**
     *
     * @return
     * The errCode
     */
    public Integer getErrCode() {
        return errCode;
    }

    /**
     *
     * @param errCode
     * The err_code
     */
    public void setErrCode(Integer errCode) {
        this.errCode = errCode;
    }

}