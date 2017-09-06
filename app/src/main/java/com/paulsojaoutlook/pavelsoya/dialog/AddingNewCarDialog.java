package com.paulsojaoutlook.pavelsoya.dialog;

import android.annotation.TargetApi;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.paulsojaoutlook.pavelsoya.R;
import com.paulsojaoutlook.pavelsoya.database.DBHandler;
import com.paulsojaoutlook.pavelsoya.database.DBHelper;
import com.paulsojaoutlook.pavelsoya.fragment.CarsFragment;
import com.paulsojaoutlook.pavelsoya.model.CarItem;

/**
 * Created by p-sha on 02.09.2017.
 */

public class AddingNewCarDialog extends DialogFragment implements View.OnClickListener{

    public static final String TAG_ADDING_NEW_CAR = "TAG_ADDING_NEW_CAR";

    public interface OnCarsChangedListener {
        void onCarsChanged();
    }

    private EditText etCarName;
    private EditText etCarYear;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View root = inflater.inflate(R.layout.dialog_car, container, false);
        etCarName = root.findViewById(R.id.etDialogCarName);
        etCarYear = root.findViewById(R.id.etDialogCarYear);
        root.findViewById(R.id.DialogCarBtnYes).setOnClickListener(this);
        root.findViewById(R.id.DialogCarBtnNo).setOnClickListener(this);
        return root;
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.DialogCarBtnYes:
                CarItem carItem = new CarItem();

                carItem.setName(etCarName.getText().toString());
                carItem.setYear(Integer.parseInt(etCarYear.getText().toString()));

                DBHelper helper = new DBHelper(getContext());
                DBHandler handler = new DBHandler(helper);

                handler.getCarService().addCar(carItem);

                Bundle bundle = getArguments();
                String listenerTag = bundle.getString(CarsFragment.TAG_CARS_FRAGMENT);
                Fragment fragment = getFragmentManager().findFragmentByTag(listenerTag);
                if (fragment instanceof OnCarsChangedListener) {
                    OnCarsChangedListener listener = (OnCarsChangedListener) fragment;
                    listener.onCarsChanged();
                }
                dismiss();
                break;
            case R.id.DialogCarBtnNo:
                //close dialog without any changes
                dismiss();
        }
    }
}
