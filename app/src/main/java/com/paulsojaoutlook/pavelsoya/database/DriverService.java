package com.paulsojaoutlook.pavelsoya.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.paulsojaoutlook.pavelsoya.model.DriverItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by p-sha on 04.09.2017.
 */

public class DriverService implements IDriverService {

    private final SQLiteDatabase db;

    public DriverService(SQLiteDatabase db) {
        this.db = db;
    }

    static void createTable(SQLiteDatabase db) {
        String sql = "CREATE TABLE Drivers ("
                + "Id INTEGER PRIMARY KEY, "
                + "Name TEXT, "
                + "Age INTEGER"
                + ")";
        db.execSQL(sql);
    }

    @Override
    public void addDriver(DriverItem driverItem) {
        ContentValues values = new ContentValues();
        values.put("Name", driverItem.getName());
        values.put("Age", driverItem.getAge());
        db.insert("Drivers", null, values);
    }

    @Override
    public DriverItem getDriver(int id) {
        return null;
    }

    @Override
    public List<DriverItem> getAllDriver() {
        List<DriverItem> driverItemList = new ArrayList<>();
        String selectQuery = "SELECT * FROM Drivers";

        Cursor cursor = db.rawQuery(selectQuery, null);

        while (cursor.moveToNext()) {
            DriverItem driverItem = new DriverItem();
            driverItem.setId(Integer.parseInt(cursor.getString(0)));
            driverItem.setName(cursor.getString(1));
            driverItem.setAge(Integer.parseInt(cursor.getString(2)));
            driverItemList.add(driverItem);
        }
        cursor.close();

        return driverItemList;
    }

    @Override
    public int getDriverCount() {
        return 0;
    }

    @Override
    public int updateDriver(int id, DriverItem driverItem) {
        ContentValues values = new ContentValues();
        values.put("Name", driverItem.getName());
        values.put("Age", driverItem.getAge());
        return db.update("Drivers", values, "Id = " + id, null);
    }

    @Override
    public void deleteDriver(int id) {
        db.delete("Drivers", "Id = " + id, null);
        db.close();
    }
}
