package com.paulsojaoutlook.pavelsoya.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.paulsojaoutlook.pavelsoya.R;
import com.paulsojaoutlook.pavelsoya.dialog.AddingNewCar;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by p-sha on 02.09.2017.
 */

public class CarsFragment extends Fragment implements View.OnClickListener{

    private static final String NAME = "car_name";
    private static final String YEAR = "car_year";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View root = inflater.inflate(R.layout.fragment_cars, container, false);
        ListView list = root.findViewById(R.id.lvCars);
        FloatingActionButton fab = root.findViewById(R.id.fab);
        fab.setOnClickListener(this);

        ArrayList<HashMap<String, Object>> arrayList = new ArrayList<>();
        HashMap<String, Object> hashMap;

        hashMap = new HashMap<>();
        hashMap.put(NAME, "Mann");
        hashMap.put(YEAR, 2008);
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put(NAME, "Mercedes");
        hashMap.put(YEAR, 2010);
        arrayList.add(hashMap);

        SimpleAdapter adapter = new SimpleAdapter(getContext(), arrayList,
                R.layout.item_car, new String[]{NAME, YEAR},
                new int[]{R.id.txtCarName, R.id.txtCarYear});

        // присваиваем адаптер списку
        list.setAdapter(adapter);
        return root;
    }

    @Override
    public void onClick(View view) {
        AddingNewCar addingNewCar = new AddingNewCar();
        addingNewCar.show(getActivity().getFragmentManager(), "AddingNewCar");
    }
}
