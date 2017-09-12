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
import com.paulsojaoutlook.pavelsoya.adapter.DriversAdapter;
import com.paulsojaoutlook.pavelsoya.database.DBHandler;
import com.paulsojaoutlook.pavelsoya.database.DBHelper;
import com.paulsojaoutlook.pavelsoya.dialog.AddingNewDriverDialog;
import com.paulsojaoutlook.pavelsoya.dialog.DeleteDriverDialog;
import com.paulsojaoutlook.pavelsoya.dialog.EditDriverDialog;
import com.paulsojaoutlook.pavelsoya.model.DriverItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by p-sha on 02.09.2017.
 */

public class DriversFragment extends Fragment implements View.OnClickListener, AddingNewDriverDialog.OnDriversChangedListener,
        AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener, DeleteDriverDialog.OnDriverDeleteListener {

    public static final String TAG_DRIVERS_FRAGMENT = "TAG_DRIVERS_FRAGMENT";
    public static final String TAG_DRIVERS_EDIT = "TAG_DRIVERS_EDIT";
    public static final int REQUEST_CODE = 1233;

    private DriverItem driverItem;
    DriversAdapter adapter;

    private int itemPosition;
    private List<DriverItem> driverItemList;

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
        list.setOnItemClickListener(this);
        list.setOnItemLongClickListener(this);

        adapter = new DriversAdapter(getContext(), driverItemList);
        list.setAdapter(adapter);

        //Header in the list
        View header = inflater.inflate(R.layout.listview_header, container, false);
        list.addHeaderView(header, null, false);

        return root;
    }

    @Override
    public void onClick(View view) {
        //open dialog for new data from user
        AddingNewDriverDialog addingNewDriverDialog = new AddingNewDriverDialog();
        Bundle bundle = new Bundle();
        bundle.putString(TAG_DRIVERS_FRAGMENT, getTag());
        addingNewDriverDialog.setArguments(bundle);
        addingNewDriverDialog.show(getFragmentManager(), AddingNewDriverDialog.TAG_ADDING_NEW_DRIVER);
    }

    private void fillData() {
        //fill the list from the base
        DBHelper helper = new DBHelper(getContext());
        DBHandler handler = new DBHandler(helper);
        driverItemList = handler.getDriverService().getAllDriver();
    }

    @Override
    public void onDriversChanged() {
        //reload list after added car
        driverItemList.clear();
        fillData();
        adapter.reload();
    }

    @Override
    //click item for edit
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        itemPosition = i;
        EditDriverDialog editDriverDialog = new EditDriverDialog();
        editDriverDialog.setTargetFragment(this, REQUEST_CODE);
        editDriverDialog.show(getFragmentManager(), TAG_DRIVERS_EDIT);
    }

    @Override
    //long click item for delete
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        itemPosition = i;
        DeleteDriverDialog deleteDriverDialog = new DeleteDriverDialog();
        Bundle bundle = new Bundle();
        bundle.putString(TAG_DRIVERS_FRAGMENT, getTag());
        deleteDriverDialog.setArguments(bundle);
        deleteDriverDialog.show(getFragmentManager(), DeleteDriverDialog.TAG_DELETE_DRIVER);
        return true;
    }

    @Override
    public void onDriverDelete() {
        DBHelper helper = new DBHelper(getActivity());
        DBHandler handler = new DBHandler(helper);
        handler.getDriverService().deleteDriver(driverItemList.get(itemPosition - 1).getId());
        driverItemList.clear();
        fillData();
        adapter.reload();

        Toast.makeText(getContext(), R.string.Toast_itemIsDeleted, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                String name = data.getExtras().getString(EditDriverDialog.TAG_DRIVER_NAME);
                int age = data.getExtras().getInt(EditDriverDialog.TAG_DRIVER_AGE);
                driverItem.setId(itemPosition - 1);
                driverItem.setName(name);
                driverItem.setAge(age);
                DBHelper helper = new DBHelper(getActivity());
                DBHandler handler = new DBHandler(helper);
                handler.getDriverService().updateDriver(driverItemList.get(itemPosition - 1).getId(), driverItem);
                driverItemList.clear();
                fillData();
                adapter.reload();

                //выводим сообщение
                Toast.makeText(getContext(), R.string.Toast_itemIsEdited, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
