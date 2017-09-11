package com.paulsojaoutlook.pavelsoya.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.paulsojaoutlook.pavelsoya.R;
import com.paulsojaoutlook.pavelsoya.adapter.CarsAdapter;
import com.paulsojaoutlook.pavelsoya.database.DBHandler;
import com.paulsojaoutlook.pavelsoya.database.DBHelper;
import com.paulsojaoutlook.pavelsoya.dialog.AddingNewCarDialog;
import com.paulsojaoutlook.pavelsoya.dialog.DeleteCarDialog;
import com.paulsojaoutlook.pavelsoya.dialog.UpdateCarDialog;
import com.paulsojaoutlook.pavelsoya.model.CarItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by p-sha on 02.09.2017.
 */

public class CarsFragment extends Fragment implements View.OnClickListener,
        AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener, AddingNewCarDialog.OnCarsChangedListener,
        DeleteCarDialog.OnCarDeleteListener, UpdateCarDialog.OnCarUpdateListener {

    public static final String TAG_CARS_FRAGMENT = "TAG_CARS_FRAGMENT";
    public static final String TAG_CAR_EDIT = "TAG_CAR_EDIT";
    public static final int REQUEST_CODE = 1234;

    CarItem carItem;
    CarsAdapter adapter;
    List<CarItem> carItemList;
    ListView list;
    AddingNewCarDialog addingNewCarDialog;
    DeleteCarDialog deleteCarDialog;
    //UpdateCarDialog updateCarDialog;
    int itemPosition;

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
        addingNewCarDialog = new AddingNewCarDialog();
        deleteCarDialog = new DeleteCarDialog();
        //updateCarDialog = new UpdateCarDialog();

        /*Bundle bundle = getArguments();
        if (bundle != null) {
            String name = bundle.getString(UpdateCarDialog.TAG_NAME);
            int year = bundle.getInt(UpdateCarDialog.TAG_YEAR);
            carItem.setName(name);
            carItem.setYear(year);
        }*/

        fillData();
        list.setOnItemClickListener(this);
        list.setOnItemLongClickListener(this);

        adapter = new CarsAdapter(getContext(), carItemList);
        list.setAdapter(adapter);

        View myHeader = inflater.inflate(R.layout.listview_header, container, false);
        list.addHeaderView(myHeader, null, false);

        setRetainInstance(true);

        return root;
    }

    @Override
    public void onClick(View view) {
        //open dialog for new data from user

        Bundle bundle = new Bundle();
        bundle.putString(TAG_CARS_FRAGMENT, getTag());
        addingNewCarDialog.setArguments(bundle);
        addingNewCarDialog.show(getFragmentManager(), AddingNewCarDialog.TAG_ADDING_NEW_CAR);
    }

    //fill the list from the base
    private void fillData() {
        DBHelper helper = new DBHelper(getContext());
        DBHandler handler = new DBHandler(helper);
        carItemList = handler.getCarService().getAllCar();
    }

    @Override
    public void onCarsChanged() {
        carItemList.clear();
        fillData();
        adapter.reload();
    }

    //click item for edit
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        itemPosition = i;
        UpdateCarDialog updateCarDialog = new UpdateCarDialog();
        updateCarDialog.setTargetFragment(this, REQUEST_CODE);
        updateCarDialog.show(getFragmentManager(), TAG_CAR_EDIT);
        /*Bundle bundle = new Bundle();
        bundle.putString(TAG_CARS_FRAGMENT, getTag());
        updateCarDialog.setArguments(bundle);
        updateCarDialog.show(getFragmentManager(), UpdateCarDialog.TAG_UPDATE_CAR);
        carItem.setName("nulik");
        carItem.setYear(9999);
        DBHelper helper = new DBHelper(getActivity());
        DBHandler handler = new DBHandler(helper);
        handler.getCarService().updateCar(carItemList.get(i - 1).getId());
        carItemList.clear();
        fillData();
        adapter.reload();*/
    }

    //long click item for delete
    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        itemPosition = i;
        Bundle bundle = new Bundle();
        bundle.putString(TAG_CARS_FRAGMENT, getTag());
        deleteCarDialog.setArguments(bundle);
        deleteCarDialog.show(getFragmentManager(), DeleteCarDialog.TAG_DELETE_CAR);
        return true;
    }

    @Override
    public void onCarDelete() {
        DBHelper helper = new DBHelper(getActivity());
        DBHandler handler = new DBHandler(helper);
        handler.getCarService().deleteCar(carItemList.get(itemPosition - 1).getId());
        carItemList.clear();
        fillData();
        adapter.reload();

        Toast.makeText(getContext(), "item is delete", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCarEdit() {
        /*DBHelper helper = new DBHelper(getActivity());
        DBHandler handler = new DBHandler(helper);

        handler.getCarService().updateCar(carItemList.get(itemPosition - 1).getId());
        carItemList.clear();
        fillData();
        adapter.reload();

        Toast.makeText(getContext(), "item is edit", Toast.LENGTH_SHORT).show();*/
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                String name = data.getExtras().getString(UpdateCarDialog.TAG_NAME);
                int year = data.getExtras().getInt(UpdateCarDialog.TAG_YEAR);
                carItem.setId(itemPosition - 1);
                carItem.setName(name);
                carItem.setYear(year);
                DBHelper helper = new DBHelper(getActivity());
                DBHandler handler = new DBHandler(helper);
                handler.getCarService().updateCar(carItemList.get(itemPosition - 1).getId(), carItem);
                carItemList.clear();
                fillData();
                adapter.reload();

                Toast.makeText(getContext(), "item is edit", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
