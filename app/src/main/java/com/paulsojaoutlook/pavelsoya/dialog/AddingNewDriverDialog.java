package com.paulsojaoutlook.pavelsoya.dialog;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.paulsojaoutlook.pavelsoya.R;
import com.paulsojaoutlook.pavelsoya.database.DBHandler;
import com.paulsojaoutlook.pavelsoya.database.DBHelper;
import com.paulsojaoutlook.pavelsoya.fragment.DriversFragment;
import com.paulsojaoutlook.pavelsoya.model.DriverItem;

/**
 * Created by p-sha on 04.09.2017.
 */

public class AddingNewDriverDialog extends DialogFragment implements View.OnClickListener {

    public static final String TAG_ADDING_NEW_DRIVER = "TAG_ADDING_NEW_DRIVER";

    public interface OnDriversChangedListener {
        void onDriversChanged();
    }

    private EditText etDriverName;
    private EditText etDriverAge;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View root = inflater.inflate(R.layout.dialog_add_driver, container, false);
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
                if(TextUtils.isEmpty(etDriverName.getText().toString()) //проверка на пустые поля
                        || TextUtils.isEmpty(etDriverAge.getText().toString())) {
                    //выводим сообщение
                    Toast.makeText(getContext(), R.string.Toast_etIsEmpty, Toast.LENGTH_SHORT).show();
                    return;
                }
                //получаем значения из edittext и добавляем их в базу
                DriverItem driverItem = new DriverItem();
                driverItem.setName(etDriverName.getText().toString());
                driverItem.setAge(Integer.parseInt(etDriverAge.getText().toString()));
                DBHelper helper = new DBHelper(getContext());
                DBHandler handler = new DBHandler(helper);
                handler.getDriverService().addDriver(driverItem);

                Bundle bundle = getArguments();
                String listenerTag = bundle.getString(DriversFragment.TAG_DRIVERS_FRAGMENT);
                Fragment fragment = getFragmentManager().findFragmentByTag(listenerTag);
                if (fragment instanceof OnDriversChangedListener) {
                    OnDriversChangedListener listener = (OnDriversChangedListener) fragment;
                    listener.onDriversChanged();
                }

                dismiss();
                break;
            case R.id.DialogDriverBtnNo:
                //close dialog without any changes
                dismiss();
        }
    }
}
