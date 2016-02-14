package com.spb.kbv.catcallm.services;

import com.spb.kbv.catcallm.services.entities.UserDetails;

import java.util.ArrayList;
import java.util.List;

public final class Contacts {
    private Contacts(){
    }

    public static class GetCompaniesRequest {

    }

    public static class GetCompaniesResponse {
        public ArrayList<UserDetails> companies;
    }
}
