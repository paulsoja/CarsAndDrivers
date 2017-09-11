package com.paulsojaoutlook.pavelsoya.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.paulsojaoutlook.pavelsoya.model.CarItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by p-sha on 02.09.2017.
 */

public class CarService implements ICarService {

    private final SQLiteDatabase db;
    private CarItem carItem;

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
    public void addCar(CarItem carItem) {
        ContentValues values = new ContentValues();

        values.put("Name", carItem.getName());
        values.put("Year", carItem.getYear());
        db.insert("Cars", null, values);
    }

    @Override
    public CarItem getCar(int id) {
        return null;
    }

    @Override
    public List<CarItem> getAllCar() {
        List<CarItem> carItemList = new ArrayList<>();
        String selectQuery = "SELECT * FROM Cars";

        Cursor cursor = db.rawQuery(selectQuery, null);

        while (cursor.moveToNext()) {
            CarItem carItem = new CarItem();
            carItem.setId(Integer.parseInt(cursor.getString(0)));
            carItem.setName(cursor.getString(1));
            carItem.setYear(Integer.parseInt(cursor.getString(2)));
            carItemList.add(carItem);
        }
        cursor.close();

        return carItemList;
    }

    @Override
    public int getCarCount() {
        return 0;
    }

    @Override
    public int updateCar(int id, CarItem carItem) {
        ContentValues values = new ContentValues();
        values.put("Name", carItem.getName());
        values.put("Year", carItem.getYear());
        return db.update("Cars", values, "Id = " + id, null);
    }

    @Override
    public void deleteCar(int id) {
        db.delete("Cars", "Id = " + id, null);
        db.close();
    }
}
