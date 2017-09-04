package com.paulsojaoutlook.pavelsoya.database;

import com.paulsojaoutlook.pavelsoya.model.DriverItem;

import java.util.List;

/**
 * Created by p-sha on 04.09.2017.
 */

public interface IDriverService {

    void addDriver(DriverItem driverItem);
    DriverItem getDriver(int id);
    List<DriverItem> getAllDriver();
    int getDriverCount();
    int updateDriver(DriverItem driverItem);
    void deleteDriver(DriverItem driverItem);
}
