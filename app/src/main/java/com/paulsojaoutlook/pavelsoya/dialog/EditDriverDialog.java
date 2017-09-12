package com.paulsojaoutlook.pavelsoya.dialog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.paulsojaoutlook.pavelsoya.R;

/**
 * Created by p-sha on 12.09.2017.
 */

public class EditDriverDialog extends DialogFragment implements View.OnClickListener {

    //теги для передачи значений в DriversFragment
    public static final String TAG_DRIVER_NAME = "TAG_DRIVER_NAME";
    public static final String TAG_DRIVER_AGE = "TAG_DRIVER_AGE";

    private EditText etAge;
    private EditText etName;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View root = inflater.inflate(R.layout.dialog_edit_driver, container, false);
        root.findViewById(R.id.DialogEditDriverBtnYes).setOnClickListener(this);
        root.findViewById(R.id.DialogEditDriverBtnNo).setOnClickListener(this);

        etName = root.findViewById(R.id.etDialogEditDriverName);
        etAge = root.findViewById(R.id.etDialogEditDriverAge);

        return root;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.DialogEditDriverBtnYes:
                if(TextUtils.isEmpty(etName.toString()) //проверка на пустые поля
                        || TextUtils.isEmpty(etAge.getText().toString())) {
                    //выводим сообщение
                    Toast.makeText(getContext(), R.string.Toast_etIsEmpty, Toast.LENGTH_SHORT).show();
                    return;
                }
                //получаем значения из edittext и передаем их в DriversFragment
                String name = etName.getText().toString();
                int age = Integer.parseInt(etAge.getText().toString());
                Bundle bundle = new Bundle();
                bundle.putString(TAG_DRIVER_NAME, name);
                bundle.putInt(TAG_DRIVER_AGE, age);
                Intent intent = new Intent().putExtras(bundle);
                getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, intent);

                dismiss();
                break;
            case R.id.DialogEditDriverBtnNo:
                dismiss();
        }
    }
}
