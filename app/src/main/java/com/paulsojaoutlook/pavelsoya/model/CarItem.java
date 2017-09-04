package com.paulsojaoutlook.pavelsoya.model;

/**
 * Created by p-sha on 30.08.2017.
 */

public class CarItem {


    private int id;
    private String name;
    private int year;

    public CarItem() {}

    public CarItem(String name, int year) {
        this.name = name;
        this.year = year;
    }

    public CarItem(int id, String name, int year) {
        this.id = id;
        this.name = name;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
