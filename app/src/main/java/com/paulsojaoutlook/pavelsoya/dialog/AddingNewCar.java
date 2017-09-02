package com.paulsojaoutlook.pavelsoya.dialog;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.paulsojaoutlook.pavelsoya.R;

/**
 * Created by p-sha on 02.09.2017.
 */

public class AddingNewCar extends DialogFragment implements View.OnClickListener{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        getDialog().setTitle(R.string.adding_new_car_title);
        View root = inflater.inflate(R.layout.dialog_car, container, false);
        root.findViewById(R.id.DialoBtnYes).setOnClickListener(this);
        root.findViewById(R.id.DialogBtnNo).setOnClickListener(this);
        return root;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.DialoBtnYes:
                dismiss();
                break;
            case R.id.DialogBtnNo:
                dismiss();
        }
    }
}
