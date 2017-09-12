package com.paulsojaoutlook.pavelsoya.database;

/**
 * Created by p-sha on 03.09.2017.
 */

public class DBHandler {

    private final CarService carService;
    private final DriverService driverService;

    public DBHandler(DBHelper helper) {
        carService = new CarService(helper.getWritableDatabase());
        driverService = new DriverService(helper.getWritableDatabase());
    }

    public CarService getCarService() {
        return carService;
    }

    public DriverService getDriverService() {
        return driverService;
    }
}
