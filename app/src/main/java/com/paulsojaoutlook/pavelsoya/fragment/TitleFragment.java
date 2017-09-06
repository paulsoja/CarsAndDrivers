package com.paulsojaoutlook.pavelsoya.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.paulsojaoutlook.pavelsoya.R;


/**
 * Created by p-sha on 02.09.2017.
 */

public class TitleFragment extends Fragment implements View.OnClickListener {

    DriversFragment driversFragment;
    CarsFragment carsFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View root = inflater.inflate(R.layout.fragment_title, container, false);

        Button cars = root.findViewById(R.id.Btn_Cars);
        Button drivers = root.findViewById(R.id.Btn_Drivers);

        cars.setOnClickListener(this);
        drivers.setOnClickListener(this);

        return root;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.Btn_Cars:
                carsFragment = new CarsFragment();
                getFragmentManager().beginTransaction()
                        .replace(R.id.Frame_Content, carsFragment, CarsFragment.TAG_CARS_FRAGMENT)
                        .addToBackStack(CarsFragment.TAG_CARS_FRAGMENT)
                        .commit();

                break;
            case R.id.Btn_Drivers:
                driversFragment = new DriversFragment();
                getFragmentManager().beginTransaction()
                        .replace(R.id.Frame_Content, driversFragment, "driversFragment")
                        .addToBackStack(null)
                        .commit();
                break;
        }
    }
}
