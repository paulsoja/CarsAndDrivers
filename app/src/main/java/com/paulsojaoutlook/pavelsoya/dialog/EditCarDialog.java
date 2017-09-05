package com.paulsojaoutlook.pavelsoya.dialog;

import android.annotation.TargetApi;
import android.app.DialogFragment;
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
 * Created by p-sha on 05.09.2017.
 */

public class EditCarDialog extends DialogFragment implements View.OnClickListener {
    private EditText etEditCarName;
    private EditText etEditCarYear;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View root = inflater.inflate(R.layout.dialog_car, container, false);
        etEditCarName = root.findViewById(R.id.etDialogEditCarName);
        etEditCarYear = root.findViewById(R.id.etDialogEditCarYear);
        root.findViewById(R.id.DialogEditCarBtnYes).setOnClickListener(this);
        root.findViewById(R.id.DialogEditCarBtnNo).setOnClickListener(this);
        return root;
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.DialogEditCarBtnYes:
                CarItem carItem = new CarItem();

                carItem.setName(etEditCarName.getText().toString());
                carItem.setYear(Integer.parseInt(etEditCarYear.getText().toString()));

                DBHelper helper = new DBHelper(getContext());
                DBHandler handler = new DBHandler(helper);

                handler.getCarService().addCar(carItem);

                dismiss();
                break;
            case R.id.DialogEditCarBtnNo:
                dismiss();
        }
    }
}