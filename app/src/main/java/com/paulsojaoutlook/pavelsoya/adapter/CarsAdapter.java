package com.paulsojaoutlook.pavelsoya.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.paulsojaoutlook.pavelsoya.R;
import com.paulsojaoutlook.pavelsoya.model.CarItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by p-sha on 02.09.2017.
 */

public class CarsAdapter extends BaseAdapter {

    private class ViewHolder {
        TextView id;
        TextView name;
        TextView year;
    }

    LayoutInflater inflater;
    List<CarItem> carItemList = new ArrayList<>();
    Context context;

    public CarsAdapter(Context context, List<CarItem> carItemList) {
        this.context = context;
        this.carItemList = carItemList;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return carItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return carItemList.get(position);
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
            v = inflater.inflate(R.layout.item_car, viewGroup, false);
            viewHolder = new ViewHolder();
            viewHolder.id = v.findViewById(R.id.txtCarId);
            viewHolder.name = v.findViewById(R.id.txtCarName);
            viewHolder.year = v.findViewById(R.id.txtCarYear);
            notifyDataSetChanged();
            v.setTag(viewHolder);
        } else {
            v = view;
            notifyDataSetChanged();
            viewHolder = (ViewHolder) v.getTag();
        }

        viewHolder.id.setText(String.valueOf(getItemId(position)));
        viewHolder.name.setText(carItemList.get(position).getName());
        viewHolder.year.setText(String.valueOf(carItemList.get(position).getYear()));

        return v;
    }
}
