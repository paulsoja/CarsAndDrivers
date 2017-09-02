package com.paulsojaoutlook.pavelsoya.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.paulsojaoutlook.pavelsoya.model.Car;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by p-sha on 02.09.2017.
 */

public class DBQueryManager {

    private SQLiteDatabase database;

    public DBQueryManager(SQLiteDatabase database) {
        this.database = database;
    }

    public List<Car> getCar(String selection, String[] selectionArgs, String orderBy) {
        List<Car> carList = new ArrayList<>();

        Cursor c = database.query(DatabaseHelper.CARS_TABLE, null, selection, selectionArgs, null, null, orderBy);

        if (c.moveToFirst()) {
            do {
                String name = c.getString(c.getColumnIndex(DatabaseHelper.CAR_NAME_COLUMN));
                int year = c.getInt(c.getColumnIndex(DatabaseHelper.CAR_YEAR_COLUMN));

                Car car = new Car(name, year);
                carList.add(car);
            } while (c.moveToNext());
        }
        c.close();

        return carList;
    }
}
