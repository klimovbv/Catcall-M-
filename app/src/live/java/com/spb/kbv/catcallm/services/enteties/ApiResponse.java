package com.spb.kbv.catcallm.services.enteties;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApiResponse {

    @SerializedName("response")
    @Expose
    private Response response;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    @SerializedName("error")
    @Expose
    private Error error;

    /**
     *
     * @return
     * The error
     */
    public Error getError() {
        return error;
    }

    /**
     *
     * @param error
     * The error
     */
    public void setError(Error error) {
        this.error = error;
    }
    public boolean didSucceed() {
        return (error == null);
    }

    public void showErrorToast(Context context){
        if (context == null || error == null)
            return;
        Toast.makeText(context, error.getErrMsg(), Toast.LENGTH_LONG).show();
    }

}