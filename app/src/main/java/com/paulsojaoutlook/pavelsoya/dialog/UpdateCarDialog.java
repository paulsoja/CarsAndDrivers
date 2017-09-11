package com.paulsojaoutlook.pavelsoya.dialog;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.paulsojaoutlook.pavelsoya.R;
import com.paulsojaoutlook.pavelsoya.fragment.CarsFragment;

/**
 * Created by p-sha on 08.09.2017.
 */

public class UpdateCarDialog extends DialogFragment implements View.OnClickListener{

    public static final String TAG_UPDATE_CAR = "TAG_UPDATE_CAR";
    public static final String TAG_NAME = "TAG_NAME";
    public static final String TAG_YEAR = "TAG_YEAR";

    EditText etYear;
    EditText etName;

    public interface OnCarUpdateListener {
        void onCarEdit();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View root = inflater.inflate(R.layout.dialog_edit_car, container, false);
        root.findViewById(R.id.DialogEditCarBtnYes).setOnClickListener(this);
        root.findViewById(R.id.DialogEditCarBtnNo).setOnClickListener(this);

        etName = root.findViewById(R.id.etDialogEditCarName);
        etYear = root.findViewById(R.id.etDialogEditCarYear);

        return root;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.DialogEditCarBtnYes:
                /*Bundle bundle = getArguments();
                String listenerTag = bundle.getString(CarsFragment.TAG_CARS_FRAGMENT);
                Fragment fragment = getFragmentManager().findFragmentByTag(listenerTag);

                String name = etName.getText().toString();
                int year = Integer.parseInt(etYear.getText().toString());

                CarsFragment carsFragment = new CarsFragment();
                Bundle bundle2 = new Bundle();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                bundle2.putString(TAG_NAME, name);
                bundle2.putInt(TAG_YEAR, year);
                carsFragment.setArguments(bundle2);
                fragmentTransaction.commit();

                if (fragment instanceof UpdateCarDialog.OnCarUpdateListener) {
                    UpdateCarDialog.OnCarUpdateListener listener = (UpdateCarDialog.OnCarUpdateListener) fragment;
                    listener.onCarEdit();
                }*/

                String name = etName.getText().toString();
                int year = Integer.parseInt(etYear.getText().toString());
                Bundle bundle = new Bundle();
                bundle.putString(TAG_NAME, name);
                bundle.putInt(TAG_YEAR, year);
                Intent intent = new Intent().putExtras(bundle);
                getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, intent);

                dismiss();
                break;
            case R.id.DialogEditCarBtnNo:
                dismiss();
        }
    }
}
