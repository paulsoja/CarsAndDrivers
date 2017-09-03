package com.paulsojaoutlook.pavelsoya.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.paulsojaoutlook.pavelsoya.R;
import com.paulsojaoutlook.pavelsoya.adapter.CarsAdapter;
import com.paulsojaoutlook.pavelsoya.database.DBHandler;
import com.paulsojaoutlook.pavelsoya.database.DBHelper;
import com.paulsojaoutlook.pavelsoya.dialog.AddingNewCar;
import com.paulsojaoutlook.pavelsoya.model.CarItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by p-sha on 02.09.2017.
 */

public class CarsFragment extends Fragment implements View.OnClickListener, AddingNewCar.OnCarsChangedListener{

    CarItem carItem;
    CarsAdapter adapter;
    List<CarItem> carItemList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View root = inflater.inflate(R.layout.fragment_cars, container, false);
        ListView list = root.findViewById(R.id.lvCars);
        FloatingActionButton fab = root.findViewById(R.id.fab);
        fab.setOnClickListener(this);

        carItem = new CarItem();
        carItemList = new ArrayList<>();

        fillData();

        adapter = new CarsAdapter(getContext(), carItemList);
        list.setAdapter(adapter);



        return root;
    }

    @Override
    public void onClick(View view) {
        AddingNewCar addingNewCar = new AddingNewCar();
        addingNewCar.show(getActivity().getFragmentManager(), "AddingNewCar");
    }

    private void fillData() {
        DBHelper helper = new DBHelper(getContext());
        DBHandler handler = new DBHandler(helper);
        carItemList = handler.getService().getAllCar();

        /*for (int i=0; i<carItemList.size(); i++) {
            carItem.setName(carItemList.get(i).getName());
            carItem.setYear(carItemList.get(i).getYear());
        }*/
    }


    @Override
    public void onCarsChanged() {

    }
}
