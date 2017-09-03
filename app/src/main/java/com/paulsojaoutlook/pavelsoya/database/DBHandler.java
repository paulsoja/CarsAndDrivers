package com.paulsojaoutlook.pavelsoya.database;

/**
 * Created by p-sha on 03.09.2017.
 */

public class DBHandler {

    private final DBHelper helper;
    private final CarService service;

    public DBHandler(DBHelper helper) {
        this.helper = helper;
        service = new CarService(helper.getWritableDatabase());
    }

    public CarService getService() {
        return service;
    }
}
