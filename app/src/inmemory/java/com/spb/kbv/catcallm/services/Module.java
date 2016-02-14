package com.spb.kbv.catcallm.services;

import com.spb.kbv.catcallm.infrastructure.CatcallApplication;

public class Module {
    public static void register (CatcallApplication application) {
        /*new InMemoryAccountService(application);*/
        new InMemoryMessagesService(application);
    }
}
