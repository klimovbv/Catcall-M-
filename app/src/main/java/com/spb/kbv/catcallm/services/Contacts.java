package com.spb.kbv.catcallm.services;

import com.spb.kbv.catcallm.services.entities.UserDetails;

import java.util.ArrayList;

public final class Contacts {
    private Contacts(){
    }

    public static class GetCompaniesRequest {

    }

    public static class GetCompaniesResponse {
        public ArrayList<UserDetails> companies;
    }

    public static class SearchCompanyRequest {
        public String query;

        public SearchCompanyRequest(String query) {
            this.query = query;
        }
    }

    public static class SearchCompanyResponse {
        public ArrayList<UserDetails> companies;
    }
}
