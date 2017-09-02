package com.paulsojaoutlook.pavelsoya.database;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.paulsojaoutlook.pavelsoya.model.Car;

/**
 * Created by p-sha on 02.09.2017.
 */

public class DBUpdateManager {

    SQLiteDatabase database;

    public DBUpdateManager(SQLiteDatabase database) {
        this.database = database;
    }

    public void carName(String name) {
        update(DatabaseHelper.CAR_NAME_COLUMN, name);
    }

    public void carYear(int year) {
        update(DatabaseHelper.CAR_YEAR_COLUMN, year);
    }

    public void car(Car car) {
        carName(car.getName());
        carYear(car.getYear());
    }


    private void update(String column, String value) {
        ContentValues cv = new ContentValues();
        cv.put(column, value);
        database.update(DatabaseHelper.CARS_TABLE, cv, null, null);
    }

    private void update (String column, long value) {
        ContentValues cv = new ContentValues();
        cv.put(column, value);
        database.update(DatabaseHelper.CARS_TABLE, cv, null, null);
    }
}
