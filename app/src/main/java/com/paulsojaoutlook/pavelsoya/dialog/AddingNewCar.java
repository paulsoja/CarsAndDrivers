package com.paulsojaoutlook.pavelsoya.dialog;

import android.annotation.TargetApi;
import android.app.DialogFragment;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.paulsojaoutlook.pavelsoya.R;
import com.paulsojaoutlook.pavelsoya.database.DBHandler;
import com.paulsojaoutlook.pavelsoya.database.DBHelper;
import com.paulsojaoutlook.pavelsoya.model.CarItem;

/**
 * Created by p-sha on 02.09.2017.
 */

public class AddingNewCar extends DialogFragment implements View.OnClickListener{

    public interface OnCarsChangedListener {
        void onCarsChanged();
    }

    private EditText etCarName;
    private EditText etCarYear;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        getDialog().setTitle(R.string.adding_new_car_title);
        View root = inflater.inflate(R.layout.dialog_car, container, false);
        etCarName = root.findViewById(R.id.etDialogCarName);
        etCarYear = root.findViewById(R.id.etDialogCarYear);
        root.findViewById(R.id.DialoBtnYes).setOnClickListener(this);
        root.findViewById(R.id.DialogBtnNo).setOnClickListener(this);
        return root;
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.DialoBtnYes:
                CarItem carItem = new CarItem();

                carItem.setName(etCarName.getText().toString());
                carItem.setYear(Integer.parseInt(etCarYear.getText().toString()));

                DBHelper helper = new DBHelper(getContext());
                DBHandler handler = new DBHandler(helper);

                handler.getService().addCar(carItem);

                Fragment fragment = getFragmentManager().findFragmentByTag("CarsFragment");
                if (fragment instanceof OnCarsChangedListener) {
                    OnCarsChangedListener listener = (OnCarsChangedListener) fragment;
                    listener.onCarsChanged();
                }
                dismiss();
                break;
            case R.id.DialogBtnNo:
                dismiss();
        }
    }
}
