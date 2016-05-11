package com.spb.kbv.catcallm.services.enteties;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.spb.kbv.catcallm.infrastructure.ApiError;

public class ApiResponse {

    @SerializedName("error")
    @Expose
    private ApiError error;

    public ApiError getError() {
        return error;
    }

    public void setError(ApiError error) {
        this.error = error;
    }

    public boolean didSucceed() {
        return (error == null);
    }

    public void showErrorToast(Context context){
        if (context == null || error == null)
            return;
        Toast.makeText(context, error.getMessage(), Toast.LENGTH_LONG).show();
    }

}