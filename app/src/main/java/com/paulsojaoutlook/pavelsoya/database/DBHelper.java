package com.paulsojaoutlook.pavelsoya.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by p-sha on 02.09.2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    //Версия базы данных. При изменении схемы увеличить на единицу
    private static final int DATABASE_VERSION = 1;
    //Имя базы данных
    private static final String DATABASE_NAME = "manager";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //создание таблицы Cars
        CarService.createTable(db);
        //Создание таблицы Drivers
        DriverService.createTable(db);
    }

    //Вызывается при обновлении схемы базы данных
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Cars");
        db.execSQL("DROP TABLE IF EXISTS Drivers");
        onCreate(db);
    }
}
