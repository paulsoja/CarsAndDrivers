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
import com.paulsojaoutlook.pavelsoya.adapter.DriversAdapter;
import com.paulsojaoutlook.pavelsoya.database.DBHandler;
import com.paulsojaoutlook.pavelsoya.database.DBHelper;
import com.paulsojaoutlook.pavelsoya.dialog.AddingNewDriverDialog;
import com.paulsojaoutlook.pavelsoya.model.DriverItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by p-sha on 02.09.2017.
 */

public class DriversFragment extends Fragment implements View.OnClickListener, AddingNewDriverDialog.OnDriversChangedListener{

    DriverItem driverItem;
    DriversAdapter adapter;
    List<DriverItem> driverItemList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View root = inflater.inflate(R.layout.fragment_drivers, container, false);
        ListView list = root.findViewById(R.id.listDrivers);
        FloatingActionButton fab = root.findViewById(R.id.fabDrivers);
        fab.setOnClickListener(this);

        driverItem = new DriverItem();
        driverItemList = new ArrayList<>();

        fillData();

        adapter = new DriversAdapter(getContext(), driverItemList);
        list.setAdapter(adapter);

        return root;
    }

    @Override
    public void onClick(View view) {
        AddingNewDriverDialog addingNewDriverDialog = new AddingNewDriverDialog();
        addingNewDriverDialog.show(getActivity().getFragmentManager(), "AddingNewDriverDialog");
    }

    private void fillData() {
        DBHelper helper = new DBHelper(getContext());
        DBHandler handler = new DBHandler(helper);
        driverItemList = handler.getDriverService().getAllDriver();
    }

    @Override
    public void onDriversChanged() {

    }
}
