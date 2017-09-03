package com.paulsojaoutlook.pavelsoya.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.paulsojaoutlook.pavelsoya.model.Car;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by p-sha on 02.09.2017.
 */

public class CarsAdapter extends BaseAdapter {

    List<Car> carList = new ArrayList<>();

    @Override
    public int getCount() {
        return carList.size();
    }

    @Override
    public Object getItem(int position) {
        return carList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return carList.get(position).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}
