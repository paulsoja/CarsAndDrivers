package com.paulsojaoutlook.pavelsoya.dialog;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.paulsojaoutlook.pavelsoya.R;
import com.paulsojaoutlook.pavelsoya.database.DBHandler;
import com.paulsojaoutlook.pavelsoya.database.DBHelper;
import com.paulsojaoutlook.pavelsoya.model.CarItem;

/**
 * Created by p-sha on 04.09.2017.
 */

public class DeleteCarDialog extends DialogFragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        getDialog().setTitle("Delete this item");
        View root = inflater.inflate(R.layout.dialog_delete, container, false);
        root.findViewById(R.id.DialogDeleteBtnYes).setOnClickListener(this);
        root.findViewById(R.id.DialogDeleteBtnNo).setOnClickListener(this);
        return root;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.DialogDeleteBtnYes:
                CarItem carItem = new CarItem();
                DBHelper helper = new DBHelper(getActivity());
                DBHandler handler = new DBHandler(helper);

                //handler.getCarService().deleteCar(carItem);

                dismiss();
                break;
            case R.id.DialogDeleteBtnNo:
                dismiss();
        }
    }
}
