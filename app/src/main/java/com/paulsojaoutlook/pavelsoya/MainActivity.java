package com.paulsojaoutlook.pavelsoya;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.paulsojaoutlook.pavelsoya.fragment.TitleFragment;

public class MainActivity extends AppCompatActivity{

    TitleFragment titleFragment;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();

        titleFragment = new TitleFragment();
        fragmentManager.beginTransaction().replace(R.id.Frame_Content, titleFragment)
                .addToBackStack(null)
                .commit();
    }
}
