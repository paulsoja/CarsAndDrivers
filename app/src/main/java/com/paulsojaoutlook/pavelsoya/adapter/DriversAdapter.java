package com.paulsojaoutlook.pavelsoya.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.paulsojaoutlook.pavelsoya.R;
import com.paulsojaoutlook.pavelsoya.model.DriverItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by p-sha on 04.09.2017.
 */

public class DriversAdapter extends BaseAdapter {

    private class ViewHolder {
        TextView id;
        TextView name;
        TextView age;
    }

    LayoutInflater inflater;
    List<DriverItem> driverItemList = new ArrayList<>();
    Context context;

    public DriversAdapter(Context context, List<DriverItem> driverItemList) {
        this.context = context;
        this.driverItemList = driverItemList;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return driverItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return driverItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View v;
        ViewHolder viewHolder;
        if (view == null) {
            v = inflater.inflate(R.layout.item_driver, viewGroup, false);
            viewHolder = new ViewHolder();
            viewHolder.id = v.findViewById(R.id.txtDriverId);
            viewHolder.name = v.findViewById(R.id.txtDriverName);
            viewHolder.age = v.findViewById(R.id.txtDriverAge);
            v.setTag(viewHolder);
        } else {
            v = view;
            viewHolder = (ViewHolder) v.getTag();
        }

        viewHolder.id.setText(String.valueOf(getItemId(position + 1)));
        viewHolder.name.setText(driverItemList.get(position).getName());
        viewHolder.age.setText(String.valueOf(driverItemList.get(position).getAge()));

        return v;
    }
}
