package com.paulsojaoutlook.pavelsoya.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.paulsojaoutlook.pavelsoya.R;
import com.paulsojaoutlook.pavelsoya.fragment.CarsFragment;

/**
 * Created by p-sha on 04.09.2017.
 */

public class DeleteCarDialog extends DialogFragment implements View.OnClickListener {

    public static final String TAG_DELETE_CAR = "TAG_DELETE_CAR";

    public interface OnCarDeleteListener {
        void onCarDelete();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View root = inflater.inflate(R.layout.dialog_delete_car, container, false);
        root.findViewById(R.id.DialogDeleteBtnYes).setOnClickListener(this);
        root.findViewById(R.id.DialogDeleteBtnNo).setOnClickListener(this);
        return root;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.DialogDeleteBtnYes:
                Bundle bundle = getArguments();
                String listenerTag = bundle.getString(CarsFragment.TAG_CARS_FRAGMENT);
                Fragment fragment = getFragmentManager().findFragmentByTag(listenerTag);
                if (fragment instanceof DeleteCarDialog.OnCarDeleteListener) {
                    DeleteCarDialog.OnCarDeleteListener listener = (DeleteCarDialog.OnCarDeleteListener) fragment;
                    listener.onCarDelete();
                }

                dismiss();
                break;
            case R.id.DialogDeleteBtnNo:
                dismiss();
        }
    }
}
