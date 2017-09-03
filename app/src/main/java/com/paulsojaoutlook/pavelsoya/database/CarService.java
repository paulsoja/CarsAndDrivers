package com.paulsojaoutlook.pavelsoya.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.paulsojaoutlook.pavelsoya.model.Car;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by p-sha on 02.09.2017.
 */

public class CarService implements ICarService {

    private final SQLiteDatabase db;

    public CarService(SQLiteDatabase db) {
        this.db = db;
    }

    static void createTable(SQLiteDatabase db) {
        String sql = "CREATE TABLE Cars ("
                + "Id INTEGER PRIMARY KEY, "
                + "Name TEXT, "
                + "Year INTEGER"
                + ")";
        db.execSQL(sql);
    }

    @Override
    public void addCar(Car car) {
        ContentValues values = new ContentValues();
        values.put("Name", car.getName());
        values.put("Year", car.getYear());
        db.insert("Cars", null, values);
    }

    @Override
    public Car getCar(int id) {
        return null;
    }

    @Override
    public List<Car> getAllCar() {
        List<Car> carList = new ArrayList<>();
        String selectQuery = "SELECT Id, Name, Year FROM Cars";

        Cursor cursor = db.rawQuery(selectQuery, null);

        while (cursor.moveToNext()) {
            Car car = new Car();
            car.setId(Integer.parseInt(cursor.getString(0)));
            car.setName(cursor.getString(1));
            car.setYear(Integer.parseInt(cursor.getString(2)));
            carList.add(car);
        }
        cursor.close();

        return carList;
    }

    @Override
    public int getCarCount() {
        return 0;
    }

    @Override
    public int updateCar(Car car) {
        return 0;
    }

    @Override
    public void deleteCar(Car car) {

    }
}
