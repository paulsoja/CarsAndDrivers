package com.paulsojaoutlook.pavelsoya.dialog;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.paulsojaoutlook.pavelsoya.R;
import com.paulsojaoutlook.pavelsoya.database.DBHandler;
import com.paulsojaoutlook.pavelsoya.database.DBHelper;
import com.paulsojaoutlook.pavelsoya.model.DriverItem;

/**
 * Created by p-sha on 04.09.2017.
 */

public class AddingNewDriverDialog extends DialogFragment implements View.OnClickListener {

    public interface OnDriversChangedListener {
        void onDriversChanged();
    }

    private EditText etDriverName;
    private EditText etDriverAge;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        getDialog().setTitle(R.string.adding_new_driver_title);
        View root = inflater.inflate(R.layout.dialog_driver, container, false);
        etDriverName = root.findViewById(R.id.etDialogDriverName);
        etDriverAge = root.findViewById(R.id.etDialogDriverAge);
        root.findViewById(R.id.DialogDriverBtnYes).setOnClickListener(this);
        root.findViewById(R.id.DialogDriverBtnNo).setOnClickListener(this);
        return root;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.DialogDriverBtnYes:
                DriverItem driverItem = new DriverItem();

                driverItem.setName(etDriverName.getText().toString());
                driverItem.setAge(Integer.parseInt(etDriverAge.getText().toString()));

                DBHelper helper = new DBHelper(getActivity());
                DBHandler handler = new DBHandler(helper);

                handler.getDriverService().addDriver(driverItem);

                Fragment fragment = getFragmentManager().findFragmentByTag("DriversFragment");
                if (fragment instanceof AddingNewDriverDialog.OnDriversChangedListener) {
                    AddingNewDriverDialog.OnDriversChangedListener listener = (AddingNewDriverDialog.OnDriversChangedListener) fragment;
                    listener.onDriversChanged();
                }
                dismiss();
                break;
            case R.id.DialogDriverBtnNo:
                dismiss();
        }
    }
}
