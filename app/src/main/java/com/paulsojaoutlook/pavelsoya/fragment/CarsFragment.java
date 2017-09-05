package com.paulsojaoutlook.pavelsoya.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.paulsojaoutlook.pavelsoya.R;
import com.paulsojaoutlook.pavelsoya.adapter.CarsAdapter;
import com.paulsojaoutlook.pavelsoya.database.DBHandler;
import com.paulsojaoutlook.pavelsoya.database.DBHelper;
import com.paulsojaoutlook.pavelsoya.dialog.AddingNewCarDialog;
import com.paulsojaoutlook.pavelsoya.model.CarItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by p-sha on 02.09.2017.
 */

public class CarsFragment extends Fragment implements View.OnClickListener,
        AddingNewCarDialog.OnCarsChangedListener, AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener{

    public static final String TAG_CARS_FRAGMENT = "TAG_CARS_FRAGMENT";

    CarItem carItem;
    CarsAdapter adapter;
    List<CarItem> carItemList;
    ListView list;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View root = inflater.inflate(R.layout.fragment_cars, container, false);
        list = root.findViewById(R.id.listCars);
        FloatingActionButton fab = root.findViewById(R.id.fabCars);
        fab.setOnClickListener(this);

        carItem = new CarItem();
        carItemList = new ArrayList<>();

        fillData();
        list.setOnItemClickListener(this);
        list.setOnItemLongClickListener(this);

        adapter = new CarsAdapter(getContext(), carItemList);
        list.setAdapter(adapter);

        return root;
    }

    @Override
    public void onClick(View view) {
        //open dialog for new data from user
        AddingNewCarDialog addingNewCarDialog = new AddingNewCarDialog();
        Bundle bundle = new Bundle();
        bundle.putString(AddingNewCarDialog.TAG_ADDING_NEW_CAR, getTag());
        addingNewCarDialog.setArguments(bundle);
        addingNewCarDialog.show(getActivity().getFragmentManager(), TAG_CARS_FRAGMENT);
    }

    //fill the list from the base
    private void fillData() {
        DBHelper helper = new DBHelper(getContext());
        DBHandler handler = new DBHandler(helper);
        carItemList = handler.getCarService().getAllCar();
    }


    @Override
    public void onCarsChanged() {
        adapter.notifyDataSetChanged();
    }

    //click item for edit
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        carItem.setName("nulik");
        carItem.setYear(9999);
        DBHelper helper = new DBHelper(getActivity());
        DBHandler handler = new DBHandler(helper);
        handler.getCarService().updateCar(carItemList.get(i).getId());
    }

    //long click item for delete
    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        DBHelper helper = new DBHelper(getActivity());
        DBHandler handler = new DBHandler(helper);
        handler.getCarService().deleteCar(carItemList.get(i).getId());
        return true;
    }
}
