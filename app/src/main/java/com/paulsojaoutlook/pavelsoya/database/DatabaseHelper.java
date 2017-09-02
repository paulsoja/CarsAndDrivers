package com.paulsojaoutlook.pavelsoya.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import com.paulsojaoutlook.pavelsoya.model.Car;
import com.paulsojaoutlook.pavelsoya.model.Driver;

/**
 * Created by p-sha on 30.08.2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "database_company";

    public static final String CARS_TABLE = "cars_table";
    public static final String CAR_NAME_COLUMN = "car_name";
    public static final String CAR_YEAR_COLUMN = "car_year";

    public static final String DRIVERS_TABLE = "drivers_table";
    public static final String DRIVER_NAME_COLUMN = "driver_name";
    public static final String DRIVER_AGE_COLUMN = "driver_age";

    private static final String CARS_TABLE_CREATE_SCRIPT = "CREATE TABLE " + CARS_TABLE + "("
            + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + CAR_NAME_COLUMN + " TEXT,"
            + CAR_YEAR_COLUMN + " INTEGER" + ")";

    private static final String DRIVERS_TABLE_CREATE_SCRIPT = "CREATE TABLE " + DRIVERS_TABLE + "("
            + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + DRIVER_NAME_COLUMN + " TEXT,"
            + DRIVER_AGE_COLUMN + " INTEGER" + ")";

    private DBQueryManager queryManager;
    private DBUpdateManager updateManager;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        queryManager = new DBQueryManager(getReadableDatabase());
        updateManager = new DBUpdateManager(getWritableDatabase());
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CARS_TABLE_CREATE_SCRIPT);
        db.execSQL(DRIVERS_TABLE_CREATE_SCRIPT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CARS_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + DRIVERS_TABLE);
        onCreate(db);
    }

    private void saveCar(Car car) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(CAR_NAME_COLUMN, car.getName());
        contentValues.put(CAR_YEAR_COLUMN, car.getYear());
        getWritableDatabase().insert(CARS_TABLE, null, contentValues);
    }

    private void saveDrivers(Driver driver) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DRIVER_NAME_COLUMN, driver.getName());
        contentValues.put(DRIVER_AGE_COLUMN, driver.getAge());
        getWritableDatabase().insert(DRIVERS_TABLE, null, contentValues);
    }

    public DBQueryManager query() {
        return queryManager;
    }

    public DBUpdateManager update() {
        return updateManager;
    }
}
