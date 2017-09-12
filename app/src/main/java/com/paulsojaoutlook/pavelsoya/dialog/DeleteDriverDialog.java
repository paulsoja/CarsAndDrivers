package com.paulsojaoutlook.pavelsoya.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.paulsojaoutlook.pavelsoya.R;
import com.paulsojaoutlook.pavelsoya.fragment.DriversFragment;

/**
 * Created by p-sha on 04.09.2017.
 */

public class DeleteDriverDialog extends DialogFragment implements View.OnClickListener {

    public static final String TAG_DELETE_DRIVER = "TAG_DELETE_DRIVER";

    public interface OnDriverDeleteListener {
        void onDriverDelete();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View root = inflater.inflate(R.layout.dialog_delete_driver, container, false);
        root.findViewById(R.id.DialogDeleteDriverBtnYes).setOnClickListener(this);
        root.findViewById(R.id.DialogDeleteDriverBtnNo).setOnClickListener(this);
        return root;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.DialogDeleteDriverBtnYes:
                Bundle bundle = getArguments();
                String listenerTag = bundle.getString(DriversFragment.TAG_DRIVERS_FRAGMENT);
                Fragment fragment = getFragmentManager().findFragmentByTag(listenerTag);
                if (fragment instanceof OnDriverDeleteListener) {
                    OnDriverDeleteListener listener = (OnDriverDeleteListener) fragment;
                    listener.onDriverDelete();
                }

                dismiss();
                break;
            case R.id.DialogDeleteDriverBtnNo:
                dismiss();
        }
    }
}
