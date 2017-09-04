package com.paulsojaoutlook.pavelsoya.model;

/**
 * Created by p-sha on 30.08.2017.
 */

public class DriverItem {
    private int id;
    private String name;
    private int age;

    public DriverItem() {}

    public DriverItem(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public DriverItem(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
