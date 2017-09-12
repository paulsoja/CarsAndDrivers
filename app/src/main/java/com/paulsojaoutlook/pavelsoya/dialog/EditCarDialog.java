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
 * Created by p-sha on 08.09.2017.
 */

public class EditCarDialog extends DialogFragment implements View.OnClickListener {

    //теги для передачи значений в CarsFragment
    public static final String TAG_NAME = "TAG_NAME";
    public static final String TAG_YEAR = "TAG_YEAR";

    private EditText etYear;
    private EditText etName;

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
                if(TextUtils.isEmpty(etName.getText().toString()) //проверка на пустые поля
                        || TextUtils.isEmpty(etYear.getText().toString())) {
                    //выводим сообщение
                    Toast.makeText(getContext(), R.string.Toast_etIsEmpty, Toast.LENGTH_SHORT).show();
                    return;
                }
                //получаем значения из edittext и передаем их в CarsFragment
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
