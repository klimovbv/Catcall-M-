package com.spb.kbv.catcallm.services;

import com.spb.kbv.catcallm.infrastructure.CatcallApplication;

public class InMemoryMessagesService extends BaseInMemoryService{
    protected InMemoryMessagesService(CatcallApplication application) {
        super(application);
    }
}
