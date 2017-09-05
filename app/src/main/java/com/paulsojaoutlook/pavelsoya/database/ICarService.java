package com.paulsojaoutlook.pavelsoya.database;

import com.paulsojaoutlook.pavelsoya.model.CarItem;

import java.util.List;

/**
 * Created by p-sha on 02.09.2017.
 */

public interface ICarService {

    void addCar(CarItem carItem);
    CarItem getCar(int id);
    List<CarItem> getAllCar();
    int getCarCount();
    int updateCar(int id);
    void deleteCar(int id);

}
