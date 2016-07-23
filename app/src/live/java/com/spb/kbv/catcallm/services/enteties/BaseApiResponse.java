package com.spb.kbv.catcallm.services.enteties;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BaseApiResponse {

    @SerializedName("error")
    @Expose
    private Error error;

    public Error getError() {
        return error;
    }

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