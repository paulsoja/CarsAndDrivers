package com.paulsojaoutlook.pavelsoya.database;

import com.paulsojaoutlook.pavelsoya.model.Car;

import java.util.List;

/**
 * Created by p-sha on 02.09.2017.
 */

public interface ICarService {

    void addCar(Car car);
    Car getCar(int id);
    List<Car> getAllCar();
    int getCarCount();
    int updateCar(Car car);
    void deleteCar(Car car);

}
